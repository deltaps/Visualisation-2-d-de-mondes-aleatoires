package vue;

import model.Case;
import model.ColorMap;
import model.WorldMap;

import javax.swing.*;
import java.awt.*;

// Caméra en vue de dessus. Pour l'instant avec des nuances de gris en noir et blanc.
// S'adapte à la taille de la map.

public class TopCamera extends JPanel implements CameraStrategy {

    private WorldMap map;
    private ColorMap colorMap;
    private int taille;

    public TopCamera(WorldMap map, ColorMap colorMap) { // Pas besoins de savoir la taille de la map
        this.map = map;
        this.colorMap = colorMap;
        this.taille = Math.round(600/this.map.getWorldMap().length);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        /*for(int x = 0; x < map.length; x++){
            for(int y = 0; y < map[x].length; y++){
                //Case square = map[x][y];
                g.setColor(this.colorMap[x][y]);
                g.fillRect(x*this.taille, y*this.taille, this.taille,this.taille);
            }
        }*/
    }

    @Override
    public void moveUser(int direction) {

    }

    @Override
    public void moveCamera(int direction) {

    }

    @Override
    public void moveHeight(int direction) {

    }

    @Override
    public void setColorMap(int index) {
        this.colorMap = new ColorMap(this.map, index);
        repaint();
    }
}
