package adrian.roszkowski.verkefni4audioplayer.vidmot;

import adrian.roszkowski.verkefni4audioplayer.vinnsla.Askrifandi;
import adrian.roszkowski.verkefni4audioplayer.vinnsla.Lagalistar;
import adrian.roszkowski.verkefni4audioplayer.vinnsla.Lagalisti;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class PlayerController {

    private Askrifandi user;

    private Lagalistar lagalistar;

    private static Lagalisti lagalisti;

    @FXML
    private GridPane playlistListContained_ID;
    @FXML
    private Text userText_ID;

    /**
     * Instantiates two separate playlists and their container.
     */
    @FXML
    void initialize(){
        lagalistar = new Lagalistar(2);


        lagalistar.addLagalist(new Lagalisti("media/playlist_1.txt"), 0);
        lagalistar.addLagalist(new Lagalisti("media/playlist_2.txt"), 1);
    }


    /**
     * Creates the login dialog and waits for the result.
     * @param e unused
     */
    @FXML
    void onLogin(ActionEvent e){
        AskrifandiDialog dialog = new AskrifandiDialog();
        user = dialog.getAskrifandi();
        if (user.getNafn() != null){
            userText_ID.setText("Welcome " + user.getNafn() + "!");
        }
    }


    /**
     * Switches to the playlist view with the selected playlist.
     * @param event the playlist button.
     */
    @FXML
    void playlistSwitch(ActionEvent event){
        Button caller = (Button) event.getSource();
        int x = playlistListContained_ID.getColumnIndex(caller);

        lagalisti = lagalistar.getLagalist(x);
        ViewSwitcher.switchTo(View.PLAYLIST);
    }

    public static Lagalisti getLagalisti() {
        return lagalisti;
    }
}