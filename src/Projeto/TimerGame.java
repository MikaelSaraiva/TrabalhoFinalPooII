package Projeto;

import java.util.Timer;
import java.util.TimerTask;

public class TimerGame {

	private Timer time = new Timer();

	public TimerGame() {
	}

	public void timer(long delay, Clicker clicker) {
		time.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				clicker.accumulate(1);
			}
		}, delay, delay);
	}

	public void cancelTimer() {
		time.cancel();
		time = new Timer();
	}

}
