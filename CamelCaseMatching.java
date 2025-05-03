import java.util.ArrayList;
import java.util.List;

public class CamelCaseMatching {
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        // Base case
        if (queries == null || queries.length == 0) {
            return new ArrayList<>();
        }
        List<Boolean> result = new ArrayList<>();
        for (String word : queries) {
            // Take a flag
            boolean flag = true;
            int i = 0; // Index on the pattern
            // Inner loop on each char of this word
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                if ((i < pattern.length()) && (pattern.charAt(i) == c)) {
                    i++;
                }
                // Else if doesnt match, check if the char in word is a uppercase, which is not
                // allowed
                else if (Character.isUpperCase(c)) {
                    // Turn flag to false
                    flag = false;
                    // And break
                    break;
                }
            }
            // Check if the flag is true , then we have matched all chars in pattern that
            // i==pattern.length()
            if (flag == true && i == pattern.length()) {
                result.add(true);
            } else {
                result.add(false);
            }

        }
        return result;
    }
}
