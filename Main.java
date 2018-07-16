package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static final int COUNT = 100;

    public static void main(String[] args) {
        Word_Generator wg = new Word_Generator();
        Learner l = new Learner(wg.full_list);
        Pocket_Learner pl = new Pocket_Learner(wg.full_list);
        //test(pl);
        compare(l, pl);
    }

    //positive results mean learn2 was better, negative resutls mean learn1 was better
    public static void compare(Learner learn1, Learner learn2){
        double total = 0;
        ArrayList<Integer> hist = new ArrayList<Integer>();
        for (int i = 0; i < COUNT; i++){

            learn1.learn();
            learn2.learn();
            total += learn1.getTrial_count() - learn2.getTrial_count();
            hist.add(learn1.getTrial_count() - learn2.getTrial_count());
            learn1.reset();
            learn2.reset();
        }
        System.out.println("mean: "+total/COUNT);
        System.out.println("median: "+hist.get(hist.size()/2));
        System.out.println("mode: " + mostCommonElement(hist));
    }

    public static void test(Learner learner){
        double total = 0;
        ArrayList<Integer> hist = new ArrayList<Integer>();

        for (int i = 0; i < COUNT; i++){

            learner.learn();
            total += learner.getTrial_count();
            hist.add(learner.getTrial_count());
            //System.out.println(l.getTrial_count());
            learner.reset();
        }

        System.out.println("mean: "+total/COUNT);
        System.out.println("median: "+hist.get(hist.size()/2));
        System.out.println("mode: " + mostCommonElement(hist));
    }

    private static Integer mostCommonElement(List<Integer> list) {

        Map<Integer, Integer> map = new HashMap<Integer, Integer>();

        for(int i=0; i< list.size(); i++) {

            Integer frequency = map.get(list.get(i));
            if(frequency == null) {
                map.put(list.get(i), 1);
            } else {
                map.put(list.get(i), frequency+1);
            }
        }

        Integer mostCommonKey = null;
        int maxValue = -1;
        for(Map.Entry<Integer, Integer> entry: map.entrySet()) {

            if(entry.getValue() > maxValue) {
                mostCommonKey = entry.getKey();
                maxValue = entry.getValue();
            }
        }

        return mostCommonKey;
    }
}
