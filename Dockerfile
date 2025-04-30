FROM mcr.microsoft.com/playwright/java:v1.51.0-noble

WORKDIR /workspace

COPY pom.xml pom.xml

RUN mvn dependency:resolve

RUN mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"
RUN mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install-deps"
RUN mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install-deps chromium"

COPY src src
#COPY logs logs
#COPY target target
#COPY traces traces
#COPY videos videos
COPY tester.sh tester.sh
COPY testNRM.sh testNRM.sh
COPY state.json state.json

CMD ["mvn", "clean", "-Dtest=aicore.**", "test", "-e"]
