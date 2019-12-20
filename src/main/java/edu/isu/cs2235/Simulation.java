package main.java.edu.isu.cs2235;

import main.java.edu.isu.cs2235.structures.impl.LinkedQueue;

import java.util.Random;
import java.util.Vector;

/**
 * Class representing a wait time simulation program.
 *
 * @author Isaac Griffith
 * @author
 */
public class Simulation {

    private int arrivalRate;
    private int maxNumQueues;
    private Random r;
    private int numIterations = 50;
    public int avgTime;
    private static final int TOTAL_PERIOD_OF_TIME = 720;    //measured in minutes
    private static final int SERVICE_RATE_PER_MINUTE = 2;   //the number of people that pass through every minute
    private int[] avgTimes;     //stores the average time for each minute

    /**
     * Constructs a new simulation with the given arrival rate and maximum number of queues. The Random
     * number generator is seeded with the current time. This defaults to using 50 iterations.
     *
     * @param arrivalRate the integer rate representing the maximum number of new people to arrive each minute
     * @param maxNumQueues the maximum number of lines that are open
     */
    public Simulation(int arrivalRate, int maxNumQueues) {
        this.arrivalRate = arrivalRate;
        this.avgTimes = new int[TOTAL_PERIOD_OF_TIME];

        this.maxNumQueues = maxNumQueues;
        r = new Random();
    }

    /**
     * Constructs a new siulation with the given arrival rate and maximum number of queues. The Random
     * number generator is seeded with the provided seed value, and the number of iterations is set to
     * the provided value.
     *
     * @param arrivalRate the integer rate representing the maximum number of new people to arrive each minute
     * @param maxNumQueues the maximum number of lines that are open
     * @param numIterations the number of iterations used to improve data
     * @param seed the initial seed value for the random number generator
     */
    public Simulation(int arrivalRate, int maxNumQueues, int numIterations, int seed) {
        this(arrivalRate, maxNumQueues);
        r = new Random(seed);
        this.numIterations = numIterations;
        this.avgTimes = new int[TOTAL_PERIOD_OF_TIME];

    }


    public int getAvgTime(){
        return avgTime;
    }

    /**
     * Executes the Simulation
     */

    public void runSimulation(){
        Vector<LinkedQueue<Integer>> queues = new Vector<>();
        for(int i = 0; i < maxNumQueues; i++){
            queues.add(new LinkedQueue<Integer>());
        }
        int totalPeopleExited = 0;
        avgTime = 0;

        for(int time = 0; time < TOTAL_PERIOD_OF_TIME; time++){
            int peopleEntering = getRandomNumPeople(arrivalRate);
            for (int i = 0; i < peopleEntering; i++) {
                LinkedQueue<Integer> queue = shortestQueue(queues);
                queue.offer(time);
            }
            //people exiting the lines
            for(LinkedQueue<Integer> queue: queues) {
                for (int j = 0; j < SERVICE_RATE_PER_MINUTE; j++) {
                    if (queue.isEmpty()) {
                        break;
                    }
                    int enteredTime = (Integer) queue.poll();
                    int waitTime = time - enteredTime + 1;
                    avgTime += waitTime;
                    totalPeopleExited++;
                }
            }
        }
        avgTime /= totalPeopleExited;
    }

    public LinkedQueue<Integer> shortestQueue(Vector<LinkedQueue<Integer>> queues){
        int minSize = queues.get(0).size();
        LinkedQueue<Integer> shortestQ = queues.get(0);
        for(LinkedQueue<Integer> queue: queues){
            int size = queue.size();
            if(size < minSize){
                minSize = size;
                shortestQ = queue;
            }
        }
        return shortestQ;
    }


    public void runSimulation2(){
        LinkedQueue<Integer> queue = new LinkedQueue<>();

        //people entering the line
        for(int time = 0; time < TOTAL_PERIOD_OF_TIME; time++){
            int peopleEntering = getRandomNumPeople(arrivalRate);
            for(int i = 0; i < peopleEntering; i++){
                queue.offer(time);
            }
        }

        //people exiting the line
        int totalPeopleExited = 0;
        avgTime = 0;
        for(int exitTime = 0; exitTime < TOTAL_PERIOD_OF_TIME; exitTime++){
            for(int i = 0; i < SERVICE_RATE_PER_MINUTE; i++){
                int enteredTime = (Integer) queue.poll();
                int waitTime = exitTime - enteredTime;
                avgTime += waitTime;
                totalPeopleExited++;
            }
        }
        avgTime /= totalPeopleExited;




    }




    public void runSimulation1() {
        int peopleEntering;     //number of people entering at each point of time
        int totalPeople = 0;    //total number of people standing in line at each point of time
        int sumWaitTime = 0;    //average wait time for the number of people in line at each point of time
        int tmp = 0;
        int m = 1;      //wait time for each person
        for(int i = 0; i < TOTAL_PERIOD_OF_TIME; i++){
            peopleEntering = getRandomNumPeople(arrivalRate);
            if(i == 0){
                totalPeople = peopleEntering;
            }
            else {
                totalPeople += peopleEntering - SERVICE_RATE_PER_MINUTE;
            }
            m = 1;
            for(int j = 1; j <= totalPeople; j++){
                sumWaitTime += m;
                if((j % SERVICE_RATE_PER_MINUTE) == 0){
                    m++;
                }
            }
            tmp = sumWaitTime/totalPeople;
            avgTimes[i] = tmp;
        }
        for(int k = 0; k < avgTimes.length; k++){
            avgTime += avgTimes[k];
        }
        avgTime /= TOTAL_PERIOD_OF_TIME;


        //throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * returns a number of people based on the provided average
     *
     * @param avg The average number of people to generate
     * @return An integer representing the number of people generated this minute
     */
    //Don't change this method.
    private static int getRandomNumPeople(double avg) {
        Random r = new Random();
        double L = Math.exp(-avg);
        int k = 0;
        double p = 1.0;
        do {
            p = p * r.nextDouble();
            k++;
        } while (p > L);
        return k - 1;
    }
}
