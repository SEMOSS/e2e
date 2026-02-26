# Eclipse Setup
## Git Setup
Install [git](https://git-scm.com/install/) 

### Create a workspace folder
For windows 

In C:\ create a folder “workspace” 
### Clone the git repository 
In CMD line open C:\workspace and run the command:   
```
git clone https://github.com/SEMOSS/e2e.git 
```

## Install Java 21
Install [Java 21](https://www.azul.com/downloads/?version=java-21-lts&architecture=x86-64-bit&package=jdk-fx#zulu)  

Scroll down to Java 21 and click on Download next to Windows x86 64-bit 

Use the MSI installer 

## Add JAVA_HOME to env variables
In Windows, from your start menu/search bar, navigate to your Control Panel > System and Security > System > Advanced system settings. 

On the Systems Properties window that appears, select `Environment Variables` 


Under `System variables` (bottom section), select New... 

For variable name, type `JAVA_HOME` 

For variable value, choose Browse Directory, go to Program Files, go to Java, and select the jdk folder (wherever you downloaded/moved it to) 

For example, `C:\Program Files\Zulu\zulu-21` 

>Note: Environment variables are case-sensitive 

Click OK 

## Add JAVA_HOME to PATH

Next, Under system variables (bottom section), locate the Path variable, select it, and click Edit 

In the window that appears, click New 

In the new row that appears, paste `%JAVA_HOME%\bin`

Click OK 

## Eclipse Install
Install [Eclipse IDE](https://www.eclipse.org/downloads/packages/release/2025-06/r/eclipse-ide-enterprise-java-and-web-developers)

Select the link under ‘Download Links’ ->  

Windows 64-bit 

Unzip this to Desktop or Default location 

### Setup Eclipse Workspace Folder 

Once your Eclipse & JDK are installed, open Eclipse and specify where you want your workspace to be 

Specify `C:\workspace` instead of the default name that shows up 

We recommend that you pin eclipse to your Taskbar and pin your workspace to your Quick Access Bar 

### Import e2e into Eclipse 
Open Eclipse and click File >> Import, then find Maven, click on the dropdown and select `Existing Maven Projects` 

It will start searching existing project and might take some time 

For Root Directory: Browse for your workspace and click OK. This should reflect where you saved your workspace folder i.e. `C:\workspace` 

For Projects: 

Check `e2e` 

At the bottom of the import window, click Finish to import your projects 

## E2E Configuration 
Update `.env` file. This file points to the urls where the test scripts will be executed

### Create local.properties file 

In C:\workspace\e2e\src\main\resources 

Make a copy config.properties and save as local.properties 

Update the configuration options for your local instance of SEMOSS.

This file contains the login information for the test accounts that will be created to execute the test scripts.
