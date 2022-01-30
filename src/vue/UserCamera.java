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
    private float phi; // Angle de vue salut
    private int sensibiliteRotation = 20;
    protected final double CONVERSIONPI = (Math.PI / 180);

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
        this.phi = 0;

        this.screenWidth = 600;
        this.screenHeight = 600;

        setBackground(new Color(119, 181, 254));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        float sinphi = (float)Math.sin(this.phi);
        float cosphi = (float)Math.cos(this.phi);
        for(int distanceActu = this.distance; distanceActu != 1; distanceActu--){

            Point pleft = new Point((-cosphi*distanceActu - sinphi*distanceActu) + this.x, (sinphi*distanceActu - cosphi*distanceActu) + this.y); // Point le plus a gauche
            Point pright = new Point((cosphi*distanceActu - sinphi*distanceActu) + this.x,(-sinphi*distanceActu - cosphi*distanceActu) + this.y);

            float dx = (pright.getX() - pleft.getX()) / this.screenWidth; // Ratio du nombre de point
            float dy = (pright.getY() - pleft.getY()) / this.screenWidth;
            for(int i = 0; i < this.screenWidth; i++){ // on boucle sur la taille de l'écran
                float heightOnScreen = (this.height - this.map.getCase(Math.round(pleft.getX()),Math.round(pleft.getY())).getElevation()) / distanceActu * this.scaleHeight + this.horizon;
                int RGB = this.map.getCase(Math.round(pleft.getX()),Math.round(pleft.getY())).getElevation();
                drawVerticalLine(g, (int) heightOnScreen, i, new Color(RGB,RGB,RGB));
                pleft.setX(pleft.getX() + dx); // Problème a régler par rapport au débordement
                pleft.setY(pleft.getY() + dy);
            }
        }
    }

    public void drawVerticalLine(Graphics g, int height, int x, Color color) {
        g.setColor(color);
        g.fillRect(x, this.screenHeight - height, 1, height); //a voir avec drawline
    }

    // Fonction pour bouger l'utilisateur en fonction de Keyboard (l'écouteur du clavier)
    public void moveUser(int direction) { //TODO je ne gère pas si c'est égale au dégrés spécifié
        int angle = (int) Math.round(this.phi * (180/Math.PI));
        boolean diagonal = false;
        if(angle > 30 && angle < 60){
            System.out.println("Angle supérieur à 30 et inférieur a 60");
            diagonal = true;
        }
        else if(angle > 60 && angle < 120){
            System.out.println("Angle supérieur à 60 et inférieur a 120");
            direction = (direction + 1) % 4;
        }
        else if(angle > 120 && angle < 150){
            System.out.println("Angle supérieur à 120 et inférieur a 150");
            direction = (direction + 1) % 4;
            diagonal = true;
        }
        else if(angle > 150 && angle < 210){
            System.out.println("Angle supérieur à 150 et inférieur a 210");
            direction = (direction + 2) % 4;
        }
        else if(angle > 210 && angle < 240){
            System.out.println("Angle supérieur à 210 et inférieur a 240");
            direction = (direction + 2) % 4;
            diagonal = true;
        }
        else if(angle > 240 && angle < 300){
            System.out.println("Angle supérieur à 240 et inférieur a 300");
            direction = (direction + 3) % 4;
        }
        else if(angle > 300 && angle < 330){
            System.out.println("Angle supérieur à 300 et inférieur a 330");
            direction = (direction + 3) % 4;
            diagonal = true;
        }
        /*
        if(angle > 45 && angle < 135){
            //System.out.println("Angle entre 45 et 135");
            direction = (direction + 1) % 4;
        }
        else if(angle > 135 && angle < 225){
            //System.out.println("Angle entre 135 et 225");
            direction = (direction + 2) % 4;
        }
        else if(angle > 225 && angle < 315){
            //System.out.println("Angle entre 225 et 315");
            direction = (direction + 3) % 4;
        }
        switch (direction) {
            case 0 -> this.y -= 1;
            case 1 -> this.x -= 1;
            case 2 -> this.y += 1;
            case 3 -> this.x += 1;
        }//TODO FAIRE MARCHe
        */
        switch (direction){
            case 0 :
                if(diagonal){
                    this.x -= 1;
                    this.y -= 1;
                }
                else{
                    this.y -= 1;
                }
                break;
            case 1:
                if(diagonal){
                    this.x -= 1;
                    this.y += 1;
                }
                else{
                    this.x -= 1;
                }

                break;
            case 2:
                if(diagonal){
                    this.x += 1;
                    this.y += 1;
                }
                else{
                    this.y += 1;
                }
                break;
            case 3:
                if(diagonal){
                    this.x += 1;
                    this.y -= 1;
                }
                else{
                    this.x += 1;
                }
                break;
        }

        this.height = this.map.getCase(this.x,this.y).getElevation();

        repaint();
    }

    // Fonction pour bouger la caméra en fonction de Keyboard (l'écouteur du clavier)
    public void moveCamera(int direction) {
        if(direction == 2){//rotation a droite
            this.phi -= (Math.PI / 180) * this.sensibiliteRotation;
            if(this.phi < 0){
                this.phi += (Math.PI / 180) * 360;
            }
        }
        if(direction == 3){//rotation a gauche
            this.phi+= (Math.PI / 180) * this.sensibiliteRotation;
            if(this.phi > (Math.PI / 180) * 360){
                this.phi-=(Math.PI / 180) * 360;
            }
        }
        System.out.println(Math.round(this.phi * (180/Math.PI)));
        repaint();
    }
}
