package minesagm.vista;

import minesagm.model.Model;

/**
 * Classe que mostra els camps i resultats del jugador
 */

public class Vista {

    /**
     * Mostra el camp de mines del jugador
     * @param camp El camp amb el que el jugador està interactuant
     */
    public static void mostrarCampMines(char camp[][]) {
        int f = camp.length;
        int c = camp[0].length;
        System.out.println();
        System.out.println();

        for (int k = 0; k < c; ++k) {
            System.out.print("\t" + k);
        }
        for (int i = 0; i < f; ++i) {
            System.out.println();
            System.out.printf((char) ('A' + i) + "\t");
            for (int j = 0; j < c; ++j) {
                System.out.print(camp[i][j] + "\t");
            }
        }
    }


    /**
     * Mostra un missatge si has perdut o guanyat, depenent del resultat
     */
    public static void mostrarMissatge() {
        System.out.println();
        if (Model.win) {
            System.out.println("Has guanyat");
        } else {
            System.out.println("Has perdut");
            System.exit(0);
        }
    }
}
