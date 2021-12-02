package controlleur;

import model.PredefinedWorldMap;
import model.WorldMap;
import vue.TopCamera;
import vue.Vue;

public class Controller {

    //Classe qui créer le model et la vue, ne prend pas encore en compte les écouteurs.

    public Controller() {

        WorldMap map = new WorldMap(new PredefinedWorldMap());

        new Vue(map, new TopCamera(map), this);
    }
}
