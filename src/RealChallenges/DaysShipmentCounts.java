package RealChallenges;

import java.util.Arrays;

/*
https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
Q1 : Given an array denoting shipment counts at each warehouse, calculate how many days it would take ship them all, if equal number of shipments can only be shipped from each warehouse having non-zero shipment.

Example - [4,2,3,3,3] -> it would take 3 days
2 shipments from each warehouse on 1st day => [2,0,1,1,1]
1 shipment from each warehouse on 2nd day => [1,0,0,0,0]
1 shipment from the 1st warehouse on 3rd day

 */
public class DaysShipmentCounts {

    public static void main(String[] args) {
        int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int days = 5;
        int maxCapacity = 15;
        System.out.println("Max capacity " + maxCapacity + " should be: " + shipWithinDays(weights, days));
    }

    public static int shipWithinDays(int[] weights, int days) {
        int left = Arrays.stream(weights).max().getAsInt();
        int right = Arrays.stream(weights).sum();

        while (left < right) {
            int middle = (left + right) / 2;

            if (canShip(middle, weights, days)) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return right;
    }

    public static boolean canShip(int middleWeight, int[] weights, int days) {
        int currentWeight = 0;
        int daysTaken = 1;

        for (int weight : weights) {
            currentWeight += weight;
            if (currentWeight > middleWeight) {
                daysTaken++;
                currentWeight = weight;
            }
        }
        return daysTaken <= days;
    }

}
