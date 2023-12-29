import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Random;
import java.io.IOException;

import static java.lang.Math.min;


public class Main {

    static int N;
    static int []count_ = new int [14];
    static int []sum;
    static boolean []done;
    static int c = 0;

    static String yes;

    static int shuffle(){
        while(true) {
            Random rand = new Random();
            int iValue = rand.nextInt(13) + 1;
            if (count_[iValue] < 4) {
                count_[iValue]++;
                c++;
                return iValue;
            }
        }

    }


    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = scanner.nextInt();  // # of player
        sum = new int[N+1];
        done = new boolean[N+1];
        for(int i=1;i<=N;i++){
            sum[i] = 0;
            done[i] = false;
        }
        int s;
        int turn = 0;
        while(true) {

            turn++;
            if (turn == 1) {
                for (int i = 1; i <= N - 1; i++) {
                    int ret = shuffle();
                    sum[i] += ret;
                    System.out.println("Player " + i + "'s score: " + sum[i]);
                }
                System.out.println("It's your turn. Choose whether pick up the card or not.");
                yes = br.readLine();
                if (yes.equals("Y")) {
                    int ret = shuffle();
                    sum[N] += ret;
                    System.out.println("Player " + N + "'s score: " + sum[N]);
                } else {
                    done[N] = true;
                }

            } else {
                for (int i = 1; i <= N - 1; i++) {

                    int target = (21 - sum[i]);
                    s = 0;
                    for (int j = 1; j <= min(target, 13); j++) {
                        s += (4 - count_[j]);
                    }

                    if ((double) ((s) / (52 - c)) < 0.5) {
                        done[i] = true;
                        System.out.println("Player " + i + " done!");
                    } else {
                        int ret = shuffle();
                        sum[i] += ret;
                        if (sum[i] > 21) {
                            done[i] = true;
                            System.out.println("Player " + i + "'s score: " + sum[i] + " is over 21!");
                        } else {

                            System.out.println("Player " + i + "'s score: " + sum[i]);
                        }
                    }
                }
                System.out.println("It's your turn. Choose whether pick up the card or not.");
                yes = br.readLine();
                if (yes.equals("Y")) {
                    int ret = shuffle();
                    sum[N] += ret;
                    if (sum[N] > 21) {
                        done[N] = true;
                        System.out.println("Your score: " + sum[N] + " is over 21!");
                    } else {
                        System.out.println("Player " + N + "'s score: " + sum[N]);
                    }

                } else {
                    done[N] = true;
                }
                int p = 0;
                for (int k = 1; k <= N; k++) if (done[k] == false) p++;
                if (p == 1) {
                    for (int k = 1; k <= N; k++) {
                        if (done[k] == false) {
                            System.out.println("Player " + k + " win!");
                            break;
                        }
                    }
                    return;
                }
                else if(p==0){
                    System.out.println("Tie!!");
                    return;
                }
            }
        }
            // N 일때


        }

    }




/*
public static string Personality(int n) {


}










 */