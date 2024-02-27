package adrian.roszkowski.verkefni4audioplayer.vidmot;

import adrian.roszkowski.verkefni4audioplayer.vinnsla.Lag;
import adrian.roszkowski.verkefni4audioplayer.vinnsla.Lagalisti;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class ListiController {

    @FXML
    private ListView<Lag> listView_ID;
    @FXML
    private ImageView songImageContained_ID;
    @FXML
    private Button PlayStopButton_ID;
    @FXML
    private ProgressBar progressBar_ID;

    private Lagalisti songsList;

    private Lag currentSong;

    private int virkurIndex = 0;

    private MediaPlayer player;

    /**
     * Frumstillir lagalistann og tengir hann við ListView viðmótshlut
     */
    @FXML
    void initialize(){
        songsList = PlayerController.getLagalisti();
        listView_ID.setItems(songsList.getSongs());

        listView_ID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println (newValue);
            // Indexinn í listanum.
            virkurIndex = listView_ID.getSelectionModel().getSelectedIndex();
            // Til að fá virkni sem skoðar námskeiðið þegar valið breytist þá má gera það svona
            if (!veljaLag(virkurIndex)) return;
            spilaLag();
        });
    }
    /**
     * Exists merely because the lack of this results in a crash.
     *
     * @param mouseEvent
     */
    @FXML
    void onValidLag(MouseEvent mouseEvent){




    }
    /**
     * Lætur laga lista vita hvert valda lagið er. Uppfærir myndina fyrir lagið.
     */
    boolean veljaLag(int index){
        if(index <= -1) return false;
        currentSong = songsList.getLag(index);
        songsList.setCurrentSongIndex(index);
        setjaMynd();
        return true;
    }
    /**
     * Lagið er pásað ef það er í spilun, lagið er spilað ef það er í pásu
     *
     * @param actionEvent ónotað
     */
    @FXML
    void onPlayPause(ActionEvent actionEvent){
        if (player == null) return;
        switch (player.getStatus()) {
            case PAUSED:
                PlayStopButton_ID.setText("||");
                player.play();
                break;
            case PLAYING:
                PlayStopButton_ID.setText(">");
                player.pause();
                break;
            default:
                break;
        }
    }
    /**
     * Fara aftur í heima view.
     *
     * @param actionEvent ónotað
     */
    @FXML
    void onHeim(ActionEvent actionEvent){
        if (player != null) player.stop();
        ViewSwitcher.switchTo(View.MAIN);
    }
    /**
     * Spila nýtt lagið
     */
    void spilaLag(){
        try
        {
            if (player != null) player.stop();
            player = new MediaPlayer(new Media(getClass().getResource(currentSong.getSongLocation()).toExternalForm()));
            player.setOnEndOfMedia(() -> naestaLag());
            player.play();
            bindProgressBar(player, progressBar_ID);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    /**
     * Setja mynd með nafni á ImageView
     */
    void setjaMynd(){
        songImageContained_ID.setImage(new Image(getClass().getResource(currentSong.getImageLocation()).toExternalForm()));
    }

    /**
     * Næsta lag er spilað. Kallað á þessa aðferð þegar fyrra lag á listanum endar
     */
    void naestaLag(){
        if (virkurIndex < songsList.getSize() - 1) {
            virkurIndex++;
            listView_ID.getSelectionModel().select(virkurIndex);
            spilaLag();
        }
        else{
            virkurIndex = 0;
            listView_ID.getSelectionModel().select(virkurIndex);
            spilaLag();
        }
    }


    /**
     * Binds the progress bar to the percentage of the media that has been played.
     * If 45 seconds of 60 seconds of audio have been played, the progress bar will be 0.75.
     * @param player The player that plays the media
     * @param bar The progress bar
     *
     * Slightly modified. Implementation found in
     *
     *           https://stackoverflow.com/questions/71682501/javafx-media-player-binding-progress-bar-with-media-player-mac-m1-silicon
     *           by Slaw
     */
    private void bindProgressBar(MediaPlayer player, ProgressBar bar) {
        var Binding =
                Bindings.createDoubleBinding(() -> {
                    Duration currentTime = player.getCurrentTime();
                    Duration duration = player.getMedia().getDuration();
                    if (isValidDuration(currentTime) && isValidDuration(duration)) {
                        return currentTime.toMillis() / duration.toMillis();
                    }
                    return ProgressBar.INDETERMINATE_PROGRESS;
                },
                player.currentTimeProperty(),
                player.getMedia().durationProperty());
        bar.progressProperty().bind(Binding);
    }


    /**
     * Checks if the given duration is valid ie. it isn't indefinite or unknown.
     * @param d The duration to check
     * @return True if the duration is valid, false otherwise
     *
     * Slightly modified. Implementation found in
     *
     *               https://stackoverflow.com/questions/71682501/javafx-media-player-binding-progress-bar-with-media-player-mac-m1-silicon
     *               by Slaw
     */
    private boolean isValidDuration(Duration d) {
        return d != null && !d.isIndefinite() && !d.isUnknown();
    }

}
