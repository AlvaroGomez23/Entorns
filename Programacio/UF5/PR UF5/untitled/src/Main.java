import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {

        int opcio = 0;
        System.out.println("Quina opció vols?");
        System.out.println("1. Demanar productes");
        System.out.println("0. Acabar");
        opcio = scan.nextInt();

        switch (opcio) {
            case 1:
                int op2 = 0;
                System.out.println("-------------");
                System.out.println("PRODUCTE");
                System.out.println("-------------");
                System.out.println("Quin tipus de producte vols?");
                System.out.println("1. Alimentacio");
                System.out.println("2. Tèxtil");
                System.out.println("3. Electrònica");
                System.out.println("0. Tornar ");
                op2 = scan.nextInt();
                scan.nextLine();

                switch (op2) {
                    case 1:
                        afegirAlimentacio();
                        break;
                    case 2:
                        afegirTextil();
                        break;
                    case 3:
                        afegirElectronica();
                        break;
                    default:
                        //Sortir del programa
                        break;
                }

                break;
        
            default:
                //Sortir del programa
                break;
        }


    }

    static void afegirAlimentacio() {

        try {
            String nom = "Guayaba";
            float preu = 0;
            String codiBarres = "0";
            //Date dataCaducitat = "0";
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
            //dataCaducitat = scan.nextLine();
            Alimentacio aliment = new Alimentacio(preu, nom, codiBarres, null);
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

            Textil roba = new Textil(preu, nom, codiBarres, composicio);
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

            Electronica electronica = new Electronica(preu, nom, codiBarres, garantia);
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
}