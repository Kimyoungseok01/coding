/**
 * packageName : com.example.coding.CombineAngles
 * fileName : CombineAngles
 * author : user
 * date : 2024-10-08
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2024-10-08           user             최초 생성
 */
package com.example.coding.CombineAngles;

import java.util.Scanner;

public class CombineAngles {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int angle1 = sc.nextInt();
        int angle2 = sc.nextInt();

        int sum_angle = angle1 + angle2;
        System.out.println(sum_angle % 360);
    }
}
