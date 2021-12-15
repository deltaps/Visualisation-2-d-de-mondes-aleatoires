package controlleur;

import model.PredefinedWorldMap;
import model.TruePredefinedWorldMap;
import model.WorldMap;
import vue.TopCamera;
import vue.UserCamera;
import vue.Vue;

public class Controller {

    //Classe qui créer le model et la vue, ne prend pas encore en compte les écouteurs.

    public Controller() {

        WorldMap map = new WorldMap(new PredefinedWorldMap());

        for(int i = 0; i < map.getWorldMap().length; i++){
            for(int j = 0; j < map.getWorldMap().length; j++){
                System.out.print("[" + map.getWorldMap()[i][j] + "] ");
            }
            System.out.println("");
        }

        new Vue(map, new UserCamera(map), this);
    }
}
