package Set;

import java.util.*;

public class Lotto {
    /* 로또를 판매하는 프로그램 작성하기

 사용자는 로또를 판매할 때 판매할 금액을 입력하고
 입력한 금액에 맞게 로또번호를 출력한다.
 (단, 로또 한장의 금액은 1000원이며 최대 100장까지만 구입할 수 있고,
      거스름돈도 계산하여 출력한다.)
*/
    Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        Lotto lo = new Lotto();
        lo.select();
        System.out.println("감사합니다");
    }
    public void select() {
        boolean exit = false;
        while(!exit) {
            System.out.println("==========================");
            System.out.println("Lotto 프로그램");
            System.out.println("--------------------------");
            System.out.println("1. Lotto 구입");
            System.out.println("2. 프로그램 종료");
            System.out.println("==========================");
            System.out.print("메뉴 선택>> ");
            int sel = sc.nextInt();
            switch (sel) {
                case 1:
                    buyLotto();
                    break;
                case 2:
                    exit = true;
                    break;
                    default:
                    System.out.println("작업번호 다시 입력.");
            }
        }

    }
    public void buyLotto() {
        System.out.println(" Lotto 구입 시작");
        System.out.println("(1000원에 로또번호 하나입니다.)");
        int money = 0;
        boolean moneyChk = true;
        while(moneyChk) {
            System.out.print("금액 입력 >>");
            money = sc.nextInt();
            if(money > 100000) System.out.println("입력 금액이 너무 많습니다. 로또번호 구입 실패!!!");
            else if(money < 1000) System.out.println("입력 금액이 너무 적습니다. 로또번호 구입 실패!!!");
            else moneyChk = false;
        }

        int countLotto = money / 1000;
        int change = money % 1000;
        int count = 0;
        System.out.println("행운의 로또번호는 아래와 같습니다.");
        while(count < countLotto ) {
            System.out.print("로또번호" + (count+1) + ": ");
            setLotto();
            count++;
        }

        System.out.println("받은 금액은 " + money + "원이고 거스름돈은 " + change + "원 입니다.");
    }

    public void setLotto() {
        //난수 생성
        Set<Integer> lottoNum = new HashSet<Integer>();
        while(lottoNum.size() < 6) {
            lottoNum.add((int)(Math.random()*45+1));
        }

        ArrayList<Integer> lotto = new ArrayList<>(lottoNum);
        Collections.sort(lotto);

        System.out.println(lotto);
    }

}
