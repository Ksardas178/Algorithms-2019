package lesson1;

import kotlin.NotImplementedError;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     * <p>
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
     * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
     * <p>
     * Пример:
     * <p>
     * 01:15:19 PM
     * 07:26:57 AM
     * 10:00:03 AM
     * 07:56:14 PM
     * 01:15:19 PM
     * 12:40:31 AM
     * <p>
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
     * <p>
     * 12:40:31 AM
     * 07:26:57 AM
     * 10:00:03 AM
     * 01:15:19 PM
     * 01:15:19 PM
     * 07:56:14 PM
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortTimes(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка адресов
     * <p>
     * Средняя
     * <p>
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     * <p>
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     * <p>
     * Людей в городе может быть до миллиона.
     * <p>
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     * <p>
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка температур
     * <p>
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     * <p>
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     * <p>
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     * <p>
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     * <p>
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */

    /*
    t - температурный диапазон (дискретный)
    n - количество входных данных

    Трудоемкость:O(n)+O(n)=O(n)
    Ресурсоемкость: O(t)
    */
    static public void sortTemperatures(String inputName, String outputName) {
        int correction = 2730;
        int[] temp = new int[correction + 5000 + 1];//+1 - Ноль градусов
        File input = new File(inputName);
        try (FileReader reader = new FileReader(inputName)) {
            Scanner scan = new Scanner(reader);
            while (scan.hasNextLine()) {
                temp[Math.round(Float.parseFloat(scan.nextLine()) * 10)+correction]++;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        try (FileWriter writer = new FileWriter(outputName, false)) {
            for (int i = 0; i <= 5000 + correction; i++) {
                for (int j = temp[i]; j > 0; j--) {
                    final double c = (i-correction)/10.0;
                    //System.out.println(c);
                    writer.write(Double.toString(c)+'\n');
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Сортировка последовательности
     * <p>
     * Средняя
     * (Задача взята с сайта acmp.ru)
     * <p>
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     * <p>
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     * <p>
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     * <p>
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     */

    /*
    n - количество входных данных

    Трудоемкость: O(n)+O(n)+O(n)+O(n)+O(n*) = O(n)//* - O([1..n]) в зависимости от того, сколько одинаковых чисел добавим в конец
    Ресурсоемкость:
        Худший случай, все числа различны: O(2*n) = O(n)
    */
    static public void sortSequence(String inputName, String outputName) {
        HashMap<Integer, Integer> nums = new HashMap<>();
        int minNum = Integer.MAX_VALUE;
        try {
            FileReader reader = new FileReader(inputName);
            FileWriter writer = new FileWriter(outputName);
            Scanner scan = new Scanner(reader);
            while (scan.hasNextLine()) {
                int idx = Integer.parseInt(scan.nextLine());
                if (nums.keySet().contains(idx)) {
                    nums.put(idx, nums.get(idx) + 1);
                } else {
                    nums.put(idx, 1);
                }
            }
            Integer max = 0;
            for (Integer value : nums.values()) {
                if (value>max) max=value;
            }
            for (Map.Entry<Integer, Integer> entry : nums.entrySet()) {
                Integer key = entry.getKey();
                Integer value = entry.getValue();
                if (value == max && key<minNum) {
                    minNum = key;
                }
            }

            reader = new FileReader(inputName);
            scan = new Scanner(reader);
            while (scan.hasNextLine()) {
                int idx = Integer.parseInt(scan.nextLine());
                if (minNum!=idx) {
                    final String out = Integer.toString(idx)+'\n';
                    writer.write(out);
                }
            }
            for (int i=1; i<=max; i++) {
                final String out = Integer.toString(minNum)+'\n';
                writer.write(out);
            }
            writer.close();
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Соединить два отсортированных массива в один
     * <p>
     * Простая
     * <p>
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     * <p>
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     * <p>
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        throw new NotImplementedError();
    }
}
