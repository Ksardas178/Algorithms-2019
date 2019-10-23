package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@SuppressWarnings("unused")
public class JavaAlgorithms {
    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     * <p>
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     * <p>
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     * <p>
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) {
        throw new NotImplementedError();
    }

    /**
     * Задача Иосифа Флафия.
     * Простая
     * <p>
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 5
     * <p>
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 х
     * <p>
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 х 3
     * 8   4
     * 7 6 Х
     * <p>
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     * <p>
     * 1 Х 3
     * х   4
     * 7 6 Х
     * <p>
     * 1 Х 3
     * Х   4
     * х 6 Х
     * <p>
     * х Х 3
     * Х   4
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   х
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   Х
     * Х х Х
     * <p>
     * Общий комментарий: решение из Википедии для этой задачи принимается,
     * но приветствуется попытка решить её самостоятельно.
     */
    static public int josephTask(int menNumber, int choiceInterval) {
        throw new NotImplementedError();
    }

    /**
     * Наибольшая общая подстрока.
     * Средняя
     * <p>
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     */

    /*
    L1, L2 - длины строк

    Трудоемкость: O(L1)*O(L2)*O(1) = O(L1*L2) //O(1) - поиск по индексу в массиве
    Ресурсоемкость: O(L1*L2)
    */
    static public String longestCommonSubstring(String first, String second) {
        int[][] M = new int[first.length()][second.length()];
        int max = 0;
        int pos = 0;
        for (int i = 0; i < first.length(); i++)
            for (int j = 0; j < second.length(); j++) {
                if (first.charAt(i) == second.charAt(j))
                    if (i > 0 && j > 0) M[i][j] = M[i - 1][j - 1] + 1;
                    else M[i][j] = 1;
                if (M[i][j] > max) {
                    max = M[i][j];
                    pos = i + 1;
                }
            }
        if (max > 0) return first.substring(pos - max, pos);
        else return "";
    }

    /**
     * Число простых чисел в интервале
     * Простая
     * <p>
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     * <p>
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     */
    static public int calcPrimesNumber(int limit) {
        throw new NotImplementedError();
    }

    /**
     * Балда
     * Сложная
     * <p>
     * В файле с именем inputName задана матрица из букв в следующем формате
     * (отдельные буквы в ряду разделены пробелами):
     * <p>
     * И Т Ы Н
     * К Р А Н
     * А К В А
     * <p>
     * В аргументе words содержится множество слов для поиска, например,
     * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
     * <p>
     * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
     * и вернуть множество найденных слов. В данном случае:
     * ТРАВА, КРАН, АКВА, НАРТЫ
     * <p>
     * И т Ы Н     И т ы Н
     * К р а Н     К р а н
     * А К в а     А К В А
     * <p>
     * Все слова и буквы -- русские или английские, прописные.
     * В файле буквы разделены пробелами, строки -- переносами строк.
     * Остальные символы ни в файле, ни в словах не допускаются.
     */

    /*
    w - количество слов для поиска
    l - длина слова (средняя)
    L - длина слова(максимальная)
    W,H - размеры матрицы поиска

    Трудоемкость:
        Худшая: O(H)+O(H*W)+O(H*W*w*(4*3^(L-2))) = O(H*W*w*3^L) //4*3^(L-2) - примерное количество листьев у
                                                                                      дерева обхода матрицы по направлениям
        Средняя (для средней длины слова и вероятности успешного определения направления): O(H*W*w*(2*1.5^(l-2))) = O(H*W*w*1.5^l)
    Ресурсоемкость: O(W*H*2*L)+O((L^2)/2) = O(W*H*L) + O(L^2)//Самая длинная рекурсия со всеми матрицами и искомым словом
    */
    public static boolean wordFromPosExists(int i, int j, int w, int h, String word, char[][] M, boolean[][] fM) {
        boolean out = false;
        if (word.charAt(0) == M[i][j] && word.length() != 1) {
            for (Direction dir : Direction.values()) {//Up, right, down, left
                if (!out && i + dir.offsetX >= 0 && i + dir.offsetX < h
                        && j + dir.offsetY >= 0 && j + dir.offsetY < w && !fM[i + dir.offsetX][j + dir.offsetY]) {
                    fM[i][j] = true;
                    out = wordFromPosExists(i + dir.offsetX, j + dir.offsetY, w, h, word.substring(1), M, fM);
                }
            }
        } else return word.charAt(0) == M[i][j];
        return out;
    }

    static public Set<String> baldaSearcher(String inputName, Set<String> words) {
        LinkedHashSet<String> answers = new LinkedHashSet<>();
        char[][] letters;
        int h, w;
        h = 1;
        try {
            FileReader reader = new FileReader(inputName);
            Scanner scan = new Scanner(reader);
            w = scan.nextLine().length() / 2 + 1;
            while (scan.hasNextLine()) {
                scan.nextLine();
                h++;
            }
            letters = new char[h][w];

            reader = new FileReader(inputName);
            scan = new Scanner(reader);
            int j = 0;
            while (scan.hasNextLine()) {
                final String line = scan.nextLine();
                for (int i = 0; i < w; i++) letters[j][i] = line.charAt(i * 2);
                j++;
            }

            for (int i = 0; i < h; i++)
                for (j = 0; j < w; j++)
                    for (String word : words)
                        if (!answers.contains(word) && wordFromPosExists(i, j, w, h, word, letters, new boolean[h][w]))
                            answers.add(word);

            reader.close();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return answers;
    }
}
