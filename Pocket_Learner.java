package com.company;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Nicholas on 7/15/2018.
 */
public class Pocket_Learner extends Learner {

    public Pocket_Learner(ArrayList<String[]> input){
        super(input);
    }

    @Override
    public void reset(){
        super.reset();
    }

    @Override
    public void learn(){
        boolean notDone = true;
        while (notDone){
            boolean failed = false;
            int guess, error;

            //learning loop
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
                    vals = get_new_vals(sample, occ, error, i);
                }
            }

            if (!failed) notDone = false;
            trial_count++;
            //print();
            //if (trial_count == 50) notDone = false;
        }
    }

    private int[] get_new_vals(ArrayList<Integer> sample, Integer[] occ, int error, int i){
        Random r = new Random();
        int[][] test_vals = new int[sample.size()][5];
        int test_guess, test_error;
        int[] error_scores = new int[sample.size()];
        int error_score_sum = 0;

        for (int j = 0; j < sample.size(); j++){
            test_vals[j] = vals;
            test_vals[j][sample.get(j)] -= error/sample.size();
            test_guess = test_vals[j][0] * occ[0] + test_vals[j][1] * occ[1] + test_vals[j][2] * occ[2] + test_vals[j][3] * occ[3] + test_vals[j][4] * occ[4];
            test_error = test_guess - scores.get(i);
            if (test_error == 0) return test_vals[j];
            error_scores[j] = test_error * test_error;
            error_score_sum += error_scores[j];
        }
        int sum = 0;
        for (int j = 0; j < sample.size(); j++){
            error_scores[j] = error_score_sum/(error_scores[j]);
            sum += error_scores[j];
        }

        int decider = r.nextInt(sum);
        sum = 0;
        int index = 0;
        for (int j = 0; j < sample.size(); j++){
            if (decider <= sum){
                index = j;
                break;
            }
            sum += error_scores[j];
        }
        return test_vals[index];
    }
}
