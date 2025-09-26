import java.util.Random;

public class MatrixTask_lab1 {
    public static void main(String[] args) {
        try {
            int rows = 3; // кількість рядків
            int cols = 4; // кількість стовпців

            float[][] B = new float[rows][cols];
            Random rand = new Random();

            // 1. Заповнюємо матрицю випадковими числами
            System.out.println("Matrix B:");
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    B[i][j] = rand.nextInt(20) + rand.nextFloat(); // випадкові float
                    System.out.printf("%6.2f ", B[i][j]);
                }
                System.out.println();
            }

            // 2. Транспонування (C = B^T)
            float[][] C = new float[cols][rows];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    C[j][i] = B[i][j];
                }
            }

            System.out.println("\nMatrix C = B^T:");
            for (int i = 0; i < C.length; i++) {
                for (int j = 0; j < C[0].length; j++) {
                    System.out.printf("%6.2f ", C[i][j]);
                }
                System.out.println();
            }

            // 3. Пошук суми за умовою
            float sum = 0;
            for (int j = 0; j < C[0].length; j++) {
                float min = C[0][j];
                float max = C[0][j];

                // проходимо по рядках стовпця
                for (int i = 1; i < C.length; i++) {
                    if (C[i][j] < min) min = C[i][j];
                    if (C[i][j] > max) max = C[i][j];
                }

                if ((j + 1) % 2 == 0) { // стовпець парний -> максимум
                    sum += max;
                } else { // стовпець непарний -> мінімум
                    sum += min;
                }
            }

            System.out.println("\nAmount by condition = " + sum);

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Array bounds exceeded!");
        } catch (NullPointerException e) {
            System.out.println("Error: matrix not initialized!");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}



