package model;

public class PredefinedWorldMap implements MapStrategy {

    @Override
    public Case[][] creationWorldMap() {
        // Roughness <= 128/width+height
        Noise noise = new Noise(null,128.0f,50,50);
        noise.initialise();
        float[][] grid = noise.getGrid_();
        Case[][] worldmap = new Case[50][50];
        for(int i = 0; i < 50; i++){
            for(int j = 0; j < 50; j++){
                worldmap[i][j] = new Case(i,j, (int) (grid[i][j]));
            }
        }
        return worldmap;
    }
}
