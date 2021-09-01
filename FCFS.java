import java.util.Arrays;
import java.util.Scanner;

public class FCFS {
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
        int completionTime[] = new int[n];
        completionTime[0] = arrivalTime[0] + burstTime[0];
        for (int i = 1; i < n; i++) {
            if (arrivalTime[i] > completionTime[i - 1])
                completionTime[i] = arrivalTime[i] + burstTime[i];
            if (arrivalTime[i] < completionTime[i - 1])
                completionTime[i] = completionTime[i - 1] + burstTime[i];
        }
        System.out.println("Arrival Time: " + Arrays.toString(arrivalTime));
        System.out.println("Burst Time: " + Arrays.toString(burstTime));
        System.out.println("Completion Time: " + Arrays.toString(completionTime));
        calculateTurnArroundTime(arrivalTime, completionTime, n, burstTime);
        sc.close();
    }

    private static void calculateTurnArroundTime(int[] arrivalTime, int[] completionTime, int n, int[] burstTime) {
        int turnArroundTime[] = new int[n];
        for (int i = 0; i < n; i++) {
            turnArroundTime[i] = completionTime[i] - arrivalTime[i];
        }
        System.out.println("Turn Arround Time: " + Arrays.toString(turnArroundTime));
        calculateAverageTurnArroundTime(turnArroundTime, n);
        calculateWaitingTime(burstTime, turnArroundTime, n);
    }

    private static void calculateAverageTurnArroundTime(int[] turnArroundTime, int n) {
        float avg = 0;
        for (int i = 0; i < n; i++)
            avg += turnArroundTime[i];
        System.out.printf("Average Turn Arround Time: " + "%.2f", avg / n);
        System.out.println();
    }

    private static void calculateWaitingTime(int[] burstTime, int[] turnArroundTime, int n) {
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
        System.out.printf("Average Waiting Time: " + "%.2f", avg / n);
        System.out.println();
    }
}

/*
 * NOTE-IN PREEMTIVE SCHEDULING ALGORITHM RESPONSE TIME IS ALWAYS EQUAL TO WAITING TIME
 */
