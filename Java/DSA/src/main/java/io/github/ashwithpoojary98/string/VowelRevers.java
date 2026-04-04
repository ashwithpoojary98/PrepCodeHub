package main.java.io.github.ashwithpoojary98.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VowelRevers {

    public static void main(String[] args) {
        System.out.println(reverseVowels("leetcode"));
    }

    public static String reverseVowels(String s) {
        List<Character> list = new ArrayList<>();
        String newString = "";
        for (char c : s.toCharArray()) {
            if (c == 'a' || c == 'A' || c == 'e' || c == 'E' || c == 'i' || c == 'I' || c == 'o' || c == 'O' || c == 'u'
                    || c == 'U') {
                newString += "0";
                list.add(c);
            } else {
                newString += c;
            }
        }

        int count = 0;
        Collections.sort(list);
        Collections.reverse(list);
        String finalValue = "";
        for (int i = 0; i < s.length(); i++) {
            if (newString.charAt(i) == '0') {
                finalValue += list.get(count++);
            } else {
                finalValue += newString.charAt(i);
            }
        }
        return finalValue;

    }

}
