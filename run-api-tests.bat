@echo off
echo Running API Tests...
echo.

REM Set Java classpath
set CLASSPATH=target\classes;target\test-classes

REM Add all JAR files from Maven repository
for /r "%USERPROFILE%\.m2\repository" %%i in (*.jar) do set CLASSPATH=!CLASSPATH!;%%i

REM Run only API tests
java org.testng.TestNG -testclass tests.ApiLoginTest,tests.ApiRegisterTest

echo.
echo API Tests completed!
pause 