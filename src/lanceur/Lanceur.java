package lanceur;

import controlleur.Controller;

public class Lanceur {
    public static void main(String[] args){
        AffichageTerminal affichageTerminal = new AffichageTerminal();
        affichageTerminal.lancement();
        
        new Controller();
    }
}
