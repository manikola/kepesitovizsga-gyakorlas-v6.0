package hu.nive.ujratervezes.kepesitovizsga.finelongword;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FineLongWord {

    public char[] readFineLongWordFromFileAndGetItInAnArray(String fileName){
        char[] chars = null;
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = Files.newBufferedReader(Path.of("src/main/resources/" + fileName))) {
            String line;
            sb.append(br.readLine());
            while ((line = br.readLine()) != null) {
                char[] arr = line.toCharArray();
                sb.append(arr[arr.length - 1]);
                    chars = sb.toString().toCharArray();


            }
        } catch (IOException e) {
            throw new IllegalStateException("Can not read file", e);
        }
        return chars;

    }


}

