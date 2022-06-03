package RealChallenges;/*
2. Code Question 2
Cloud Provider has several data centers which have multiple processors that perform computations.
In one such data center, these processors are placed in a sequence with their IDs denoted by 1, 2, .., n.
Each processor consumes a certain amount of power to boot up, denoted by bootingPower[i].
After booting, a processor uses processingPower[i] of power to run the processes.
For maximum utilization, the data center wishes to group these processors into clusters.

Clusters can only be formed of processors located adjacent to each other.

For example, processors 2, 3, 4, 5 can form a cluster, but 1, 3, 4 cannot.

The net power consumption of a cluster of k processors (1, / + 1, .., / + k - 1) is defined as:

max(bootingPower[i],bootingPoser[1],...,bootingPower[i+k-1])

    ( SUM(processingPower[j]) ) * k

That is, net power consumption = maximum booting power among the k processors + (sum of processing power of processors) * k.

A cluster of processors is said to be sustainable if its net power consumption does not exceed a given threshold value powerMax.

Given the booting power consumption and the processing power consumption of n processors denoted by bootingPower and processingPower respectively,
and the threshold value powerMax, find the maximum possible number of processors which can be grouped together to form a sustainable cluster.

If no such clusters can be formed, return 0.

Note: It is not mandatory for all clusters of size k to be sustainable. Even one such cluster is sufficient.

Example
bootingPower = [3, 6, 1, 3, 4]
processing Power = [2, 1, 3, 4, 5]
powerMax = 25

If k = 2, any adjacent pair can be chosen. The highest usage is the pair [4, 5] with net power consumption 4 + (4+5) * 2 = 22.

Next, try k = 3. Group the first 3 processors together as:

                         _______________________
        BOOTING POWER   |   3       6       1   |      3       4
                        |                       |
        PROCESSING      |   2       1       3   |      4       5
        POWER            -----------------------
                         CLUSTER OF 3 PROCESSORS
                         maximum booting power 6
                         sum processing power 6
                         net power consumption = 6 + 6 * 3 = 24

        MAXIMUM POWER   25


Here,
    * Maximum booting power = max(3, 6, 1) = 6
    * Sum of processing powers = 2 + 1 + 3 = 6
    * Thus, net power consumption =6+6 * 3 = 24 <= powerMax

Thus, we can group & = 3 processors to form a sustainable cluster.
Note that the minimum power consumption to form a cluster of k = 4 processors is 46, by forming a cluster of the first 4 processors.
Since this cost is greater than the threshold, we cannot form a cluster with 4 processors. The maximum possible cluster size is 3.

Function Description
Complete the function findMaximumSustainableClusterSize in the editor below.

findMaximumSustainableClusterSize has the following parameters:

    int processingPower[n]: the power consumption of each processor in running the processes'
    int bootingPower[n]: the power consumption of each processor in boot up
    long. int powerMax: the threshold power consumption

Returns
int: the maximum cluster size which is sustainable,

If no cluster exists, return 0.

Constraints
1 ≤n ≤ 10 exp 5
1s powerMax ≤ 1014
Is processing Power til=104
1s bootingPower
length of processingPower = length of bootingPower


-----------------------------------------
Sample Case 0

Sample Input For Custom Testing
STDIN               FUNCTION
5           ->      processingPower[] size n = 5
4           ->      processingPower = [4, 1, 4, 5, 3]
1
4
5
3
5           ->      bootingPower[] size n = 5
8           ->      bootingPower = [8, 8, 10, 9, 12]
8
10
9
12
33          ->      powerMax = 33

Sample Output: 2
Explanation:
Here processingPower= [4, 1, 4, 5, 3], bootingPower = [8, 8, 10, 9, 12] and powerMax = 33.

We can form a cluster of size * = 2, consisting of the first 2 processors.
The net power consumption is net power consumption = max(8, 8) + (4 + 1) *2 = 18, which is less than the threshold.
There are other clusters of size 2 which can be formed as well.
However, the minimum net power consumption for a cluster of size 3 is 37 (the first 3 processors, max(8, 8, 10) + (4 + 1 + 4) * 3 = 37).
This is greater than the threshold (33), thus not sustainable.
The maximum possible sustainable cluster size is 2.

-----------------------------------------
Sample Case 1

Sample Input For Custom Testing
STDIN               FUNCTION
3           ->      processingPower[] size n 3
10          ->      processingPower = [10, 8, 7]
8
7
3           ->      bootingPower[] size n = 3
11          ->      bootingPower = [11, 12, 19]
12
19
6           ->      powerMax = 6

Sample Output: 0
Explanation:
It is not possible to form any sustainable cluster for the given processors with net power consumption
less than or equal to powerMax, thus the answer is 0.

 */

public class FindMaximumSustainableClusterSize {

    public static int findMaximumSustainableClusterSize(int [] processingPower, int[] bootingPower, long powerMax) {

        return 0;
    }
}

