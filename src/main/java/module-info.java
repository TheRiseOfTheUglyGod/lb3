module ru.kafpin.lb3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens ru.kafpin.lb3 to javafx.fxml;
    exports ru.kafpin.lb3;
}