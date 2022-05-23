package LeetCode.medium;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Arrays.deepToString;
import static java.util.Collections.emptyList;

/*
Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts.
Two accounts definitely belong to the same person if there is some common email to both accounts.
Note that even if two accounts have the same name, they may belong to different people as people could have the same name.
A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order.
The accounts themselves can be returned in any order.


Example 1:
Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]

Explanation:
The first and second John's are the same person as they have the common email "johnsmith@mail.com".
The third John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.


Example 2:
Input: accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
Output: [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]
 */
public class AccountsMerge {

    public static void main(String[] args) {
        List<List<String>> accountInput = asList(
                asList("John", "johnsmith@mail.com", "john_newyork@mail.com"),
                asList("John", "johnsmith@mail.com", "john00@mail.com"),
                asList("Mary", "mary@mail.com"),
                asList("John", "johnnybravo@mail.com"));
        List<List<String>> output = asList(
                asList("John", "johnnybravo@mail.com"),
                asList("John", "john00@mail.com", "john_newyork@mail.com", "johnsmith@mail.com"),
                asList("Mary", "mary@mail.com"));

        List<List<String>> accountInput2 = asList(
                asList("Gabe", "Gabe0@m.co", "Gabe3@m.co", "Gabe1@m.co"),
                asList("Kevin", "Kevin3@m.co", "Kevin5@m.co", "Kevin0@m.co"),
                asList("Ethan", "Ethan5@m.co", "Ethan4@m.co", "Ethan0@m.co"),
                asList("Hanzo", "Hanzo3@m.co", "Hanzo1@m.co", "Hanzo0@m.co"),
                asList("Fern", "Fern5@m.co", "Fern1@m.co", "Fern0@m.co"));
        List<List<String>> output2 = asList(
                asList("Hanzo", "Hanzo0@m.co", "Hanzo1@m.co", "Hanzo3@m.co"),
                asList("Fern", "Fern0@m.co", "Fern1@m.co", "Fern5@m.co"),
                asList("Gabe", "Gabe0@m.co", "Gabe1@m.co", "Gabe3@m.co"),
                asList("Kevin", "Kevin0@m.co", "Kevin3@m.co", "Kevin5@m.co"),
                asList("Ethan", "Ethan0@m.co", "Ethan4@m.co", "Ethan5@m.co"));

        List<List<String>> accountInput3 = asList(
                asList("Alex", "Alex5@m.co", "Alex4@m.co", "Alex0@m.co"),
                asList("Ethan", "Ethan3@m.co", "Ethan3@m.co", "Ethan0@m.co"),
                asList("Kevin", "Kevin4@m.co", "Kevin2@m.co", "Kevin2@m.co"),
                asList("Gabe", "Gabe0@m.co", "Gabe3@m.co", "Gabe2@m.co"),
                asList("Gabe", "Gabe3@m.co", "Gabe4@m.co", "Gabe2@m.co"));
        List<List<String>> output3 = asList(
                asList("Gabe", "Gabe0@m.co", "Gabe2@m.co", "Gabe3@m.co", "Gabe4@m.co"),
                asList("Kevin", "Kevin2@m.co", "Kevin4@m.co"),
                asList("Alex", "Alex0@m.co", "Alex4@m.co", "Alex5@m.co"),
                asList("Ethan", "Ethan0@m.co", "Ethan3@m.co"));

        List<List<String>> accountInput4 = asList(
                asList("Ethan", "Ethan1@m.co", "Ethan2@m.co", "Ethan0@m.co"),
                asList("David", "David1@m.co", "David2@m.co", "David0@m.co"),
                asList("Lily", "Lily0@m.co", "Lily0@m.co", "Lily4@m.co"),
                asList("Gabe", "Gabe1@m.co", "Gabe4@m.co", "Gabe0@m.co"),
                asList("Ethan", "Ethan2@m.co", "Ethan1@m.co", "Ethan0@m.co"));
        List<List<String>> output4 = asList(
                asList("Ethan", "Ethan0@m.co", "Ethan1@m.co", "Ethan2@m.co"),
                asList("Gabe", "Gabe0@m.co", "Gabe1@m.co", "Gabe4@m.co"),
                asList("Lily", "Lily0@m.co", "Lily4@m.co"),
                asList("David", "David0@m.co", "David1@m.co", "David2@m.co"));

        List<List<String>> accountInput5 = asList(
                asList("David", "David0@m.co", "David1@m.co"),
                asList("David", "David3@m.co", "David4@m.co"),
                asList("David", "David4@m.co", "David5@m.co"),
                asList("David", "David2@m.co", "David3@m.co"),
                asList("David", "David1@m.co", "David2@m.co"));
        List<List<String>> output5 = asList(
                asList("David", "David0@m.co", "David1@m.co", "David2@m.co", "David3@m.co", "David4@m.co", "David5@m.co"));

        List<List<String>> accountInput6 = asList(
                asList("Gabe", "Gabe0@m.co", "Gabe3@m.co", "Gabe1@m.co"),
                asList("Kevin", "Kevin3@m.co", "Kevin5@m.co", "Kevin0@m.co"),
                asList("Ethan", "Ethan5@m.co", "Ethan4@m.co", "Ethan0@m.co"),
                asList("Hanzo", "Hanzo3@m.co", "Hanzo1@m.co", "Hanzo0@m.co"),
                asList("Fern", "Fern5@m.co", "Fern1@m.co", "Fern0@m.co"));
        List<List<String>> output6 = asList(
                asList("Hanzo", "Hanzo0@m.co", "Hanzo1@m.co", "Hanzo3@m.co"),
                asList("Fern", "Fern0@m.co", "Fern1@m.co", "Fern5@m.co"),
                asList("Gabe", "Gabe0@m.co", "Gabe1@m.co", "Gabe3@m.co"),
                asList("Kevin", "Kevin0@m.co", "Kevin3@m.co", "Kevin5@m.co"),
                asList("Ethan", "Ethan0@m.co", "Ethan4@m.co", "Ethan5@m.co"));

        testMerge(accountInput, output);
        testMerge(accountInput2, output2);
        testMerge(accountInput3, output3);
        testMerge(accountInput4, output4);
        testMerge(accountInput5, output5);
        testMerge(accountInput6, output6);

    }

