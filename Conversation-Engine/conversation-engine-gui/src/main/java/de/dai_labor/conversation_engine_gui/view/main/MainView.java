package de.dai_labor.conversation_engine_gui.view.main;

import java.net.URL;
import java.util.ResourceBundle;

import javax.inject.Singleton;

import de.saxsys.mvvmfx.FxmlView;
import de.saxsys.mvvmfx.InjectViewModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * The MainView is used for the navigation between the different view elements
 *
 * @author Marcel Engelmann
 *
 */
@Singleton
public class MainView implements FxmlView<MainViewModel>, Initializable {

	@InjectViewModel
	private MainViewModel viewModel;
	@FXML
	private BorderPane mainBorderPane;
	@FXML
	private VBox contentArea;
	@FXML
	private MenuItem closeMenuItem;
	@FXML
	private MenuItem newMenuItem;
	@FXML
	private MenuItem openMenuItem;
	@FXML
	private MenuItem exportMenuItem;
	@FXML
	private MenuItem saveMenuItem;
	@FXML
	private MenuItem saveAsMenuItem;
	@FXML
	private Button navButtonDialogue;
	@FXML
	private Button navButtonDialogueData;
	@FXML
	private Button navButtonSettings;
	@FXML
	private Button navButtonSimulation;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// set the new view to the back
		this.contentArea.setViewOrder(1000);
		this.newMenuItem.setOnAction(this.viewModel::newFile);
		this.openMenuItem.setOnAction(this.viewModel::openFile);
		this.exportMenuItem.setOnAction(this.viewModel::exportFile);
		this.saveMenuItem.setOnAction(this.viewModel::saveFile);
		this.saveAsMenuItem.setOnAction(this.viewModel::saveAsFile);
		this.closeMenuItem.setOnAction(this.viewModel::closeApplication);
		this.navButtonDialogue.setOnAction(this.viewModel::setView);
		this.navButtonDialogueData.setOnAction(this.viewModel::setView);
		this.navButtonSettings.setOnAction(this.viewModel::setView);
		this.navButtonSimulation.setOnAction(this.viewModel::setView);
		this.viewModel.getCurrentViewProperty().addListener(event -> this.updateView());
		this.updateView();
	}

	/**
	 * Updates the {@link contentArea}'s children to the current view. The current
	 * view is determined by the selected navigation button
	 */
	private void updateView() {
		Node view = this.viewModel.getCurrentViewProperty().get();
		this.contentArea.getChildren().clear();
		this.contentArea.getChildren().add(view);
		VBox.setVgrow(view, Priority.ALWAYS);
	}
}
