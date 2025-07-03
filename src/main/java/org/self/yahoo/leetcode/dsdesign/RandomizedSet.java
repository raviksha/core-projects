package org.self.yahoo.leetcode.dsdesign;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

class RandomizedSetV1 {

    Set<Integer> set;
    Random random;
    public RandomizedSetV1() {
        set = new HashSet<>();
        random = new Random();
    }

    public boolean insert(int val) {
        return set.add(val);
    }

    public boolean remove(int val) {
        return set.remove(val);
    }

    public int getRandom() {
        List<Integer> list = new ArrayList<>(set);
        return list.get(random.nextInt(list.size()));
    }

}



class RandomizedSetV2 {

    List<Integer> list;
    Map<Integer, Integer> indexMap;
    Random random;

    public RandomizedSetV2() {
        list = new ArrayList<>();
        indexMap = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        if (indexMap.containsKey(val)) {
            return false;
        }

        list.add(val);
        indexMap.put(val, list.size() -1);
        return true;
    }

    public boolean remove(int val) {
        if (!indexMap.containsKey(val)) {
            return false;
        }

        int index = indexMap.get(val);
        int lastVal = list.get(list.size() -1);
        list.set(index, lastVal);
        list.remove(list.size() -1);

        indexMap.put(lastVal, index);
        indexMap.remove(val);
        return true;
    }

    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }

}

class RandomizedSet {
    public static void main(String[] args) {
        System.out.println("RandomizedSetV1...");

        /*
            Approach: Using a Set for add() and remove() operation.
            Initializing a new List each time a random element has to be returned

            Time complexity:
                            insert(): O(1)
                            remove(): O(1): HashSet remove() takes O(1) Vs O(n log n) for TreeSet
                            random(): O(n): Copy all the n elements to an ArrayList to fetch the element at a random index
                            Drawback: getRandom() creates a new ArrayList every time, which is costly in time and memory.


            Space complexity: O(n) => Space required to store n elements on the Set and List


         */
        RandomizedSetV1 obj = new RandomizedSetV1();
        System.out.println(obj.insert(1) + " true");
        System.out.println(obj.remove(2) + " false");
        System.out.println(obj.getRandom() == 1);


        /*
            Approach: Uses a list to store the elements and a Map to store the index position of each element in the list
                      This provides index look up with O(1) constant time compared to O(n) if the whole array had ot be traversed

            Time complexity:
                            insert(): O(1): Constant time to insert an element in the List and Map
                            remove(): O(1): Constant time to remove elements from the indexMap and Arraylist based on index
                            random(): O(1): Constant time to fetch the nextRandom() followed by an index lookup of the ArrayList

            Space complexity: O(n) : Space required to store n elements in the ArrayList and HashMap

         */
        System.out.println("RandomizedSetV2....");
        RandomizedSetV2 obj2 = new RandomizedSetV2();
        System.out.println(obj2.insert(1) + " true");
        System.out.println(obj2.remove(2) + " false");
        System.out.println(obj2.getRandom() == 1);
    }
}

