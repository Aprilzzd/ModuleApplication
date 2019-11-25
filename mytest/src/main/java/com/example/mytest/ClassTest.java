package com.example.mytest;

public class ClassTest {

    static class A{
        static {
            System.out.println("staticA");
        }

        public A() {
            System.out.println("A()");
        }
    }

    static class B extends A{
        static {
            System.out.println("staticB");
        }

        public B() {
            System.out.println("B()");
        }
    }

    public static void main(String[] args) {
        new B();
    }
}
