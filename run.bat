@echo off
title VigilCore - Malware Scanner
color 0B

echo ========================================
echo   VIGIL CORE - Malware Scanner
echo ========================================
echo.

:: Change to script directory first
cd /d "%~dp0"

:: Check if Java is installed
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo [ERROR] Java is not installed or not in PATH!
    echo Please install Java JDK 8 or higher.
    echo.
    pause
    exit /b 1
)

:: Check if compiled classes exist
if not exist "bin\com\vigilcore\gui\VigilCoreGUI.class" (
    echo [WARNING] Compiled classes not found!
    echo.
    echo [INFO] Auto-compiling project...
    call compile.bat
    if %errorlevel% neq 0 (
        echo.
        echo [ERROR] Compilation failed. Cannot run application.
        pause
        exit /b 1
    )
)

echo [INFO] Starting VigilCore GUI...
echo [INFO] The application window should open now...
echo.
echo ========================================
echo   Application Starting...
echo ========================================
echo.

:: Run the application directly (GUI will stay open)
java -cp bin com.vigilcore.gui.VigilCoreGUI

:: When GUI closes, show this message
echo.
echo [INFO] Application closed.
echo.
