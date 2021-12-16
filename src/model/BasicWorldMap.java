package model;

public class BasicWorldMap implements WorldMap{
    private int size;
    private Case[][] worldMap;
    public BasicWorldMap(int size){
        this.size = size;
        Noise noise = new Noise(null,128.0f,size,size);
        noise.initialise();
        float[][] grid = noise.getGrid_();
        Case[][] worldmap = new Case[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                worldmap[i][j] = new Case((int) (grid[i][j]));
            }
        }
        this.worldMap = worldmap;
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
