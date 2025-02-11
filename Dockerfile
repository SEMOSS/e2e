FROM mcr.microsoft.com/playwright/java:v1.49.0-jammy

WORKDIR /workspace

COPY pom.xml pom.xml

RUN mvn dependency:resolve

RUN mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"

COPY src src
COPY logs logs
COPY target target
COPY videos videos
COPY test.props test.props
COPY tester.sh tester.sh
COPY testNRM.sh testNRM.sh

CMD ["mvn", "clean", "-Dtest=e2e.**", "test", "-e"]