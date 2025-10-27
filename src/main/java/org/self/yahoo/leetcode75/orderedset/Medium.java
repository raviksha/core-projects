package org.self.yahoo.leetcode75.orderedset;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class MyCalendarV1 {

    List<Event> eventList = new ArrayList<>();

    MyCalendarV1() {}

    public boolean book(int s2, int e2) {
        Event event = new Event(s2, e2);

        if (eventList.isEmpty()) {
            eventList.add(event);
            return true;
        }

        for (Event currEvent : eventList) {
            int s1 = currEvent.start;
            int e1 = currEvent.end;

            /*
                1. Events start before the current event (eStart) and end before the  current event (eEnd)
                2. The Event starts in between the current event and ends after the current event
             */

            /*
                e1 > s2 => Checks if the new event s2 starts before the current one ends
                s1 > e2 => Checks if the new events e2 ends after s1 starts
                Example of overlap which covers both the cases
                Existing: [20, 30)
                New:      [10, 20)
             */

            if (e1 > s2 && e2 > s1) {
                return false;
            }

        }
        eventList.add(event);
        return true;
    }

}

class MyCalendarV3 {

    MyCalendarV3() {
    }

    TreeMap<Integer, Integer> eventMap = new TreeMap<>();

    public boolean book(int start, int end) {

        // Get the previous event that ends before start
        Integer prev = eventMap.floorKey(start);

        // Get the next event that begins after start
        Integer next = eventMap.ceilingKey(start);

        if (prev != null && eventMap.get(prev) > start) {
            return false;
        }

        if (next != null && end > next) {
            return false;
        }

        eventMap.put(start, end);
        return true;

    }

}

class MyCalendarV2 {
    MyCalendarV2() {
    }

    Map<Integer, Integer> eventMap = new TreeMap<>();

    public boolean book(int start, int end) {
        eventMap.put(start, eventMap.getOrDefault(start, 0) + 1);
        eventMap.put(end, eventMap.getOrDefault(end, 0) - 1);

        int sum = 0;

        for (int val : eventMap.values()) {
            sum += val;

            if (sum > 1) {
                eventMap.put(start, eventMap.getOrDefault(start, 0) - 1);
                eventMap.put(end, eventMap.getOrDefault(end, 0) + 1);
                return false;
            }
        }
        return true;
    }
}


public class Medium {
    public static void main(String[] args) {
        System.out.println("Ordered Set ....... Medium...");

        // Leet code 729. My Calendar I

        /*
            Uses the approach of storing the calendar events i.e start and end time in a TreeMap which internally will sort the entries
            Each time when a new entry is process it checks for 2 things:
            1.      s1 ------------- e1
                           s2 --------------e2   => s1 < s2 < e1

            2.          s1 -----------------e1
                   s2------------------e2        => s1 < e2 < e1


            Overlapping events falls under 2 basic scenarios
                1: Event s2 starting prior to s1 and ends before e1 => s1 < e2 < e1
                2. Event s2 starting after s1 but before e1 => s1 < s2 < e1
         */

        /*
            Time complexity: O(n ^ 2): For each booking it traverses through the total n booking listings

            Space complexity: O(n): Extra space to store n bookings in the list
         */
        testMyCalendarIV1();

        /*
            Time complexity: O(n ^ 2)
                              Time complexity to add an element in the Tree Map: O(log n)
                              Time complexity to traverse all the elements on the booking list: O(n)
                              Time complexity to add one element: O(log n) + O(n) => O(n)
                              Final time complexity to add n bookings: O(n * n) => O(n ^ 2)

            Space complexity: O(n): Extra space to store the 2n booking entries, one entry each for start and end for each booking
         */
        testMyCalendarIV2();

        /*
            Approach: Implementation uses a TreeMap which internally used a Red Black Tree which is a self-balancing binary tree
                      All operations of the tree such as put(), get() and remove() takes O(log n) compute time
            Time complexity: O(log n): Red Black Tree t/c operations

            Space complexity: O(n): Extra space required for the eventMap to store all the n calendar events
         */

        testMyCalendarIV3();

    }

    private static void testMyCalendarIV2() {
        MyCalendarV2 myCalendarV2 = new MyCalendarV2();
        System.out.println("myCalendarV2:");
        System.out.println(myCalendarV2.book(10, 20) + ": true");
        System.out.println(myCalendarV2.book(15, 25) + ": false");
        System.out.println(myCalendarV2.book(20, 30) + ": true");
    }

    private static void testMyCalendarIV3() {
        MyCalendarV3 myCalendarV3 = new MyCalendarV3();
        System.out.println("myCalendarV3:");
        System.out.println(myCalendarV3.book(10, 20) + ": true");
        System.out.println(myCalendarV3.book(15, 25) + ": false");
        System.out.println(myCalendarV3.book(20, 30) + ": true");
    }

    private static void testMyCalendarIV1() {
        // [[],[10,20],[15,25],[20,30]]
        MyCalendarV1 myCalendarV1 = new MyCalendarV1();
        System.out.println("testMyCalendar1V1:");
        System.out.println(myCalendarV1.book(10, 20) + ": true");
        System.out.println(myCalendarV1.book(15, 25) + ": false");
        System.out.println(myCalendarV1.book(20, 30) + ": true");
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







//
//            /*
//                Two conditions define an overlapping event
//
//                1. Events starts before and ends in between an existing slot
//                2. Events starts in between an existing slot and ends after the slot
//             */
//
//class MyCalendar {
//    TreeMap<Integer, Integer> eventMap = new TreeMap<>();
//    public MyCalendar() {
//    }
//
//    public boolean book(int startTime, int endTime) {
//        Integer prev = eventMap.floorKey(startTime); // Get the previous calendar event which is closest to the start time of the event;
//        Integer next = eventMap.ceilingKey(startTime); // Get the future calendar event which is closest to the start tine of the event;
//
//        // int prevEndTime = eventMap.get(prev);
//        // int nextEndTime = eventMap.get(next);
//
//        if (prev != null && eventMap.get(prev) > startTime || next != null && next < endTime) {
//            return false;
//        }
//
//        eventMap.put(startTime, endTime);
//        return true;
//    }
//}
//
////  if ((startTime <= sTime && endTime > sTime) || (startTime >= sTime && startTime < eTime)) {
////         return false;
////     }
//
///**
// * Your MyCalendar object will be instantiated and called as such:
// * MyCalendar obj = new MyCalendar();
// * boolean param_1 = obj.book(startTime,endTime);
// */