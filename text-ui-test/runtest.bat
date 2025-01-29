@echo off

REM Ensure the script runs from its own folder (text-ui-test)
pushd "%~dp0"

REM Step one folder up to the project root
cd ..

REM Make sure bin\ exists
if not exist bin mkdir bin

REM Remove old ACTUAL.TXT (if any)
if exist text-ui-test\ACTUAL.TXT del text-ui-test\ACTUAL.TXT

REM Compile all .java in src\taskmaster\java\taskmaster into bin\
javac -cp . -Xlint:none -d bin src\taskmaster\java\taskmaster\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)

REM Run the program with input.txt, write output to ACTUAL.TXT
java -cp bin taskmaster.taskmaster < text-ui-test\input.txt > text-ui-test\ACTUAL.TXT

REM Compare ACTUAL.TXT to EXPECTED.TXT
FC text-ui-test\ACTUAL.TXT text-ui-test\EXPECTED.TXT