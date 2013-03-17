package org.fxone.ui.rt.components.clocks;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;

public class SimpleLabelClockAdapter {

	private Label targetLabel;
	private static final long TIME_PERIOD = 200L;
	private long lastTimeCall = 0;
	private SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss",
			Locale.GERMAN);

	private final AnimationTimer TIMER = new AnimationTimer() {
		@Override
		public void handle(long l) {
			long currentTime = System.currentTimeMillis();
			if (currentTime > lastTimeCall + TIME_PERIOD) {
				setClock();
				lastTimeCall = System.currentTimeMillis();
			}
		}
	};
	
	public SimpleLabelClockAdapter(Label targetLabel) {
		if(targetLabel==null){
			throw new IllegalArgumentException("targetLabel required.");
		}
		this.targetLabel = targetLabel;
		setClock();
		TIMER.start();
	}

	public void setClock() {
		this.targetLabel.setText(df.format(new Date()));
	}

	public void customize(String pattern, Locale locale) {
		df = new SimpleDateFormat(pattern,
				locale);
	}

	public void start() {
		TIMER.start();
	}
	
	public void stop() {
		TIMER.stop();
	}

}
