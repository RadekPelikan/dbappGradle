module com.spsmb.dbappgradle {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.spsmb.dbappgradle to javafx.fxml;
    exports com.spsmb.dbappgradle;
}