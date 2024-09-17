
/*
 * Ioannis Giannopoulos, Section 1
 *
 * Homework # 1 (Programming Assignment). This Java class defines some basic
 * manipulation operations on Linked-Lists and Stacks.
 *
 * Additionally, there are two algorithm analysis methods where you need
 * to return a specified number as provided in comments of each method indicating
 * the complexity of the code shown. The testing routine will be looking for a
 * specific number returned.
 */

import java.util.Stack;

public class HW1 {

    /*
     * Class LinkedList
     *
     * This class builds a singly linked list. Each node of the linked-list
     * contains a single integer values.
     *
     * Methods:
     *  - void   sortInserted(val)     - Inserts value 'val' into the linked-list in
     *                                   sorted fashion
     *  - void   removeElementsLT(val) - Removed values in the linked-list that are less
     *                                   than 'val'
     *  - void   removeElement(val)    - Removes all values in the linked list of
     *                                   value 'val'
     *  - String toString()            - coverts and returns the linked-lists as a string
     *                                   delimited by brackets []
     *
     */

    static class LinkedList {
        static class Node {
            int data;
            Node next;

            Node(int d)       // Constructor
            {
                data = d;
                next = null;
            }
        }
        Node head;            // head of Linked-list

        /*
         * Method sortedInsert() - this method will insert a new node to the
         * linked list containing the value specific in teh parameter 'data'.
         * The newly inserted node will be inserted in sorted order within
         * the linked-list.
         *
         */
        public void sortedInsert ( int data )
        {
            Node new_node = new Node(data);

            new_node.next = null;

            // Special case for head node.
            if (this.head == null || head.data >= new_node.data) {
                new_node.next = head;
                head = new_node;
            } else {
                // locate the node before the point of insertion
                Node current = this.head;

                // Identify where to place the item to insert
                while (current.next != null && current.next.data < data) {
                    current = current.next;
                }
                new_node.next = current.next;
                current.next = new_node;
            }

            return;
        }


        /*
         * Method removeElementsLT() - the method removes all nodes that contain a
         * value that is less than the provided parameter 'ltValue'.
         *
         * The method will invoke the method removeElements for each element
         * found in the linked-list that is less than the parameter value passed.
         * 
         */
        public void removeElementsLT ( int ltValue ){
            Node temp = head; // start from the beginning of the linkedlist
            // iterate through the linkedlist
            while (temp != null) {
                // check if data at node is less than or equal to the less than value
                if (ltValue >= temp.data) {
                    // remove all nodes that are equal or less than the current nodes' data
                    removeElement(temp.data);
                }
                // iterate to the next node
                temp = temp.next;
            }
        }

        /*
         * Method removeElement() - the method removes all nodes that contain a
         * value equal to the value the provided parameter 'value'.
         *
         */
        public void removeElement ( int value ){
            // special case for handling nodes starting from the head
            while (head != null && head.data == value) {
                head = head.next;
            }

            Node current = head; // start with a new head
            // iterate through the rest of the linkedlist
            while (current.next != null) {
                // check if the next node contains the value to be removed
                if (current.next.data == value) {
                    // remove the node by linking the current node to the node after the next node
                    current.next = current.next.next;
                }
                else {
                    // move the pointer to the next node
                    current = current.next;
                }
            }
        }

        /*
         * Method toString() - this is a helper method for printing / constructing
         * a string object from the linked-list.
         *
         */
        public String toString () // Method to output the LinkedList as a String
        {
            String output = "[";
            Node currNode = this.head;
            while (currNode != null) {
                output += currNode.data + " ";
                currNode = currNode.next;
            }
            return output.trim() + "]";
        }

    }



    /*
     * Class Stacks
     *
     * This class utilizes the Java Common Framework Library Stack class.
     *
     * The intent of this class is to write methods which utilize the Java
     * library's Stack class. You need to utilize this library class in
     * your solution.
     *
     * Methods:
     *  - boolean isPalindrome(string)   - method returns true or false if 'string'
     *                                     is a palindrome
     *  - int     findlargestK(stack, k) - method accepts a stack and returns the
     *                                     the largest index in the stack containing
     *                                     value 'k' (stack index starts at 0)
     *
     */

