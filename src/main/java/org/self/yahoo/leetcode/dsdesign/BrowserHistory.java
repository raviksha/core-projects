package org.self.yahoo.leetcode.dsdesign;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class BrowserHistoryV1 {
    Stack<String> fwdQueue = new Stack<>();
    Stack<String> backQueue = new Stack<>();

    String currentUrl;

    public BrowserHistoryV1(String homepage) {
        this.currentUrl = homepage;
    }

    public void visit(String url) {
        fwdQueue.clear();
        backQueue.push(currentUrl);
        this.currentUrl = url;
    }

    public String back(int steps) {
        steps = Math.min(steps, backQueue.size());

        while ((steps > 0)) {
            fwdQueue.push(currentUrl);
            currentUrl = backQueue.pop();
            steps--;
        }
        return currentUrl;
    }

    public String forward(int steps) {
        steps = Math.min(steps, fwdQueue.size());

        while (steps > 0) {
            backQueue.push(currentUrl);
            currentUrl = fwdQueue.pop();
            steps--;
        }
        return currentUrl;
    }
}

class BrowserHistoryV2 {
    int counter = 0;
    List<String> urlList = new ArrayList<>();

    public BrowserHistoryV2(String homepage) {
        urlList.add(homepage);
        counter++;

    }

    public void visit(String url) {
        urlList = urlList.subList(0, counter);
        urlList.add(url);
        counter++;
    }

    public String back(int steps) {
        steps = Math.min(steps, counter);

        while (steps > 0 && counter > 1) {
            counter--;
            steps--;
        }
        return urlList.get(counter - 1);

    }

    public String forward(int steps) {
        int fwdHops = urlList.size() - counter;
        steps = Math.min(steps, fwdHops);

        while (steps > 0) {
            counter++;
            steps--;
        }
        return urlList.get(counter - 1);
    }
}

public class BrowserHistory {
    public static void main(String[] args) {

        // Leet code 1472. Design Browser History
        /*
            Approach is using two stacks, one each for Forward and Back browser history

            Time Complexity:
                1. visit(): O(1)
                2. back(): O(s): s = number of steps
                3. forward(): O(s): s = number of steps


             Space complexity: O(n + n) : n = number of browser history revisions
                                Concludes: O(n), after removing the constant factor

         */
        testBrowserHistoryV1();

        /*
            Uses the approach of having a single link list with a pointer
            This avoids maintaining two stacks compared to one list to store the browsing history

            Time complexity:
                1. visit(): O(1) : No change compared to BrowserHistoryV1
                2. back(): O(1): Constant time for look up the LinkedList using index. Better than O(s) as compared to BrowserHistoryV1
                3. forward(): O(1): Constant time for LinkList lookup using index. Better than O(s) as compared to BrowserHistoryV1


             Space complexity:
                O(n) : Extra compute space to store all the n browser history. Better than O(n + n) of BrowserHistoryV1
                       which was using 2 stacks

         */

        testBrowserHistoryV2();

    }

    private static void testBrowserHistoryV2() {
        //["BrowserHistory","visit",   "back",   "back",    "forward",  "forward",    "visit",         "visit",     "back"]
        //[["zav.com"],    ["kni.com"], [7],      [7],        [5],         [1],     ["pwrrbnw.com"],["mosohif.com"], [9]]
        // [  null,          null,    "zav.com",  "zav.com", "kni.com",  "kni.com",    null,            null,       "zav.com"]
        System.out.println("testBrowserHistoryV2: ");
        BrowserHistoryV2 browserHistory = new BrowserHistoryV2("zav.com");
        browserHistory.visit("kni.com");
        System.out.println(browserHistory.back(7).equals("zav.com"));
        System.out.println(browserHistory.back(7).equals("zav.com"));
        System.out.println(browserHistory.forward(5).equals("kni.com"));  // zav.com
        System.out.println(browserHistory.forward(1).equals("kni.com"));  // zav.com
        browserHistory.visit("pwrrbnw.com");
        browserHistory.visit("mosohif.com");
        System.out.println(browserHistory.back(9).equals("zav.com"));
    }

    private static void testBrowserHistoryV1() {
        // ["BrowserHistory","visit",         "visit",         "visit",    "back", "back", "forward",  "visit",        "forward","back","back"]
        // ["leetcode.com"],["google.com"],["facebook.com"],["youtube.com"],[1],   [1],     [1]  ,[   "linkedin.com"],   [2],     [2],   [7]]
        System.out.println("testBrowserHistoryV1: ");
        BrowserHistoryV1 browserHistory = new BrowserHistoryV1("zav.com");
        browserHistory.visit("kni.com");
        System.out.println(browserHistory.back(7).equals("zav.com"));
        System.out.println(browserHistory.back(7).equals("zav.com"));
        System.out.println(browserHistory.forward(5).equals("kni.com"));
        System.out.println(browserHistory.forward(1).equals("kni.com"));
        browserHistory.visit("pwrrbnw.com");
        browserHistory.visit("mosohif.com");
        System.out.println(browserHistory.back(9).equals("zav.com"));

    }
}
