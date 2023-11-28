package application;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class dashboardController implements Initializable {

	@FXML
    private Button logout;
	
	@FXML
    private Button bookBtn;
	
    @FXML
    private Button booking_btn;

    @FXML
    private TextField bookBuilding;

    @FXML
    private TextField bookDate;

    @FXML
    private TextField bookEnd;

    @FXML
    private TextField bookRoom;

    @FXML
    private TextField bookStart;

    @FXML
    private Button clearBtn;

    @FXML
    private TextField search;

    @FXML
    private AnchorPane searchForm;

    @FXML
    private TableView<roomData> searchTable;

    @FXML
    private TableColumn<roomData, String> searchTable_building;

    @FXML
    private TableColumn<roomData, String> searchTable_date;

    @FXML
    private TableColumn<roomData, String> searchTable_end;

    @FXML
    private TableColumn<roomData, String> searchTable_room;

    @FXML
    private TableColumn<roomData, String> searchTable_start;

    @FXML
    private TableColumn<roomData, String> searchTable_status;
	
    
    private double x = 0;
    private double y = 0;
    
    private ObservableList<roomData> roomList;
    public void roomShowListData() {
    	roomList = roomListData();

    	searchTable_date.setCellValueFactory(new PropertyValueFactory<>("date"));
    	searchTable_room.setCellValueFactory(new PropertyValueFactory<>("roomNum"));
    	searchTable_building.setCellValueFactory(new PropertyValueFactory<>("building"));
    	searchTable_start.setCellValueFactory(new PropertyValueFactory<>("startTime"));
    	searchTable_end.setCellValueFactory(new PropertyValueFactory<>("endTime"));
    	searchTable_status.setCellValueFactory(new PropertyValueFactory<>("status"));
//        WE NEED TO FIX THE DELETE ON ADD STUDENT FORM 
    	searchTable.setItems(roomList);

    }
    
    
    
    public void logout() {

        try {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");

            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                //HIDE YOUR DASHBOARD FORM
                logout.getScene().getWindow().hide();

                //LINK YOUR LOGIN FORM 
                Parent root = FXMLLoader.load(getClass().getResource("/view/Welcome.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

//                root.setOnMousePressed((MouseEvent event) -> {
//                    x = event.getSceneX();
//                    y = event.getSceneY();
//                });
//
//                root.setOnMouseDragged((MouseEvent event) -> {
//                    stage.setX(event.getScreenX() - x);
//                    stage.setY(event.getScreenY() - y);
//
//                    stage.setOpacity(.8);
//                });
//
//                root.setOnMouseReleased((MouseEvent event) -> {
//                    stage.setOpacity(1);
//                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();

            } else {
                return;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    
    
    
    public void switchForm(ActionEvent event) {
		if (event.getSource() == booking_btn) {
    		searchForm.setVisible(true);
    		// home_form.setVisible(false);
    		
    		booking_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
    		
    		roomListData();
    	} 
    }
    
    
    public void close() {
    	System.exit(0);
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		roomListData();

	}

}
