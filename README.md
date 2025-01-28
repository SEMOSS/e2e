# AI Core Playwright Setup

## AI Core
Make sure you have AI Core locally installed!

## Run Codegen
Use codegen to help write tests.
```
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen https://localhost:9090/semoss-ui/packages/client/dist/#/"
```