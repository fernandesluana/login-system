module com.example.loginsystem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.project.loginsystem to javafx.fxml;
    exports com.project.loginsystem;
}