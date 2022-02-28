package model;

public class Case {
    // Représentation d'une case
    // Catégorie de case
    private int elevation; // 0 à 255
    private float humidite;

    public Case(int elevation) {
        if(elevation > 255){
            elevation = 255;
        }
        else if(elevation < 0) {
            elevation = 0;
        }
        this.elevation = elevation;
        this.humidite = 0.9f;
    }

    @Override
    public String toString() {
        return "" + elevation;
    }

    public int getElevation() {
        return elevation;
    }

    public float getHumidite() {return humidite;}

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }
}
