import java.util.ArrayList;
import java.util.List;
// Time Complexity : Exponential - O(NL 26^L) L = avg length of each word and N = length of input array
// Space Complexity : O(NL)
public class WordSquares {
    class TrieNode {
        // It will have trienode array of 26 chars
        TrieNode[] children;
        // Each char will have the list of words
        List<String> startsWith;

        TrieNode() {
            children = new TrieNode[26];
            startsWith = new ArrayList<>();
        }

    }

    // Declare the root node
    TrieNode root;
    // Declare result list
    List<List<String>> result;

    // Insert the word in trie method
    public void insertTrie(String word) {
        // Take a curr node that starts at root
        TrieNode curr = root;
        // Iterate over each char of the word
        for (int i = 0; i < word.length(); i++) {
            // Take one char at a time
            char c = word.charAt(i);
            // Check if in children of the curr node, the char c is having a trienode or
            // not, if it is null then
            if (curr.children[c - 'a'] == null) {
                // Create a new trienode at that place
                curr.children[c - 'a'] = new TrieNode();
            }
            // Then move curr to this char
            curr = curr.children[c - 'a'];
            // Now in the list associated to this char, add the word
            curr.startsWith.add(word);
        }
    }

    // Method that will return the list of word that starts with the given prefix
    public List<String> startsWithList(String prefix) {
        // Take the current node at the root
        TrieNode curr = root;
        // Iterate over each char in the prefix
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            // If the children of curr at this char position is null, means no list, so
            // return empty list
            if (curr.children[c - 'a'] == null) {
                return new ArrayList<>();
            }
            // Else move to this char
            curr = curr.children[c - 'a'];
        }
        // Return the list present at the last char of prefix, example if prefix="lea",
        // it will return list at 'a'
        return curr.startsWith;
    }

    // Main method
    public List<List<String>> wordSquares(String[] words) {
        // Base case
        if (words == null || words.length == 0) {
            return new ArrayList<>();
        }
        // Initialize root and result
        root = new TrieNode();
        result = new ArrayList<>();
        // Insert all the words in the trie
        for (String word : words) {
            insertTrie(word);
        }
        // Take a list
        List<String> li = new ArrayList<>();
        // Iterate over the words
        for (String word : words) {
            // Action - Add the word
            li.add(word);
            // Recurse
            backtrack(li);
            // Backtrack
            li.remove(li.size() - 1);
        }
        // Return result
        return result;
    }

    private void backtrack(List<String> li) {
        // Base
        // Whenever our list size is equal to the word length(all words are of same
        // length), that means we formed a square
        if (li.size() == li.get(0).length()) {
            // So add to result
            result.add(new ArrayList<>(li));
            // Return
            return;
        }
        // Logic
        // Take stringbuilder to build our prefix string for our next word
        StringBuilder sb = new StringBuilder();
        // Take char from every word in list at the index of list.size
        int index = li.size();
        // Iterate
        for (String word : li) {
            // Append the char at index in every word to the sb
            sb.append(word.charAt(index));
        }
        // Now iterate over the list associated with the prefix
        for (String w : startsWithList(sb.toString())) {
            // Action
            li.add(w);
            // Recurse
            backtrack(li);
            // Backtrack
            li.remove(li.size() - 1);
        }
    }

    public static void main(String[] args) {
        String[] words = new String[] { "lead", "area", "wall", "lady", "ball" };
        WordSquares w = new WordSquares();

        List<List<String>> result = w.wordSquares(words);
        for (List<String> s : result) {
            for (String word : s) {
                System.out.print(word + " ");
            }

            System.out.println();
        }
    }
}
