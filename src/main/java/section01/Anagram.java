package section01;

import java.util.Arrays;

/**
 * @author alexandrubostan
 * @since 10.07.2015
 */
public class Anagram {
    public boolean isAnagram(String s, String s1) {
        char[] words1 = removeNonLetters(s);
        char[] words2 = removeNonLetters(s1);

        Arrays.sort(words1);
        Arrays.sort(words2);

        return Arrays.equals(words1, words2);
    }

    private char[] removeNonLetters(String s){
        return  s.replaceAll("[^a-zA-Z]", "").toLowerCase().toCharArray();
    }

}
