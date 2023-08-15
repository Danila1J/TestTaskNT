import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Task4 {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Использование: java Task4 <fileName>");
            return;
        }
        String fileName = args[0];
        List<Integer> nums = readFile(fileName);

        int minMoves = calculateMinMoves(nums);
        System.out.println("Минимальное количество ходов: " + minMoves);
    }

    /**
     * Читает числа из файла и возвращает список чисел.
     *
     * @param fileName имя файла для чтения
     * @return список чисел, прочитанных из файла
     */
    private static List<Integer> readFile(String fileName) {
        List<Integer> nums = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            for (String line : lines) {
                nums.add(Integer.parseInt(line));
            }
        } catch (IOException e) {
            System.err.println("Произошла ошибка при чтении файла: " + fileName);
            e.printStackTrace();
        }
        return nums;
    }

    /**
     * Рассчитывает минимальное количество ходов требуемых
     * для приведения всех элементов к одному числу
     *
     * @param nums список чисел
     * @return минимальное количество ходов
     */
    private static int calculateMinMoves(List<Integer> nums) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        int target = max - min;
        int minMoves = 0;

        for (int num : nums) {
            minMoves += Math.abs(num - target);
        }

        return minMoves;
    }
}
