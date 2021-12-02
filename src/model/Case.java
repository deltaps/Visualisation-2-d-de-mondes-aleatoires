package model;

public class Case {
    // Représentation d'une case
    // Catégorie de case
    private int x;
    private int y;
    private int elevation; // 0 à 255

    public Case(int x, int y, int elevation) {
        this.x = x;
        this.y = y;
        if(elevation > 255){
            elevation = 255;
        }
        else if(elevation < 0) {
            elevation = 0;
        }
        this.elevation = elevation;
    }

    @Override
    public String toString() {
        return "" + elevation;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }
}