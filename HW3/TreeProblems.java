/*
 * Ioannis Giannopoulos, Section 1
 *
 * This java file contains several simple tree problems that need to be
 * codified. These routines  must use the TreeMap and TreeSet library
 * classes from the Java Collection Framework.
 *
 */

import java.util.*;

public class TreeProblems {

    /**
     * Method different()
     *
     * Given two TreeSets of integers, return a TreeSet containing all elements
     * that are NOT in both sets. In other words, return a TreeSet of all the
     * elements that are in one set but not the other.
     */

    public static Set<Integer> different(Set<Integer> setA, Set<Integer> setB) {
        // create a union between setA and setB
        TreeSet<Integer> tree1 = new TreeSet<>(setA);
        tree1.addAll(setB);

        // removes any values not found in b
        TreeSet<Integer> tree2 = new TreeSet<>(setA);
        tree2.retainAll(setB);

        // remove all elements that intersect from tree1
        tree1.removeAll(tree2);
        return tree1;
    }


    /**
     * Method removeEven()
     *
     * Given a treeMap with the key as an integer, and the value as a String,
     * remove all <key, value> pairs where the key is even.
     */

    public static void removeEven(Map<Integer, String> treeMap) {
        // create a list to store all the keys to remove
        List<Integer> keysToRemove = new ArrayList<>();
        // iterate through the map's keys
        for (Integer key : treeMap.keySet()) {
            // check if key is even
            if (key % 2 == 0) {
                keysToRemove.add(key);
            }
        }
        // remove all the keys in the tree map using the list of keys to remove
        treeMap.keySet().removeAll(keysToRemove);
    }


    /**
     * Method treesEqual()
     *
     * Given two treeMaps, each with the key as an integer, and the value as a String,
     * return a boolean value indicating if the two trees are equal or not.
     */

    public boolean treesEqual(Map<Integer, String> tree1,Map<Integer, String> tree2 ) {
        return tree1.equals(tree2);
    }

} // end treeProblems class
