package model;

public class PredefinedWorldMap implements MapStrategy {

    @Override
    public Case[][] creationWorldMap() {
        // Roughness <= 128/width+height
        int size = 40;
        Noise noise = new Noise(null,128.0f,size,size);
        noise.initialise();
        float[][] grid = noise.getGrid_();
        Case[][] worldmap = new Case[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                worldmap[i][j] = new Case((int) (grid[i][j]));
            }
        }
        return worldmap;
    }
}
