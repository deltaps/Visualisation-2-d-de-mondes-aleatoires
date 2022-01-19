package controlleur;

import vue.UserCamera;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Ecouteur de clavier.

public class Keyboard {

    protected final UserCamera camera;
    private static final int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;

    public Keyboard(UserCamera camera) {
        this.camera = camera;

        InputMap inputMap = this.camera.getInputMap(condition); // Permet de récupérer les InputMap du composant component (ici un JPanel). Les InputMap permettent d'associer des variables à des touches du clavier. La variable condition est un entier qui permet de faire en sorte que les fonctions activés par ce système sont mises en marche seulement si cette fenêtre est ciblée par l'utilisateur.
        ActionMap actionMap = this.camera.getActionMap(); // Permet de récupérer les ActionMap du composant component (ici un JPanel). Les ActionMap permettent d'associer une fonction à une variable définie par les InputMap.

        String z = "Z";
        inputMap.put(KeyStroke.getKeyStroke("Z"), z); // Permet d'associer à la variable arrowU la touche flèche du haut du clavier, définie avec KeyStroke.getKeyStroke("UP").
        String q = "Q";
        inputMap.put(KeyStroke.getKeyStroke("Q"), q); // Flèche du bas
        String s = "S";
        inputMap.put(KeyStroke.getKeyStroke("S"), s); // Flèche de droite
        String d = "D";
        inputMap.put(KeyStroke.getKeyStroke("D"), d); // Flèche de gauche

        String arrowU = "arrowU";
        inputMap.put(KeyStroke.getKeyStroke("UP"), arrowU); // Permet d'associer à la variable arrowU la touche flèche du haut du clavier, définie avec KeyStroke.getKeyStroke("UP").
        String arrowD = "arrowD";
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), arrowD); // Flèche du bas
        String arrowR = "arrowR";
        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), arrowR); // Flèche de droite
        String arrowL = "arrowL";
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), arrowL); // Flèche de gauche

        actionMap.put(z, new UserAction(this.camera, 0)); // Permet de lancer une action si la touche flèche du haut du clavier est détectée.
        actionMap.put(q, new UserAction(this.camera, 1));
        actionMap.put(s, new UserAction(this.camera, 2));
        actionMap.put(d, new UserAction(this.camera, 3));

        actionMap.put(arrowU, new CameraAction(this.camera, 0)); // Permet de lancer une action si la touche flèche du haut du clavier est détectée.
        actionMap.put(arrowD, new CameraAction(this.camera, 1));
        actionMap.put(arrowR, new CameraAction(this.camera, 2));
        actionMap.put(arrowL, new CameraAction(this.camera, 3));
    }


    private static class UserAction extends AbstractAction {
        UserCamera camera;
        int direction;

        UserAction(UserCamera camera, int direction) {
            this.camera = camera;
            this.direction = direction;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            this.camera.moveUser(this.direction); // Déplace le joueur sélectionné de la direction this.direction.
        }
    }


    private static class CameraAction extends AbstractAction {
        UserCamera camera;
        int direction;

        CameraAction(UserCamera camera, int direction) {
            this.camera = camera;
            this.direction = direction;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            this.camera.moveCamera(this.direction); // Déplace le joueur sélectionné de la direction this.direction.
        }
    }
}