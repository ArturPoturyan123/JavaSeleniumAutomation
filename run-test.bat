@echo off
echo Running LoginTest...
echo.

REM Set Java classpath
set CLASSPATH=target\classes;target\test-classes

REM Add all JAR files from Maven repository
for /r "%USERPROFILE%\.m2\repository" %%i in (*.jar) do set CLASSPATH=!CLASSPATH!;%%i

REM Run the test using TestNG
java org.testng.TestNG testng.xml

echo.
echo Test completed!
pause 