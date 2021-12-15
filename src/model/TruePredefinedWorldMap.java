package model;

public class TruePredefinedWorldMap implements MapStrategy {

    @Override
    public Case[][] creationWorldMap() {

        int size = 5;
        Case[][] worldmap = new Case[size][size];

        worldmap[0][0] = new Case(255);
        worldmap[0][1] = new Case(255);
        worldmap[0][2] = new Case(255);
        worldmap[0][3] = new Case(255);
        worldmap[0][4] = new Case(255);
        worldmap[1][0] = new Case(255);
        worldmap[1][1] = new Case(255);
        worldmap[1][2] = new Case(255);
        worldmap[1][3] = new Case(255);
        worldmap[1][4] = new Case(255);
        worldmap[2][0] = new Case(255);
        worldmap[2][1] = new Case(255);
        worldmap[2][2] = new Case(60);
        worldmap[2][3] = new Case(255);
        worldmap[2][4] = new Case(255);
        worldmap[3][0] = new Case(255);
        worldmap[3][1] = new Case(255);
        worldmap[3][2] = new Case(255);
        worldmap[3][3] = new Case(255);
        worldmap[3][4] = new Case(255);
        worldmap[4][0] = new Case(255);
        worldmap[4][1] = new Case(255);
        worldmap[4][2] = new Case(255);
        worldmap[4][3] = new Case(255);
        worldmap[4][4] = new Case(255);

        return worldmap;
    }
}
