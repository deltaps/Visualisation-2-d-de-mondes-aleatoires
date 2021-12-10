package vue;

import model.Case;
import model.WorldMap;

import javax.swing.*;
import java.awt.*;

// Caméra avec une vue utilisateur. Pour l'instant vide.

public class UserCamera extends JPanel implements CameraStrategy {

    private int x;
    private int y;
    private int distance;

    private Case[][] map;
    private int mapLength;

    public UserCamera(WorldMap map) {
        this.x = 0;
        this.y = 0;
        this.distance = 10;

        this.map = map.getWorldMap();
        this.mapLength = Math.round(600/this.map.length);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(int x = this.x; x < this.distance; x++) {
            for(int y = this.y; y < this.distance; y++) {

                Case square = this.map[x][y];

                drawVerticalLine(g, square.getElevation(), x, new Color(square.getElevation(), square.getElevation(), square.getElevation()));

            }
        }
    }

    public void drawVerticalLine(Graphics g, int height, int x, Color color) {
        g.setColor(color);
        for(int h = height; h > 0; h--) {
            g.fillRect(x, h, 1, height);
        }
    }
}
