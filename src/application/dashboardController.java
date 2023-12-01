package application;

import java.net.URL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

import com.mysql.cj.xdevapi.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.Label;
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
    private Button history_btn; //show booking history data

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
    
    // those on the searchTable
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
	

//  DATABASE TOOls
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
    private roomData roomSelected;
    
    @FXML
    private Label currentUserName;

    // those on the historyTable
//    @FXML
//    private AnchorPane history_form;
    
    @FXML
    private TableView<?> historyTable;

    @FXML
    private TableColumn<?, ?> historyTable_building;

    @FXML
    private TableColumn<?, ?> historyTable_date;

    @FXML
    private TableColumn<?, ?> historyTable_end;

    @FXML
    private TableColumn<?, ?> historyTable_room;

    @FXML
    private TableColumn<?, ?> historyTable_start;

    @FXML
    private TableColumn<?, ?> historyTable_status;


    
    private double x = 0;
    private double y = 0;
    

    public ObservableList<roomData> roomListData() {

        ObservableList<roomData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM allrooms JOIN roomstatus ON allrooms.roomid = roomstatus.roomid WHERE roomstatus.booked = 0 ORDER BY allrooms.roomid";


        connect = Database.connectDB();

        try {
        	roomData roomD;

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()){
//            	
            	Date date = result.getDate("date");
            	int roomNum = result.getInt("room_num");
            	String buildingName = result.getString("building");
            	int start = result.getInt("start_time");
            	int end = result.getInt("end_time");
            	String option = result.getString("bookable_flag");
            	
            	roomD = new roomData(date,roomNum,buildingName,start,end,option);

                roomD.setRoomid(result.getInt("roomid"));
                roomD.setBuildingNum(result.getInt("buildingid"));
                roomD.setRoomStatusId(result.getInt("rsid"));

                listData.add(roomD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    
    private ObservableList<roomData> roomList;
    
    public void roomShowListData() {
    	roomList = roomListData();

    	searchTable_date.setCellValueFactory(new PropertyValueFactory<>("date"));
    	searchTable_room.setCellValueFactory(new PropertyValueFactory<>("roomNum"));
    	searchTable_building.setCellValueFactory(new PropertyValueFactory<>("buildingName"));
    	searchTable_start.setCellValueFactory(new PropertyValueFactory<>("startTime"));
    	searchTable_end.setCellValueFactory(new PropertyValueFactory<>("endTime"));
    	searchTable_status.setCellValueFactory(new PropertyValueFactory<>("option"));

    	searchTable.setItems(roomList);

    }
    
    public boolean safeCompare(String value, String searchKey) {
        return value != null && value.toLowerCase().contains(searchKey);
    }
    
    public void roomSearch() {
        FilteredList<roomData> filter = new FilteredList<>(roomList, e -> true);

        search.textProperty().addListener((Observable, oldValue, newValue) -> {
        	filter.setPredicate(null);
            filter.setPredicate(predicateRoomData -> {

                if (newValue.isEmpty() || newValue == null) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();
                boolean b = safeCompare(String.valueOf(predicateRoomData.getDate()), searchKey)
                        || safeCompare(String.valueOf(predicateRoomData.getRoomNum()), searchKey)
                        || safeCompare(predicateRoomData.getBuildingName(), searchKey)
                        || safeCompare(String.valueOf(predicateRoomData.getStartTime()), searchKey)
                        || safeCompare(String.valueOf(predicateRoomData.getEndTime()), searchKey)
                        || safeCompare(predicateRoomData.getOption(), searchKey);
                return b;
            });
            SortedList<roomData> sortList = new SortedList<>(filter);

            sortList.comparatorProperty().bind(searchTable.comparatorProperty());
            searchTable.setItems(sortList);

        });   
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
                Parent root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
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
    
    //History button action
    public void bookingHistory(ActionEvent event) {
		if (event.getSource() == history_btn) {
    		searchForm.setVisible(true);
    		// home_form.setVisible(false);
    		
    		history_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
    		
    		roomShowListData();
    	} 
    }
    
    
    //Booking button action
    public void switchForm(ActionEvent event) {
		if (event.getSource() == booking_btn) {
    		searchForm.setVisible(true);
    		// home_form.setVisible(false);
    		
    		booking_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
    		
    		roomShowListData();
    		roomSearch();
		} 
		// else if(event.getSource() == history_btn) {
//				searchForm.setVisible(false);
//    			booking_btn.setVisible(false);
    		// history_form.setVisible(true);
    	//}
		
    }
    
    
    public void close() {
    	System.exit(0);
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// show current username on dashboard
		showUsername();
		
		roomShowListData();
//		roomSearch();
		
		searchTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectRoom();
            }
        });

	}
	
    //click a row in table, show selected room info on the left side
	public void selectRoom() {
        roomSelected = searchTable.getSelectionModel().getSelectedItem();
        int num = searchTable.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        bookDate.setText(String.valueOf(roomSelected.getDate()));
        bookRoom.setText(String.valueOf(roomSelected.getRoomNum()));
        bookBuilding.setText(String.valueOf(roomSelected.getBuildingName()));
        bookStart.setText(String.valueOf(roomSelected.getStartTime()));
        bookEnd.setText(String.valueOf(roomSelected.getEndTime()));
    }
	
	@FXML
	public void bookARoom() {
		roomSelected = searchTable.getSelectionModel().getSelectedItem();
		
        int userid = CurrentUser.userid; 
        Date date = Date.valueOf(bookDate.getText());
        int start = Integer.valueOf(bookStart.getText());
        int end = Integer.valueOf(bookEnd.getText());
        int rsid = roomSelected.getRoomStatusId();
        int roomid = roomSelected.getRoomid();
        int roomNum = Integer.valueOf(bookRoom.getText());
        int buildingNum = roomSelected.getBuildingNum();
        String buildingName = bookBuilding.getText();
        String option = roomSelected.getOption(); 

        try {
        	if (option.equals("Book")) {
        		BookableRoom bRoom = new BookableRoom(roomid,roomNum,buildingNum,buildingName,option);
                bRoom.book(userid,roomid, rsid,date,start, end, option);

                roomShowListData();
                clearFields();
            } else {
            	ViewOnlyRoom vRoom = new ViewOnlyRoom(roomid,roomNum,buildingNum,buildingName,option);
                vRoom.book(userid,roomid, rsid, date,start, end, option); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	@FXML
    public void clearFields() {
    	bookDate.setText("");
    	bookRoom.setText("");
    	bookBuilding.setText("");
    	bookStart.setText("");
    	bookEnd.setText("");
    }
    
	public void showUsername(){
		currentUserName.setText(CurrentUser.username);
    }

}
