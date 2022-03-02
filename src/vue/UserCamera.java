//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import model.ColorMap;
import model.WorldMap;

public class UserCamera extends JPanel implements CameraStrategy {
    private int x;
    private int y;
    private int distance;
    private double height;
    private float scaleHeight;
    private int horizon;
    private float phi;
    private int sensibiliteRotation = 10;
    protected final double CONVERSIONPI = 0.017453292519943295D;
    private WorldMap map;
    private ColorMap colorMap;
    private int screenWidth;
    private int screenHeight;

    public UserCamera(WorldMap map, ColorMap colorMap) {
        this.map = map;
        this.colorMap = colorMap;
        this.height = 1;
        this.scaleHeight = 10000;
        this.distance = 1000;
        this.horizon = 60;
        this.phi = 0.0F;
        this.screenWidth = 600;
        this.screenHeight = 600;
        this.setBackground(new Color(119, 181, 254));

        this.x = this.map.getWorldMap().length / 2;
        this.y = this.map.getWorldMap().length / 2; // Pour se placer au milieu de la map
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setStroke(new BasicStroke(1.0F));
        float sinphi = (float)Math.sin(this.phi);
        float cosphi = (float)Math.cos(this.phi);
        double[] ybuffer = new double[this.screenWidth];

        for(int i = 0; i < this.screenWidth; ++i) {
            ybuffer[i] = this.screenHeight;
        }

        double dz = 1.0D;

        for(double z = 1.0D; z < (double)this.distance; dz += 0.2D) {
            Point pleft = new Point((float)((double)(-cosphi) * z - (double)sinphi * z + (double)this.x), (float)((double)sinphi * z - (double)cosphi * z + (double)this.y));
            Point pright = new Point((float)((double)cosphi * z - (double)sinphi * z + (double)this.x), (float)((double)(-sinphi) * z - (double)cosphi * z + (double)this.y));
            float dx = (pright.getX() - pleft.getX()) / (float)this.screenWidth;
            float dy = (pright.getY() - pleft.getY()) / (float)this.screenWidth;

            for(int i = 0; i < this.screenWidth; ++i) {
                double heightOnScreen = (double)(this.height - this.map.getCase(Math.round(pleft.getX()), Math.round(pleft.getY())).getElevation()) / z * (double)this.scaleHeight + (double)this.horizon;
                Color color = this.colorMap.getColor(Math.round(pleft.getX()), Math.round(pleft.getY()));
                this.drawVerticalLine(g, (double)((int)heightOnScreen), ybuffer[i], i, color);
                if (heightOnScreen < ybuffer[i]) {
                    ybuffer[i] = heightOnScreen;
                }

                pleft.setX(pleft.getX() + dx);
                pleft.setY(pleft.getY() + dy);
            }

            z += dz;
        }

    }

    public void drawVerticalLine(Graphics g, double heightBottom, double heightTop, int x, Color color) {
        if (!(heightTop <= heightBottom)) {
            if (heightTop < 0.0D) {
                heightTop = 0.0D;
            }

            g.setColor(color);
            g.drawLine(x, (int)Math.floor(heightTop), x, (int)Math.floor(heightBottom));
        }
    }

    public void moveUser(int direction) {
        int angle = (int)Math.round((double)this.phi * 57.29577951308232D);
        boolean diagonal = false;
        if (angle >= 30 && angle < 60) {
            diagonal = true;
        } else if (angle >= 60 && angle < 120) {
            direction = (direction + 1) % 4;
        } else if (angle >= 120 && angle < 150) {
            direction = (direction + 1) % 4;
            diagonal = true;
        } else if (angle >= 150 && angle < 210) {
            direction = (direction + 2) % 4;
        } else if (angle >= 210 && angle < 240) {
            direction = (direction + 2) % 4;
            diagonal = true;
        } else if (angle >= 240 && angle < 300) {
            direction = (direction + 3) % 4;
        } else if (angle >= 300 && angle < 330) {
            direction = (direction + 3) % 4;
            diagonal = true;
        }

        switch(direction) {
            case 0:
                if (diagonal) {
                    --this.x;
                    --this.y;
                } else {
                    --this.y;
                }
                break;
            case 1:
                if (diagonal) {
                    --this.x;
                    ++this.y;
                } else {
                    --this.x;
                }
                break;
            case 2:
                if (diagonal) {
                    ++this.x;
                    ++this.y;
                } else {
                    ++this.y;
                }
                break;
            case 3:
                if (diagonal) {
                    ++this.x;
                    --this.y;
                } else {
                    ++this.x;
                }
        }
        float actualHeight = this.map.getCase(this.x,this.y).getElevation();
        if(this.height < actualHeight){
            this.height = actualHeight;
        }
        this.repaint();
    }

    public void moveCamera(int direction) {
        //HORIZON VERTICAL -----------------
        if(direction == 0){
            this.horizon+=10;
        }
        if(direction == 1){
            this.horizon-=10;
        }
        //ROTATION ----------------------
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

    public void moveHeight(int direction) {
        this.height += direction*0.1;
        float actualHeight = this.map.getCase(this.x,this.y).getElevation();
        if(this.height < actualHeight){
            this.height = actualHeight;
        }
        this.repaint();
    }
}
