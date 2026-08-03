@echo off
setlocal
set SCRIPT_DIR=%~dp0
if exist "%SCRIPT_DIR%backend\mvnw.cmd" (
  call "%SCRIPT_DIR%backend\mvnw.cmd" %*
  exit /b %ERRORLEVEL%
)
if exist "%SCRIPT_DIR%backend\mvnw" (
  bash "%SCRIPT_DIR%backend\mvnw" %*
  exit /b %ERRORLEVEL%
)

echo Maven wrapper not found in %SCRIPT_DIR%backend
exit /b 1
