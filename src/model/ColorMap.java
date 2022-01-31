package model;

public class ColorMap {

    private int[][][] colorMap;

    public ColorMap(WorldMap map) {

        Case[][] worldMap = map.getWorldMap();
        colorMap = new int[worldMap.length][worldMap[0].length][3];

        for(int i = 0; i < worldMap.length; i++){
            for(int j = 0; j < worldMap[0].length; j++){
                int elevation = worldMap[i][j].getElevation();
                if(elevation <= 60) {
                    colorMap[i][j] = new int[]{elevation, elevation, elevation};
                }

                else if(elevation <= 120) {
                    colorMap[i][j] = new int[]{(int) Math.round(elevation*0.85), (int) Math.round(elevation*0.4), 0};
                }

                else if(elevation <= 160) {
                    colorMap[i][j] = new int[]{(int) Math.round(elevation*0.15), (int) Math.round(elevation*0.6), (int) Math.round(elevation*0.15)};
                }

                else if(elevation <= 225) {
                    colorMap[i][j] = new int[]{120,120,120};
                }
                else {
                    colorMap[i][j] = new int[]{255,255,255};
                }
            }
        }
    }

    public int[][][] getWorldMap() {
        return this.colorMap;
    }

    public int[] getCase(int x, int y) {
        while(x >= this.colorMap.length){
            x -= this.colorMap.length;
        }
        while(y >= this.colorMap.length){
            y -= this.colorMap.length;
        }
        while(x < 0){
            x += this.colorMap.length;
        }
        while(y < 0){
            y += this.colorMap.length;
        }
        return this.colorMap[y][x]; // TODO Voir avec le modulo
    }
}
