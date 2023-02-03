package ssq;

import simulation.Event;

import java.util.Random;

public class ArrivalEvent implements Event<SingleServerQueue> {
  private final Random randomTime;
  protected final static double serviceTime = 0.25;

  protected ArrivalEvent(Random randomTime) {
    this.randomTime = randomTime;
  }


  @Override
  public void invoke(SingleServerQueue ssq) {
    double nxtArr = randomTime.nextDouble();
    int population = ssq.getPopulationNo();
    ssq.increase();
    if (population == 0) {
      System.out.println("Arrival at " + ssq.getCurrentTime() + ", new population = " + ssq.getPopulationNo());
      ssq.schedule(new ArrivalEvent(randomTime), nxtArr);
      ssq.schedule(new DepartEvent(), serviceTime);
    } else {
      System.out.println("Arrival at " + ssq.getCurrentTime() + ", new population = " + ssq.getPopulationNo());
      ssq.schedule(new ArrivalEvent(randomTime), nxtArr);
    }
    ssq.addSet(ssq.getCurrentTime(), ssq.getPopulationNo());
  }
}
