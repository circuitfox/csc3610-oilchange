package edu.aurora.oilchange.controller;

import edu.aurora.oilchange.Customer;
import edu.aurora.oilchange.Date;
import edu.aurora.oilchange.db.Database;
import edu.aurora.oilchange.db.DatabaseValueService;
import edu.aurora.oilchange.ui.CustomerModel;
import edu.aurora.oilchange.ui.OilChangeModel;

import edu.aurora.oilchange.ui.UiUtils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

public class MainController {
    @FXML
    private Label lblId;
    @FXML
    private Label lblStatus;
    @FXML
    private TableView<CustomerModel> tblCustomers;
    @FXML
    private TableColumn<CustomerModel, Integer> tcId;
    @FXML
    private TableColumn<CustomerModel, String> tcVehicleMake;
    @FXML
    private TableColumn<CustomerModel, String> tcVehicleModel;
    @FXML
    private TableColumn<CustomerModel, String> tcVehicleYear;
    @FXML
    private TableColumn<CustomerModel, String> tcOilType;
    @FXML
    private TableColumn<CustomerModel, String> tcOilBrand;
    @FXML
    private TableColumn<CustomerModel, Integer> tcOilQuantity;
    @FXML
    private TableColumn<CustomerModel, BigDecimal> tcOilPrice;
    @FXML
    private TableColumn<CustomerModel, String> tcFilterBrand;
    @FXML
    private TableColumn<CustomerModel, BigDecimal> tcFilterCost;
    @FXML
    private TableColumn<CustomerModel, Date> tcDate;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnRemove;
    @FXML
    private MenuBar menuBar;

    private ExecutorService threadPool;
    private Database database;
    private DatabaseValueService tableUpdateService = new DatabaseValueService();

