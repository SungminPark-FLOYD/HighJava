package Set;
import java.util.*;

//Set을 이용하여 숫자 야구 게임 프로그램을 작성하시오
//컴퓨터의 숫자는 난수를 이용하여 구한다
//스트라이크는 s , 볼은 B로 출력한다.
public class BaseBallTest {
    private static  Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        //난수 생성
        Set<Integer> baseList = new HashSet<Integer>();

        while(baseList.size() < 3) {
            baseList.add((int)(Math.random()*9+1));
        }

        ArrayList<Integer> com = new ArrayList<>(baseList);
        System.out.println(com);

        ArrayList<Integer> player = new ArrayList<>();

        int a, b ,c;
        int count = 0;

        while(true) {
            int strike= 0;
            int ball = 0;

            count++;

            do {
                //숫자 입력
                System.out.println("숫자 입력 >>");
                a = sc.nextInt();
                b = sc.nextInt();
                c = sc.nextInt();
                if(a==b || a==c || b==c ) System.out.println("중복되는 숫자 제외");
            }while (a==b || a==c || b==c );


            player.add(a);
            player.add(b);
            player.add(c);

            //스트라이크/ 볼 판단
            for(int i = 0; i < com.size(); i++) {
                for(int j = 0; j < player.size(); j++) {
                    if(com.get(i).equals(player.get(j))) {
                        if(i == j) strike++;
                        else ball++;
                    }
                }
            }
            System.out.println(strike + "S" + ball + "B");


            if(strike == 3)  break;
            strike= 0;
            ball = 0;
        }

        System.out.println(count + "번 시도 함.");

    }

}
