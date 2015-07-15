package ui;

public class SystemOutput implements Output {

	@Override
	public void println(final String message) {
		System.out.println(message);
	}

}
