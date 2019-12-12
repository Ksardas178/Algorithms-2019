package lesson6;

import kotlin.NotImplementedError;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     * <p>
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
     * При сравнении подстрок, регистр символов *имеет* значение.
     */

    public static String longestCommonSubSequence(String first, String second) {
        int m = first.length();
        int n = second.length();
        if (m == 0 || n == 0) return "";
        if (first.charAt(m - 1) != second.charAt(n - 1)) {
            if (m == 1 && n == 1) return "";
            if (m == 1) return longestCommonSubSequence(first, second.substring(0, n - 1));
            if (n == 1) return longestCommonSubSequence(first.substring(0, m - 1), second);
            String s1 = longestCommonSubSequence(first, second.substring(0, n - 1));
            String s2 = longestCommonSubSequence(first.substring(0, m - 1), second);
            if (s1.length() > s2.length()) return s1;
            else return s2;
        } else if (m == 1) return first.substring(0, 1);
        if (n == 1) return second.substring(0, 1);
        return longestCommonSubSequence(first.substring(0, m - 1), second.substring(0, n - 1)) + first.charAt(m - 1);
    }


    /**
     * Наибольшая возрастающая подпоследовательность
     * Сложная
     * <p>
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     */
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        int s = list.size();
        if (s == 0) return Collections.emptyList();
        if (s == 1) return list;
        List<Integer> tinySeq = new LinkedList<>();
        List<Integer> lI = new LinkedList<>();
        List<Integer> lJ = new LinkedList<>();
        List<Integer> toAdd;
        int maxLi = 0;
        int maxLj = 0;
        for (int i = 0; i < s - 1; i++) {
            lI.add(list.get(i));
            for (int j = i + 1; j < s; j++) {
                if (list.get(i) < list.get(j)) {
                    lJ = longestIncreasingSubSequence(list.subList(j, s));
                    if (lJ.size() > maxLj) {
                        maxLj = lJ.size();
                        lI.addAll(lJ);
                    }
                }
            }
            if (lI.size() > maxLi) {
                maxLi = lI.size();
                tinySeq.clear();
                tinySeq.addAll(lI);
            }
            lI.clear();
        }
            return tinySeq;
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Средняя
     * <p>
     * В файле с именем inputName задано прямоугольное поле:
     * <p>
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     * <p>
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     * <p>
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    public static int shortestPathOnField(String inputName) {
        throw new NotImplementedError();
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
