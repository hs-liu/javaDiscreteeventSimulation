package simulation;

public class ScheduleEvent<S> implements Comparable<ScheduleEvent> {
  private final Event event;
  private final Double scheduledTime;

  public ScheduleEvent(Event event, Double time) {
    this.event = event;
    this.scheduledTime = time;
  }

  public Double getTime() {
    return scheduledTime;
  }

  public Event getEvent() {
    return event;
  }

  @Override
  public int compareTo(ScheduleEvent other) {
    return Double.compare(getTime(), other.getTime());
  }
}
