package operations.misc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ParseList {

    public ArrayList<String> filteredWords() {
        ArrayList<String> dirtyWords = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("sex.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                dirtyWords.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dirtyWords;
    }

    public boolean stringContainsItemFromList(String inputStr, ArrayList<String> items) {
        return items.parallelStream().anyMatch(inputStr::contains);
    }


}
