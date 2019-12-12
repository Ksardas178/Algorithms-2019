package lesson3;

import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

// Attention: comparable supported but comparator is not
public class BinaryTree<T extends Comparable<T>> extends AbstractSet<T> implements CheckableSortedSet<T> {

    private static class Node<T> {
        final T value;

        Node<T> left = null;

        Node<T> right = null;

        Node(T value) {
            this.value = value;
        }
    }

    private Node<T> root = null;

    private int size = 0;

    @Override
    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison = closest == null ? -1 : t.compareTo(closest.value);
        if (comparison == 0) {
            return false;
        }
        Node<T> newNode = new Node<>(t);
        if (closest == null) {
            root = newNode;
        } else if (comparison < 0) {
            assert closest.left == null;
            closest.left = newNode;
        } else {
            assert closest.right == null;
            closest.right = newNode;
        }
        size++;
        return true;
    }

    public boolean checkInvariant() {
        return root == null || checkInvariant(root);
    }

    public int height() {
        return height(root);
    }

    private boolean checkInvariant(Node<T> node) {
        Node<T> left = node.left;
        if (left != null && (left.value.compareTo(node.value) >= 0 || !checkInvariant(left))) return false;
        Node<T> right = node.right;
        return right == null || right.value.compareTo(node.value) > 0 && checkInvariant(right);
    }

    private int height(Node<T> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public void show() {
        show(root);
    }

    private void show(Node<T> node) {
        if (node != null) {
            System.out.println(node.value);
            if (node.right != null) show(node.right);
            if (node.left != null) show(node.left);
        }
    }

    /**
     * Удаление элемента в дереве
     * Средняя
     */
    @Override
    public boolean remove(Object o) {
        if (!contains(o)) return false;
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> toRemove = find(t);//Удаляемый элемент
        Node<T> toReplace;//Заменяемый элемент
        Node<T> temp;
        if (toRemove == root) {//Отдельно случай замены корня
            if (root.right == null && root.left == null) {//Нет потомков
                root = null;
            } else if (root.right == null) {//Нет правых потомков
                toReplace = find(toRemove.left, t);//Ищем наименее отстоящий элемент слева на замену
                toReplace.right = null;//Перепривязка
                temp = toReplace;
                while (temp.left != null) {//Определяем самый левый элемент относительно заменяющего
                    temp = temp.left;
                }
                temp.left = root.left;//Перепривязка
                removeCycleLink(toReplace);//Убираем циклическую ссылку
                root = toReplace;//Перепривязка
            } else {//Может не быть левых потомков
                if (root.right.left == null) {
                    toReplace = root.right;//Ищем наименее отстоящий элемент справа на замену
                    toReplace.left = root.left;//Перепривязка
                    root = toReplace;//Перепривязка
                } else {
                    toReplace = find(toRemove.right, t);//Ищем наименее отстоящий элемент справа на замену
                    toReplace.left = root.left;//Перепривязка
                    temp = toReplace;
                    while (temp.right != null) {//Определяем самый правый элемент относительно заменяющего
                        temp = temp.right;
                    }
                    temp.right = root.right;//Перепривязка
                    removeCycleLink(toReplace);//Убираем циклическую ссылку

                    root = toReplace;//Перепривязка
                }
            }//Случай удаления элемента из поддерева
        } else {
            temp = findAncestor(t);
            if (toRemove.right == null & toRemove.left == null) {//Если потомков не имеется
                if (temp.left != null && t.compareTo(temp.left.value) == 0)//Ищем подходящего потомка и удаляем
                    temp.left = null;
                else temp.right = null;
            } else if (toRemove.right == null) {//Если нет потомков справа, но есть слева
                if (temp.left.right == null){
                    toReplace = toRemove.left;
                    temp.left = toReplace;
                } else
                toReplace = find(toRemove.left, t);
                toReplace.left = toRemove.left;
                removeCycleLink(toReplace);//Убираем циклическую ссылку
                temp.left = toReplace;
            } else {//Может не быть левых потомков, но точно есть правые
                toReplace = find(toRemove.right, t);
                removeCycleLink(toReplace);//Убираем циклическую ссылку
                toReplace.right = toRemove.right;
                if (temp.right != null && t.compareTo(temp.right.value) == 0)
                    temp.right = toReplace;
                else temp.left = toReplace;
            }
        }
        size--;
        return true;
    }

    private void removeCycleLink(Node<T> node){
        Node<T> t = findAncestor(node.value);
        if (t.right != null && node.value.compareTo(t.right.value) == 0)
            t.right = null;
        else t.left = null;
    }

    private Node<T> findAncestor(T t) {
        //Элемент гарантированно есть!
        Node<T> currentNode = root;
        Node<T> prevNode = root;
        int comparison = t.compareTo(currentNode.value);
        while (comparison != 0) {
            prevNode = currentNode;
            if (comparison > 0) {
                currentNode = currentNode.right;
            } else {
                currentNode = currentNode.left;
            }
            comparison = t.compareTo(currentNode.value);
        }
        return prevNode;
    }

    @Override
    public boolean contains(Object o) {
        @SuppressWarnings("unchecked")
        T t = (T) o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.value) == 0;
    }

    private Node<T> find(T value) {
        if (root == null) return null;
        return find(root, value);
    }

    private Node<T> find(Node<T> start, T value) {
        int comparison = value.compareTo(start.value);
        if (comparison == 0) {
            return start;
        } else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, value);
        } else {
            if (start.right == null) return start;
            return find(start.right, value);
        }
    }

    public class BinaryTreeIterator implements Iterator<T> {

        private BinaryTreeIterator() {
            // Добавьте сюда инициализацию, если она необходима
        }

        /**
         * Проверка наличия следующего элемента
         * Средняя
         */
        @Override
        public boolean hasNext() {
            // TODO
            throw new NotImplementedError();
        }

        /**
         * Поиск следующего элемента
         * Средняя
         */
        @Override
        public T next() {
            // TODO
            throw new NotImplementedError();
        }

        /**
         * Удаление следующего элемента
         * Сложная
         */
        @Override
        public void remove() {
            // TODO
            throw new NotImplementedError();
        }
    }

    @NotNull
    @Override
    public Iterator<T> iterator() {
        return new BinaryTreeIterator();
    }

    @Override
    public int size() {
        return size;
    }


    @Nullable
    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    /**
     * Для этой задачи нет тестов (есть только заготовка subSetTest), но её тоже можно решить и их написать
     * Очень сложная
     */
    @NotNull
    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        // TODO
        throw new NotImplementedError();
    }

    /**
     * Найти множество всех элементов меньше заданного
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> headSet(T toElement) {
        // TODO
        throw new NotImplementedError();
    }

    /**
     * Найти множество всех элементов больше или равных заданного
     * Сложная
     */
    @NotNull
    @Override
    public SortedSet<T> tailSet(T fromElement) {
        // TODO
        throw new NotImplementedError();
    }

    @Override
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.value;
    }

    @Override
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }
}
