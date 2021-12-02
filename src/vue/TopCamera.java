package vue;

import model.MapStrategy;
import model.WorldMap;

import javax.swing.*;
import java.awt.*;

public class TopCamera extends JPanel implements CameraStrategy {

    private WorldMap map;

    public TopCamera(WorldMap map) {
        this.map = map;
    }


    @Override
    public void paintComponent(Graphics g) {

    }
}
