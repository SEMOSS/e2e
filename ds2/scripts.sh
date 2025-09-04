#!/bin/bash
# update semoss, build, and install
cd Semoss
git fetch
git pull
mvn clean install -DskipTests=true -U

# Update unused semoss directory in order to make sure
# Semoss resources are up to date
cd ../buildfiles/Semoss
git fetch
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
git pull
cp /app/buildfiles/web.xml /app/Monolith/WebContent/WEB-INF/web.xml
mvn clean install -DskipTests=true -U
mv /app/Monolith/target/monolith-0.0.1-SNAPSHOT/ /opt/apache-tomcat-9.0.102/webapps/Monolith/

# build frontend
cd /opt/apache-tomcat-9.0.102/webapps/SemossWeb
git fetch
git pull
pnpm install
pnpm run build:dev

# start server
/opt/apache-tomcat-9.0.102/bin/start.sh