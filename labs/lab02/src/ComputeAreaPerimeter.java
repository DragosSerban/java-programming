import java.util.Scanner;

public class ComputeAreaPerimeter {
    public static void areaOrPerimeter(double r, Scanner scan) {
        boolean isGood = false;
        while (!isGood) {
            System.out.println("Calculam aria (A) sau perimetrul (P):");
            char calculate = scan.next().charAt(0);
            if (calculate == 'a' || calculate == 'A') {
                double A = Math.PI * Math.pow(r, 2);
                System.out.println("Aria cercului este: " + A + ".");
                isGood = true;
            } else if (calculate == 'p' || calculate == 'P') {
                double P = 2 * Math.PI * r;
                System.out.println("Perimetrul cercului este: " + P + ".");
                isGood = true;
            } else {
                System.out.println("Eroare la citirea caracterului!");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        for (int i = 1; ; i++) {
            System.out.println("Introduceti raza cercului nr " + i + ":");
            double r = scan.nextDouble();
            if (r < 0) {
                System.out.println("Eroare la citirea razei!");
                break;
            }
            areaOrPerimeter(r, scan);
        }
    }
}
