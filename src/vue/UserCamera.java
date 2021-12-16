package vue;

import model.WorldMap;

import javax.swing.*;
import java.awt.*;

// Caméra avec une vue utilisateur.

public class UserCamera extends JPanel implements CameraStrategy {

    private int x;
    private int y;
    private int distance; // distance d'affichage
    private int height; // Hauteur de la position de la caméra
    private float scaleHeight; // Le multiplicateur de hauteur (pour l'affichage)
    private int horizon; // Angle vertical

    private WorldMap map;

    private int screenWidth;
    private int screenHeight;

    public UserCamera(WorldMap map) {
        this.x = 10;
        this.y = 10;
        this.distance = 40;

        this.map = map;
        this.height = this.map.getCase(this.x,this.y).getElevation();
        this.scaleHeight = 6.666666f;
        this.horizon = 120;

        this.screenWidth = 600;
        this.screenHeight = 600;

        setBackground(new Color(119, 181, 254));
    }

    @Override
    public void paintComponent(Graphics g) { // TODO Implémenté le déplacement d'abord.
        super.paintComponent(g);
        for(int distanceActu = this.distance; distanceActu != 1; distanceActu--){
            Point pleft = new Point(-distanceActu + this.x, -distanceActu + this.y); // Point le plus a gauche
            Point pright = new Point(distanceActu + this.x,-distanceActu + this.y);

            float dx = (pright.getX() - pleft.getX()) / this.screenWidth; // Ratio du nombre de point
            for(int i = 0; i < this.screenWidth; i++){ // on boucle sur la taille de l'écran
                float heightOnScreen = (this.height - this.map.getCase(Math.round(pleft.getX()),Math.round(pleft.getY())).getElevation()) / distanceActu * this.scaleHeight + this.horizon;
                int RGB = this.map.getCase(Math.round(pleft.getX()),Math.round(pleft.getY())).getElevation();
                drawVerticalLine(g, (int) heightOnScreen, i, new Color(RGB,RGB,RGB));
                pleft.setX(pleft.getX() + dx); // Problème a régler par rapport au débordement
            }
        }
    }

    public void drawVerticalLine(Graphics g, int height, int x, Color color) {
        g.setColor(color);
        g.fillRect(x, this.screenHeight - height, 1, height); //a voir avec drawline
    }

}
