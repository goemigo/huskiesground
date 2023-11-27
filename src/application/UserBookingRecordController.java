package application;

import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
//import model.BookingRecord;


public class UserBookingRecordController {
	//those on the top button bar
	@FXML
	private Button Book_Record;// get a default Book_Record, this is for testing
	
	@FXML
	private Button Refresh; //refresh the page to see whether there is new booking record coming in
	
	@FXML
	private Button user_exit; // user exist the app.
	
	//Widgets on the Table "BookingRecord"
	@FXML
	private TableView<BookingRecord> bookingRecordTableView; //Point to the whole table, and below are each column
	@FXML
	private TableColumn<BookingRecord, String> bookingid;
	@FXML
	private TableColumn<BookingRecord, String> userid;
	@FXML
	private TableColumn<BookingRecord, String> roomid;
	@FXML
	private TableColumn<BookingRecord, String> date;
	@FXML
	private TableColumn<BookingRecord, String> start_time;
	@FXML
	private TableColumn<BookingRecord, String> end_time;
	@FXML
	private TableColumn<BookingRecord, String> booking_status;
	
	// These 2 list are for receiving orderList in order to populate the order TableView
	private ObservableList<BookingRecord> BookingShowList = FXCollections.observableArrayList();// this is for showing only
	
	private List<BookingRecord> BookingList = new ArrayList<BookingRecord>(); // this is for operating
	
//	public void initialize() throws SQLException {
//		bookingRecordTableView.getItems().clear();
//		
//	}
	
//    @FXML
//    void Refresh(ActionEvent event) throws SQLException {
//    	initialize();
    	
//    }  
    
    // exit elegantly
    @FXML
    void user_exit(ActionEvent event) {
    	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	stage.close();
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("Welcome.fxml"));
    		Scene scene = new Scene(root);
    		Stage stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
    		stage1.setScene(scene);
    		stage1.show();
    	} catch (IOException e) {
    		e.printStackTrace();
    	} 

    }
	
	
}
