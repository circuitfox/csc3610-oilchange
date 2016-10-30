package edu.aurora.oilchange.controller;

import edu.aurora.oilchange.Customer;
import edu.aurora.oilchange.Date;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
	@FXML
	private Label lblId;
	@FXML
	private Label lblStatus;
	@FXML
	private TableView<Customer> tblCustomers;
	@FXML
	private Button btnAdd;
	@FXML
	private Button btnUpdate;
	@FXML
	private Button btnRemove;
    @FXML
    private MenuBar menuBar;

	@FXML
	private void initialize() {
        menuBar.getMenus().get(0).getItems().get(0).setOnAction(e -> System.exit(0));

        // TODO: Populate TableView with DB results.
        tblCustomers.setRowFactory(tv -> new TableRow<Customer>() {
            @Override
            protected void updateItem(Customer item, boolean empty) {
                super.updateItem(item, empty);
                Date today = new Date();
                if (item == null) {
                    return;
                }

                if (item.getDate().compareTo(today) < 0) {
                    getStyleClass().add("overdue");
                } else if ((item.getDate().getMonth() - today.getMonth() < 1)) {
                    getStyleClass().add("warn");
                }
            }
        });

        btnAdd.setOnAction(e -> {
            Stage addStage = new Stage();
            addStage.initOwner(btnAdd.getScene().getWindow());
            addStage.initModality(Modality.WINDOW_MODAL);
            addStage.setTitle("Add Customer");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/aurora/ui/AddView.fxml"));
            BorderPane addPane = new BorderPane();
            try {
                addPane = loader.load();
            } catch (IOException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "An IOException has occurred loading the Add view. Adding will be unavailable");
                alert.show();
                return;
            }

            Scene addScene = new Scene(addPane);
            // TODO: Move stylesheets & scaling to a separate class
            addScene.getStylesheets().add(getClass().getResource("/edu/aurora/ui/css/common.css").toExternalForm());
            addStage.setScene(addScene);
            addStage.show();
            // TODO: Update table view here
        });

        btnUpdate.setOnAction(e -> {
            Stage updateStage = new Stage();
            updateStage.initOwner(btnUpdate.getScene().getWindow());
            updateStage.initModality(Modality.WINDOW_MODAL);
            updateStage.setTitle("Update Customer Information");

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/aurora/oilchange/UpdateView.fxml"));
            AnchorPane updatePane = new AnchorPane();
            try {
                updatePane = loader.load();
            } catch (IOException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "An IOException has occurred loading the Update view. Updating will be unavailable.");
                alert.show();
                return;
            }

            Scene updateScene = new Scene(updatePane);
            updateScene.getStylesheets().add(
                    getClass().getResource("/edu/aurora/oilchange/ui/css/common.css").toExternalForm());
            updateStage.setScene(updateScene);
            updateStage.show();
            // TODO: update table
        });

        btnRemove.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove this customer?");
            alert.showAndWait().ifPresent(r -> {
                if (r.equals(ButtonType.OK)) {
                    // TODO: Call DB for removal, update table
                }
            });
        });
    }
}
