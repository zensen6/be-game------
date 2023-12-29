import java.util.*;


public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();  // # of player
        int []count_ = new int [14];
        int []sum = new int[N+1];
        boolean []done = new boolean[N+1];
        int c = 0,s;
        while(true){

            for(int i=1;i<=N-1;i++){
                int target = (21 - sum[i]);
                s = 0;
                for(int j=1;j<=target;j++){
                    s += (4 - count_[j]);
                }
                if((double)((s)/(52 - c)) < 0.5){
                    done[i] = true;
                    System.out.println("Player " + i + " done!");
                }else{

                }
            }


        }

    }
}



/*
public static string Personality(int n) {


}










 */