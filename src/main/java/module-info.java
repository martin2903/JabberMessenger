module com.jabbermessenger {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.jabbermessenger to javafx.fxml;
    opens com.jabbermessenger.controllers to javafx.fxml;
    exports com.jabbermessenger to javafx.fxml, javafx.graphics;
    exports com.jabbermessenger.controllers to javafx.fxml, javafx.graphics;


}