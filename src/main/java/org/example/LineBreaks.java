//package org.example;

import java.util.Scanner;

public class LineBreaks {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-->0){
            int n = sc.nextInt();
            int m = sc.nextInt();
            String[] a = new String[n];
            int remaining = m;
            int answer = 0;
            for (int i=0;i<n;i++){
                a[i] = sc.next();
                remaining -= a[i].length();
                if(remaining>=0){
                    answer+=1;
                }
            }
            System.out.println(answer);
        }
    }
}
