package vue;

import java.awt.*;

// Interface pour le pattern Strategy qui décide quel mode d'affichage on veut.

public interface CameraStrategy {
    void paintComponent(Graphics g);
}
