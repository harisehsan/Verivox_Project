Test Execution using command line:
Open command prompt or terminal, Go to the Directory where “pom.xml” file exits of project and execute the following command:

 mvn clean test -Dcucumber.options="--tags @address_check" allure:serve

Test Execution by executing the shell script:

In Windows, execute the file “Test_run (Windows).bat”

In Linux or Mac OS, use the following command to execute the script:
 
Pre-requisites for execution: (May be required)

There are following pre-requests may be required to run the automation project

1. The JDK or JRE must be installed in your PC. (JDK version 8 or higher)
2. Open command prompt or terminal.
3. Type “java -- version” and press enter.
4. Should be able to get java version if it is installed and configure correctly.

If you are getting the error “java command not found” then following the steps
as stated in the following video to install the JDK in your PC.

How to install JDK:

Use the following link to install JDK

https://www.youtube.com/watch?v=IJ-PJbvJBGs&
ab_channel=ProgrammingKnowledge

How to install Maven 

1. The Maven must be installed in your pc (3.6 or higher version is required)
2. In the command prompt or terminal type: mvn -version
3. Should be able to get maven version if it is installed and configured
Correctly.

If you are getting the error “mvn: command not found”

Follow the steps that are mentioned in the video to install and configure the maven:

https://www.youtube.com/watch?v=RfCWg5ay5B0&ab_channel=CodingMagic



