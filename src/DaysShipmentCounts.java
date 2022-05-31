
/*
https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
Q1 : Given an array denoting shipment counts at each warehouse, calculate how many days it would take ship them all, if equal number of shipments can only be shipped from each warehouse having non-zero shipment.

Example - [4,2,3,3,3] -> it would take 3 days
2 shipments from each warehouse on 1st day => [2,0,1,1,1]
1 shipment from each warehouse on 2nd day => [1,0,0,0,0]
1 shipment from the 1st warehouse on 3rd day

My Approach:
The idea is that one can remove minimum value from array at once
Sort the array in ascending order
Traverse the array, keeping the shipments count already shipped from a single warehouse. So instead of actual subtracting the shipments I just kept the count.
Time - O(NLogN + N)

 */
public class DaysShipmentCounts {

}
