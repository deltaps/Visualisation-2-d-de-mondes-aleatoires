package vue;

import model.Case;
import model.WorldMap;

import javax.swing.*;
import java.awt.*;

// Caméra avec une vue utilisateur.

public class UserCamera extends JPanel implements CameraStrategy {

    private int x;
    private int y;
    private int distance; // distance d'affichage
    private int height; // Hauteur de la position de la caméra
    private int scaleHeight; // Le multiplicateur de hauteur (pour l'affichage)
    private int horizon; // Angle vertical

    private WorldMap map;
    private int mapLength;

    public UserCamera(WorldMap map) {
        this.x = 49;
        this.y = 49;
        this.distance = 20;

        this.map = map;
        this.height = this.map.getCase(this.x,this.y).getElevation() +10;
        this.scaleHeight = 120;
        this.horizon = 120;
        this.mapLength = Math.round(600/this.map.getLength());
        setBackground(Color.BLUE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int distanceActu = this.distance; distanceActu != 1; distanceActu--){
            Point pleft = new Point(-distanceActu + this.x, -distanceActu + this.y); // Point le plus a gauche
            Point pright = new Point(distanceActu + this.x,-distanceActu + this.y);

            float dx = (pright.getX() - pleft.getY()) / this.map.getLength()*10; // Ratio du nombre de point
            for(int i = 0; i < this.map.getLength() * 10; i++){ // on boucle sur la taille de l'écran
                float heightOnScreen = (this.height - this.map.getCase(Math.round(pleft.getX()),Math.round(pleft.getY())).getElevation()) / distanceActu * this.scaleHeight + this.horizon;
                int hautteureuru = this.map.getCase(Math.round(pleft.getX()),Math.round(pleft.getY())).getElevation();
                drawVerticalLine(g, (int) heightOnScreen,i,new Color(hautteureuru,hautteureuru,hautteureuru));
                pleft.setX(pleft.getX() + dx);
            }
        }
    }

    public void drawVerticalLine(Graphics g, int height, int x, Color color) {
        g.setColor(color);
        g.fillRect(x, map.getWorldMap().length*10 - height, 1, height);
    }

}
