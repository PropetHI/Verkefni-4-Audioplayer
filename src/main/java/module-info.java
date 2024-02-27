module adrian.roszkowski.verkefni4audioplayer {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    exports adrian.roszkowski.verkefni4audioplayer.vinnsla;
    opens adrian.roszkowski.verkefni4audioplayer.vinnsla to javafx.fxml;

    exports adrian.roszkowski.verkefni4audioplayer.vidmot;
    opens adrian.roszkowski.verkefni4audioplayer.vidmot to javafx.fxml;
}