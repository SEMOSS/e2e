FROM mcr.microsoft.com/playwright/java:v1.49.0-jammy

WORKDIR /workspace

COPY . .

RUN mvn dependency:resolve

RUN mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"

CMD ["mvn", "clean", "test", "-e"]