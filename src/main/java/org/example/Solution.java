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
        int castTime = bandage[0]; // 시전 시간
        int healingPerSecond = bandage[1]; // 초당 회복량
        int additionalHealing = bandage[2]; // 추가 회복량

        int maxHealth = health; // 최대 체력

        int preAttackTime = attacks[0][0]; // 이전 공격 시간
        int consecutiveTime = 0; // 연속 성공 시간

        // attacks 순회 (attacks의 길이만큼)
        for (int i = 0; i < attacks.length; i++) {
            int attackTime = attacks[i][0]; // 공격 시간
            int damage = attacks[i][1]; // 피해량

            // (현재 공격 시간 - 이전 공격 시간)의 텀 동안에 체력 회복
            int timeBetween = attackTime - preAttackTime - 1;
            if (timeBetween > 0) {
                health += (timeBetween * healingPerSecond); // 초당 회복

                // 추가 회복을 연속 성공 시간에 기반하여 계산
                health += Math.min((timeBetween / castTime) * additionalHealing, consecutiveTime * additionalHealing);

                if (health > maxHealth) {
                    health = maxHealth;
                }
            }

            // 체력에 데미지(피해량) 적용
            health -= damage;

            // 체력이 0이하 일 시 -1 반환
            if (health <= 0) {
                return -1;
            }

            // 연속 성공 시간 갱신
            consecutiveTime = (attackTime - preAttackTime == 1) ? consecutiveTime + 1 : 0;

            // 이전 시간 공격
            preAttackTime = attackTime;
        }

        // 남은 체력 반환
        return health;
    }
}

