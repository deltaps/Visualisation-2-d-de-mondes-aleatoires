package vue;

import controlleur.Controller;
import model.MapStrategy;
import model.WorldMap;

import javax.swing.*;
import java.awt.*;

// Avoir pluriseurs visu différentes (pattern strategy)
// Visu en 2D de niveau de gris
// RayCasting: Voir voxel space
public class Vue extends JFrame {

    private WorldMap map;
    protected CameraStrategy camera;

    public Vue(WorldMap map, CameraStrategy camera, Controller controller) {

        this.map = map;
        this.camera = camera;

        this.GUI();
    }

    public void GUI() {

        setTitle("Visualisation de monde aléatoire");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800,800));

        Container contentPane = getContentPane();
        contentPane.setLayout(null);

        contentPane.add((Component) this.camera);

        setContentPane(contentPane);
        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
