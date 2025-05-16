package org.self.yahoo.misc;

public class Recursion {
    public static void main(String[] args) {
        sayHi("main");
    }
    private static void sayHi(String msg) {
        System.out.println(msg);
        sayHi("pre");
        sayHi("post");
    }
}
