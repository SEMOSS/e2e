# Base image arguments
ARG BASE_REGISTRY=docker.io
ARG BASE_IMAGE=ubuntu
ARG BASE_TAG=22.04

ARG AZUL_ZULU_VERSION=21.42.19
ARG JAVA_HOME=/usr/lib/jvm/zulu21
ARG JDK_VERSION=21.0.7

ARG MAVEN_HOME=/opt/apache-maven-3.8.5

ARG TOMCAT_VERSION=9.0.102
ARG TOMCAT_HOME=/opt/apache-tomcat-${TOMCAT_VERSION}

FROM ${BASE_REGISTRY}/${BASE_IMAGE}:${BASE_TAG}

ARG AZUL_ZULU_VERSION=21.42.19
ARG JAVA_HOME=/usr/lib/jvm/zulu21
ARG JDK_VERSION=21.0.7

ARG MAVEN_HOME=/opt/apache-maven-3.8.5

ARG TOMCAT_VERSION=9.0.102
ARG TOMCAT_HOME=/opt/apache-tomcat-${TOMCAT_VERSION}
ENV TOMCAT_HOME=${TOMCAT_HOME}
ENV JAVA_HOME=${JAVA_HOME}
ENV PATH=$PATH:$MAVEN_HOME/bin:$TOMCAT_HOME/bin:$JAVA_HOME/bin
# Needed for JEP

RUN printenv | grep -E '^(JAVA_HOME|TOMCAT_HOME|MAVEN_HOME|PATH)=' | awk '{print "export " $0}' >> /opt/set_env.env

#COPY . /root/
# Pulling files from the SEMOSS/semoss-artifacts project
RUN apt-get update -y \
	&& apt-get install -y lsof git \
	&& cd tmp/ && git clone https://github.com/SEMOSS/semoss-artifacts \
	&& cd semoss-artifacts \
	&& pwd \
	&& ls -als artifacts/ \
	&& chmod 777 artifacts/dockerBuilder_scripts/*

RUN apt-get -y install apt-transport-https ca-certificates git wget dirmngr gnupg software-properties-common \
	&& cd ~/ \
	&& apt-get -y install wget procps git \
	&& echo "Creating directory for ${JAVA_HOME}.." \
	&& mkdir -p $JAVA_HOME \
	&& cd /tmp/semoss-artifacts/artifacts/dockerBuilder_scripts/ \
	&& chmod +x install_java.sh \
	&& /bin/bash install_java.sh \
	&& java -version \
	&& wget https://archive.apache.org/dist/tomcat/tomcat-9/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz \
	&& tar -zxvf apache-tomcat-9.0.*.tar.gz \
	&& mkdir $TOMCAT_HOME \
	&& mv apache-tomcat-9.0.*/* $TOMCAT_HOME/ \
	&& rm -r apache-tomcat-9.0.*/ \
  	%% rm -rf $TOMCAT_HOME/webapps/* \
	&& rm apache-tomcat-9.0.*.tar.gz \
	&& rm $TOMCAT_HOME/conf/server.xml \
	&& rm $TOMCAT_HOME/conf/web.xml \
	&& cp web.xml $TOMCAT_HOME/conf/web.xml \
	&& cp server.xml $TOMCAT_HOME/conf/server.xml \
	&& chmod +x config.sh \
	&& /bin/bash config.sh \
	&& echo 'CATALINA_PID="$CATALINA_BASE/bin/catalina.pid"' > $TOMCAT_HOME/bin/setenv.sh \
	&& wget https://archive.apache.org/dist/maven/maven-3/3.8.5/binaries/apache-maven-3.8.5-bin.tar.gz\
	&& tar -zxvf apache-maven-*.tar.gz \
	&& mkdir /opt/apache-maven-3.8.5 \
	&& mv apache-maven-3.8.5/* /opt/apache-maven-3.8.5/ \
	&& rm -r apache-maven-3.8.5 \
	&& rm apache-maven-3.8.5-bin.tar.gz \
	&& apt-get -y install nano \
	&& echo '#!/bin/sh' > $TOMCAT_HOME/bin/start.sh \
	&& echo 'catalina.sh start' >> $TOMCAT_HOME/bin/start.sh \
	&& echo "tail -f $TOMCAT_HOME/logs/catalina.out" >> $TOMCAT_HOME/bin/start.sh \
	&& echo '#!/bin/sh' > $TOMCAT_HOME/bin/stop.sh \
	&& echo 'shutdown.sh -force' >> $TOMCAT_HOME/bin/stop.sh \
	&& chmod 777 $TOMCAT_HOME/bin/*.sh \
	&& chmod 777 /opt/apache-maven-3.8.5/bin/*.cmd \
	&& apt-get clean all

RUN apt-get update && apt-get install -y curl gnupg2 lsb-release

RUN curl -fsSL https://deb.nodesource.com/setup_24.x | bash - \
    && apt-get install -y nodejs

RUN npm cache clean --force && npm install -g pnpm

RUN node -v && npm -v && pnpm -v

WORKDIR /app

RUN git clone https://github.com/SEMOSS/Semoss.git
RUN git clone https://github.com/SEMOSS/Monolith.git


RUN mkdir /app/buildfiles/
RUN mkdir /app/buildfiles/db/
RUN mkdir /app/buildfiles/db/security/

WORKDIR /app/buildfiles

# git clone semoss again so I can replace directories on run for Semoss Resources
RUN git clone https://github.com/SEMOSS/Semoss.git

COPY sem/db/security/database.mv.db db/security/database.mv.db
COPY sem/RDF_Map.prop  .
COPY mon/web.xml  .

WORKDIR /opt/apache-tomcat-9.0.102/webapps/

RUN git clone https://github.com/SEMOSS/semoss-ui.git
RUN mv semoss-ui SemossWeb

WORKDIR /app

COPY scripts.sh /app/
RUN chmod 777 scripts.sh
CMD ["/app/scripts.sh"]