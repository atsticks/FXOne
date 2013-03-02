package org.fxone.ui.rt.components.splash;

import java.util.concurrent.atomic.AtomicInteger;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.fxone.ui.annot.UIComponent;
import org.fxone.ui.rt.SplashScreen;
import org.fxone.ui.rt.components.AbstractFXMLComponent;

import com.sun.javafx.tk.Toolkit;

@UIComponent(fxmlLocation = "/org/fxone/ui/rt/components/splash/SplashScreen.fxml")
public class SimpleSplashScreen extends AbstractFXMLComponent implements
		SplashScreen {

	private int maxProgressValue = 100;

	@FXML
	private Label progressLabel;

	@FXML
	private ProgressBar progressBar;

	private Stage stage = new Stage();

	private Scene splashScene;

	private ProgressTask task = new ProgressTask();

	private volatile boolean finished = false;

	private volatile AtomicInteger currentProgress = new AtomicInteger();

	public SimpleSplashScreen() {
		super();
		splashScene = new Scene(this);
		stage.setScene(splashScene);
		stage.centerOnScreen();
		stage.initStyle(StageStyle.UNDECORATED);
		progressBar.progressProperty().bind(task.progressProperty());
		new Thread(task).start();
	}

	public int getMaxProgressValue() {
		return maxProgressValue;
	}

	public void setMaxProgress(int maxProgressValue) {
		this.maxProgressValue = maxProgressValue;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.fxone.ui.rt.components.splash.SplashScreen#updateProgress(java.lang
	 * .String, java.lang.Integer)
	 */
	@Override
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.fxone.ui.rt.components.splash.SplashScreen#setFinished()
	 */
	@Override
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
					updateProgress(progress, maxProgressValue);
				}
				Thread.sleep(100L);
			}
			return null;
		}
	}

	@Override
	public void init(Stage stage) {
		stage.show();
	}

	@Override
	public void show() {
		stage.show();
	}

	@Override
	public void hide() {
		if (Toolkit.getToolkit().isFxUserThread()) {
			stage.hide();
		} else {
			Toolkit.getToolkit().defer(new Runnable() {
				@Override
				public void run() {
					stage.hide();
				}
			});
		}
	}

}
