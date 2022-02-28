package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Images {

    public static BufferedImage colorMapPattern;

    public static String DELIMITEUR_LINUX = "/";
    public static String DELIMITEUR_WINDOWS = "\\";
    public static String delimiteur;
    static {
        if (System.getProperty("os.name").equals("Windows")) {
            delimiteur = DELIMITEUR_WINDOWS;
        } else {
            delimiteur = DELIMITEUR_LINUX;
        }

        try {
            // Lis l'image (dans l'exemple de la ligne ci-dessous l'image de la case) dans le ficher images.
            // La variable delimiteur est faite pour pouvoir charger les images dans le fichier, pour les utilisateurs de Windows ou de Linux.
            colorMapPattern = ImageIO.read(new File("./images" + delimiteur + "colorMapPattern.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
