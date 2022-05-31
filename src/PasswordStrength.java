/*
https://leetcode.com/problems/count-unique-characters-of-all-substrings-of-a-given-string/
Q2 : Given a password string, calculate its strength, where strength is equals to the sum of count of distinct characters in all the substrings of that string.

Example: "aba" -> answer is 9

aba => [a,b,a,ab,aa,ba,aba] => unique characters of each password: 1+1+1+2+1+2+1 = 9.

1st Approach:
At first I generated all substrings in 2 for loops, but instead of string, I had a SET of characters.
For each larger length created the SET from previous calculated SET
Size of the SET gave the distinct count and also the size of the SET will be a constant 26
Overall Time complexity wasO(26.N^2), but it was TLE in 2 test cases.

2nd Approach:
I tried the contribution approach, where I kept the track of contribution of individual character. (for each character at i, there can be i substrings ending there)
And also maintained the lastSeen position of the character
Once a duplicate character was encountered, subtracted the lastSeen Index.

Time - O(N)
 */
public class PasswordStrength {
}
