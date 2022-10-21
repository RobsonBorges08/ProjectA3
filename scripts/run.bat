set PATH_TO_FX=./javafx-sdk-19/lib
set APPLICATION_EXECUTABLE=./budgetmanager/application/target/application-0.1.0-jar-with-dependencies.jar
java -jar --module-path %PATH_TO_FX% --add-modules javafx.base,javafx.controls,javafx.graphics,javafx.fxml %APPLICATION_EXECUTABLE%