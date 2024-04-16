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
                System.out.println("Quin tipus de producte vols?");
                System.out.println("1. Alimentacio");
                System.out.println("2. Tèxtil");
                System.out.println("3. Electrònica");
                System.out.println("0. Tornar ");
                op2 = scan.nextInt();

                switch (op2) {
                    case 1:
                        //Crida classe alimentació
                        break;
                    case 2:
                        //Crida classe tèxtil
                        break;
                    case 3:
                        //Crida classe electrònica
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
}