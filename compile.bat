@echo off
title VigilCore - Compiler
color 0A

echo ========================================
echo   VIGIL CORE - Compilation Script
echo ========================================
echo.

:: Check if Java is installed
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo [ERROR] Java is not installed or not in PATH!
    echo Please install Java JDK 8 or higher.
    echo.
    pause
    exit /b 1
)

echo [INFO] Java found. Starting compilation...
echo.

:: Create bin directory if it doesn't exist
if not exist bin (
    echo [INFO] Creating bin directory...
    mkdir bin
)

echo [INFO] Compiling source files...
echo.

:: Compile all Java files
javac -d bin -encoding UTF-8 -sourcepath src src/com/vigilcore/models/*.java src/com/vigilcore/dsa/*.java src/com/vigilcore/core/*.java src/com/vigilcore/gui/*.java

:: Check compilation result
if %errorlevel% == 0 (
    echo.
    echo ========================================
    echo   Compilation SUCCESSFUL!
    echo ========================================
    echo.
    echo [INFO] All files compiled successfully.
    echo [INFO] Output directory: bin\
    echo.
    echo To run the application, execute: run.bat
    echo.
) else (
    echo.
    echo ========================================
    echo   Compilation FAILED!
    echo ========================================
    echo.
    echo [ERROR] Please check the error messages above.
    echo [INFO] Make sure all source files are present.
    echo.
    pause
    exit /b 1
)

pause
