import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    //Creació dels ArrayLists i HashMaps necessaris
    static Scanner scan = new Scanner(System.in);
    static ArrayList<Producte> productes = new ArrayList<>(100); //Guarda els productes
    static ArrayList<String> codiBarresP = new ArrayList<>(100); //Guarda els codis de barres dels productes
    static HashMap<String, String> carret = new HashMap<>(100); //Guarda el nom i el codi de barres dels productes
    static LinkedHashMap<String, Integer> codis = new LinkedHashMap<>(); //Guarda el nom dels productes i la seva quantitat

    //Classe main, executa el programa
    public static void main(String[] args) throws IOException {
        crearFitxers();
        menuPrincipal();
    }

    //Menú principal per escollir opcions que portaran a diferents parts del programa
    static void menuPrincipal() throws IOException {
        String opcio;
        System.out.println("-------------");
        System.out.println("--BENVINGUT--");
        System.out.println("-------------");
        System.out.println("Quina opció vols?");
        System.out.println("1. Demanar productes");
        System.out.println("2. Passar per caixa");
        System.out.println("3. Mostrar carro");
        System.out.println("4. Buscar productes");
        System.out.println("0. Acabar");
        opcio = scan.nextLine();
        switch (opcio) {
            case "1":
                //Porta a un menu per afegir productes
                menuSecundari();
                break;
            case "2":
                //Executa la opció que imprimeix el tiquet
                passarCaixa();
                break;
            case "3":
                //Mostra els productes que hi ha al carro
                mostrarCarro();
                break;
            case "4":
                //Busca mitjançant el codi de barres els productes a "carret"
                buscarProductes();
                break;
            case "0":
                //Surt del programa
                System.exit(0);
                break;
            default:
                System.out.println("Has introduit un caràcter no vàlid. Torna a escollir una opció.");
                menuPrincipal();
                break;
        }
    }

    //Menu secundari per afegir els diferents tipus de productes
    static void menuSecundari() throws IOException {
        String op2;
        System.out.println("------------");
        System.out.println("--PRODUCTE--");
        System.out.println("------------");
        System.out.println("Quin tipus de producte vols?");
        System.out.println("1. Alimentacio");
        System.out.println("2. Tèxtil");
        System.out.println("3. Electrònica");
        System.out.println("0. Tornar ");
        op2 = scan.nextLine();

        switch (op2) {
            case "1":
                //Afegeix el producte "Alimentacio"
                afegirAlimentacio();
                break;
            case "2":
                //Afegeix el producte "Textil"
                afegirTextil();
                break;
            case "3":
                //Afegeix el producte "Electronica"
                afegirElectronica();
                break;
            case "0":
                //Torna al menú principal
                menuPrincipal();
                break;
            default:
                System.out.println("Has introduit un caràcter no vàlid. Torna a esollir una opció.");
                menuSecundari();
                break;
        }
    }
    //Afegix alimentació
    static void afegirAlimentacio() throws IOException {
        try {
            //Definició de variables
            String nom;
            float preu;
            String codiBarres;
            Date dataCaducitat;
            System.out.println("Afegir alimentacio");
            System.out.print("Nom: ");
            nom = scan.nextLine();
            if (nom.length() > 15) {
                throw new Exception("Nom massa llarg.");
            }

            System.out.print("Preu: ");
            preu = scan.nextFloat();
            scan.nextLine();

            System.out.print("Codi de barres: ");
            codiBarres = scan.nextLine();

            System.out.print("Data de caducitat (dd/MM/yyyy): ");
            String dataTemp = scan.nextLine();

            //Agafa el String i el passa a date mitjançant un parse
            SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
            dataCaducitat = formatData.parse(dataTemp);

            //S'afegeix el producte a totes les llistes 
            productes.add(new Alimentacio(preu, nom, codiBarres, dataCaducitat));
            codiBarresP.add(codiBarres);
            carret.put(codiBarres, nom);
            menuSecundari();
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Hi ha hagut un error al afegir l'aliment");
            recollirExcepcions();
            afegirAlimentacio();
        } catch (Exception e) {
            System.out.println("Hi ha hagut un error al afegir l'aliment");
            System.out.println(e.getMessage());
            recollirExcepcions();
            afegirAlimentacio();
        }
    }

    //Metode per afegir el textil
    static void afegirTextil() throws FileNotFoundException {
        try {
            //Definició de variables
            String nom;
            float preu;
            String composicio;
            String codiBarres;
            System.out.println("Afegir textil");
            System.out.print("Nom: ");
            nom = scan.nextLine();
            if (nom.length() > 15) {
                throw new Exception("Nom massa llarg.");
            }

            System.out.print("Preu: ");
            preu = scan.nextFloat();
            scan.nextLine();

            System.out.print("Composició: ");
            composicio = scan.nextLine();

            System.out.print("Codi de barres: ");
            codiBarres = scan.nextLine();

            //Afegeix el producte a les llistes
            productes.add(new Textil(preu, nom, codiBarres, composicio));
            codiBarresP.add(codiBarres);
            carret.put(codiBarres, nom);
            menuSecundari();
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Hi ha hagut un error, torna a introduir el producte.");
            recollirExcepcions();
            afegirTextil();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            recollirExcepcions();
            afegirTextil();
        }
    }

    //Metode per afegir el producte electronica
    static void afegirElectronica() throws FileNotFoundException {
        try {
            //Definició de variables
            String nom;
            float preu;
            int garantia;
            String codiBarres;
            System.out.println("Afegir electrònica");
            System.out.print("Nom: ");
            nom = scan.nextLine();
            if (nom.length() > 15) {
                throw new Exception("Nom massa llarg.");
            }

            System.out.print("Preu: ");
            preu = scan.nextFloat();
            scan.nextLine();

            System.out.print("Garantia (Dies): ");
            garantia = scan.nextInt();
            scan.nextLine();

            System.out.print("Codi de barres: ");
            codiBarres = scan.nextLine();

            //Afegir el producte a la llista
            productes.add(new Electronica(preu, nom, codiBarres, garantia));
            codiBarresP.add(codiBarres);
            carret.put(codiBarres, nom);
            menuSecundari();
        } catch (InputMismatchException | NumberFormatException e) {
            recollirExcepcions();
            afegirElectronica();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            recollirExcepcions();
            afegirElectronica();
        }
    }

    public static void mostrarCarro() throws IOException {

        if (carret.isEmpty()) {
            System.out.println("No hi ha cap article al carro.");
        } else {

            try {

                //Fa un recorregut i va comptant quants productes hi ha amb el mateix codi de barres, si no té cap registre, agafa 0
                for (String codi : codiBarresP) {
                    codis.put(codi, codis.getOrDefault(codi, 0) + 1);
                }

                //Imprimeix el codi 
                codis.forEach((codi, quantitat) -> {
                    String nom = carret.get(codi); //Agafa el codi del hashmap "carret"
                    System.out.println(nom + " --> " + quantitat);
                });
                menuPrincipal();

            } catch (Exception e) {
                System.out.println("Hi ha hagut un error al mostrar el carro");
                recollirExcepcions();
            }
        }
    }

    public static void passarCaixa() throws FileNotFoundException {
        try {
            //Si no hi ha res al carro t'envia al menú principal
            if (carret.isEmpty()) {
                System.out.println("No hi ha cap producte a la llista.");
                menuPrincipal();
            } else {
                //Agafa la data del tiquet
                Date dataActual = new Date(System.currentTimeMillis());
                float[] preuTotal = { 0 };

                System.out.println("--------------");
                System.out.println("--SAPAMERCAT--");
                System.out.println(dataActual);
                System.out.println("--------------");
                System.out.println("---DETALLS---");
                System.out.println("--------------");

                //Mateix metode per comptar que al mostrar carro
                for (String codi : codiBarresP) {
                    codis.put(codi, codis.getOrDefault(codi, 0) + 1);
                }

                //Imprimeix el contingut que necessita el tiquet
                codis.forEach((codi, quantitat) -> {
                    String nom = carret.get(codi);
                    Producte producte = null;
                    for (int i = 0; i < productes.size(); i++) {
                        Producte p = productes.get(i); //Agafa la ruta de l'objecte de l'arraylist
                        if (p.getNom().equals(nom)) {
                            producte = p; 
                            break;
                        }
                    }

                    //Si el producte no es null imprimeix el contingut del tiquet
                    if (producte != null) {
                        float preu = producte.getPreu();
                        System.out.printf("%-" + 15 + "s%" + 5 + "s%" + 5 + "s%" + 5 + "s\n", nom, quantitat, preu,
                                quantitat * preu);
                    }
                });

                //Recompta el preu total de tots els articles
                productes.forEach(producte -> {
                    float preu = producte.getPreu();
                    preuTotal[0] += preu;
                    return;
                });

                //Imprimeix el preu total del articles
                System.out.println("TOTAL: " + preuTotal[0]);

                //Vuidem el tot els arraylists
                carret.clear();
                productes.clear();
                codiBarresP.clear();
                codis.clear();
                menuPrincipal();
            }
        } catch (Exception e) {
            System.out.println("Hi ha hagut un error al mostrar el carro.");
            recollirExcepcions();
        }
    }

    //Recull les exceptions que llançen i printa el missatge al Exceptions.dat
    public static void recollirExcepcions() throws FileNotFoundException {

        File Exceptions = new File("./logs/Exceptions.dat");
        FileOutputStream errors = new FileOutputStream(Exceptions, true);
        PrintStream escriptor = new PrintStream(errors);
        try {

            escriptor.println("S'ha produit un error al executar el programa");

        } catch (Exception e) {
            System.out.println("S'ha produit un error recollint excepcions");
            recollirExcepcions();
        } finally {
            escriptor.close();
        }

    }

    //Metode pre crear els fitxers que necessitem per les excepcions
    public static void crearFitxers() throws IOException {

        try {
            File logs = new File("./logs");
            File updates = new File("./updates");
            File UpdateTextilPrices = new File("./updates/UpdateTextilPrices.dat");
            File Exceptions = new File("./logs/Exceptions.dat");

            //Crea les carpetes
            logs.mkdirs();
            updates.mkdirs();

            //Crea els fitxers
            UpdateTextilPrices.createNewFile();
            Exceptions.createNewFile();

        } catch (FileNotFoundException e) {
            recollirExcepcions();
        } catch (Exception e) {
            recollirExcepcions();
        }

    }

    //Busca els productes mitjançant streams i el codi de barres
    public static void buscarProductes() {


        System.out.println("Introdueix el codi de barres: ");
        String codiDeBarres = scan.nextLine().trim();

        List<String> buscadorProductes = carret.entrySet().stream() //Agafa els valors del carret
            .filter(entry -> entry.getKey().equals(codiDeBarres)) //Compara keys
            .map(Map.Entry::getValue) //Obté el valor
            .toList(); //Guarda el valor a la llista

        if (buscadorProductes.isEmpty()) {
            System.out.println("Aquest codi de barres no existeix");
        } else {
            System.out.println("Producte amb el codi de barres " + codiDeBarres + ": " + buscadorProductes.get(0));
        }
    }
}