package programmers;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    private static final int[][] arr = new int[31][31];
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        System.out.println(combination(29, 13));
    }

    public static int combination(int n, int r){
        if(n == r || r == 0) return 1;

        else
            return combination(n-1, r - 1) + combination(n-1, r);
    }
}
