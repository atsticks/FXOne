package org.fxone.ui.rt.components.dock;
//
//import java.util.List;
//
//import javafx.scene.control.Accordion;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.control.TitledPane;
//import javafx.scene.layout.Priority;
//import javafx.scene.layout.VBox;
//
//import javax.enterprise.event.Observes;
//import javax.inject.Inject;
//import javax.inject.Singleton;
//
//import org.homemotion.di.Container;
//import org.homemotion.ui.fx.framework.model.NavigationPane;
//import org.homemotion.ui.fx.framework.widget.view.ViewNavigationToolbar;
//import org.homemotion.ui.model.cmd.Notification;
//import org.homemotion.ui.model.nav.NavigationManager;
//import org.homemotion.ui.model.nav.NavigationNode;
//
//@Singleton
//public class DockNavigation extends VBox implements NavigationPane{
//
//	private ScrollPane scrollPane = new ScrollPane();
//	private Accordion navigationPane = new Accordion();
//	private VBox navigationContainer = new VBox();
//	private TitledPane navigationAccordionPane = new TitledPane("Navigation",
//			navigationContainer);
//	private NavigationNode currentNavigation;
//
//	@Inject
//	public DockNavigation(ViewNavigationToolbar viewNavigation) {
//		super();
//		setId("navigation");
//		navigationPane.getPanes().add(navigationAccordionPane);
//		navigationAccordionPane.setCollapsible(true);
//		navigationAccordionPane.setExpanded(true);
//		scrollPane.setContent(navigationPane);
//		VBox.setVgrow(scrollPane, Priority.ALWAYS);
//		getChildren().addAll(viewNavigation, scrollPane);
//		navigate((String) null);
//		setPrefWidth(USE_COMPUTED_SIZE);
//	}
//
//	public void navigate(final String path) {
//		NavigationNode nav = null;
//		if (path != null) {
//			nav = Container.getInstance(NavigationManager.class).getNavigation(
//					path);
//		}
//		if (nav == null) {
//			nav = Container.getInstance(NavigationManager.class)
//					.getRootNavigation();
//		}
//		navigate(nav);
//	}
//
//	public NavigationNode getCurrentNavigation() {
//		return currentNavigation;
//	}
//
//	public void navigate(final NavigationNode nav) {
//		if (nav == null) {
//			throw new IllegalArgumentException("Navigation node required.");
//		}
//		navigateInternal(nav);
//	}
//
//	private void navigateInternal(NavigationNode nav) {
//		navigationContainer.getChildren().clear();
//		currentNavigation = nav;
//		List<NavigationNode> nodes = nav.getChildren();
//		for (NavigationNode cmd : nodes) {
//			if (cmd.isLeaf()) {
//				navigationContainer.getChildren().add(new DockCommand(cmd));
//			} else {
//				navigationContainer.getChildren().add(
//						new DockNavigationCommand(cmd));
//			}
//		}
//	}
//
//	public void notified(@Observes AbstractNotification cmd) {
//		if ("Navigate".equals(cmd.getFamily())) {
//			if ("to".equals(cmd.getMethod())) {
//				String target = cmd.getParam(0, String.class);
//				if (target != null) {
//					navigate(target);
//				}
//			} else if ("up".equals(cmd.getMethod())) {
//				navigateUp();
//			} else if ("home".equals(cmd.getMethod())) {
//				NavigationNode node = Container.getInstance(
//						NavigationManager.class).getRootNavigation();
//				navigate(node);
//			}
//		}
//	}
//
//	private void navigateUp() {
//		if (currentNavigation != null) {
//			NavigationNode node = currentNavigation.getParent();
//			if (node != null) {
//				navigate(node);
//			}
//		}
//	}
//
//}
