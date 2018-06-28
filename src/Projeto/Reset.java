package Projeto;

import java.util.Timer;
import java.util.TimerTask;

public class Reset {

	private Clicker clicker = Clicker.getInstance();
	private Timer time = new Timer();

	public Reset(Clicker clicker) {
		this.clicker = clicker;
	}

	public void timer(long delay) {
		time.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				System.out.println("OI");
				clicker.accumulate(1);
			}
		}, delay, delay);
	}

	public void cancelTimer() {
		time.cancel();
		time = new Timer();
	}

}
