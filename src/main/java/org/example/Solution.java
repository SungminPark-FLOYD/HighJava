package org.example;

public class Solution {
    public static void main(String[] args) {
        int[] bandage = {5, 1, 5};
        int health = 30;
        int[][] attacks = {{2, 10}, {9, 15}, {10, 5}, {11, 5}};

        int result = solution(bandage, health, attacks);
        System.out.println(result);
    }

    public static int solution(int[] bandage, int health, int[][] attacks) {
        int currentTime = 0;
        int consecutiveTime = 0;
        int currentHealth = health;

        for (int[] attack : attacks) {
            int attackTime = attack[0];
            int damage = attack[1];

            // 몬스터의 공격 시간까지 붕대 감기를 시전
            while (currentTime < attackTime) {
                // 붕대 감기 중인 경우
                if (consecutiveTime > 0) {
                    currentHealth += bandage[1]; // 1초당 회복
                    consecutiveTime--;
                }

                // 캐릭터의 체력이 최대 체력을 초과하지 않도록 조정
                currentHealth = Math.min(currentHealth, health);

                // 캐릭터가 체력이 0 이하가 되면 즉시 종료
                if (currentHealth <= 0) {
                    return -1;
                }

                currentTime++;
            }

            // 몬스터의 공격을 받아서 붕대 감기가 취소됨
            currentHealth -= damage;

            // 캐릭터의 체력이 0 이하가 되면 즉시 종료
            if (currentHealth <= 0) {
                return -1;
            }

            // 붕대 감기 시전 시간과 연속 성공 시간 초기화
            currentTime++;
            consecutiveTime = bandage[0] - 1;
        }

        // 남은 시간 동안 붕대 감기를 시전
        while (consecutiveTime > 0) {
            currentHealth += bandage[1]; // 1초당 회복
            consecutiveTime--;

            // 캐릭터의 체력이 최대 체력을 초과하지 않도록 조정
            currentHealth = Math.min(currentHealth, health);

            // 캐릭터가 체력이 0 이하가 되면 즉시 종료
            if (currentHealth <= 0) {
                return -1;
            }

            currentTime++;
        }

        return currentHealth;
    }
}
