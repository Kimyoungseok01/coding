/**
 * packageName : com.example.coding.video
 * fileName : GPTVideo
 * author : user
 * date : 2024-10-02
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2024-10-02           user             최초 생성
 */
package com.example.coding.video;

public class GPTVideo {

    // "mm:ss" 형식의 시간을 초 단위로 변환
    public static int timeToSeconds(String time) {
        String[] parts = time.split(":");
        int minutes = Integer.parseInt(parts[0]);
        int seconds = Integer.parseInt(parts[1]);
        return minutes * 60 + seconds;
    }

    // 초 단위 시간을 "mm:ss" 형식으로 변환
    public static String secondsToTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public static String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        // 동영상의 길이, 현재 위치, 오프닝 시작/끝 시간 초 단위로 변환
        int videoLength = timeToSeconds(video_len);
        int position = timeToSeconds(pos);
        int openingStart = timeToSeconds(op_start);
        int openingEnd = timeToSeconds(op_end);

        // 오프닝 구간이면 오프닝 끝으로 이동
        if (position >= openingStart && position <= openingEnd) {
            position = openingEnd;
        }
        for (String command : commands) {
            if (command.equals("prev")) {
                position = Math.max(0, position - 10);  // 10초 전으로 이동
            } else if (command.equals("next")) {
                position = Math.min(videoLength, position + 10);  // 10초 후로 이동
            }

            // 오프닝 구간이면 오프닝 끝으로 이동
            if (position >= openingStart && position <= openingEnd) {
                position = openingEnd;
            }
        }

        // 최종 위치를 "mm:ss" 형식으로 변환하여 반환
        return secondsToTime(position);
    }

    public static void main(String[] args) {
        // 테스트 케이스
        String video_len1 = "34:33";
        String pos1 = "13:00";
        String op_start1 = "00:55";
        String op_end1 = "02:55";
        String[] commands1 = {"next", "prev"};
        System.out.println(solution(video_len1, pos1, op_start1, op_end1, commands1));  // "13:00"

        String video_len2 = "10:55";
        String pos2 = "00:05";
        String op_start2 = "00:15";
        String op_end2 = "06:55";
        String[] commands2 = {"prev", "next", "next"};
        System.out.println(solution(video_len2, pos2, op_start2, op_end2, commands2));  // "06:55"

        String video_len3 = "07:22";
        String pos3 = "04:05";
        String op_start3 = "00:15";
        String op_end3 = "04:07";
        String[] commands3 = {"next"};
        System.out.println(solution(video_len3, pos3, op_start3, op_end3, commands3));  // "04:17"
    }
}
