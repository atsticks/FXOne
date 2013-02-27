package org.fxone.ui.rt.components.splash;

import java.util.concurrent.atomic.AtomicInteger;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.inject.Named;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

import org.fxone.ui.rt.components.AbstractFXMLComponent;

import com.sun.javafx.tk.Toolkit;

@Dependent
@Default
@Named("splash-screen")
public class SplashScreen extends AbstractFXMLComponent {

	private int STEPS = 100;

	@FXML
	private Label progressLabel;

	@FXML
	private ProgressBar progressBar;

	private ProgressTask task = new ProgressTask();

	private volatile boolean finished = false;

	private volatile AtomicInteger currentProgress = new AtomicInteger();

	public SplashScreen() {
		super();
		progressBar.progressProperty().bind(task.progressProperty());
		new Thread(task).start();
	}

	public void updateProgress(final String msg, Integer progress) {
		if (progress != null) {
			this.currentProgress.set(progress);
		}
		if (Toolkit.getToolkit().isFxUserThread()) {
			progressLabel.setText(msg);
		} else {
			Toolkit.getToolkit().defer(new Runnable() {
				@Override
				public void run() {
					progressLabel.setText(msg);
				}
			});
		}
		try {
			Thread.sleep(200L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void setFinished() {
		this.finished = true;
	}

	public class ProgressTask extends Task<Void> {

		int progress = 0;

		@Override
		protected Void call() throws Exception {
			while (!finished) {
				if (progress < currentProgress.get()) {
					progress++;
					updateProgress(progress, STEPS);
				}
				Thread.sleep(100L);
			}
			return null;
		}
	}

}
