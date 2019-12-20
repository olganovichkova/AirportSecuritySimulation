package main.java.edu.isu.cs2235;

import java.util.Random;
import java.util.Scanner;


public class Driver {

    public static final int MAX_RANDOM_VALUE = 20;
    public static final int AVG_SIMULATION = 50;

    public static Random random = new Random();

    public static int randomInt(){
        return random.nextInt(MAX_RANDOM_VALUE);
    }


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int maxNumQueues, arrivalRate;

        System.out.print("Enter number of people :  ");
        while (!input.hasNextInt()) {
            input.nextLine();
            System.out.print("Invalid. Enter valid number of people :  ");
        }
        arrivalRate = input.nextInt();

        System.out.print("Enter maximum number of lines :  ");
        while (!input.hasNextInt()) {
            input.nextLine();
            input.next();
            System.out.print("Invalid. Enter valid maximum number of lines :  ");
        }
        maxNumQueues = input.nextInt();

        System.out.println("Arrival Rate : " + arrivalRate);
        for(int i = 0; i < maxNumQueues; i++){
            Simulation simulation = new Simulation(arrivalRate, i + 1);

            int sumAvgTime = 0;
            for (int j = 0; j < AVG_SIMULATION; j++) {
                simulation.runSimulation();
                sumAvgTime += simulation.getAvgTime();
            }
            int avgTime = sumAvgTime / AVG_SIMULATION;

            System.out.println("Average wait time using " + (i+1) + " queue(s) : " + avgTime + " minute(s)");
        }
    }
}
