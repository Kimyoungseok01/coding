/**
 * packageName : com.example.coding
 * fileName : Video
 * author : user
 * date : 2024-09-13
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2024-09-13           user             최초 생성
 */
package com.example.coding.video;

public class Video {

    public static void main(String[] args) {
        String video_len = "07:22"; //비디오 길이

        String pos = "04:05"; // 시작 시작

        String op_start = "00:15"; // 오프닝 시작시간

        String op_end = "04:07"; // 오프닝 종료시간

        String[] commands = {"next"}; // 커맨드

        /*System.out.println(solution("30:35", "30:30", "01:00", "02:00", new String[]{"next"}));;
        System.out.println(solution("30:00", "15:00", "15:10", "15:30", new String[]{"next", "next"}));
        System.out.println(solution("30:00", "01:05", "01:00", "01:30", new String[]{"prev"}));
        System.out.println( solution("30:00", "29:55", "01:00", "01:30", new String[]{"next"}));
        System.out.println(solution("30:00", "01:55", "01:00", "01:30", new String[]{"next"}));
        System.out.println(solution("30:00", "00:11", "05:00", "06:00", new String[] {"prev"}));
        System.out.println(solution("59:59", "59:45", "00:00", "01:00", new String[] {"next"}));
        System.out.println( solution("30:00", "00:08", "00:00", "00:05", new String[] {"prev"}));*/
        System.out.println( solution("07:22", "04:05", "00:15", "04:07", new String[] {"next"}));
        System.out.println( solution("07:22", "00:16", "00:15", "00:18", new String[] {"prev"}));
        /**
         * 1. pos 가 op_end 시간 보다 작으면 pos = op_end
         * 2. pos 가 10초보다 작을때 prev 시 0으로 초기화
         * */

    }

    public static String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {

        int videoTotalSecond = Tosecond(video_len);
        int posTotalSecond = Tosecond(pos);
        int opStartSecond = Tosecond(op_start);
        int opEndSecond =  Tosecond(op_end);

        if (posTotalSecond >= opStartSecond && posTotalSecond <= opEndSecond) {
            posTotalSecond = opEndSecond;
        }
        for(String command : commands){
            if (posTotalSecond >= opStartSecond && posTotalSecond <= opEndSecond) {
                posTotalSecond = opEndSecond;
            }
            if(command.equals("next")){
                posTotalSecond += 10;
                if (posTotalSecond > videoTotalSecond) posTotalSecond = videoTotalSecond;
            }else{
                posTotalSecond -= 10;
                if(posTotalSecond < 0) posTotalSecond = 0;
            }
        }

        if (posTotalSecond >= opStartSecond && posTotalSecond <= opEndSecond) {
            posTotalSecond = opEndSecond;
        }

        return ToMinute(posTotalSecond);

    }

    public static int Tosecond(String timeData){
        String[] time = timeData.split(":");

        int minutes = Integer.parseInt(time[0]);
        int seconds = Integer.parseInt(time[1]);

        return (minutes * 60) + seconds;
    }

    public static String ToMinute(int second){

        int minutes = second / 60;
        int seconds = second % 60;

        return String.format("%02d:%02d", minutes, seconds);
    }
}
