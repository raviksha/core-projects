package org.self.yahoo.leetcode.orderedset;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Medium {

    private static void testMyCalendar1V1() {
        // [[],[10,20],[15,25],[20,30]]
        MyCalendarV1 myCalendarV1 = new MyCalendarV1();
        System.out.println("testMyCalendar1V1:");
        System.out.println(myCalendarV1.book(10, 20) + ": true");
        System.out.println(myCalendarV1.book(15, 25) + ": false");
        System.out.println(myCalendarV1.book(20, 30) + ": true");

    }

    private static void testMyCalendar1V2() {
        // [[],[10,20],[15,25],[20,30]]
        MyCalendarV2 myCalendarV2 = new MyCalendarV2();
        System.out.println("testMyCalendar1V2:");
        System.out.println(myCalendarV2.book(10, 20) + ": true");
        System.out.println(myCalendarV2.book(15, 25) + ": false");
        System.out.println(myCalendarV2.book(20, 30) + ": true");
    }

    private static void testMyCalendar1V3() {
        // [[],[10,20],[15,25],[20,30]]
        MyCalendarV3 myCalendarV3 = new MyCalendarV3();
        System.out.println("testMyCalendar1V3:");
        System.out.println(myCalendarV3.book(10, 20) + ": true");
        System.out.println(myCalendarV3.book(15, 25) + ": false");
        System.out.println(myCalendarV3.book(20, 30) + ": true");
    }
    public static void main(String[] args) {

        System.out.println("Medium: Ordered Set");

        // Leet code 729. My Calendar I

        /*
            Uses a brute force approach

            Time complexity: O(n ^ 2): Given n events, the control loops through all the m events stored in the list : O(n * m)
                             O(n * m) => concludes to O(n ^ 2)

            Space complexity: O(n): Extra memory to store all n events in a List
         */

        testMyCalendar1V1();

        /*
            Uses a approach uf using a sorted Map to count for overlapping events.

            Time complexity: O(n ^ 2)
                             Time complexity of inserting elements in the sorted TreeMap: O(log n)
                             Time complexity to iterate over n elements to compute sum; O(n)
                             Dominating: O(n) for each element = O(n ^ 2) for n elements in the list

            Space complexity : O(n): Extra space to store n events in the sorted map
         */
        testMyCalendar1V2();

        /*
            Uses the approach of storing the calendar events i.e start and end time in a TreeMap which internally will sort the entries
            Each time when a new entry is process it checks for 2 things:
            1.      s1 ------------- e1
                           s2 --------------e2   => s1 < s2 < e1

            2.          s1 -----------------e1
                   s2------------------e2        => s1 < e2 < e1


            Time complexity: O(log n)
                            O(log n): Adding elements to the sorted TreeMap which uses a Red Black self-balancing BST
                            put(), get(), remove() operations of a Rad Black tree works with O(log n) time complexity
                            Concluding t/c: O(log n)

            Space complexity: O(n): Event map storing calendar events for all n entries
         */
        testMyCalendar1V3();


        //  Leet code 731. My Calendar II

        /*
            Uses the approach of maintaining a TreeMap of Red Black self-balancing trees
            Maintains a bit map of each calendar start amd end time event
            At any given time if the sum of all the bit maps entries > number of allowed conflicts then return false;

            Time complexity: O(n ^ 2)
                             TreeMap operations: put(), get(), remove() : O(log n)
                             Traversing all the n values in the TreeMap: O(n)
                             Dominating t/c per calendar event: O(n) * n events = O(n ^ 2)

            Space complexity: O(n): Extra space required to store the n calendar events in the TreeMap
         */
        testMyCalendar2V1();


    }

    private static void testMyCalendar2V1() {
        //[[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
        MyCalendar2V2 myCalendarTwo = new MyCalendar2V2();
        System.out.println("testMyCalendar2V1: ...");
        System.out.println("true: " + myCalendarTwo.book(10, 20)); // return True, The event can be booked.
        System.out.println("true: " + myCalendarTwo.book(50, 60)); // return True, The event can be booked.
        System.out.println("true: " + myCalendarTwo.book(10, 40)); // return True, The event can be double booked.
        System.out.println("false: " + myCalendarTwo.book(5, 15));  // return False, The event cannot be booked, because it would result in a triple booking.
        System.out.println("true: " + myCalendarTwo.book(5, 10)); // return True, The event can be booked, as it does not use time 10 which is already double booked.
        System.out.println("true: " + myCalendarTwo.book(25, 55)); // return True, The event can be booked, as the time in [25, 40) will be double booked with the third event, the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.
    }


}

class MyCalendar2V2 {

    Map<Integer, Integer> eventMap;

    public MyCalendar2V2 () {
        eventMap = new TreeMap<>();
    }

    public boolean book(int startTime, int endTime) {
        eventMap.put(startTime, eventMap.getOrDefault(startTime, 0) + 1);
        eventMap.put(endTime, eventMap.getOrDefault(endTime, 0) - 1);

        int sum = 0;
        for (int val : eventMap.values()) {
            sum += val;

            if (sum > 2) {
                eventMap.put(startTime, eventMap.getOrDefault(startTime, 0) - 1);
                eventMap.put(endTime, eventMap.getOrDefault(endTime, 0) + 1);
                return false;
            }
        }
        return true;
    }
}


class MyCalendarV3 {

    TreeMap<Integer, Integer> eventMap;

    public MyCalendarV3 () {
        eventMap = new TreeMap<>();
    }
    /*
            1.     s1 ------------- e1
                           s2 --------------e2   => s1 < s2 < e1

            2.          s1 -----------------e1
                   s2------------------e2        => s1 < e2 < e1
     */

    public boolean book(int startTime, int endTime) {
        Integer prev = eventMap.floorKey(startTime); // Gets the closes previous calendar event
        Integer next = eventMap.ceilingKey(startTime); // Get the future next calendar event

        if (prev != null && startTime < eventMap.get(prev) || next != null && next < endTime) {
            return false;
        }
        eventMap.put(startTime, endTime);
        return true;
    }
}



class MyCalendarV2 {

    Map<Integer, Integer> eventMap;

    public MyCalendarV2 () {
        eventMap = new TreeMap<>();
    }

    public boolean book(int startTime, int endTime) {
        eventMap.put(startTime, eventMap.getOrDefault(startTime, 0) + 1);
        eventMap.put(endTime, eventMap.getOrDefault(endTime, 0) - 1);
        int sum = 0;
        for (int val : eventMap.values()) {
            sum += val;

            if (sum > 1) {
                eventMap.put(startTime, eventMap.getOrDefault(startTime, 0) - 1);
                eventMap.put(endTime, eventMap.getOrDefault(endTime, 0) + 1);
                return false; // Conflicting even found
            }
        }
        return true;
    }
}

class Event {
    int start;
    int end;
    public Event(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class MyCalendarV1 {

    List<Event> eventsL = new ArrayList<>();
    public MyCalendarV1 () {

    }

    public boolean book(int startTime, int endTime) {
        Event e = new Event(startTime, endTime);

        if (eventsL.isEmpty()) {
            eventsL.add(e);
            return true;
        }

        for (Event tmpE : eventsL) {
            int sTime = tmpE.start;
            int eTime = tmpE.end;

            /*
                Two conditions define an overlapping event

                1. Events starts before and ends in between an existing slot
                2. Events starts in between an existing slot and ends after the slot
             */

            if ((startTime <= sTime && endTime > sTime) || (startTime >= sTime && startTime < eTime)) {
                return false;
            }

        }
        eventsL.add(e);
        return true;

    }
}
