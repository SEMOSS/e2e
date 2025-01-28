-- cfg

mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen https://workshop.cfg.deloitte.com/cfg-ai-demo/SemossWeb/packages/client/dist/#/login"

-- local

mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen http://localhost:9090/semoss-ui/packages/client/dist/#/"


