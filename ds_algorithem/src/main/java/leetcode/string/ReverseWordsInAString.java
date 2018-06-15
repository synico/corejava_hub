package leetcode.string;

import java.util.Stack;

public class ReverseWordsInAString {
    
    public String reverseWords(String s) {
        if(s == null || s.trim().length() == 0) {
            s = s == null ? s : s.trim();
            return s;
        } else {
            Stack<String> wordStack = new Stack<String>();
            String [] words = s.trim().split(" ");
            for(int i = 0; i < words.length; i++) {
                if(words[i].trim().length() > 0) {
                    wordStack.push(words[i].trim());
                }
            }
            StringBuilder sb = new StringBuilder();
            while(!wordStack.isEmpty()) {
                sb.append(wordStack.pop());
                sb.append(" ");
            }
            int len = sb.length();
            s = sb.substring(0 , len -1);
        }
        return s;
    }

    public static void main(String[] args) {
        ReverseWordsInAString rws = new ReverseWordsInAString();
        String reversedStr = rws.reverseWords(" ");
        System.out.println(reversedStr);
    }

}
