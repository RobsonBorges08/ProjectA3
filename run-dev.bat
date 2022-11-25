set PATH_TO_FX=./javafx-sdk-19/lib
set PATH_TO_MYSQL=%cd%/mysql-8.0.31-winx64/bin/mysqld.exe
set PATH_TO_MAILHOG=%cd%/MailHog_windows_amd64.exe
set INIT_FILE=%cd%/mysql-init.txt
set APPLICATION_EXECUTABLE=./budgetmanager/application/target/application-0.1.0-jar-with-dependencies.jar

start /B cmd /C call %PATH_TO_MYSQL% --init-file=%INIT_FILE% &
start /B cmd /C call %PATH_TO_MAILHOG% &
start http://localhost:8025/
java -jar --module-path %PATH_TO_FX% --add-modules javafx.base,javafx.controls,javafx.graphics,javafx.fxml %APPLICATION_EXECUTABLE%