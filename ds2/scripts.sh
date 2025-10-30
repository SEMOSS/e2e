#!/bin/bash

echo "$SEMOSS_BRANCH"
echo "$MONOLITH_BRANCH"
echo "$UI_BRANCH"

# update semoss, build, and install
cd Semoss
git fetch
if [[ -n "$semoss" ]]; then
  git switch "$semoss"
fi;
git pull
mvn install -DskipTests=true -U

# Update unused semoss directory in order to make sure
# Semoss resources are up to date
cd ../buildfiles/Semoss
git fetch
if [[ -n "$semoss" ]]; then
  git switch "$semoss"
fi;
git pull

# remove dirty semoss and replace it with clean semoss resources
dirs=("db" "function" "InsightCache" "model" "project" "storage" "vector")
for d in "${dirs[@]}"; do
  rm -rf "/app/Semoss/$d"
  cp -a "/app/buildfiles/Semoss/$d" "/app/Semoss/$d"
done

# Copy config files and loaded databases for semoss
cp /app/buildfiles/RDF_Map.prop /app/Semoss/RDF_Map.prop
cp /app/buildfiles/db/security/database.mv.db /app/Semoss/db/security/database.mv.db

# Build Monolith and place it in webapps
cd ../../Monolith
git fetch
if [[ -n "$monolith" ]]; then
  git switch "$monolith"
fi;
git pull
cp /app/buildfiles/web.xml /app/Monolith/WebContent/WEB-INF/web.xml
mvn install -DskipTests=true -U
mv /app/Monolith/target/monolith-0.0.1-SNAPSHOT/ /opt/apache-tomcat-9.0.102/webapps/Monolith/

# build frontend
cd /opt/apache-tomcat-9.0.102/webapps/SemossWeb
git fetch
if [[ -n "$ui" ]]; then
  git switch "$ui"
fi;
git pull
pnpm install
pnpm run build:dev

# start server
bash /opt/apache-tomcat-9.0.102/bin/start.sh &
echo "Tomcat started"

cd /workspace
mvn -Dtest=aicore.suite.FullSuite test -e
TEST_RESULT=$?
echo "Test result code was: $TEST_RESULT"
cd /app

TC_PATH=$(find "/opt/" -maxdepth 1 -type d -name "apache-tomcat-*" | head -n 1)
echo $TC_PATH

mono="$TC_PATH/webapps/Monolith/WEB-INF/lib"
echo "MONOLITH PATH:$mono"

SEMOSS_JAR_PATH=$(find ${mono} -maxdepth 1 -type f -name \"semoss-*-SNAPSHOT.jar\" | head -n 1)

echo "SEMOSS JAR PATH=$SEMOSS_JAR_PATH"

SHUTDOWN_COMMAND="${TC_PATH}/bin/shutdown.sh"

echo "SHUTDOWN COMMAND is: $SHUTDOWN_COMMAND"

# File based
JACOCLI_COMMAND="java -jar /opt/testjars/jacococli.jar report /opt/testresults/jacoco.exec \
--classfiles /app/Monolith/target/classes \
--sourcefiles /app/Monolith/src \
--classfiles /app/Semoss/target/classes \
--sourcefiles /app/Semoss/src \
--html /opt/testresults/coverage"

eval "$SHUTDOWN_COMMAND"
eval "$SHUTDOWN_COMMAND"
eval "$SHUTDOWN_COMMAND"
eval "$SHUTDOWN_COMMAND"
eval "$SHUTDOWN_COMMAND"
eval "$SHUTDOWN_COMMAND"

echo "Finished shutting down"

echo "running coverage command"

echo "JACOCO Command is: $JACOCLI_COMMAND"
eval "$JACOCLI_COMMAND"

#!/bin/bash

echo "$SEMOSS_BRANCH"
echo "$MONOLITH_BRANCH"
echo "$UI_BRANCH"

# update semoss, build, and install
cd Semoss
git fetch
if [[ -n "$semoss" ]]; then
  git switch "$semoss"
fi;
git pull
mvn install -DskipTests=true -U

# Update unused semoss directory in order to make sure
# Semoss resources are up to date
cd ../buildfiles/Semoss
git fetch
if [[ -n "$semoss" ]]; then
  git switch "$semoss"
fi;
git pull

# remove dirty semoss and replace it with clean semoss resources
dirs=("db" "function" "InsightCache" "model" "project" "storage" "vector")
for d in "${dirs[@]}"; do
  rm -rf "/app/Semoss/$d"
  cp -a "/app/buildfiles/Semoss/$d" "/app/Semoss/$d"
done

# Copy config files and loaded databases for semoss
cp /app/buildfiles/RDF_Map.prop /app/Semoss/RDF_Map.prop
cp /app/buildfiles/db/security/database.mv.db /app/Semoss/db/security/database.mv.db

# Build Monolith and place it in webapps
cd ../../Monolith
git fetch
if [[ -n "$monolith" ]]; then
  git switch "$monolith"
fi;
git pull
cp /app/buildfiles/web.xml /app/Monolith/WebContent/WEB-INF/web.xml
mvn install -DskipTests=true -U
mv /app/Monolith/target/monolith-0.0.1-SNAPSHOT/ /opt/apache-tomcat-9.0.102/webapps/Monolith/

# build frontend
cd /opt/apache-tomcat-9.0.102/webapps/SemossWeb
git fetch
if [[ -n "$ui" ]]; then
  git switch "$ui"
fi;
git pull
pnpm install
pnpm run build:dev

# start server
bash /opt/apache-tomcat-9.0.102/bin/start.sh &
echo "Tomcat started"

cd /workspace
mvn -Dtest=aicore.suite.FullSuite test -e
TEST_RESULT=$?
echo "Test result code was: $TEST_RESULT"
cd /app

TC_PATH=$(find "/opt/" -maxdepth 1 -type d -name "apache-tomcat-*" | head -n 1)
echo $TC_PATH

mono="$TC_PATH/webapps/Monolith/WEB-INF/lib"
echo "MONOLITH PATH:$mono"

SEMOSS_JAR_PATH=$(find ${mono} -maxdepth 1 -type f -name \"semoss-*-SNAPSHOT.jar\" | head -n 1)

echo "SEMOSS JAR PATH=$SEMOSS_JAR_PATH"

SHUTDOWN_COMMAND="${TC_PATH}/bin/shutdown.sh"

echo "SHUTDOWN COMMAND is: $SHUTDOWN_COMMAND"

# File based
JACOCLI_COMMAND="java -jar /opt/testjars/jacococli.jar report /opt/testresults/jacoco.exec \
--classfiles /app/Monolith/target/classes \
--sourcefiles /app/Monolith/src \
--classfiles /app/Semoss/target/classes \
--sourcefiles /app/Semoss/src \
--html /opt/testresults/coverage"

eval "$SHUTDOWN_COMMAND"
eval "$SHUTDOWN_COMMAND"
eval "$SHUTDOWN_COMMAND"
eval "$SHUTDOWN_COMMAND"
eval "$SHUTDOWN_COMMAND"
eval "$SHUTDOWN_COMMAND"

echo "Finished shutting down"

echo "running coverage command"

echo "JACOCO Command is: $JACOCLI_COMMAND"
eval "$JACOCLI_COMMAND"

echo "Moving test results to workspace"
if [ -d "/workspace/testresults" ]; then
  rm -rf /workspace/testresults
fi

mv /opt/testresults /workspace/testresults

exit $TEST_RESULT

exit $TEST_RESULT