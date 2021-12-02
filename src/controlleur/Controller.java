package controlleur;

import model.PredefinedWorldMap;
import model.WorldMap;
import vue.TopCamera;
import vue.Vue;

public class Controller {

    public Controller() {

        WorldMap map = new WorldMap(new PredefinedWorldMap());

        new Vue(map, new TopCamera(map), this);
    }
}
