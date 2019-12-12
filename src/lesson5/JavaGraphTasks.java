package lesson5;

import kotlin.NotImplementedError;
import sun.security.provider.certpath.Vertex;

import java.util.*;

@SuppressWarnings("unused")
public class JavaGraphTasks {
    /**
     * Эйлеров цикл.
     * Средняя
     *
     * Дан граф (получатель). Найти по нему любой Эйлеров цикл.
     * Если в графе нет Эйлеровых циклов, вернуть пустой список.
     * Соседние дуги в списке-результате должны быть инцидентны друг другу,
     * а первая дуга в списке инцидентна последней.
     * Длина списка, если он не пуст, должна быть равна количеству дуг в графе.
     * Веса дуг никак не учитываются.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Вариант ответа: A, E, J, K, D, C, H, G, B, C, I, F, B, A
     *
     * Справка: Эйлеров цикл -- это цикл, проходящий через все рёбра
     * связного графа ровно по одному разу
     */

    private static List<Graph.Edge> findEulerCycle(Graph graph, List<Graph.Edge> path, Graph.Vertex start){

        List<Graph.Edge> result = new LinkedList<>();
        List<Graph.Edge> toReveal = new LinkedList<>();
        List<Graph.Edge> newPath = new LinkedList<>();
        Graph.Edge currentEdge;
        Graph.Vertex currentVertex;
        int connections;
        int index = 0;

        //if (path.size() == graph.getVertices().size()) return Collections.emptyList();//!

        //Индекс для вставки
        if (!path.isEmpty()) {
            while (path.get(index).getBegin() != start && path.get(index).getEnd() != start) {
                index++;
            }
            index++;
        }

        //Ищем новое ребро, выходящее из start
        Iterator<Graph.Edge> edgeIterator = graph.getConnections(start).values().iterator();
        currentEdge = edgeIterator.next();
        while (edgeIterator.hasNext() && path.contains(currentEdge)) {
            currentEdge = edgeIterator.next();
        }
        if (path.contains(currentEdge)) return Collections.emptyList();

        //Ищем цикл
        Iterator<Graph.Vertex> vertexIterator;
        currentVertex = currentEdge.getBegin() == start ? currentEdge.getEnd() : currentEdge.getBegin();

        while (currentVertex != start){//Пока не замкнем цикл

            toReveal.add(currentEdge);//Записываем новый цикл

            vertexIterator = graph.getNeighbors(currentVertex).iterator();
            currentEdge = graph.getConnection(vertexIterator.next(), currentVertex);
            while (path.contains(currentEdge) || toReveal.contains(currentEdge)) currentEdge = graph.getConnection(vertexIterator.next(), currentVertex);//Ищем новый(!) путь из текущей вершины

            currentVertex = currentEdge.getBegin() == currentVertex ? currentEdge.getEnd() : currentEdge.getBegin();//Смещаем указатель на текущую вершину
        }
        toReveal.add(currentEdge);

        newPath.addAll(path);
        newPath.addAll(index, toReveal);//Добавляем цикл в путь*/
        result.addAll(toReveal);

        //Обходим новый цикл и расширяем toReveal рекурсивно
        int i = 0;
        currentVertex = start;
        for (Graph.Edge e : toReveal){
            List<Graph.Edge> toAdd = findEulerCycle(graph, newPath, currentVertex);
            result.addAll(index+i, toAdd);//Из каждой вершины найденного цикла ищем новый цикл
            newPath.addAll(toAdd);
            int size = toAdd.size();
            i++;
            currentVertex = e.getBegin() == currentVertex ? e.getEnd() : e.getBegin();//Смещаем указатель на текущую вершину
        }

        return result;
    }

    public static List<Graph.Edge> findEulerLoop(Graph graph) {
        Graph.Edge currentEdge;
        if (graph.getEdges().isEmpty()) return Collections.emptyList();//Проверка на пустой граф

        //Условие Эйлера
        for (Graph.Vertex v : graph.getVertices()) {
            if (graph.getConnections(v).size() % 2 == 1) return Collections.emptyList();
        }

        currentEdge = graph.getEdges().iterator().next();//Какая-нибудь грань
        return findEulerCycle(graph, Collections.emptyList(), currentEdge.getBegin());
    }

    /**
     * Минимальное остовное дерево.
     * Средняя
     *
     * Дан граф (получатель). Найти по нему минимальное остовное дерево.
     * Если есть несколько минимальных остовных деревьев с одинаковым числом дуг,
     * вернуть любое из них. Веса дуг не учитывать.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Ответ:
     *
     *      G    H
     *      |    |
     * A -- B -- C -- D
     * |    |    |
     * E    F    I
     * |
     * J ------------ K
     */
    public static Graph minimumSpanningTree(Graph graph) {
        throw new NotImplementedError();
    }

    /**
     * Максимальное независимое множество вершин в графе без циклов.
     * Сложная
     *
     * Дан граф без циклов (получатель), например
     *
     *      G -- H -- J
     *      |
     * A -- B -- D
     * |         |
     * C -- F    I
     * |
     * E
     *
     * Найти в нём самое большое независимое множество вершин и вернуть его.
     * Никакая пара вершин в независимом множестве не должна быть связана ребром.
     *
     * Если самых больших множеств несколько, приоритет имеет то из них,
     * в котором вершины расположены раньше во множестве this.vertices (начиная с первых).
     *
     * В данном случае ответ (A, E, F, D, G, J)
     *
     * Если на входе граф с циклами, бросить IllegalArgumentException
     *
     * Эта задача может быть зачтена за пятый и шестой урок одновременно
     */
    public static Set<Graph.Vertex> largestIndependentVertexSet(Graph graph) {
        throw new NotImplementedError();
    }

    /**
     * Наидлиннейший простой путь.
     * Сложная
     *
     * Дан граф (получатель). Найти в нём простой путь, включающий максимальное количество рёбер.
     * Простым считается путь, вершины в котором не повторяются.
     * Если таких путей несколько, вернуть любой из них.
     *
     * Пример:
     *
     *      G -- H
     *      |    |
     * A -- B -- C -- D
     * |    |    |    |
     * E    F -- I    |
     * |              |
     * J ------------ K
     *
     * Ответ: A, E, J, K, D, C, H, G, B, F, I
     */
    public static Path longestSimplePath(Graph graph) {
        throw new NotImplementedError();
    }
}
