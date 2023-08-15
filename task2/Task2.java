import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Использование: java Task2 <circleFile> <pointsFile>");
            return;
        }

        // Чтение координат и радиуса окружности из первого файла
        float centerX, centerY, radius;
        try (Scanner circleScanner = new Scanner(new File(args[0]))) {
            centerX = circleScanner.nextFloat();
            centerY = circleScanner.nextFloat();
            radius = circleScanner.nextFloat();
        } catch (FileNotFoundException e) {
            System.err.println("Файл с координатами и радиусом окружности не найден.");
            e.printStackTrace();
            return;
        }

        // Чтение координат точек из второго файла
        try (Scanner pointsScanner = new Scanner(new File(args[1]))) {
            while (pointsScanner.hasNext()) {
                float pointX = pointsScanner.nextFloat();
                float pointY = pointsScanner.nextFloat();

                // Вычисление расстояния между точкой и центром окружности
                double distance = Math.sqrt(Math.pow(pointX - centerX, 2) + Math.pow(pointY - centerY, 2));

                // Сравнение расстояния с радиусом, чтобы определить положение точки
                int position;
                if (distance == radius) {
                    position = 0; // точка лежит на окружности
                } else if (distance < radius) {
                    position = 1; // точка находится внутри
                } else {
                    position = 2; // точка находится снаружи
                }

                // Вывод положения точки в консоль
                System.out.println(position);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Файл точек не найден.");
            e.printStackTrace();
        }
    }
}
