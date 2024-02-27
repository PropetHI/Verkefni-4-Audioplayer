package adrian.roszkowski.verkefni4audioplayer.vidmot;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public enum View {
    PLAYLIST("listi-view.fxml"),
    MAIN("heima-view.fxml");

    private String fileName;

    View(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
