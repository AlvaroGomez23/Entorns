package minesagm.model;


import minesagm.vista.Vista;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Classe que s'encarrega de fer el model dels camps i basicament el funcionament en general del joc
 */
public class Model {
    /****Dades globals privades****/
    private static int columnes, files, bombes;
    private static boolean jocFinalitzat;
    private static char[][] campMinesOcult, campMinesVisible, campMinesSolucio;
    /** Determina quan el joc acaba si el jugador ha guanyat o no
     */
    public static boolean win = false;
    static Scanner scan = new Scanner(System.in);


    /**
     * S'inicialitza el joc amb els paràmetres que li ha passat el jugador
     *
     * @param f Files que vol el jugador
     * @param c Columnes que vol el jugador
     * @param b Bombes que vol el jugador
     */
    public static void inicialitzarJoc(int f, int c, int b) {
        files = f;
        columnes = c;
        bombes = b;
        jocFinalitzat = false;

        campMinesOcult = new char[files][columnes];
        campMinesVisible = new char[files][columnes];
        campMinesSolucio = new char[files][columnes];

        inicialitzarCampMines();
        generarBombesAleatoriament();
        comptarBombes();
        mostrarCampVisible();
    }

    /**
     * Inicialitza tots els camps de mines
     */
    private static void inicialitzarCampMines() {

        for (int i = 0; i < files; ++i) {
            for (int j = 0; j < columnes; ++j) {
                campMinesOcult[i][j] = 'X'; //Camp mines amb numeros
                campMinesVisible[i][j] = '·'; //Camp mines del jugador
                campMinesSolucio[i][j] = 'B'; //Camp mines de la solucio
            }
        }

    }

    /**
     * Genera el camp de mines aleatòriament al camp ocult
     */
    private static void generarBombesAleatoriament() {
        int[] posicioBombes = new int[2];

        for (int i = 0; i < bombes; ++i) {
            for (int j = 0; j < 1; ++j) {
                do {
                    posicioBombes[0] = (int) (Math.random() * (files));
                    posicioBombes[1] = (int) (Math.random() * (columnes));
                } while (campMinesOcult[posicioBombes[0]][posicioBombes[1]] == 'B');
                campMinesOcult[posicioBombes[0]][posicioBombes[1]] = 'B';
            }
        }


    }


