package vue;

import model.Case;
import model.WorldMap;

import javax.swing.*;
import java.awt.*;

// Caméra avec une vue utilisateur. Pour l'instant vide.

public class UserCamera extends JPanel implements CameraStrategy {

    private int x;
    private int y;
    private int distance; // distance d'affichage
    private int height; // Hauteur de la position de la caméra
    private int scaleHeight; // Le multiplicateur de hauteur (pour l'affichage)
    private int horizon; // Angle vertical

    private Case[][] map;
    private int mapLength;

    public UserCamera(WorldMap map) {
        this.x = 0;
        this.y = 0;
        this.distance = 50;

        this.map = map.getWorldMap();
        this.height = this.map[this.x][this.y].getElevation() +10;
        this.scaleHeight = 120;
        this.horizon = 120;
        this.mapLength = Math.round(600/this.map.length);
        setBackground(Color.BLUE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int distanceActu = this.distance; distanceActu != 1; distanceActu--){
            Point pleft = new Point(-distanceActu + this.x, -distanceActu + this.y);
            Point pright = new Point(distanceActu + this.x,-distanceActu + this.y);

            float dx = (pright.getX() - pleft.getY()) / this.map.length*10;

            for(int i = 0; i <= this.map.length * 10; i++){
                float heightOnScreen = (this.height - this.map[pleft.getX()][pleft.getY()].getElevation()) / distanceActu * this.scaleHeight + this.horizon;
                //drawVerticalLine(g,);
            }
        }
        /*
        for(int x = this.x; x < this.distance; x++) {
            for(int y = this.y; y < this.distance; y++) {

                Case square = this.map[x][y];

                drawVerticalLine(g, square.getElevation(), x, new Color(square.getElevation(), square.getElevation(), square.getElevation()));

            }
        }

         */
    }

    public void drawVerticalLine(Graphics g, int height, int x, Color color) {
        g.setColor(color);
        for(int h = height; h > 0; h--) {
            g.fillRect(x*10, h, 10, height);
        }
    }
}
