package simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class World {
    private static final int DAYS_NO = 10;

    public static void main(String[] args) {
        System.out.println("Start");
        for(int i =0; i<DAYS_NO; i++){
            Simulation.simulateDay();
        }
        System.out.println("Stop");


    }


}