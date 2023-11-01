package application.pastlogs;

import application.dashboard.Dashboard;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import util.Database;
import java.util.List;

public class PastLogsController {
	@FXML private Button dashboardButton;
    @FXML private ComboBox<String> cbLogs;
    @FXML private Label lblStartTime;
    @FXML private Label lblStopTime;
    @FXML private Label lblTotalTime;
    @FXML private Label lblProjectCategory;
    @FXML private Label lblEffortCategory;

    @FXML
    public void initialize() {
        List<String> dates = Database.getAllDates();
        cbLogs.getItems().addAll(dates);
        setupDashboardButton();
    }

    @FXML
    private void viewLog() {
        String selectedLog = cbLogs.getValue();
        if (selectedLog != null && !selectedLog.isEmpty()) {
            String[] logDetails = Database.getLog(selectedLog).split(",");

            lblStartTime.setText("Start Time: " + logDetails[0]);
            lblStopTime.setText("Stop Time: " + logDetails[1]);
            lblTotalTime.setText("Total Time: " + logDetails[2].toUpperCase());
            lblProjectCategory.setText("Project Category: " + logDetails[3].toUpperCase());
            lblEffortCategory.setText("Effort Category: " + logDetails[4].toUpperCase());
        }
    }

    @FXML
    private void setupDashboardButton() {
    	dashboardButton.setOnAction(event -> {
            Dashboard dashboard = new Dashboard();
            dashboard.show((Stage) dashboardButton.getScene().getWindow());
        });
    }
}
