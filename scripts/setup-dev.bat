set JFXSDK_URL=https://download2.gluonhq.com/openjfx/19/openjfx-19_windows-x64_bin-sdk.zip
set JFXSDK_ZIP=./jfxsdk.zip

set MYSQL_URL=https://cdn.mysql.com//Downloads/MySQL-8.0/mysql-8.0.31-winx64.zip
set MYSQL_ZIP=./mysql.zip

set MAILHOG_URL=https://github.com/mailhog/MailHog/releases/download/v1.0.1/MailHog_windows_amd64.exe
set MAILHOG_BINARY=./MailHog_windows_amd64.exe

set PATH_TO_MYSQL=%cd%/mysql-8.0.31-winx64/bin/mysqld.exe

curl -o %JFXSDK_ZIP% %JFXSDK_URL%
curl -o %MYSQL_ZIP% %MYSQL_URL%
curl -L -o %MAILHOG_BINARY% %MAILHOG_URL%

powershell Expand-Archive %JFXSDK_ZIP% -DestinationPath . -Force
powershell Expand-Archive %MYSQL_ZIP% -DestinationPath . -Force

%PATH_TO_MYSQL% --initialize