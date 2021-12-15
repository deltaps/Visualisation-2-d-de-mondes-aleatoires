package model;
//Strategy dans la génération
//Ne pas mettre l'algo de génération dans cette class (strategy)
// Bruit blan
//La seed -> généré des seed (pour les générateur aléatoire) (1 fois pour la carte d'élévation, puis 1 fois pour la carte d'humidité etc...)
//ZOOM -> utilisé un bruit déjà existant
public class WorldMap {
    // Représentation de notre monde
    // Liste de Case
    // Génération aléatoire de monde
    private Case[][] worldMap;
    private MapStrategy creationMap;

    public WorldMap(MapStrategy creationMap){
        this.creationMap = creationMap;
        this.worldMap = this.creationMap.creationWorldMap();
    }

    public Case[][] getWorldMap() {
        return worldMap;
    }

    public Case getCase(int x, int y){
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
        return this.worldMap[y][x];
    }

    public int getLength(){
        return this.worldMap.length;
    }

}