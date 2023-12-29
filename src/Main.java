import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Random;
import java.io.IOException;

import static java.lang.Math.min;


public class Main {

    static int N;
    static int[] count_ = new int[14];
    static int[] sum;
    static boolean[] done;


    static boolean[] finish;
    static int c = 0;

    static String yes;

    static int shuffle() {
        while (true) {
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
        System.out.println("Enter the number of Player participating the game. Your number is the last number of all player.");
        N = scanner.nextInt();  // # of player
        sum = new int[N + 1];
        done = new boolean[N + 1];
        finish = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            sum[i] = 0;
            done[i] = false;
            finish[i] = false;
        }
        int s;
        int turn = 0;
        while (true) {

            turn++;
            if (turn == 1) {
                for (int i = 1; i <= N - 1; i++) {
                    int ret = shuffle();
                    sum[i] += ret;
                    System.out.println("Player " + i + " chose " + ret+ ", score: " + sum[i]);
                }
                System.out.println("It's your turn. Choose whether pick up the card or not. Y/N");
                yes = br.readLine();
                if (yes.equals("Y")) {
                    int ret = shuffle();
                    sum[N] += ret;
                    c++;
                    System.out.println("You chose " + ret + ", score: " + sum[N]);
                } else {
                    done[N] = true;
                }

            } else {
                for (int i = 1; i <= N - 1; i++) {
                    if (finish[i] || done[i]) continue;
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
                            finish[i] = true;
                            System.out.println("Player " + i + "'s score: " + sum[i] + " is over 21!");
                        } else {
                            if (sum[i] == 21) {
                                System.out.println("Player " + i + "'s Score is 21, Win!!");
                                return;
                            } else System.out.println("Player " + i + " chose " + ret + ", score: " + sum[i]);
                        }
                    }
                }
                if (done[N] || finish[N]) continue;
                System.out.println("It's your turn. Choose whether pick up the card or not. Y/N");
                yes = br.readLine();
                if (yes.equals("Y")) {
                    int ret = shuffle();
                    sum[N] += ret;
                    c++;
                    if (sum[N] > 21) {
                        finish[N] = true;
                        System.out.println("Your score: " + sum[N] + " is over 21!");
                    } else {
                        if (sum[N] == 21) {
                            System.out.println("Your score is 21. You Win!!");
                            return;
                        }
                        System.out.println("Player " + N + "'s score: " + sum[N]);
                    }

                } else {
                    done[N] = true;
                }


                int p = 0;
                int active = 0;
                for (int k = 1; k <= N; k++) {
                    if (finish[k] == false && done[k] == true) p++;
                    if (done[k] == false && finish[k] == false) active++;
                }

                if (active == 0 && (p > 0)) { // 안죽었는데 done은 있음
                    int m = 21;
                    int index = 0;
                    for (int k = 1; k <= N; k++) {
                        if (finish[k] == false && done[k] == true) {
                            if (m > 21 - sum[k]) {
                                m = 21 - sum[k];
                                index = k;
                            }
                        }
                    }
                    System.out.println("Player " + index + " win by Score: " + sum[index]);
                    return;
                }


                else if(p == 1 || active == 1) {
                    for (int k = 1; k <= N; k++) {
                        if (finish[k] == false) {
                            System.out.println("Player " + k + " win! He is the last survivor");
                            return;
                        }
                    }
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