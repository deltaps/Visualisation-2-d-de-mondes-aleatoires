package controlleur;

import model.BasicWorldMap;
import model.WorldMap;
import vue.CameraStrategy;
import vue.UserCamera;
import vue.Vue;

public class Controller {

    //Classe qui créer le model et la vue, ne prend pas encore en compte les écouteurs.

    public Controller() {

        //WorldMap1 map = new WorldMap1(new PredefinedWorldMap());
        WorldMap map = new BasicWorldMap(40);

        for(int i = 0; i < map.getWorldMap().length; i++){
            for(int j = 0; j < map.getWorldMap().length; j++){
                System.out.print("[" + map.getWorldMap()[i][j] + "] ");
            }
            System.out.println("");
        }

        CameraStrategy camera = new UserCamera(map);

        if(camera instanceof UserCamera) {
            new Keyboard((UserCamera) camera);
        }

        new Vue(map, camera, this);
    }

    public void move(int direction) {

    }
}
