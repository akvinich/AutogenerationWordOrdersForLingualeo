package read;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Reader {

    private List<String> listWords = new ArrayList<>();

    private Map<String, List<String>> alphabetMap = new HashMap<>();

    public List<String> readWords(String nameFile){
        String word = "";
        File myFile = new File(nameFile);
        try(FileReader fr = new FileReader(myFile); Scanner scan = new Scanner(fr);){
            while (scan.hasNext()) {
                word = scan.next();
                listWords.add(word);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return listWords;
    }

    public Map<String, List<String>> readWordsOrderByAlphabet(String nameFile){
        String word = "";
        File myFile = new File(nameFile);
        try(FileReader fr = new FileReader(myFile); Scanner scan = new Scanner(fr);){
            while (scan.hasNext()) {
                word = scan.next();
                String literal = word.substring(0,1).toUpperCase();
                if (alphabetMap.containsKey(literal)){
                    List<String> list = alphabetMap.get(literal);
                    list.add(word);
                    alphabetMap.put(literal, list);
                }
                else {
                    List<String> list = new ArrayList<>();
                    list.add(word);
                    alphabetMap.put(literal, list);
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return alphabetMap;
    }


}
