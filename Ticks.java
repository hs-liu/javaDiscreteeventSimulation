package ticks;

import simulation.Simulation;

public class Ticks extends Simulation<Ticks> {

  private final static double elapsedTime = 1.0;
  private final double endTime;

  public Ticks(double end) {
    this.endTime = end;
  }

  @Override
  protected boolean stop() {
    return endTime == (getCurrentTime() + elapsedTime);
  }

  @Override
  protected Ticks getState() {
    return this;
  }

  public static void main(String[] args) {
    double endTime = Double.parseDouble(args[0]);
    Ticks tick1 = new Ticks(endTime);
    tick1.schedule(new TicksEvent(), elapsedTime);
    tick1.simulate();
  }
}