    /**
     * Verifica el camp per no intentar destapar caselles que es trobin fora del camp
     *
     * @param fila    Numero màxim de files que hi ha
     * @param columna Numero màxim de columnes que hi ha
     * @return Retorna fals si alguna coordenada es troba fora del taulell i true si és correcte
     */
    private static boolean verificarCamp(int fila, int columna) {
        if (fila < 0 || columna < 0 || columna >= columnes || fila >= files) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * Funció que es dedica a comptar les bombes que té al voltant la casella que seleccionem
     */
    private static void comptarBombes() {
        int cont;
        for (int i = 0; i < files; ++i) {
            for (int j = 0; j < columnes; ++j) {
                cont = 0;
                if (campMinesOcult[i][j] == 'X') {
                    if (verificarCamp(i + 1, j) && campMinesOcult[i + 1][j] == 'B') ++cont; //Dreta
                    if (verificarCamp(i, j + 1) && campMinesOcult[i][j + 1] == 'B') ++cont; //Esquerra
                    if (verificarCamp(i - 1, j) && campMinesOcult[i - 1][j] == 'B') ++cont; //Abaix
                    if (verificarCamp(i, j - 1) && campMinesOcult[i][j - 1] == 'B') ++cont; //Adalt
                    if (verificarCamp(i - 1, j - 1) && campMinesOcult[i - 1][j - 1] == 'B') ++cont; //Adalt dreta
                    if (verificarCamp(i - 1, j + 1) && campMinesOcult[i - 1][j + 1] == 'B') ++cont; //Adalt esquerra
                    if (verificarCamp(i + 1, j + 1) && campMinesOcult[i + 1][j + 1] == 'B') ++cont; //Abaix dreta
                    if (verificarCamp(i + 1, j - 1) && campMinesOcult[i + 1][j - 1] == 'B') ++cont; //Abaix esquerra
                    campMinesOcult[i][j] = (char) (cont + '0');
                    campMinesSolucio[i][j] = campMinesOcult[i][j];
                }
                if (campMinesSolucio[i][j] == '0') {
                    campMinesSolucio[i][j] = ' ';
                }
            }
        }
    }


    /**
     * Crida a la funció que mostra els dos camps, el taulell amb el que juga el jugador i la solució
     */
    public static void mostrarCampVisible() {
        Vista.mostrarCampMines(campMinesOcult);
        Vista.mostrarCampMines(campMinesSolucio); //Es el camp de mines que determina si el jugador ha guanyat o no
        Vista.mostrarCampMines(campMinesVisible);
    }


    /**
     * Funció que destapa la casella corresponent
     * @param fila    Numero màxim de files
     * @param columna Numero màxim de columnes
     */
    public static void trepitjar(int fila, int columna) {
        String coordenada;
        char lletra;
        String numeros;
        int num;

        System.out.println("Quina coordenada vols? ");
        coordenada = scan.nextLine();

        lletra = coordenada.toUpperCase().charAt(0);
        numeros = coordenada.substring(1);
        num = Integer.parseInt(numeros);
        lletra = (char) (lletra - 'A');

        if (campMinesOcult[lletra][num] == 'B') { //En cas de trepitjar una bomba, el joc acaba i el jugador perd
            win = false;
            jocFinalitzat = true;
            Vista.mostrarCampMines(campMinesSolucio); // Si el jugador trepitja una bomba, mostra la solucio
            Vista.mostrarMissatge();
        } else {

            trepijarRecursivament(lletra, num); //Crida a la funció recursiva
            Vista.mostrarCampMines(campMinesVisible); //Actualitza el camp de mines del jugador

        }


        estat();

    }


    /**
     * Afegeix les banderes a les coordenades corresponents
     */
    public static void posarBandera() {
        String coordenada;
        char lletra;
        String numeros;
        int num;

        System.out.println();
        System.out.println("Quina coordenada vols? ");
        coordenada = scan.nextLine();

        lletra = coordenada.toUpperCase().charAt(0);
        numeros = coordenada.substring(1);
        num = Integer.parseInt(numeros);
        lletra = (char) (lletra - 'A');

        if (campMinesVisible[lletra][num] == '·') {
            campMinesVisible[lletra][num] = 'B';
        } else if (campMinesVisible[lletra][num] == 'B') {
            campMinesVisible[lletra][num] = '·';
        }

        Vista.mostrarCampMines(campMinesVisible);
        estat();

    }


    /**
     * Funció que comprova l'estat del joc per saber si ha acabat
     * @return Si és fals, el joc continua, en cas de que sigui cert, el joc acaba
     */
    public static boolean estatJoc() {
        return jocFinalitzat;
    }


    /**
     * Comprova si el camp del jugador correspon a la solució
     */
    private static void estat() {
        if(Arrays.deepEquals(campMinesVisible, campMinesSolucio)) {
            jocFinalitzat = true;
            win = true;
            System.out.println();
        }
    }




    /**
     * Mitjançant recursivitat destapa les caselles adjacents a la coordenada indicada, si una de les destapades té el valor 0,
     * continuara destapant fins que trobi un numero que no sigui 0
     * @param fila    Fila entrada per el jugador
     * @param columna Columna entrada per el jugador
     */
    private static void trepijarRecursivament(int fila, int columna) {
        if (!(verificarCamp(fila, columna))) {
            return;
        }

        if (campMinesVisible[fila][columna] == '·') {
            campMinesVisible[fila][columna] = campMinesOcult[fila][columna];
            if (campMinesOcult[fila][columna] == '0') {
                trepijarRecursivament(fila + 1, columna + 1);
                trepijarRecursivament(fila - 1, columna + 1);
                trepijarRecursivament(fila + 1, columna - 1);
                trepijarRecursivament(fila - 1, columna - 1);
                trepijarRecursivament(fila + 1, columna);
                trepijarRecursivament(fila, columna + 1);
                trepijarRecursivament(fila - 1, columna);
                trepijarRecursivament(fila, columna - 1);
                if (campMinesVisible[fila][columna] == '0') campMinesVisible[fila][columna] = ' ';
            }
        }
    }
}
