package ssq;

import simulation.Event;

public class DepartEvent implements Event<SingleServerQueue> {
  private final static double serviceTime = 0.25;

  @Override
  public void invoke(SingleServerQueue ssq) {
    ssq.decrease();
    System.out.println("Departure at " + ssq.getCurrentTime() + ", new population = " + ssq.getPopulationNo());
    if (ssq.getPopulationNo() > 0) {
      ssq.schedule(new DepartEvent(), serviceTime);
    }
    ssq.addSet(ssq.getCurrentTime(), ssq.getPopulationNo());
  }
}