    @FXML
    private void initialize() {
        menuBar.getMenus().get(0).getItems().get(0).setOnAction(e -> System.exit(0));

        tblCustomers.setRowFactory(tv -> new TableRow<CustomerModel>() {
            @Override
            protected void updateItem(CustomerModel item, boolean empty) {
                if (item != null) {
                    super.updateItem(item, empty);
                    Date today = new Date();
                    Date changeAt = getOilChangeDate(item.getDate());
                    int monthDiff = (changeAt.getYear() * 12 + changeAt.getMonth()) -
                            (today.getYear() * 12 + today.getMonth());

                    if (changeAt.compareTo(today) < 0) {
                        getStyleClass().add("overdue");
                    } else if (monthDiff < 1) {
                        getStyleClass().add("warn");
                    } else {
                        getStyleClass().remove("overdue");
                        getStyleClass().remove("warn");
                    }
                }
            }
        });

        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));

        tcVehicleMake.setCellValueFactory(new PropertyValueFactory<>("make"));
        tcVehicleModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        tcVehicleYear.setCellValueFactory(new PropertyValueFactory<>("year"));

        tcOilType.setCellValueFactory(new PropertyValueFactory<>("oilType"));
        tcOilBrand.setCellValueFactory(new PropertyValueFactory<>("oilBrand"));
        tcOilQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tcOilPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tcFilterBrand.setCellValueFactory(new PropertyValueFactory<>("filterBrand"));
        tcFilterCost.setCellValueFactory(new PropertyValueFactory<>("filterCost"));

        tcDate.setCellValueFactory(param ->
                new ReadOnlyObjectWrapper<>(getOilChangeDate(param.getValue().getDate())));

        tblCustomers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                lblStatus.getStyleClass().removeAll("overdue", "warn");
                lblStatus.setText("");
                lblId.setText("");
            } else {
                Date today = new Date();
                Date changeAt = getOilChangeDate(newValue.getDate());
                int monthDiff = (changeAt.getYear() * 12 + changeAt.getMonth()) -
                        (today.getYear() * 12 + today.getMonth());
                if (changeAt.compareTo(today) < 0) {
                    lblStatus.getStyleClass().remove("warn");
                    lblStatus.getStyleClass().add("overdue");
                    lblStatus.setText("OVERDUE");
                } else if (monthDiff < 1) {
                    lblStatus.getStyleClass().remove("overdue");
                    lblStatus.getStyleClass().add("warn");
                    lblStatus.setText("WARN");
                } else {
                    lblStatus.getStyleClass().removeAll("overdue", "warn");
                    lblStatus.setText("OKAY");
                }
                lblId.setText(Integer.toString(newValue.getId()));
            }
        });

        tableUpdateService.setOnSucceeded(e -> updateTable(tableUpdateService.getValue()));

        tableUpdateService.exceptionProperty().addListener((observable, oldValue, newValue) -> {
            System.err.println("Could not get new database values for " + database);
            newValue.printStackTrace();
        });

        btnAdd.setOnAction(e -> {
            Stage addStage = new Stage();
            addStage.initOwner(btnAdd.getScene().getWindow());
            addStage.initModality(Modality.WINDOW_MODAL);
            addStage.setTitle("Add Customer");

            FXMLLoader loader = UiUtils.getView("AddView.fxml");
            BorderPane addPane = new BorderPane();
            try {
                addPane = loader.load();
                AddController addController = loader.getController();
                addController.setDatabase(database);
                addController.setThreadPool(threadPool);
                addController.setTableUpdateService(tableUpdateService);
            } catch (IOException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "An IOException has occurred loading the Add view. Adding will be unavailable");
                alert.show();
                return;
            }

            Scene addScene = UiUtils.getScene(addPane);
            addStage.setScene(addScene);
            addStage.show();
        });

        btnUpdate.setOnAction(e -> {
            Stage updateStage = new Stage();
            updateStage.initOwner(btnUpdate.getScene().getWindow());
            updateStage.initModality(Modality.WINDOW_MODAL);
            updateStage.setTitle("Update Customer Information");

            CustomerModel customerModel = tblCustomers.getSelectionModel().getSelectedItem();
            if (customerModel == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "No customer selected for update.");
                alert.show();
                return;
            }

            OilChangeModel oilChangeModel = new OilChangeModel();

            FXMLLoader loader = UiUtils.getView("UpdateView.fxml");
            AnchorPane updatePane = new AnchorPane();
            try {
                updatePane = loader.load();
                UpdateController updateController = loader.getController();
                updateController.setCustomerModel(customerModel);
                updateController.setOilChangeModel(oilChangeModel);
                updateController.setDatabase(database);
                updateController.setThreadPool(threadPool);
                updateController.setTableUpdateService(tableUpdateService);

            } catch (IOException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR,
                        "An IOException has occurred loading the Update view. Updating will be unavailable.");
                alert.show();
                return;
            }

            Scene updateScene = UiUtils.getScene(updatePane);
            updateStage.setScene(updateScene);
            updateStage.show();
        });

        btnRemove.setOnAction(e -> {
            final CustomerModel selected = tblCustomers.getSelectionModel().getSelectedItem();
            if (selected == null) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "No customer selected for removal");
                alert.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove this customer?");
                alert.showAndWait().ifPresent(r -> {
                    if (r.equals(ButtonType.OK)) {
                        // we'll combine removal and select here, so the Service won't be used.
                        Task<Set<Customer>> removeAndSelect = new Task<Set<Customer>>() {
                            @Override
                            protected Set<Customer> call() throws Exception {
                                database.delete(selected.getCustomer());
                                return database.selectAll();
                            }
                        };

                        removeAndSelect.setOnSucceeded(v -> updateTable(removeAndSelect.getValue()));
                        removeAndSelect.exceptionProperty().addListener((observable, oldValue, newValue) -> {
                            System.err.println("Could not remove customer and update values");
                            newValue.printStackTrace();
                        });

                        threadPool.execute(removeAndSelect);
                    }
                });
            }
        });
    }


    public void setThreadPool(ExecutorService threadPool) {
        this.threadPool = threadPool;
        tableUpdateService.setExecutor(this.threadPool);
    }

    public void setDatabase(Database database) {
        this.database = database;
        tableUpdateService.setDatabase(this.database);
    }

    // we need to be able to delegate initial TableView population to AppLauncher
    // because it sets the proper value for our db and thread pool. Feels hackish,
    // but whatever.
    /**
     * Populate the Table View with values. Called only after initialization.
     */
    public void runTableUpdateService() {
        tableUpdateService.restart();
    }

    private Date getOilChangeDate(Date date) {
        LocalDate changeDate = LocalDate.of(date.getYear(), date.getMonth(), date.getDay())
                .plusMonths(Customer.OILCHANGE_DURATION_MONTHS);
        return new Date(changeDate.getMonthValue(), changeDate.getDayOfMonth(), changeDate.getYear());
    }

    private void updateTable(Set<Customer> results) {
        tblCustomers.setItems(FXCollections.observableArrayList(results
                .stream()
                .map(CustomerModel::new)
                .collect(Collectors.toList())));
    }
}
