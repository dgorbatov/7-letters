import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Words {
   private List<String> words;
   
   Words(String path) {
    words = new ArrayList<>();
    readInWords(path);
   }

   private void readInWords(String path) {
    try {
        File myObj = new File(path);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
          String data = myReader.nextLine();
          words.add(data);
        }
        myReader.close();
      } catch (FileNotFoundException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
   }

   public int testCombo(String letters) {
        int ret = 0;
        List<Character> lettersList = new ArrayList<Character>();
        int j;

        for (int i=0; i < letters.length(); ++i) {
            lettersList.add(letters.charAt(i));
        }

        for (int i=0; i < words.size(); ++i) {
            Boolean wordPossible = true;
            for (j=0; j < words.get(i).length(); ++j) {
                if (!lettersList.contains(words.get(i).charAt(j))) {
                    wordPossible = false;
                }
            }

            if (wordPossible) {
                ret += 1;
            }
        }

        return ret;
   }

   public String solve(int letters) {
    return solve("", letters);
   }

   private String solve(String letters, int numLetters) {
    if (letters.length() == numLetters) {
        System.out.println(letters);
         return letters;
    } else {
        int localCurrMax = -1;
        String retLetters = "";
        for(char c = 'a'; c <= 'z'; ++c) {
            String newLetters = solve(letters + c, numLetters);

            if (testCombo(newLetters) > localCurrMax) {
             localCurrMax = testCombo(newLetters);   
             retLetters = newLetters;
            }
        }

        return retLetters;
    }
   }
}
