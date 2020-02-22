package Tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CollectionTasks {
    public static ArrayList<Integer> sortedArrayList(ArrayList<Integer> array) {
        ArrayList<Integer> sortedArray = new ArrayList<>(array);
        for (int i = 0; i < array.size() - 1; i++) {
            if (sortedArray.get(i) < sortedArray.get(i + 1)) {
                int tmp = sortedArray.get(i);
                sortedArray.set(i, sortedArray.get(i + 1));
                sortedArray.set(i + 1, sortedArray.get(tmp));
            }
        }
        return sortedArray;
    }

    public static int sumOfArray(List<Integer> array) {
        int sum = 0;
        for (int i = 0; i < array.size(); i++) {
            sum += array.get(i);
        }
        return sum;
    }

    public static boolean checkForElemMap(Map<Integer, Integer> map, int elem) {
        return map.containsValue(elem);
    }

    public static void putElemAtStart(LinkedList<Integer> list, int elem) {
        list.addFirst(elem);
    }

    public static void putElemAtEnd(LinkedList<Integer> list, int elem) {
        list.addLast(elem);
    }

    public static boolean checkForElemArrayList(ArrayList<Integer> array, int elem) {
       return array.contains(elem);
    }

}
