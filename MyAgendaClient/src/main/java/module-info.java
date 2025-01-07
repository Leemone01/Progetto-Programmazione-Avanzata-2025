module it.unipi.myagendaclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires com.google.gson;

    opens it.unipi.myagendaclient to javafx.fxml;
    exports it.unipi.myagendaclient;
}
