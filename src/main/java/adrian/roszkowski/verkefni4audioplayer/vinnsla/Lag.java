package adrian.roszkowski.verkefni4audioplayer.vinnsla;

public class Lag {

    private String name;
    private String imageLocation;
    private String songLocation;

    public Lag(String imageLocation, String songLocation, String name) {
        this.imageLocation = imageLocation;
        this.songLocation = songLocation;
        this.name = name;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public String getSongLocation() {
        return songLocation;
    }

    @Override
    public String toString() {
        return name;
    }
}
