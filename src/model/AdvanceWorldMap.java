package model;

import java.util.Random;

public class AdvanceWorldMap implements WorldMap{

    private int size;
    private Case[][] worldMap;

    public AdvanceWorldMap(int size){
        this.size = size;
        Random rand = new Random();
        int seed1 = rand.nextInt();
        int seed2 = rand.nextInt();

    }
    @Override
    public Case[][] getWorldMap() {
        return new Case[0][];
    }

    @Override
    public Case getCase(int x, int y) {
        return null;
    }
}
