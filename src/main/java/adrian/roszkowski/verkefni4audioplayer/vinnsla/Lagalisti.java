package adrian.roszkowski.verkefni4audioplayer.vinnsla;

import adrian.roszkowski.verkefni4audioplayer.vidmot.PlayerController;
import javafx.beans.property.ListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lagalisti {

    private ObservableList<Lag> songs = FXCollections.observableArrayList();

    private int currentSongIndex = 0;

    public Lagalisti(String nameOfFile) {
        try {
            updateList(nameOfFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Updates the list of songs from a file.
     * @param nameOfFile The name of the file to read
     * @throws IOException
     */
    public void updateList(String nameOfFile) throws IOException {

        try{
            InputStream input = this.getClass().getResourceAsStream(nameOfFile);
            Scanner sc = new Scanner(input, StandardCharsets.UTF_8);

            while (sc.hasNextLine()){
                String line = sc.nextLine();
                String[] parts = line.split("; ");
                songs.add(new Lag(parts[0], parts[1], parts[2]));
            }
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    public Lag getLag(int index) {
        return songs.get(index);
    }

    public int getCurrentSongIndex() {
        return currentSongIndex;
    }

    public void setCurrentSongIndex(int currentSongIndex) {
        this.currentSongIndex = currentSongIndex;
    }

    public ObservableList<Lag> getSongs() {
        return songs;
    }

    public int getSize() {
        return songs.size();
    }

    public void incrementCurrentSongIndex() {
        if (currentSongIndex < songs.size() - 1) {
            currentSongIndex++;
        }
    }
}
