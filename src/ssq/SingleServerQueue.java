package ssq;

import simulation.Simulation;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

public class SingleServerQueue extends Simulation<SingleServerQueue> {

  //main: [0]: seed, [1]: time to execute

  private final static double serviceTime = 0.25;
  private final double endTime;
  protected TreeMap<Double, Integer> timeSet = new TreeMap<>();
  private int populationNo = 0;
  private Double sum = 0.0;

  public SingleServerQueue(double endTime) {
    this.endTime = endTime;
  }

  public int getPopulationNo() {
    return populationNo;
  }

  protected void decrease() {
    populationNo--;
  }

  protected void increase() {
    populationNo++;
  }

  protected void addSet(double interval, int pop) {
    timeSet.put(interval, pop);
  }

  private Double getMean() {
    Set<Entry<Double, Integer>> entrySet = timeSet.entrySet();
    Iterator<Entry<Double, Integer>> itr = entrySet.iterator();
    Double initKey = timeSet.firstKey();
    Integer initValue = timeSet.firstEntry().getValue();

    for (Entry<Double, Integer> entry : entrySet) {
      if (!entry.getKey().equals(initKey)) {
        sum += (entry.getKey() - initKey) * initValue;
        initKey = entry.getKey();
        initValue = entry.getValue();
      }
    }
    sum += (endTime - initKey) * initValue;
    return sum / endTime;
  }

  @Override
  protected boolean stop() {
    return (getCurrentTime() + serviceTime) >= endTime;
  }

  @Override
  protected SingleServerQueue getState() {
    return this;
  }

  public static void main(String[] args) {
    long seed = Long.parseLong(args[0]);
    Random initArr = new Random(seed);
    Double nxtArr = initArr.nextDouble();
    Double endTime = Double.parseDouble(args[1]);
    SingleServerQueue init = new SingleServerQueue(endTime);
    init.schedule(new ArrivalEvent(initArr), nxtArr);
    init.simulate();
    System.out.println("SIMULATION COMPLETE - the mean queue length was " + init.getMean());
  }
}
