package minesagm;
import minesagm.controlador.*;

/**
 * Classe principal que crida a controlador per començar a jugar
 */
public class Mines {
    /**
     * Funcio que fa que funcioni el joc
     * @param args El teclat
     */
    public static void main(String[] args) {
        Controlador.jugar(); //Fa que comenci el joc
    }
}
