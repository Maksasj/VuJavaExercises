module com.example.poke_pedia {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.radioboos.poke_pedia to javafx.fxml;
    exports com.radioboos.poke_pedia;
    exports com.radioboos.poke_pedia.pokemon;
    exports com.radioboos.poke_pedia.common;
    exports com.radioboos.poke_pedia.filter;
    opens com.radioboos.poke_pedia.pokemon to javafx.fxml;
    exports com.radioboos.poke_pedia.controllers;
    opens com.radioboos.poke_pedia.controllers to javafx.fxml;
}