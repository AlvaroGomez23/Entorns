import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;
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
    static ArrayList<String> codiBarresP = new ArrayList<>(100);
    static HashMap<String, String> carret = new HashMap<>(100);
    static LinkedHashMap<String, Integer> codis = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {
        crearFitxers();
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
            codiBarresP.add(codiBarres);
            carret.put(codiBarres, nom);
            menuSecundari();
        } catch (InputMismatchException | NumberFormatException e) {
            recollirExcepcions();
            afegirAlimentacio();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            recollirExcepcions();
            afegirAlimentacio();
        }
    }

    static void afegirTextil() throws FileNotFoundException {
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

    static void afegirElectronica() throws FileNotFoundException {
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

            for (String codi : codiBarresP) {
                codis.put(codi, codis.getOrDefault(codi, 0) + 1);
            }

            codis.forEach((codi, quantitat) -> {
                String nom = carret.get(codi);
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
            if (carret.isEmpty()) {
                System.out.println("No hi ha cap producte a la llista.");
            } else {
                Date dataActual = new Date(System.currentTimeMillis());
                float[] preuTotal = { 0 };

                System.out.println("--------------");
                System.out.println("--SAPAMERCAT--");
                System.out.println(dataActual);
                System.out.println("--------------");
                System.out.println("---DETALLS---");
                System.out.println("--------------");

                for (String codi : codiBarresP) {
                    codis.put(codi, codis.getOrDefault(codi, 0) + 1);
                }

                codis.forEach((codi, quantitat) -> {
                    String nom = carret.get(codi);
                    Producte producte = null;
                    for (int i = 0; i < productes.size(); i++) {
                        Producte p = productes.get(i);
                        if (p.getNom().equals(nom)) {
                            producte = p;
                            break;
                        }
                    }

                    if (producte != null) {
                        float preu = producte.getPreu();
                        System.out.printf("%-" + 15 + "s%" + 5 + "s%" + 5 + "s%" + 5 + "s\n", nom, quantitat, preu, quantitat * preu);
                    }
                });

                productes.forEach(producte -> {
                    float preu = producte.getPreu();
                    preuTotal[0] += preu;
                    return;
                });

                System.out.println("TOTAL: " + preuTotal[0]);

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

    public static void recollirExcepcions() throws FileNotFoundException {

        File Exceptions = new File("./logs/Exceptions.dat");
        PrintStream escriptor = new PrintStream(Exceptions);
        try {

            escriptor.println("S'ha produit un error al executar el programa");

        } catch (Exception e) {
            System.out.println("S'ha produit un error recollint excepcions");
            recollirExcepcions();
        } finally {
            escriptor.close();
        }

    }

    public static void crearFitxers() throws IOException {

        try {
            File logs = new File("./logs");
            File updates = new File("./updates");
            File UpdateTextilPrices = new File("./updates/UpdateTextilPrices.dat");
            File Exceptions = new File("./logs/Exceptions.dat");

            logs.mkdirs();
            updates.mkdirs();
            UpdateTextilPrices.createNewFile();
            Exceptions.createNewFile();

        } catch (FileNotFoundException e) {
            recollirExcepcions();
        } catch (Exception e) {
            recollirExcepcions();
        }

    }
}