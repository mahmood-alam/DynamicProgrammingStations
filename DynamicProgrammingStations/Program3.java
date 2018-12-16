/*
 * Name: <your name>
 * EID: <your EID>
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Your solution goes in this class.
 * 
 * Please do not modify the other files we have provided for you, as we will use
 * our own versions of those files when grading your project. You are
 * responsible for ensuring that your solution works with the original version
 * of all the other files we have provided for you.
 * 
 * That said, please feel free to add additional files and classes to your
 * solution, as you see fit. We will use ALL of your additional files when
 * grading your solution.
 */
public class Program3 extends AbstractProgram3 {
    /**
     * Determines a solution to the optimal antenna range for the given input set. Study the
     * project description to understand the variables which represent the input to your solution.
     *
     * @return Updated TownPlan town with the optimal solution
     */
    @Override
    public TownPlan OptimalRange(TownPlan town) {
        /* TODO implement this function */
        float[][] arr = new float[town.getStationCount()][town.getHouseCount()];
        ArrayList<Float> houses = town.getHousePosition();
        for(int i=0; i<arr.length; i++){
            for(int j=0; j<arr[0].length; j++){
                if(j==0 || j<=i){
                    arr[i][j] = 0;
                }
                else if(i==0){
                    arr[i][j] = (houses.get(j) - houses.get(0))/2;
                }
                else{
                    float c = Math.max(arr[i-1][1], (houses.get(j)-houses.get(2))/2);
                    for(int k=2; k<i; k++){
                        Math.min(c, Math.max(arr[i-1][k], (houses.get(j)-houses.get(k+1))/2));
                    }
                    arr[i][j] = c;
                }

            }
        }
        town.setRange(arr[arr.length-1][arr[0].length-1]);
        return town; /* TODO remove this line */
    }

    /**
     * Determines a solution to the set of base station positions that optimise antenna range for the given input set. Study the
     * project description to understand the variables which } represent the input to your solution.
     *
     * @return Updaed TownPlan town with the optimal solution
     */
    @Override
    public TownPlan OptimalPosBaseStations(TownPlan town) {
        /* TODO implement this function */
        ArrayList<Float> houses = town.getHousePosition();
        ArrayList<Float> result = new ArrayList<>();
        float check, range = OptimalRange(town).getRange();
        int i=0, stations = town.getStationCount();
        do{
            check = houses.get(i)+range;
            if(check>houses.get(i+1)){
                result.add((houses.get(i)+houses.get(i+1))/2);
            } else {
                result.add(check);
            }
            stations--;
            i++;
            if(Math.abs(houses.get(i)-result.get(result.size()-1))<range){
                i++;
            }
        } while(i<houses.size()-1);
        if(stations>0){
            result.add(houses.get(houses.size()-1) - range);
        }
        town.setPositionBaseStations(result);
        return town; /* TODO remove this line */
    }
}
