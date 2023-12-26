package org.example;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StackQueue {
    //Stack : first in last out
    /*자료 입력 : push(입력값)
    * 자료 출력 : pop() => 자료를 꺼내온 후 꺼내온 자료를 Stack에서 삭제한다
    *            peek() => 자료를 삭제없이 꺼내온다
    * */
    //Queue : first in first out
    /*자료 입력 : offer(입력값)
    * 자료 출력 : poll() => 자료를 꺼내오고 꺼내온 자료를 Queue에서 삭제한다.
    *            peek() => 삭제없이 자료를 거내온다.*/

    public static void main(String[] args) {

        Stack<String> stack = new Stack<>();

        stack.push("asd");
        stack.push("aasv");
        stack.push("zxc");
        stack.push("bvasd");

        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack);

        Queue<String> queue = new LinkedList<String>();

        queue.offer("홍길동");
        queue.offer("ㅁㄴㅇ");
        queue.offer("ㅁㄴㅇㅁㄴ");
        queue.offer("돌쇠");
        System.out.println(queue);
        System.out.println(queue.poll());
    }
}
