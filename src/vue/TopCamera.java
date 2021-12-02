package vue;

import model.Case;
import model.MapStrategy;
import model.WorldMap;

import javax.swing.*;
import java.awt.*;

public class TopCamera extends JPanel implements CameraStrategy {

    private Case[][] map;

    public TopCamera(WorldMap map) {
        this.map = map.getWorldMap();
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for(int x = 0; x < map.length; x++){
            for(int y = 0; y < map[x].length; y++){
                Case square = map[x][y];
                g.setColor(new Color(square.getElevation(), square.getElevation(), square.getElevation()));
                g.drawRect(x*40, y*40, 40,40);
            }
        }
    }
}
