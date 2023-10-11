module com.example.poke_pedia {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.radioboos.poke_pedia to javafx.fxml;
    exports com.radioboos.poke_pedia;
}