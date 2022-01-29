package vue;

import controlleur.Controller;

import javax.swing.*;
import java.awt.event.WindowEvent;

// Classe qui est lancée au tout début pour définir les options du programme.

public class VueOptions extends JFrame {

    private final Object[] options = {"Vue par dessus", "Vue 1ère personne"};

    public VueOptions(Controller controller) {

        int camera = afficheOptionPaneAvecOptions("Type de caméra ?", "Choisissez une option", options);
        controller.setCamera(camera);

        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)); // Ferme cette fenêtre.
    }


    // Affiche un "pop up" avec JOptionPane qui permet à l'utilisateur d'entrer une valeur et de la récupérer.
    public int afficheOptionPaneAvecOptions(String message, String titre, Object[] options) {
        return JOptionPane.showOptionDialog(null, message, titre, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }
}
