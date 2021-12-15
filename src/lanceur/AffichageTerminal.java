package lanceur;

import controlleur.Controller;
import model.PredefinedWorldMap;
import model.WorldMap;

import java.util.Random;

public class AffichageTerminal {
    public void lancement(){
        for(int i = 0; i < 50; i++){
            new WorldMap(new PredefinedWorldMap());
        }
        WorldMap worldMap = new WorldMap(new PredefinedWorldMap());
        for(int i = 0; i < worldMap.getWorldMap().length; i++){
            for(int j = 0; j < worldMap.getWorldMap().length; j++){
                System.out.print("[" + worldMap.getWorldMap()[i][j] + "] ");
            }
            System.out.println("");
        }

        new Controller();
    }
}
