import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Wordle {
    // Colors for letters
    public static final String ANSI_UNBOLD = "\u001B[21m";
    public static final String ANSI_BOLD = "\u001B[1m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) throws IOException {

        List<String> dict = new ArrayList<String>(); // list that holds strings of a file

        BufferedReader bf = new BufferedReader(new FileReader("dict.txt")); // load data from file
        String line = bf.readLine(); // read entire line as string
        Scanner scanner = new Scanner(System.in); // create a scanner object

        while (line != null) { // checking for end of file
            dict.add(line);
            line = bf.readLine();
        }

        // closing bufferreader object
        bf.close();

        // select a random word from dict
        String word = dict.get((int) (Math.random() * (dict.size() - 1)));

        // input attempt
        String userWord = "";
        for (int i = 1; i <= 7; i++) {

            if (i == 7) { // exit the program if exceed the attempt limit
                System.out.println("Sorry, You failed! The word was: " + ANSI_BOLD + ANSI_GREEN + word + ANSI_RESET);
                break;
            }

            userWord = scanner.nextLine();

            if (userWord.length() != 5) { // ensure input length is 5
                System.out.println("The length of word must be five!");
                continue;
            } else if (!isContains(dict, userWord)) { // checks whether the word is in dictionary or not
                System.out.println("Word does not exist in the dictionary!");
                continue;
            } else if (userWord.equals(word)) { // if the words match exit the program
                if (i == 1)
                    System.out.println("Congratulations! You guess right in " + i + "st shot!");
                else if (i == 2)
                    System.out.println("Congratulations! You guess right in " + i + "nd shot!");
                else if (i == 3)
                    System.out.println("Congratulations! You guess right in " + i + "rd shot!");
                else
                    System.out.println("Congratulations! You guess right in " + i + "th shot!");
                break;

            } else { // if the conditions are met, compare the letters

                char[] ch = userWord.toCharArray();

                for (int j = 0; j < 5; j++) {
                    if (userWord.charAt(j) == word.charAt(j)) {
                        System.out.print(ANSI_BOLD + ANSI_GREEN + ch[j] + ANSI_RESET);
                    } else if (word.indexOf(userWord.charAt(j)) != -1) {
                        System.out.print(ANSI_BOLD + ANSI_YELLOW + ch[j] + ANSI_RESET);
                    } else {
                        System.out.print(ANSI_BOLD + ch[j] + ANSI_RESET);
                    }
                }
                System.out.println();
            }
        }
        scanner.close();
    }

    // checks whether the word is in dictionary or not. If contains it returns true
    public static boolean isContains(List<String> dict, String word) {
        for (String string : dict) {
            if (string.equals(word))
                return true;
        }
        return false;
    }
}