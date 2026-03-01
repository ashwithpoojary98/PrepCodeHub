package main.java.io.github.ashwithpoojary98.custom.stack;

import java.util.Arrays;

public class CustomStack {

    private int[] stack;
    private int length;

    public CustomStack() {
        this.stack = new int[10];
    }

    public CustomStack(int size) {
        this.stack = new int[size];
    }

    public int size() {
        return this.length;
    }

    public void push(int data) {

        if (length < stack.length) {
            stack[length++] = data;
        } else {
            throw new RuntimeException("Stack is Full");
        }

    }

    public int peek() {
        return stack[length - 1];
    }

    public int pop() {
        if (length == 0) {
            throw new RuntimeException(
                    "Stack is is empty");
        } else {
            int data = stack[length - 1];
            stack[length - 1] = 0;
            length--;
            return data;
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(stack, length));
    }


    @Override
    public int hashCode(){

        int result=1;
        for(int i=0;i<length;i++){
            result=31*result+stack[i];
        }
        return result;
    }

    public static void main(String[] args) {
        CustomStack customStack = new CustomStack();
        customStack.push(3);
        customStack.push(4);
        customStack.push(4);

        System.out.println(customStack);
    }

}