package hu.nive.ujratervezes.kepesitovizsga.digits;

import java.util.ArrayList;
import java.util.List;

public class Digits {

    public int getNumbers(){
       List<Integer> numbers = new ArrayList<>();
        for(int i = 10; i<100; i++){
            if(Math.abs(i % 10  - i/10) == 5){
                numbers.add(i);
            }
        }
        return numbers.size();

    }
}