    private static void testMerge(List<List<String>> accountInput, List<List<String>> output) {
        List<List<String>> result = accountsMerge(accountInput);
        System.out.println(deepToString(new List[]{result}));
        System.out.println(result.equals(output));
    }

    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        HashMap<String, Set<String>> graph = new HashMap<>();
        HashMap<String, String> emailsName = new HashMap<>();

        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                if (graph.containsKey(email)) {
                    Set<String> setEmails = graph.get(email);
                    setEmails.add(account.get(1));
                } else {
                    graph.put(email, new HashSet<>(List.of(account.get(1))));
                }
                if (graph.containsKey(account.get(1))) {
                    Set<String> setEmails = graph.get(account.get(1));
                    setEmails.add(email);
                } else {
                    graph.put(account.get(1), new HashSet<>(List.of(email)));
                }
                emailsName.put(email, name);
            }
        }

        List<List<String>> response = new ArrayList<>();
        Set<String> visited = new HashSet<>();

        for (String email : graph.keySet()) {
            if (!visited.contains(email)) {
                Stack<String> stack = new Stack<>();
                List<String> localResponse = new ArrayList<>();

                stack.push(email);
                visited.add(email);

                while (!stack.isEmpty()) {
                    String node = stack.pop();
                    localResponse.add(node);

                    for (String edge : graph.get(node)) {
                        if (!visited.contains(edge)) {
                            stack.push(edge);
                            visited.add(edge);
                        }
                    }
                }
                List<String> accountProcessed = getAccountProcessed(emailsName.get(email), localResponse);
                response.add(accountProcessed);
            }
        }
        return response;
    }

    private static List<String> getAccountProcessed(String name, List<String> emailsToAdd) {
        List<String> sortedAccount = new ArrayList<>();
        sortedAccount.add(name);
        sortedAccount.addAll(emailsToAdd.stream().sorted().toList());
        return sortedAccount;
    }

    public static List<List<String>> accountsMergeNotOptimal(List<List<String>> accounts) {
        do {
            doTheMerge(accounts);
        } while (isMoreAccountsToMerge(accounts));

        return accounts.stream().filter(account -> !account.isEmpty()).collect(Collectors.toList());
    }

    private static void doTheMerge(List<List<String>> accounts) {
        for (int i = 0; i < accounts.size(); i++) {
            if (!accounts.get(i).isEmpty()) {
                List<String> emailsPivot = getEmails(accounts.get(i));
                for (int j = i + 1; j < accounts.size(); j++) {
                    List<String> emailsToMerge = getEmails(accounts.get(j));
                    if (isThereEmailsToMerge(emailsPivot, emailsToMerge)) {
                        List<String> emailsMerged = mergeAccountsEmails(accounts.get(i), accounts.get(j));
                        List<String> mergedAccount = getAccountProcessed(accounts.get(i), emailsMerged);
                        accounts.set(i, mergedAccount);
                        accounts.set(j, emptyList());
                    }
                }
                List<String> currentAccount = accounts.get(i);
                List<String> sortedEmails = getEmails(currentAccount);
                List<String> sortedAccount = getAccountProcessed(currentAccount, sortedEmails);
                accounts.set(i, sortedAccount);
            }
        }
    }

    private static boolean isMoreAccountsToMerge(List<List<String>> accounts) {
        return accounts.stream()
                .flatMap(Collection::stream)
                .filter(account -> account.contains("@"))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values()
                .stream()
                .anyMatch(count -> count > 1);
    }

    private static List<String> getAccountProcessed(List<String> currentAccount, List<String> sortedEmails) {
        List<String> sortedAccount = new ArrayList<>();
        sortedAccount.add(currentAccount.get(0));
        sortedAccount.addAll(sortedEmails);
        return sortedAccount;
    }

    private static List<String> getEmails(List<String> account) {
        if (account.size() == 0) return emptyList();
        Set<String> emails = new HashSet<>(account.subList(1, account.size()));
        return emails.stream().sorted().collect(Collectors.toList());
    }

    private static boolean isThereEmailsToMerge(List<String> emailsPivot, List<String> emailsMerge) {
        Set<String> mainEmails = new HashSet<>(emailsPivot);
        for (String email : emailsMerge) {
            if (mainEmails.contains(email)) {
                return true;
            }
        }
        return false;
    }

    private static List<String> mergeAccountsEmails(List<String> account1, List<String> account2) {
        Set<String> emails = new HashSet<>();
        emails.addAll(account1.subList(1, account1.size()));
        emails.addAll(account2.subList(1, account2.size()));
        return emails.stream().sorted().collect(Collectors.toList());
    }

}
