## Git 

Install Git https://git-scm.com/install/

Clone the git repository 

### For windows 

In C:\ create a folder “workspace” 

In CMD line open C:\workspace and run the command:   
```
git clone https://github.com/SEMOSS/e2e.git 
```

## Java JDK 21 

Install java21 

Scroll down to Java 21 and click on Download next to Windows x86 64-bit 

Use the MSI installer 

## Environment Variables
In Windows, from your start menu/search bar, navigate to your Control Panel > System and Security > System > Advanced system settings

On the Systems Properties window, select Environment Variables

Under system variables (bottom section), select "New...", and create a new variable with name "JAVA_HOME" (without the quotation marks) and select the jdk folder for the directory (For example, C:\Program Files\Zulu\zulu-21). Select "OK" once you are done.

Next, update the path variable. Under system variables, find locate the "path" variable, select it, and click "edit"
In the window that appears, click New, and in the new row that appears, type "%JAVA_HOME%\bin" without the quotation marks.  

Click "OK", and click "OK" again to close the window. Open windows command prompt and type the command "echo %JAVA_HOME" and it should return the new variable you entered.

## Install Eclipse
Select link under "Download Links" at https://www.eclipse.org/downloads/packages/release/2025-06/r/eclipse-ide-enterprise-java-and-web-developers for windows x86_64

Unzip this folder to desktop or to your default location

## Eclipse Setup
Open eclipse, and specify "C:\workspace" as your default workspace.

Once eclipse is opened, click on **File >> Import** and find **Maven**. Click on the dropdown and select **Existing Maven Projects**. It will start searching for existing projects.

For **Root Directory**, browse for your workspace and click OK. This should be C:\workspace

For **Projects**, check "e2e"

At the bottom of the import window, select **Finish** to import your projects.

## E2E Configuration
Update env.defaults with credentials needed to run the test suite.

If you wish to have it point to your local machine, make sure to include "http://" in front of the address.

## Run the test suite!
In eclipse, open the file **FullSuite.java**

Right click the file, under "Run As..." select **JUnit Test**



