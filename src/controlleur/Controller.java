package controlleur;

import model.BasicWorldMap;
import model.WorldMap;
import vue.*;

public class Controller {

    //Classe qui créer le model et la vue.

    protected CameraStrategy camera;
    protected WorldMap map;

    public Controller() {

        //this.map = new WorldMap1(new PredefinedWorldMap());
        this.map = new BasicWorldMap(40);

        for(int i = 0; i < map.getWorldMap().length; i++){
            for(int j = 0; j < map.getWorldMap().length; j++){
                System.out.print("[" + map.getWorldMap()[i][j] + "] ");
            }
            System.out.println("");
        }

        new VueOptions(this);
        //CameraStrategy camera = new UserCamera(map);

        if(this.camera instanceof UserCamera) {
            new Keyboard((UserCamera) this.camera);
        }

        new Vue(map, this.camera, this);
    }

    public void setCamera(int camera) {
        if(camera==0) { // Si l'option "Vue par dessus a été choisie"
            this.camera = new TopCamera(this.map);
        }
        else {
            this.camera = new UserCamera(this.map);
        }
    }
}
