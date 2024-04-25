
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class Main {

    static Scanner scan = new Scanner(System.in);
    static ArrayList<Producte> productes = new ArrayList<Producte>(100);
    static ArrayList<String> barresProductes = new ArrayList<>(100);
    static HashMap<String,String> carret = new HashMap<>(100);
        public static void main(String[] args) {

            menuPrincipal();
        }
        
            

        static void menuPrincipal() {
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
       
        
        static void menuSecundari() {
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
        

        

        static void afegirAlimentacio() {

            try {
                String nom = "Guayaba";
                float preu = 0;
                String codiBarres = "0";
                String dataTemp;
                Date dataCaducitat;
                System.out.println("Afegir alimentacio");
                System.out.print("Nom: ");
                nom = scan.nextLine();

                System.out.print("Preu: ");
                preu = scan.nextInt();
                scan.nextLine();

                System.out.print("Codi de barres: ");
                codiBarres = scan.nextLine();

                System.out.print("Data de caducitat: ");
                System.out.println();
                dataTemp = scan.nextLine();

                SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
                dataCaducitat = formatData.parse(dataTemp);
                
                productes.add(new Alimentacio(preu, nom, codiBarres, dataCaducitat));
                barresProductes.add(codiBarres);
                carret.put(codiBarres, nom);
                menuSecundari();
            } catch (InputMismatchException e){
                System.out.println("Hi ha hagut un problema al afegir el aliment (Dades introduides malament)");
                System.out.println("Torna a introduir el producte");
                afegirAlimentacio();
            } catch (Exception e) {
                System.out.println("Hi ha hagut un problema al afegir l'aliment"+e.getCause());
                System.out.println("Torna a introduir el producte");
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

                System.out.print("Preu: ");
                preu = scan.nextInt();
                scan.nextLine();

                System.out.print("Composició: ");
                composicio = scan.nextLine();

                System.out.println("Codi de barres: ");
                codiBarres = scan.nextLine();

                productes.add(new Textil(preu, nom, codiBarres, composicio));
                barresProductes.add(codiBarres);
                carret.put(codiBarres, nom);
                menuSecundari();
            } catch (InputMismatchException e){
                System.out.println("Hi ha hagut un problema al afegir el producte (Dades introduides malament)");
                System.out.println("Torna a introduir el producte");
                afegirTextil();
            } catch (Exception e) {
                System.out.println("Hi ha hagut un problema al afegir el producte"+e.getCause());
                System.out.println("Torna a introduir el producte");
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

                System.out.print("Preu: ");
                preu = scan.nextInt();
                scan.nextLine();

                System.out.print("Garantia (Dies): ");
                garantia = scan.nextInt();
                scan.nextLine();

                System.out.println("Codi de barres: ");
                codiBarres = scan.nextLine();

                productes.add(new Electronica(preu, nom, codiBarres, garantia));
                barresProductes.add(codiBarres);
                carret.put(codiBarres, nom);

                menuSecundari();
            } catch (InputMismatchException e){
                System.out.println("Hi ha hagut un problema al afegir el producte (Dades introduides malament)");
                System.out.println("Torna a introduir el producte");
                afegirElectronica();
            } catch (Exception e) {
                System.out.println("Hi ha hagut un problema al afegir el producte"+e.getCause());
                System.out.println("Torna a introduir el producte");
                afegirElectronica();
            }
        }

        public static void mostrarCarro() {

            
            System.out.println(productes.toString());
            System.out.println(barresProductes.toString());
            System.out.println(carret.toString());
        

            

            carret.forEach((key, value) -> System.out.println("Nom: " + value + " --> "));
            comptarQuantitat();
            
            

        }

        public static void passarCaixa() {
            Date dataActual = new Date(System.currentTimeMillis());
            System.out.println("--------------");
            System.out.println("--SAPAMERCAT--");
            System.out.println(dataActual);
            System.out.println("--------------");
            System.out.println("---DETALLS---");
            

            carret.clear();
            productes.clear();
            menuPrincipal();
        }

        public static void comptarQuantitat() {
            
            HashMap<String, Integer> barcodeCount = new HashMap<>();

        for (String key : carret.keySet()) {
            String nom = carret.get(key);
            barcodeCount.put(nom, barcodeCount.getOrDefault(nom, 0) + 1);
        }

        // Imprimir la cantidad de cada código de barras
        System.out.println("Conteo por código de barras:");
        for (String nom : barcodeCount.keySet()) {
            System.out.println(nom + " --> " + barcodeCount.get(nom));
        }
    }
} 