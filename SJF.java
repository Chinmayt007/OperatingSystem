import java.util.Arrays;
import java.util.Scanner;

public class SJF {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int arrivalTime[] = new int[n];
        for (int i = 0; i < n; i++)
            arrivalTime[i] = sc.nextInt();
        int burstTime[] = new int[n];
        for (int i = 0; i < n; i++)
            burstTime[i] = sc.nextInt();

        for (int i = 1; i < n - 1; i++) {
            for (int j = 0; j < n - i; j++) {
                if (arrivalTime[j] > arrivalTime[j + 1]) {
                    int temp = arrivalTime[j];
                    arrivalTime[j] = arrivalTime[j + 1];
                    arrivalTime[j + 1] = temp;
                    int temp1 = burstTime[j];
                    burstTime[j] = burstTime[j + 1];
                    burstTime[j + 1] = temp1;
                }
            }
        }
        if (arrivalTime[0] == arrivalTime[1] && burstTime[0] > burstTime[1]) {
            int temp = burstTime[0];
            burstTime[0] = burstTime[1];
            burstTime[1] = temp;
        }
        for (int i = 1; i < n; i++) {
            if (burstTime[0] > arrivalTime[i]) {
                for (int j = i + 1; j < n; j++) {
                    int k = i;
                    if (burstTime[k] > burstTime[j]) {
                        int temp = arrivalTime[k];
                        arrivalTime[k] = arrivalTime[j];
                        arrivalTime[j] = temp;
                        int temp1 = burstTime[k];
                        burstTime[k] = burstTime[j];
                        burstTime[j] = temp1;
                    }
                }
            }
        }
        System.out.println(Arrays.toString(arrivalTime));
        System.out.println(Arrays.toString(burstTime));
        calculateCompletionTime(arrivalTime, burstTime, n);
        sc.close();
    }

    private static void calculateCompletionTime(int[] arrivalTime, int[] burstTime, int n) {
        int completionTime[] = new int[n];
        completionTime[0] = arrivalTime[0] + burstTime[0];
        for (int i = 1; i < n; i++) {
            completionTime[i] = burstTime[i] + completionTime[i - 1];
        }
        System.out.println("Completion Time: " + Arrays.toString(completionTime));
        calculateTurnArroundTime(arrivalTime, completionTime, burstTime, n);
    }

    private static void calculateTurnArroundTime(int[] arrivalTime, int[] completionTime, int burstTime[], int n) {
        int turnArroundTime[] = new int[n];
        for (int i = 0; i < n; i++) {
            turnArroundTime[i] = completionTime[i] - arrivalTime[i];
        }
        System.out.println("TurnArround Time: " + Arrays.toString(turnArroundTime));
        calculateAverageTurnArroundTime(turnArroundTime, n);
        calculateWaitingTime(turnArroundTime, burstTime, n);
    }

    private static void calculateAverageTurnArroundTime(int[] turnArroundTime, int n) {
        float avg = 0;
        for (int i = 0; i < n; i++)
            avg += turnArroundTime[i];
        avg = avg / n;
        System.out.printf("Average TurnArroundTime: " + "%.2f", avg);
        System.out.println();
    }

    private static void calculateWaitingTime(int[] turnArroundTime, int[] burstTime, int n) {
        int waitingTime[] = new int[n];
        for (int i = 0; i < n; i++)
            waitingTime[i] = turnArroundTime[i] - burstTime[i];
        System.out.println("Waiting Time: " + Arrays.toString(waitingTime));
        calculateAverageWaitingime(waitingTime, n);
    }

    private static void calculateAverageWaitingime(int[] waitingTime, int n) {
        float avg = 0;
        for (int i = 0; i < n; i++)
            avg += waitingTime[i];
        avg = avg / n;
        System.out.printf("Average waitingTime: " + "%.2f", avg);
        System.out.println();
    }
}

/*
 * NOTE-IN PREEMTIVE SCHEDULING ALGORITHM RESPONSE TIME IS ALWAYS EQUAL TO WAITING TIME
 */
