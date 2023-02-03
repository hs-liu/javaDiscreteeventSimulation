package simulation;

import java.util.PriorityQueue;
import java.util.Queue;

public abstract class Simulation<S> {
  protected Double virtualTime = 0.0;
  protected Queue<ScheduleEvent> diary = new PriorityQueue<>();

  protected abstract boolean stop();

  protected abstract S getState();

  public Double getCurrentTime() {
    return virtualTime;
  }

  public void schedule(Event e, Double offset) {
    diary.add(new ScheduleEvent(e, virtualTime + offset));
  }

  public void simulate() {
    while (!diary.isEmpty() && !stop()) {
      ScheduleEvent currentEvent = diary.poll();
      virtualTime = currentEvent.getTime();
      currentEvent.getEvent().invoke(this);
    }
  }
}
