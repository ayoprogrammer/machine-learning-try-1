package com.company;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Nicholas on 7/9/2018.
 */
public class Word_Generator {
    private int size = 100;
    public ArrayList<String[]> word_list;
    public ArrayList<String[]> full_list;
    private Character[] char_list = {'a','b','c','d','e'};

    public Word_Generator(){
        word_list = new ArrayList<String[]>();
        full_list = new ArrayList<String[]>();
        Random r = new Random();

        for (int i = 0; i < size; i++){
            StringBuilder word = new StringBuilder();
            String[] data = new String[2];
            int letterIndex;
            int sum = 5;
            for (int j = 0; j < 5; j++){
                letterIndex = r.nextInt(5);
                word.append(char_list[letterIndex]);
                sum += letterIndex;
            }
            data[0] = word.toString();
            data[1] = Integer.toString(sum);
            word_list.add(data);
        }

        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                for (int k = 0; k < 5; k++){
                    for (int l = 0; l < 5; l++){
                        for (int m = 0; m < 5; m++){
                            StringBuilder word = new StringBuilder();
                            String[] data = new String[2];
                            int sum = 5;
                            word.append(char_list[i]);
                            word.append(char_list[j]);
                            word.append(char_list[k]);
                            word.append(char_list[l]);
                            word.append(char_list[m]);
                            sum += i+j+k+l+m;
                            data[0] = word.toString();
                            data[1] = Integer.toString(sum);
                            full_list.add(data);
                        }
                    }
                }
            }
        }
    }
}
