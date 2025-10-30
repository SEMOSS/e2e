#!/usr/bin/env bash

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

# echo "JACOCO Command is: $JACOCLI_COMMAND"