import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class Main {

    static Scanner scan = new Scanner(System.in);
    static ArrayList<Producte> productes = new ArrayList<>(100);
    static ArrayList<String> barresProductes = new ArrayList<>(100);
    static HashMap<String,String> carret = new HashMap<>(100);

    public static void main(String[] args) throws IOException {
        File logs = new File(".\\logs");
        logs.createNewFile();
        menuPrincipal();
    }

    static void menuPrincipal() throws IOException {
        String opcio;
        System.out.println("-------------");
        System.out.println("--BENVINGUT--");
        System.out.println("-------------");
        System.out.println("Quina opció vols?");
        System.out.println("1. Demanar productes");
        System.out.println("2. Passar per caixa");
        System.out.println("3. Mostrar carro");
        System.out.println("0. Acabar");
        opcio = scan.nextLine();
        switch (opcio) {
            case "1":
                menuSecundari();
                break;
            case "2":
                passarCaixa();
                break;
            case "3":
                mostrarCarro();
                break;
            case "0":
                System.exit(0);
                break;
            default:
                System.out.println("Has introduit un caràcter no vàlid. Torna a escollir una opció.");
                menuPrincipal();
                break;
        }
    }

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
                afegirAlimentacio();
                break;
            case "2":
                afegirTextil();
                break;
            case "3":
                afegirElectronica();
                break;
            case "0":
                menuPrincipal();
                break;
            default:
                System.out.println("Has introduit un caràcter no vàlid. Torna a esollir una opció.");
                menuSecundari();
                break;
        }
    }

    static void afegirAlimentacio() throws IOException {
        try {
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

            SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
            dataCaducitat = formatData.parse(dataTemp);

            productes.add(new Alimentacio(preu, nom, codiBarres, dataCaducitat));
            barresProductes.add(codiBarres);
            carret.put(codiBarres, nom);
            menuSecundari();
        } catch (InputMismatchException | NumberFormatException e) {
            recollirExcepcions();
            afegirAlimentacio();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            afegirAlimentacio();
        }
    }

    static void afegirTextil() {
        try {
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

            productes.add(new Textil(preu, nom, codiBarres, composicio));
            barresProductes.add(codiBarres);
            carret.put(codiBarres, nom);
            menuSecundari();
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Hi ha hagut un error, torna a introduir el producte.");
            recollirExcepcions();
            afegirTextil();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            afegirTextil();
        }
    }

    static void afegirElectronica() {
        try {
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

            productes.add(new Electronica(preu, nom, codiBarres, garantia));
            barresProductes.add(codiBarres);
            carret.put(codiBarres, nom);
            menuSecundari();
        } catch (InputMismatchException | NumberFormatException e) {
            recollirExcepcions();
            afegirElectronica();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            afegirElectronica();
        }
    }

    public static void mostrarCarro() throws IOException {
        LinkedHashMap<String, Integer> codis = new LinkedHashMap<>();
        for (String codi : barresProductes) {
            codis.put(codi, codis.getOrDefault(codi, 0) + 1);
        }

        codis.forEach((codi, quantitat) -> {
            String nom = carret.get(codi);
            System.out.println(nom + " --> " + quantitat);
        });
        menuPrincipal();
    }

    public static void passarCaixa() {
    try {
        if (carret.isEmpty()) {
            System.out.println("No hi ha cap producte a la llista.");
        } else {
            Date dataActual = new Date(System.currentTimeMillis());
            float[] preuTotal = {0}; // Utilizamos un array para almacenar el precio total

            System.out.println("--------------");
            System.out.println("--SAPAMERCAT--");
            System.out.println(dataActual);
            System.out.println("--------------");
            System.out.println("---DETALLS---");
            System.out.println("--------------");

            // Utilizar una expresión lambda para calcular el precio total y mostrar los detalles
            productes.forEach(producte -> {
                String nom = producte.getNom();
                float preu = producte.getPreu();
                System.out.printf("%-" + 15 + "s%" + 10 + "s\n", nom, preu);
                preuTotal[0] += preu; // Sumamos el precio de cada producto al precio total
            });

            System.out.println("TOTAL: " + preuTotal[0]);

            carret.clear();
            productes.clear();
            menuPrincipal();
        }
    } catch (Exception e) {
        System.out.println("Hi ha hagut un error al mostrar el carro.");
        recollirExcepcions();
    }
}

    public static void recollirExcepcions() {
        System.out.println("Hi ha hagut un problema al introduir les dades. Torna a intentar-ho.");
    }
}