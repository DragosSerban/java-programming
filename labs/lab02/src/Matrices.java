import java.util.Scanner;

public class Matrices {
    public static final int MAX = 256;

    public static int[][] readMatrix(int n, Scanner scan) {
        int[][] a = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                a[i][j] = scan.nextInt();
            }
        }
        return a;
    }

    public static int[][] sumOfMatrices(int[][] a, int[][] b, int n) {
        int[][] c = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                c[i][j] = a[i][j] + b[i][j];
            }
        }
        return c;
    }

    public static void printMatrix(int[][] a, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%d ", a[i][j]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // subpunctul A
        System.out.println("A.");
        int n;
        System.out.printf("n = ");
        n = scan.nextInt();
        scan.nextLine();
        String[] students = new String[MAX];
        double[] averages = new double[MAX];
        for (int i = 0; i < n; i++) {
            students[i] = scan.nextLine();
            averages[i] = scan.nextDouble();
            scan.nextLine();
        }
        for (int i = 0; i < n; i++) {
            System.out.printf("%s   ", students[i]);
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.printf("%.2f   ", averages[i]);
        }
        System.out.println();

        // subpunctul B
        System.out.println("B.");
        System.out.printf("n = ");
        n = scan.nextInt();
        if (n >= 10 || n <= 0) {
            System.out.println("Eroare! n >= 10!");
            return;
        }
        System.out.println("A = ");
        int[][] a = readMatrix(n, scan);
        System.out.println("B = ");
        int[][] b = readMatrix(n, scan);
        int[][] c = sumOfMatrices(a, b, n);
        System.out.println("C = ");
        printMatrix(c, n);
    }
}
