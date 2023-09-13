import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class StringProblems {
    // Problem 1.1: Is Unique
    static boolean areStringCharsUnique(String in) {
        // Assume that capital letters are considered identical to lowercase letters
        HashSet<Character> usedChars = new HashSet<>();
        // This is an O(n) algorithm that uses a HashSet to keep track of unique
        // characters in the input string
        for (int i = 0; i < in.length(); i++) {
            char toCompare = in.charAt(i);
            if (Character.isAlphabetic(in.charAt(i))) {
                toCompare = Character.toLowerCase(in.charAt(i));
            }
            if (usedChars.contains(toCompare)) {
                return false;
            }
            usedChars.add(toCompare);
        }
        return true;

        // Solution with no data structures would be a O(n^2) algorithm that would
        // iteratively compare every character in the string to each other. It would
        // return true if no two characters match and return false if any single pair
        // of characters are identical.
    }

    // Problem 1.2: Check Permutation
    static boolean checkPermutation(String a, String b) {
        // Assume white space is considered as a character. This matters
        // because if white space isn't valid then different string lengths
        // is not necessarily an indication that the two strings are not
        // permutations of each other.

        // If the strings are of two different lengths, they have a different
        // number of characters, and it is therefore impossible for them to
        // be permutation of each other
        if (a.length() != b.length()) {
            return false;
        }

        // Assume capital letters are considered identical to lowercase letters
        HashMap<Character, Integer> charCounts = new HashMap<>();
        for (int i = 0; i < a.length(); i++) {
            char toCount = a.charAt(i);
            // Convert any capital letter to lowercase
            if (Character.isUpperCase(toCount)) {
                toCount = Character.toLowerCase(toCount);
            }
            if (!charCounts.containsKey(toCount)) {
                // Add new characters to the map with an initial count of 1
                charCounts.put(toCount, 1);
            } else {
                // Increment the count for existing characters in the map
                charCounts.replace(toCount, charCounts.get(toCount) + 1);
            }
        }

        for (int j = 0; j < b.length(); j++) {
            char toCheck = b.charAt(j);
            // Convert any capital letter to lowercase
            if (Character.isUpperCase(toCheck)) {
                toCheck = Character.toLowerCase(toCheck);
            }
            // Check if there are still enough characters from the first string
            // for the second string to be a permutation of the first
            if (!charCounts.containsKey(toCheck) || charCounts.get(toCheck) == 0) {
                return false;
            }
            // Decrement the count of the current character
            charCounts.replace(toCheck, charCounts.get(toCheck) - 1);
        }
        return true;
    }

    // Problem 1.3: URLify
    static String doURLify(String in, int length) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (in.charAt(i) != ' ') {
                out.append(in.charAt(i));
            } else {
                out.append("%20");
            }
        }
        return out.toString();
    }

    // Problem 1.4: Palindrome Permutation
    static boolean isPalindromePermutation(String in) {
        int[] charCounts = new int[26];
        int numAlphabeticChars = 0;
        // Count the number of alphabetic characters in the string
        // Convert all uppercase letters to lowercase
        for (int i = 0; i < in.length(); i++) {
            if (Character.isAlphabetic(in.charAt(i))) {
                charCounts[Character.toLowerCase(in.charAt(i)) - 'a']++;
                numAlphabeticChars++;
            }
        }

        // Palindrome permutation definition:
        // Must have a rearrangement of letters that is the same read backwards and forwards
        // Given the definition, a string with an odd number of alphabetic characters must have
        // exactly one character that has an odd number of occurrences. A string with an even
        // number of alphabetic characters must have an even number of occurrences for every
        // character in the string.
        int numberOfOddAllowed = 0;
        if (numAlphabeticChars % 2 == 1) {
            numberOfOddAllowed++;
        }
        for (int i = 0; i < 26; i++) {
            if (charCounts[i] % 2 == 1) {
                if (numberOfOddAllowed < 1) {
                    return false;
                }
                numberOfOddAllowed--;
            }
        }
        return numberOfOddAllowed == 0;
    }

    // Problem 1.5: Palindrome Permutation
    static boolean isOneEditAway(String a, String b) {
        if (a.equals(b)) {
            return true;
        }

        if (Math.abs(a.length() - b.length()) > 1) {
            return false;
        }

        String longer, shorter;
        if (a.length() > b.length()) {
            longer = a;
            shorter = b;
        } else {
            longer = b;
            shorter = a;
        }

        int indexLong = 0, indexShort = 0, editsRemaining = 1;
        while (indexLong < longer.length() && indexShort < shorter.length()) {
            if (longer.charAt(indexLong) != shorter.charAt(indexShort)) {
                if (editsRemaining == 1) {
                    editsRemaining--;
                    indexLong++;
                    if (longer.length() == shorter.length()) {
                        indexShort++;
                    }
                } else {
                    return false;
                }
            } else {
                indexLong++;
                indexShort++;
            }
        }
        return true;
    }

    // Problem 1.6: String Compression
    static String stringCompression(String in) {
        if (in.length() <= 2) {
            return in;
        }

        char currChar = in.charAt(0);
        int currCount = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < in.length(); i++) {
            if (in.charAt(i) != currChar) {
                sb.append(currChar).append(currCount);
                currChar = in.charAt(i);
                currCount = 1;
            } else {
                currCount++;
            }
        }
        sb.append(currChar).append(currCount);
        String out = sb.toString();
        if (out.length() >= in.length()) {
            return in;
        }
        return out;
    }

    // Problem 1.7: Rotate Matrix
    static int[][] rotateMatrix(int[][] in) {
        /* Algorithm using new matrix
        int[][] out = new int[in.length][in.length];
        int newRow = 0, newCol = 0;
        for (int oldCol = 0; oldCol < in.length; oldCol++) {
            for (int oldRow = 0; oldRow < in.length; oldRow++) {
                out[newCol][newRow] = in[oldCol][oldRow];
                newCol++;
            }
            newRow++;
            newCol = 0;
        }
        return out;
        */

        // Algorithm performing operation in place
        // Review to understand answer better
        int max = in.length - 1;
        for (int i = 0; i < in.length / 2; i++) {
            int begin = i, end = max - i;
            for (int j = begin; j < end; j++) {
                int offset = j - begin;
                int temp = in[begin][j];
                in[begin][j] = in[end - offset][begin];
                in[end - offset][begin] = in[end][end - offset];
                in[end][end - offset] = in[j][end];
                in[j][end] = temp;
            }
        }
        return in;
    }

    // Problem 1.8: Zero Matrix
    static int[][] zeroMatrix(int[][] in) {
        ArrayList<Integer[]> zeroLocs = new ArrayList<>();
        int count = 0;
        for (int m = 0; m < in.length; m++) {
            for (int n = 0; n < in[0].length; n++) {
                if (in[m][n] == 0) {
                    zeroLocs.add(new Integer[] {m, n});
                }
            }
        }

        for (Integer[] pair : zeroLocs) {
            int col = pair[0];
            int row = pair[1];

            for (int i = 0; i < in[0].length; i++) {
                in[col][i] = 0;
            }
            for (int j = 0; j < in.length; j++) {
                in[j][row] = 0;
            }
        }

        return in;
    }

    // Problem 1.9: String Rotation
    static boolean stringRot(String s1, String s2) {
        if (s1.isEmpty() || s2.isEmpty() || s1.length() != s2.length()) {
            return false;
        }
        String concat = s2 + s2;
        return concat.contains(s1);
    }

    public static void main(String[] args) {
        System.out.println(stringRot("waterbottle", "erbottlewat"));
        System.out.println(stringRot("foo", "bar"));
        System.out.println(stringRot("hello", "world!"));
        System.out.println(stringRot("", "foobar"));
    }
}
