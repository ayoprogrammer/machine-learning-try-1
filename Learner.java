package com.company;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Nicholas on 7/10/2018.
 */
public class Learner {
    protected int[] vals;
    protected ArrayList<String> words;
    protected ArrayList<Integer> scores;
    protected ArrayList<Integer[]> occurences;
    protected int size;
    protected int trial_count;

    public Learner(ArrayList<String[]> input){
        trial_count = 0;
        vals = new int[5];
        size = input.size();
        words = new ArrayList<String>();
        scores = new ArrayList<Integer>();
        occurences = new ArrayList<Integer[]>();
        for (int i = 0; i < size; i++){
            words.add(input.get(i)[0]);
            scores.add(Integer.parseInt(input.get(i)[1]));
            occurences.add(get_occurence(words.get(i)));
        }
    }

    public void learn(){
        boolean notDone = true;
        Random r = new Random();
        while (notDone){

            boolean failed = false;
            int guess, error, index;
            for (int i = 0; i < size; i++){
                Integer[] occ = occurences.get(i);
                guess = vals[0] * occ[0] + vals[1] * occ[1] + vals[2] * occ[2] + vals[3] * occ[3] + vals[4] * occ[4];
                error = guess - scores.get(i);
                if (error != 0) {
                    failed = true;
                    ArrayList<Integer> sample = new ArrayList<Integer>();
                    for (int j = 0; j < 5; j++){
                        if (occ[j] != 0){
                            sample.add(j);
                        }
                    }

                    index = r.nextInt(sample.size());
                    index = sample.get(index);
                    vals[index] -= error/sample.size();
                }
                //extreme detail
                //print();
            }
            if (!failed) notDone = false;
            trial_count++;
            //print();
        }
    }

    private Integer[] get_occurence(String s){
        Integer[] occ = {0,0,0,0,0};

        for (int i = 0; i < 5; i++){
            if (s.charAt(i) == 'a') occ[0]++;
            if (s.charAt(i) == 'b') occ[1]++;
            if (s.charAt(i) == 'c') occ[2]++;
            if (s.charAt(i) == 'd') occ[3]++;
            if (s.charAt(i) == 'e') occ[4]++;
        }

        return occ;
    }

    public void print(){
        System.out.println("trial: " + trial_count);
        for (int i = 0; i < 5; i++){
            System.out.println(vals[i]);
        }
        System.out.println();
    }

    public int getTrial_count(){
        return trial_count;
    }

    public void reset(){
        trial_count = 0;
        vals = new int[5];
    }
}
