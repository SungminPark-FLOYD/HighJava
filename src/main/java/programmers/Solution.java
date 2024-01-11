package programmers;

import java.util.*;

public class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> answer = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();

        for(int i =0; i < progresses.length; i++) {
            if((100 - progresses[i])%speeds[i] == 0) {
                q.add((100-progresses[i]) / speeds[i]);
            }
            else {
                q.add((100-progresses[i])/speeds[i] + 1);
            }
        }

        int x = q.poll();
        int count = 1;
        while(!q.isEmpty()) {
            if(x >= q.peek()){
                count++;
                q.poll();
            }
            else {
                answer.add(count);
                count = 1;
                x = q.poll();
            }
        }
        answer.add(count);

        int[] res = new int[answer.size()];
        for(int i=0; i < res.length; i++) {
            res[i] = answer.get(i);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] progresses = {93, 30, 55};
        int[] speed = {1, 30, 5};

       int[] answer = new Solution().solution(progresses, speed);
        System.out.println(answer.toString());
    }
}
