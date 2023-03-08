module com.example.tareaec1_ernestoz {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tareaec1_ernestoz to javafx.fxml;
    exports com.example.tareaec1_ernestoz;
}