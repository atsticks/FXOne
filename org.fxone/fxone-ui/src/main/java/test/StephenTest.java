package test;
//
//import java.util.Date;
//
//import javafx.application.Application;
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
//import javafx.scene.Scene;
//import javafx.scene.control.ChoiceBox;
//import javafx.scene.layout.VBox;
//import javafx.stage.Stage;
//
//public class StephenTest extends Application {
//
//	private static final class ChoicesPane extends VBox {
//
//		private ChoiceBox<String> box1 = new ChoiceBox<String>();
//		private ChoiceBox<String> box2 = new ChoiceBox<String>();
//
//		public ChoicesPane() {
//			box1.getItems().addAll("Item1", "Item2");
//			box1.getSelectionModel().selectedItemProperty()
//					.addListener(new ChangeListener() {
//						@Override
//						public void changed(ObservableValue owner,
//								Object newValue, Object oldValue) {
//							box2.getItems().setAll("ab", "bc", String.valueOf(new Date()));
//						}
//					});
//			getChildren().addAll(box1, box2);
//		}
//	}
//
//	@Override
//	public void start(Stage stage) throws Exception {
//		ChoicesPane pane = new ChoicesPane();
//		Scene scene = new Scene(new ChoicesPane());
//		stage.setScene(scene);
//		stage.show();
//	}
//
//	public static void main(String[] args) {
//		Application.launch(StephenTest.class, args);
//	}
//
//}
