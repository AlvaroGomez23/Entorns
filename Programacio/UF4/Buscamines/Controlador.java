package minesagm.controlador;

import minesagm.model.Model;
import minesagm.vista.Vista;

import java.util.Scanner;

/**
 * Classe que controla el estat del joc
 */
public class Controlador {
    static Scanner scan = new Scanner(System.in);


    /**
     * Funció que pregunta al jugador com vol jugar
     */
    public static void jugar() {

        /**
         * Definició de variables
         * Primera interacció amb el jugador
         * Mostren missatges per després inicialitzar el joc
         */
        int F = 0;
        int C = 0;
        int B = 0;
        int mode = 0;
        String opcio;

        System.out.println("Escolleix mode de joc: " );
        System.out.println("1 - 8x8 / 10 bombes ");
        System.out.println("2 - 16x16 / 40 bombes");
        System.out.println("3 - 16x30 / 99 bombes");
        System.out.println("4 - Custom");
        System.out.println("0 - Sortir (Per defecte)");
        mode = scan.nextInt();
        scan.nextLine();

        if (mode == 1) {
            F = 8; C = 8; B = 10;
        } else if (mode == 2) {
            F = 16; C = 16; B = 40;
        } else if (mode == 3) {
            F = 16; C = 30; B = 99;
        } else if (mode == 4) {
            System.out.print("Quantes files vols: ");
            F = scan.nextInt();
            System.out.print("Quantes columnes vols: ");
            C = scan.nextInt();
            System.out.print("Quantes bombes vols: ");
            B = scan.nextInt();
            scan.nextLine();
        } else {
            System.exit(0);
        }

        Model.inicialitzarJoc(F, C, B);

        /**
         * Opcions a escollir pel jugador
         */
        while (!Model.estatJoc()) {
            System.out.println();
            System.out.println("Quina acció vols fer?");
            System.out.println("T - Trepitjar");
            System.out.println("B - Bandera");
            System.out.println("S - Sortir");
            opcio = scan.nextLine().trim().toUpperCase();
            switch (opcio) {
                case "T":
                    Model.trepitjar(F, C);
                    break;
                case "B":
                    Model.posarBandera();
                    break;
                case "S":
                    System.exit(0);
                    break;
            }
        }
        Vista.mostrarMissatge();
    }
}
