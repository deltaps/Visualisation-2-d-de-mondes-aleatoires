package vue;

import model.Case;
import model.ColorMap;
import model.WorldMap;

import javax.swing.*;
import java.awt.*;

// Caméra en vue de dessus. Pour l'instant avec des nuances de gris en noir et blanc.
// S'adapte à la taille de la map.

public class TopCamera extends JPanel implements CameraStrategy {

    private Case[][] map;
    private int[][][] colorMap;
    private int taille;

    public TopCamera(WorldMap map, ColorMap colorMap) { // Pas besoins de savoir la taille de la map
        this.map = map.getWorldMap();
        this.colorMap = colorMap.getWorldMap();
        this.taille = Math.round(600/this.map.length);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(int x = 0; x < map.length; x++){
            for(int y = 0; y < map[x].length; y++){
                //Case square = map[x][y];
                g.setColor(new Color(this.colorMap[x][y][0], this.colorMap[x][y][1], this.colorMap[x][y][2]));
                g.fillRect(x*this.taille, y*this.taille, this.taille,this.taille);
            }
        }
    }
}