    static class Stacks {

        /*
         * Method isPalindrome() - This method will return the boolean value 'true'
         * or 'false' on if the passed in parameter string is a palindrome or not.
         *
         * The routine should be case insensitive! Meaning 'RaCe cAr' is a palindrome.
         * Moreover, spaces are ignored, so both 'race car' and 'racecar' are plaindromes.
         *
         */
        public static boolean isPalindrome(String input) {
            Stack<Character> stack = new Stack<>();

            // format the input by removing all spaces and convert the input to lower case
            input = input.toLowerCase().replaceAll("\\s+", "");

            // get the length and set loop index
            int n = input.length(), i = 0;

            // add the first half of the string to the stack
            while ((n / 2) > i) {
                stack.push(input.charAt(i));
                i++;
            }

            // skip one index if it's odd, as the middle index is already a palindrome
            if (n % 2 == 1) {
                i++;
            }

            // compare the first to the second half of the string
            while (n > i) {
                // check if the characters do not correspond with each other
                if (stack.peek() != input.charAt(i)) {
                    return false; // not a palindrome
                }
                // remove top character and increment
                stack.pop();
                i++;
            }
            return true;
        }


        /*
         * Method findLargestk() - This method will return the largest index
         * position in the stack for the value specified by the parameter 'k'.
         *
         * Note that the bottom of the stack is index location 0. So if you push
         * on to the stack the values 3 4 9 4 4 7 4, in that order. And you pass the
         * value '4' for the parameter k, then the largest index position is index
         * location 6.
         *
         */
        public static int findLargestK(Stack<Integer> stack, int k) {
            int largestIndex = -1; // initialize to -1 indicating 'k' is not found yet
            int tempIndex = 0; // index to keep track of position in the stack
            Stack<Integer> tempStack = new Stack<>(); // temporary stack to hold original elements
            int n = stack.size() - 1; // maximum index of the stack

            // find the largest index where that's equal to k
            while (!stack.isEmpty()) {
                int val = stack.pop(); // set current value to most recently removed element from stack
                // check if the current value is equal to k
                if (val == k) {
                    // update the largest index if it is larger than the current index
                    largestIndex = Math.max(largestIndex, n - tempIndex); // use n - tempIndex because we are iterating starting from n
                }
                // store the value in the tempStack and increment tempIndex
                tempStack.push(val);
                tempIndex++;
            }

            // restore the original values for the stack by transferring the values from tempStack
            while (!tempStack.isEmpty()) {
                // move the current element back to the stack
                stack.push(tempStack.pop());
            }

            return largestIndex;
        }
    }


    /*******************************
     *
     * Algorithm Analysis
     *
     * Below are two methods, algorithmAnalysis1 and algorithmAnalysis2.
     * Modify the return statement to return the correct option number.
     *
     *********************************/

    public static int algorithmAnalysis1(int n, int m) {
        int a = 0, b = 0;

        for (int i=0; i < n; i++)
            a+= Math.random();

        for (int j=0; j < m; j++)
            b+= Math.random();

        /*
         * Select the correct option listed below:
         *   1. O(N * M) time, O(1) space
         *   2. O(N + M) time, O(N + M) space
         *   3. O(N + M) time, O(1) space
         *   4. O(N * M) time, O(N + M) space
         *
         * TODO: return the answer (which option is correct), in the return statement
         */

        return 3;
    }


    public static int algorithmAnalysis2(int n) {
        int i, j, k = 0;
        for (i = n/2; i <= n; i++)
            for ( j = 2; j <= n; j = j*2 )
                k+= n/2;

        /*
         * Select the correct option listed below:
         *   1. O(N) time
         *   2. O(N log N) time
         *   3. O(N^2) time
         *   4. O(N^2Log n) time
         *
         * TODO: return the answer (which option is correct), in the return statement
         */

        return 2;
    }

}
