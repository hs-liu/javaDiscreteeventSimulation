package ticks;

import simulation.Event;

public class TicksEvent implements Event<Ticks> {

  private static final double offset = 1.0;

  @Override
  public void invoke(Ticks simulation) {
    System.out.println("Tick at: " + simulation.getCurrentTime());
    simulation.schedule(new TicksEvent(), offset);
  }
}
