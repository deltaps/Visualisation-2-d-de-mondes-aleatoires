package model;

import java.util.Random;

public class AdvanceWorldMap implements WorldMap{

    private int size;
    private Case[][] worldMap;

    public AdvanceWorldMap(int size){
        this.size = size;
        Random rand = new Random();
        int seed = rand.nextInt();
        AdvanceNoise noise = new AdvanceNoise(seed);
        noise.SetNoiseType(AdvanceNoise.NoiseType.OpenSimplex2);

        this.worldMap = new Case[this.size][this.size];

        for(int y = 0; y < this.size; y++){
            for(int x = 0; x < this.size; x++){
                float elevation = 1 * ((noise.GetNoise(x,y) + 1)/2) + 0.2f * ((noise.GetNoise(3*x,3*y) + 1) / 2) + 0.09f * ((noise.GetNoise(8*x,8*y) + 1) / 2); //Octave
                elevation = elevation/(1+0.2f+0.09f);
                elevation = (float) Math.pow(elevation,5);
                this.worldMap[x][y] = new Case(elevation);
            }
        }
        for(int y = 0; y < this.size; y++){
            for(int x = 0; x < this.size; x++){
                this.worldMap[x][y].setHumidite((noise.GetNoise(x,y) + 1) / 2);
            }
        }
    }
    @Override
    public Case[][] getWorldMap() {
        return this.worldMap;
    }

    @Override
    public Case getCase(int x, int y) {
        while(x >= this.worldMap.length){
            x -= this.worldMap.length;
        }
        while(y >= this.worldMap.length){
            y -= this.worldMap.length;
        }
        while(x < 0){
            x += this.worldMap.length;
        }
        while(y < 0){
            y += this.worldMap.length;
        }
        return this.worldMap[y][x]; // TODO Voir avec le modulo
    }
}
