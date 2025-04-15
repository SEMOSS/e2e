ARG TOMCAT_HOME=/opt/apache-tomcat-9.0.102

FROM quay.io/semoss/docker:ubi8-rhel
LABEL authors="jvitunac"

RUN mvn dependency:get -Dartifact=org.jacoco:org.jacoco.agent:0.8.12:jar:runtime
RUN mvn dependency:get -Dartifact=org.jacoco:org.jacoco.core:0.8.12
RUN mvn dependency:get -Dartifact=org.jacoco:org.jacoco.cli:0.8.12

RUN mkdir /opt/testresults
RUN mkdir /opt/testresults/coverage
RUN mkdir /opt/testjars
RUN mkdir /opt/repos

WORKDIR /opt/repos

#RUN apt-get update && apt-get install -y git
RUN dnf install git

RUN git clone https://github.com/SEMOSS/Semoss.git
RUN git clone https://github.com/SEMOSS/Monolith.git

WORKDIR /opt/testjars

RUN wget "https://repo1.maven.org/maven2/org/jacoco/org.jacoco.cli/0.8.12/org.jacoco.cli-0.8.12-nodeps.jar"
RUN mv "org.jacoco.cli-0.8.12-nodeps.jar" "jacococli.jar"
RUN wget "https://repo1.maven.org/maven2/org/jacoco/org.jacoco.agent/0.8.12/org.jacoco.agent-0.8.12-runtime.jar"
RUN mv "org.jacoco.agent-0.8.12-runtime.jar" "jacocoagent.jar"

RUN echo 'export CATALINA_OPTS="$CATALINA_OPTS -javaagent:/root/.m2/repository/org/jacoco/org.jacoco.agent/0.8.12/org.jacoco.agent-0.8.12-runtime.jar=destfile=/opt/testresults/jacoco.exec,output=file"' >> $TOMCAT_HOME/bin/setenv.sh

CMD ["$TOMCAT_HOME/bin/start.sh" ]