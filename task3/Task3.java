import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Task3 {
    /**
     * Считывает данные из файлов с входными данными,
     * обновляет значения тестовых данных и записывает результат в файл "report.json".
     *
     * @param args Аргументы командной строки. Ожидается два аргумента - имя файла "tests.json" и имя файла "values.json".
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Использование: java Task3 <tests_file> <values_file>");
            return;
        }
        String testsFileName = args[0];
        String valuesFileName = args[1];
        try (FileReader testsFileReader = new FileReader(testsFileName);
             FileReader valuesFileReader = new FileReader(valuesFileName);
             FileWriter fileWriter = new FileWriter("report.json")) {
            JSONParser parser = new JSONParser();
            JSONObject testsJsonObj = (JSONObject) parser.parse(testsFileReader);
            JSONObject valuesJsonObj = (JSONObject) parser.parse(valuesFileReader);

            updateTestValues(testsJsonObj, valuesJsonObj);

            fileWriter.write(testsJsonObj.toJSONString());
        } catch (ParseException ex) {
            System.err.println("Произошла ошибка во время синтаксического анализа");
            ex.printStackTrace();
        } catch (IOException e) {
            System.err.println("Произошла ошибка при чтении файла");
            e.printStackTrace();
        }
    }

    /**
     * Обновляет значения тестовых данных.
     *
     * @param testsJsonObj  JSON объект с тестовыми данными.
     * @param valuesJsonObj JSON объект с значениями тестовых данных.
     */
    private static void updateTestValues(JSONObject testsJsonObj, JSONObject valuesJsonObj) {
        JSONArray testsArray = (JSONArray) testsJsonObj.get("tests");
        updateValues(testsArray, valuesJsonObj);
    }

    /**
     * Обновляет значения вложенных тестовых данных.
     *
     * @param nestedTestsArray JSON массив с вложенными тестовыми данными.
     * @param valuesJsonObj    JSON объект с значениями тестовых данных.
     */
    private static void updateNestedTestValues(JSONArray nestedTestsArray, JSONObject valuesJsonObj) {
        updateValues(nestedTestsArray, valuesJsonObj);
    }

    /**
     * Обновляет значения value.
     *
     * @param testsArray    JSON массив с тестовыми данными.
     * @param valuesJsonObj JSON объект с значениями тестовых данных.
     */
    private static void updateValues(JSONArray testsArray, JSONObject valuesJsonObj) {
        for (Object testObj : testsArray) {
            JSONObject testJsonObj = (JSONObject) testObj;
            long testIdValue = (long) testJsonObj.get("id");

            JSONObject valueJsonObj = findValue(valuesJsonObj, testIdValue);

            if (valueJsonObj != null) {
                testJsonObj.put("value", valueJsonObj.get("value"));
            }
            JSONArray nestedTestsArray = (JSONArray) testJsonObj.get("values");
            if (nestedTestsArray != null) {
                updateNestedTestValues(nestedTestsArray, valuesJsonObj);
            }
        }
    }

    /**
     * Находит значение по идентификатору теста.
     *
     * @param valuesJsonObj JSON объект с значениями тестовых данных.
     * @param valueTestId   идентификатор теста.
     * @return JSON объект с найденным значением или null, если значение не найдено.
     */
    private static JSONObject findValue(JSONObject valuesJsonObj, long valueTestId) {
        JSONArray valuesArray = (JSONArray) valuesJsonObj.get("values");

        for (Object valueObj : valuesArray) {
            JSONObject valueJsonObj = (JSONObject) valueObj;
            long valueTestIdValue = (long) valueJsonObj.get("id");

            if (valueTestIdValue == valueTestId) {
                return valueJsonObj;
            }
        }
        return null;
    }
}