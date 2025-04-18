#!/bin/sh
docker pull quay.io/semoss/docker:ubi8-rhel \
; docker compose rm -f semoss \
; docker compose up semoss --build -d


# SETTING VARIABLES TO USE IN SCRIPTS BELOW
TC_PATH=$(docker compose exec semoss sh -c \
'find "/opt/" -maxdepth 1 -type d -name "apache-tomcat-*" | head -n 1')

echo $TC_PATH

mono="$TC_PATH/webapps/Monolith/WEB-INF/lib"
echo $mono

command="find ${mono} -maxdepth 1 -type f -name \"semoss-*-SNAPSHOT.jar\" | head -n 1"
echo $command

SEMOSS_JAR_PATH=$(docker compose exec semoss sh -c \
"$command")

echo $SEMOSS_JAR_PATH

SHUTDOWN_COMMAND="${TC_PATH}/bin/shutdown.sh"

# Currently
JACOCLI_COMMAND="java -jar jacococli.jar report /opt/testresults/jacoco.exec \
--classfiles ${TC_PATH}/webapps/Monolith/WEB-INF/classes \
--sourcefiles /opt/repos/Monolith/src \
--classfiles ${SEMOSS_JAR_PATH} \
--sourcefiles /opt/repos/Semoss/src \
--html /opt/testresults/coverage"

echo $JACOCLI_COMMAND

# I HAVE NO IDEA WHY I RUN THE SHUTDOWN COMMAND 4 TIMES. IT WORKS, MOST OF THE TIME.
# SO PLEASE DON'T TOUCH IT
# IT'S PROBABLY FINE...
# PROBABLY...
# TO BE HONEST, I REALLY WANTED CODE COVERAGE AND DIDN'T KNOW THE RIGHT WAY TO DO IT
# BUT THIS IS A WAY TO DO IT. THE RIGHT WAY? NO, IT CAN'T BE. BUT HERE WE ARE.
docker compose run test \
; docker compose exec semoss sh "$SHUTDOWN_COMMAND" \
; docker compose exec semoss sh "$SHUTDOWN_COMMAND" \
; docker compose exec semoss sh "$SHUTDOWN_COMMAND" \
; docker compose exec semoss sh "$SHUTDOWN_COMMAND" \
; docker compose exec semoss ls -lh /opt/testresults/jacoco.exec \
; docker compose exec semoss echo $JACOCLI_COMMAND \
; docker compose exec -T semoss $JACOCLI_COMMAND \
; docker compose stop semoss
