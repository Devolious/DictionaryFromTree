module com.example.dictionaryusingtree {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.example.dictionaryusingtree to javafx.fxml;
    exports com.example.dictionaryusingtree;
}