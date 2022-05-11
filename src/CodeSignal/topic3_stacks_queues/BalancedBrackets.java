package CodeSignal.topic3_stacks_queues;

import java.util.HashMap;
import java.util.Map;

public class BalancedBrackets {
    private static final Map<Character , Character> dic
        = new HashMap<Character , Character>(){{
        put('{', '}');
        put('[', ']');
        put('(', ')');
    }};

    static String isBalanced(String s) {
        char[] brackets = s.toCharArray();
        char[] stack = new char[brackets.length];
        int count = 0;

        for(int i=0; i< brackets.length; i++){
            char brack = brackets[i];

            if( dic.containsKey(brack) ) {
                stack[count] = brack;
                count++;
            }else {
                if(count > 0) {
                    char opening = stack[count-1];
                    char closing = dic.get(opening);
                    if(closing != brack){
                        return "NO";
                    }
                    count--;
                } else{
                    return "NO";
                }
            }
        }

        if(count == 0) return "YES";

        return "NO";
    }

    public static void main(String[] args) {
        System.out.println(isBalanced("[()()]"));
        System.out.println(isBalanced("()()"));
        System.out.println(isBalanced("]({}{()}[}}[]{]([]{}({({(][})}{)[[(})][)})(){(}{){]][(}(][{[])(]]([[{{(]]{}([}]]){[[({]}[(}][(]){[]}])}{]])][([][([)]{[}()])}[{][]{{(]{[][){[)([}]}[{}(({{({)}()}}{{{{}[}]}){})[((}[[}[[}("));
    }
}
