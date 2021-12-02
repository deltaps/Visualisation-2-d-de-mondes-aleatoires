package vue;

import controlleur.Controller;
import model.WorldMap;

import javax.swing.*;
import java.awt.*;

// Avoir pluriseurs visu différentes (pattern strategy)
// Visu en 2D de niveau de gris
// RayCasting: Voir voxel space
public class Vue extends JFrame {

    // Classe représentant la fenêtre principale, prend un WorldMap, une CameraStrategy (pour le pattern Strategy) et le controller pour plus tard.

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
        setPreferredSize(new Dimension(600,600));

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        contentPane.add((Component) this.camera, BorderLayout.CENTER);

        setContentPane(contentPane);
        pack();
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
