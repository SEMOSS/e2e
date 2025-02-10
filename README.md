# AI Core Playwright Setup

## AI Core
Make sure you have AI Core locally installed!

## Run Codegen
Use codegen to help write tests.
```
mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen http://localhost:9090/semoss-ui/packages/client/dist/#/"
```

### Docker setup

+ Follow docker instructions found in [here](docker/README.md)