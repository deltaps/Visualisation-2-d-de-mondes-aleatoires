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
import model.Case;
import model.ColorMap;
import model.WorldMap;

public class UserCamera extends JPanel implements CameraStrategy {
    private int x = 10;
    private int y = 10;
    private int distance = 50;
    private int height;
    private float scaleHeight;
    private int horizon;
    private float phi;
    private int sensibiliteRotation = 20;
    protected final double CONVERSIONPI = 0.017453292519943295D;
    private WorldMap map;
    private ColorMap colorMap;
    private int screenWidth;
    private int screenHeight;

    public UserCamera(WorldMap map, ColorMap colorMap) {
        this.map = map;
        this.colorMap = colorMap;
        int max = 0;
        Case[][] var4 = map.getWorldMap();
        int var5 = var4.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Case[] cases = var4[var6];
            Case[] var8 = cases;
            int var9 = cases.length;

            for(int var10 = 0; var10 < var9; ++var10) {
                Case casess = var8[var10];
                if (casess.getElevation() > max) {
                    max = casess.getElevation();
                }
            }
        }

        this.height = max;
        this.scaleHeight = 15.0F;
        this.horizon = 60;
        this.phi = 0.0F;
        this.screenWidth = 600;
        this.screenHeight = 600;
        this.setBackground(new Color(119, 181, 254));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setStroke(new BasicStroke(2.0F));
        float sinphi = (float)Math.sin((double)this.phi);
        float cosphi = (float)Math.cos((double)this.phi);
        double[] ybuffer = new double[this.screenWidth];

        for(int i = 0; i < this.screenWidth; ++i) {
            ybuffer[i] = (double)this.screenHeight;
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

        this.repaint();
    }

    public void moveCamera(int direction) {
        if (direction == 2) {
            this.phi = (float)((double)this.phi - 0.017453292519943295D * (double)this.sensibiliteRotation);
            if (this.phi < 0.0F) {
                this.phi = (float)((double)this.phi + 6.283185307179586D);
            }
        }

        if (direction == 3) {
            this.phi = (float)((double)this.phi + 0.017453292519943295D * (double)this.sensibiliteRotation);
            if ((double)this.phi > 6.283185307179586D) {
                this.phi = (float)((double)this.phi - 6.283185307179586D);
            }
        }

        System.out.println(Math.round((double)this.phi * 57.29577951308232D));
        this.repaint();
    }
}
