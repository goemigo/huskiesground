package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class dashboardController implements Initializable {

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
    private TableView<?> searchTable;

    @FXML
    private TableColumn<?, ?> searchTable_building;

    @FXML
    private TableColumn<?, ?> searchTable_date;

    @FXML
    private TableColumn<?, ?> searchTable_end;

    @FXML
    private TableColumn<?, ?> searchTable_room;

    @FXML
    private TableColumn<?, ?> searchTable_start;

    @FXML
    private TableColumn<?, ?> searchTable_status;
	
    public void switchForm(ActionEvent event) {
		if (event.getSource() == booking_btn) {
    		searchForm.setVisible(true);
    		// home_form.setVisible(false);
    		
    		booking_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #3f82ae, #26bf7d);");
    	}
    }
    
    
    public void close() {
    	System.exit(0);
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		
	}

}
