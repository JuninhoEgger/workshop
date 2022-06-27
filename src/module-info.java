module workshop {
	requires javafx.controls;
    requires javafx.fxml;
	requires java.sql;
	requires mysql.connector.java;

    opens application to javafx.graphics, javafx.fxml;
    opens gui to javafx.graphics, javafx.fxml;
    opens model.entities to javafx.graphics, javafx.fxml, javafx.base;
    opens model.services to javafx.graphics, javafx.fxml;
}