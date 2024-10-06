package com.example.coding.puzzle;

import java.util.Arrays;

public class Puzzle {
    /**
     n개의 퍼즐을 제한 시간에 풀어야해

     퍼즐별로 난이도와 소요시간은 정해져 있어

     숙련도에 따라 퍼즐을 풀때 틀리는 회수가 변경

     n개의 퍼즐 중 현재 퍼즐 난이도를 diff
     현재 퍼즐의 소요시간을 time_cur
     이전 퍼즐의 소요 시간을 time_prev
     숙련도를 level

     난이도 < 숙련도   틀리지는 않음    그냥 소요시간 만큼 끝

     난이도 > 숙련도 		틀린 횟수 = 난이도 - 숙련도     (틀린 횟수 * (소요시간 + 이전 소요시간 )) +소요시간

     난이도 3
     소요시간 2
     이전 소요시간 4


     1.
     숙련도 1
     틀린 회수는 2

     (2 * (2+4)) + 2

     2.
     숙련도 2
     틀린 횟수 1
     (1*(2+4)) + 2 = 8

     3.
     숙련도 3
     틀린 횟수 0
     2

     시간이 정해져 있고 숙련도의 최소값을 구하여야 한다
     */
    public static void main(String[] args) {
        System.out.println(solution(new int[]{1,5,3}, new int[]{2,4,7},30));
        System.out.println(solution(new int[]{1, 1, 1}, new int[]{1, 1, 1}, 3));
        System.out.println(solution(new int[]{1, 5, 10}, new int[]{2, 5, 10}, 1000));
        System.out.println(solution(new int[]{10, 10, 10}, new int[]{10, 10, 10}, 100));
        System.out.println(solution(new int[]{100, 100, 100}, new int[]{100, 100, 100}, 3000));
        System.out.println(solution(new int[]{1, 2, 3}, new int[]{1000, 2000, 3000}, 1000000000L));
        System.out.println(solution(new int[]{1, 5, 9}, new int[]{2, 6, 9}, 15));
        System.out.println(solution(new int[]{1, 2, 3, 4, 5}, new int[]{5, 5, 5, 5, 5}, 50));
        System.out.println(solution(new int[]{1, 2, 2, 3, 3, 4, 4}, new int[]{2, 2, 3, 3, 4, 4, 5}, 50));
        System.out.println(solution(new int[]{1, 50, 100}, new int[]{1, 1, 1}, 300));
    }


    public static int solution(int[] diffs, int[] times, long limit) {
        int level = 1;
        int maxLevel = 100000; //최대값 세팅
        int answer = maxLevel;

        while (level <= maxLevel) {
            int mid = (level + maxLevel) / 2;
            long solveTime = calculate(mid, diffs, times, limit);

            if (solveTime <= limit) {
                answer = mid;
                maxLevel = mid - 1;
            } else {
                level = mid + 1;
            }
        }


        return answer;
    }

    /**
     * param
     * level = 숙련도
     * diffs = 난이도
     * times = 소요시간
     * limit = 최대 시간
     * 난이도 > 숙련도 	틀린 횟수 = 난이도 - 숙련도     (틀린 횟수 * (소요시간 + 이전 소요시간 )) +소요시간
     */
    public static long calculate(int level, int[] diffs, int[] times, long limit){
        int prevTime = 0;
        long totalTime = 0;
        for(int i=0; i<diffs.length; i++){
            int diff = diffs[i]; //현재 난이도
            int time = times[i]; //현재 완료 시간

            if (diff <= level) {
                totalTime += time;
                prevTime = time;
            } else {
                totalTime += (long)(diff - level) * (time + prevTime) + time;
                prevTime = time;
            }

            if (totalTime > limit) {
                break;
            }
        }

        return totalTime;
    }
}
