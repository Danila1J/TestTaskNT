public class Task1 {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Укажите два аргумента командной строки: n и m.");
            System.out.println("Использование: java Task1 n m");
            return;
        }
        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);
        if (n <= 0 || m <= 0) {
            System.out.println("n и m должны быть целыми положительными числами.");
            return;
        }
        //Создание и заполнение массива
        int[] circularArray = createAndFillArray(n);

        int startIndex = 0; // начало интервала
        int endIndex = startIndex + m - 1; // конец интервала
        printPath(circularArray,startIndex, endIndex,n,m);
    }

    /**
     * Выводит путь из начальных элементов полученных интервалов.
     *
     * @param circularArray Круговой массив.
     * @param startIndex    Начальный индекс интервала.
     * @param endIndex      Конечный индекс интервала.
     */
    private static void printPath(int[] circularArray, int startIndex, int endIndex, int n, int m) {
        while (circularArray[0] != circularArray[endIndex]) {
            System.out.print(circularArray[startIndex]);
            startIndex = endIndex;
            endIndex = (startIndex + m - 1) % n;
        }
        System.out.print(circularArray[startIndex]);
        System.out.println();
    }

    /**
     * Создает и заполняет массив числами от 1 до n.
     *
     * @param n Размер массива.
     * @return Заполненный массив.
     */
    private static int[] createAndFillArray(int n) {
        int[] circularArray = new int[n];
        for (int i = 0; i < n; i++) {
            circularArray[i] = i + 1;
        }
        return circularArray;
    }

    /**
     * Выводит интервал элементов массива.
     *
     * @param array      Массив элементов.
     * @param startIndex Начальный индекс интервала.
     * @param endIndex   Конечный индекс интервала.
     */
    private static void printInterval(int[] array, int startIndex, int endIndex) {
        int length = array.length;
        for (int i = startIndex; i != endIndex; i = (i + 1) % length) {
            System.out.print(array[i] + " ");
        }
        System.out.print(array[endIndex] + " ");
        System.out.println();
    }
}
