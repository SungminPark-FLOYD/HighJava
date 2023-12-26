package org.example;

import java.util.Queue;
import java.util.Stack;

public class StackTest {
    public static void main(String[] args) {
        Browser b= new Browser();

        b.history();
        b.goURL("1. 네이버");
        b.history();

        b.goURL("2.야후");
        b.history();
        b.goURL("3. 구글");
        b.history();
        b.goURL("4. 다음");
        b.history();

        System.out.println("뒤로 가기 후..");
        b.goBack();
        b.history();

        System.out.println("앞으로 가기 후...");
        b.goForward();
        b.history();

        System.out.println("새로운 사이트 접속..");
        b.goURL("5.네이트");
        b.history();
    }

}

//웹 브라우저의 앞으로 가기 뒤로가기 기능 구현
class Browser {
    private Stack<String> back;     //방문내역 저장
    private Stack<String> forward;  //다음 방문 내역 저장
    private String curURL;

    public Browser() {
        back = new Stack<String>();
        forward = new Stack<String>();
        curURL = "";
    }

    public void goURL(String url) {
        System.out.println(url + " 사이트에 접속 합니다...");
        if(curURL != null && !"".equals(curURL)) back.push(curURL);

        curURL = url;
    }


    //뒤로가기 ==> 현재 페이지를 forward스택에 추가하고,
    // back스택에서 주소를 꺼내와 현재 페이지로 변경한다,
    public void goBack() {
        if(!back.isEmpty()) {
            forward.push(curURL);
            curURL = back.pop();
        }
    }

    public void goForward() {
        if(!forward.isEmpty()) {
            back.push(curURL);
            curURL = forward.pop();
        }
    }

    public void history() {
        System.out.println("-------------");
        System.out.println("    방문기록");
        System.out.println("-------------");
        System.out.println("back => " + back);
        System.out.println("현재 => " + curURL);
        System.out.println("forward => "+ forward);
    }

}
