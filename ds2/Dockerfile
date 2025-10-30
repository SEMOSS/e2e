# Base image arguments
ARG BASE_REGISTRY=docker.io
ARG BASE_IMAGE=ubuntu
ARG BASE_TAG=24.04

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

RUN apt-get update && apt-get install -y curl gnupg2 lsb-release unzip

RUN curl -fsSL https://deb.nodesource.com/setup_24.x | bash - \
    && apt-get install -y nodejs

RUN npm cache clean --force && npm install -g pnpm

RUN node -v && npm -v && pnpm -v

WORKDIR /app

RUN git clone https://github.com/SEMOSS/Semoss.git
WORKDIR /app/Semoss
RUN mvn install -DskipTests=true -U
WORKDIR /app
RUN git clone https://github.com/SEMOSS/Monolith.git
WORKDIR /app/Monolith
RUN mvn install -DskipTests=true -U
WORKDIR /app


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
COPY BusinessInsider/bi-build.zip /tmp/bi-build.zip
RUN unzip /tmp/bi-build.zip -d /opt/apache-tomcat-9.0.102/webapps/SemossWeb/packages/legacy/
WORKDIR /opt/apache-tomcat-9.0.102/webapps/SemossWeb
RUN pnpm install
RUN pnpm run build:dev

WORKDIR /app

COPY scripts.sh /app/
RUN sed -i 's/\r$//' scripts.sh
RUN chmod 777 scripts.sh

COPY coverageShutdown.sh /app/
RUN sed -i 's/\r$//' coverageShutdown.sh
RUN chmod 777 coverageShutdown.sh

RUN apt-get update && apt-get install -y --no-install-recommends \
    curl \
    ca-certificates \
    build-essential \
    libssl-dev \
    zlib1g-dev \
    libbz2-dev \
    libreadline-dev \
    libsqlite3-dev \
    libffi-dev \
    git \
    clang \
    # python3-pip \
    && rm -rf /var/lib/apt/lists/*

# installing rust as some python packages now require it for compiling
RUN curl --proto '=https' --tlsv1.2 -sSf https://sh.rustup.rs | bash -s -- -y
ENV PATH="/root/.cargo/bin:${PATH}"

# Set environment variables for uv
ENV UV_LINK_MODE=copy \
UV_COMPILE_BYTECODE=1 \
UV_NO_CACHE=1

# we will install python inside /usr/lib/python/semossvenv
# it is done here to carry forward and limit the duplicated chowns in opt
ENV VIRTUAL_ENV="/usr/lib/python/semossvenv"
ENV UV_PYTHON_INSTALL_DIR="/usr/lib/python"
ENV UV_INSTALL_DIR="/usr/lib/uv/"
ENV PATH=$UV_INSTALL_DIR:$PATH
ENV PATH=$VIRTUAL_ENV/bin:$PATH

# Install uv
RUN curl -LsSf https://astral.sh/uv/install.sh | sh

ARG PYTHON_VERSION=3.12

ARG PYTHON_VERSION
ENV PYTHON_VERSION=${PYTHON_VERSION}

ARG COMPUTE_TYPE
ENV COMPUTE_TYPE=${COMPUTE_TYPE}

RUN uv python install ${PYTHON_VERSION} --default --preview
RUN uv venv --seed $VIRTUAL_ENV


RUN cd /tmp && \
curl -o pyproject.toml https://raw.githubusercontent.com/SEMOSS/Semoss/refs/heads/dev/py/install_config/pyproject.toml && \
uv pip install -r pyproject.toml --extra cpu

ENV VIRTUAL_ENV="/usr/lib/python/semossvenv"
ENV PATH=$VIRTUAL_ENV/bin:$PATH

# Install additional dependencies
RUN apt-get update \
    && apt-get install -y tesseract-ocr \
    && apt-get -y autoremove \
    && rm -rf /var/lib/apt/lists/*


WORKDIR /app

# Jacoco stuff
RUN mkdir /opt/testresults
RUN mkdir /opt/testresults/coverage
RUN mkdir /opt/testjars
RUN mkdir /opt/repos

WORKDIR /opt/testjars

RUN mvn dependency:get -Dartifact=org.jacoco:org.jacoco.agent:0.8.12:jar:runtime
RUN mvn dependency:get -Dartifact=org.jacoco:org.jacoco.core:0.8.12
RUN mvn dependency:get -Dartifact=org.jacoco:org.jacoco.cli:0.8.12

RUN wget "https://repo1.maven.org/maven2/org/jacoco/org.jacoco.cli/0.8.12/org.jacoco.cli-0.8.12-nodeps.jar"
RUN mv "org.jacoco.cli-0.8.12-nodeps.jar" "jacococli.jar"
RUN wget "https://repo1.maven.org/maven2/org/jacoco/org.jacoco.agent/0.8.12/org.jacoco.agent-0.8.12-runtime.jar"
RUN mv "org.jacoco.agent-0.8.12-runtime.jar" "jacocoagent.jar"

RUN echo 'export CATALINA_OPTS="$CATALINA_OPTS -javaagent:/root/.m2/repository/org/jacoco/org.jacoco.agent/0.8.12/org.jacoco.agent-0.8.12-runtime.jar=destfile=/opt/testresults/jacoco.exec,output=file,append=false,dumponexit=true,jmx=true"' >> $TOMCAT_HOME/bin/setenv.sh

WORKDIR /app
CMD ["/app/scripts.sh"]