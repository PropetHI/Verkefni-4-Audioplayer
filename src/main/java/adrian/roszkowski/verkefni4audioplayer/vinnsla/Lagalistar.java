package adrian.roszkowski.verkefni4audioplayer.vinnsla;

public class Lagalistar {
    private Lagalisti[] lagalistar;



    public Lagalistar(int x) {
        lagalistar = new Lagalisti[x];
    }
    public Lagalisti getLagalist(int x) {
        return lagalistar[x];
    }

    public void setLagalistar(Lagalisti[] lagalistar) {
        this.lagalistar = lagalistar;
    }

    /**
     * Adds a playlist to the array as long as the index is valid
     * @param lagalisti The playlist to add
     * @param x The index at which to add it
     */
    public void addLagalist(Lagalisti lagalisti, int x)
    {
        if (x > lagalistar.length || x < 0) return;
        lagalistar[x] = lagalisti;
    }
}
