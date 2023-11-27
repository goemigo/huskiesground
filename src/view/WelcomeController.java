package view;

import java.io.IOException;

/**
 * Sample Skeleton for 'Welcome.fxml' Controller Class
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class WelcomeController {

	@FXML // ResourceBundle that was given to the FXMLLoader
	private ResourceBundle resources;
	
	@FXML // URL location of the FXML file that was given to the FXMLLoader
	private URL location;
	
	// @FXML // fx:id = "button_wellcom_exit"
	// private Button button_welcome_exit;
	
	@FXML // fx:id = "button_welcome_username"
	private TextField text_field_welcome_username; 
	
	@FXML // fx:id = "text_field_welcome_password"
	private TextField text_field_welcome_password; 
	
	@FXML // fx:id = "button_welcome_login"
	private Button button_welcome_login; //Value injected by FXMLLoader
	
	@FXML
	void Input_UserName(KeyEvent event) {
		
	}
	
	@FXML
	void Input_Password(KeyEvent event) {
		
	}
	
	@FXML
	void login(ActionEvent event) {
		// input the user name and password
		String username = text_field_welcome_username.getText();
		String password = text_field_welcome_password.getText();
		// check user name and password
		if (username.equals("user1")&& password.equals("pass1")) {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("UserBookingRecord.fxml"));
				Scene scene = new Scene(root);
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}	else if (username.equals("user2")&& password.equals("pass2")) {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("userSearch.fxml"));
				Scene scene = new Scene(root);
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				stage.setScene(scene);
				stage.show();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}	else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Login Failed");
			alert.setHeaderText(null);
			alert.setContentText("Please enter the correct username and password again!");
			alert.showAndWait();
		}
	}
	
	@FXML
	void handleEnterKey(KeyEvent event) {
		if(event.getCode().toString().equals("ENTER")) {
			String username = text_field_welcome_username.getText();
			String password = text_field_welcome_password.getText();
			if(username.equals("user1")&& password.equals("pass1")) {
				try {
					Parent root = FXMLLoader.load(getClass().getResource("UserBookingRecord.fxml"));
					Scene scene = new Scene(root);
					Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					stage.setScene(scene);
					stage.show();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}	else if (username.equals("user2")&& password.equals("pass2")) {
				try {
					Parent root = FXMLLoader.load(getClass().getResource("userSearch.fxml"));
					Scene scene = new Scene(root);
					Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
					stage.setScene(scene);
					stage.show();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}	else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Login Failed");
				alert.setHeaderText(null);
				alert.setContentText("Please enter the correct username and password again!");
				alert.showAndWait();
				
				text_field_welcome_username.setText("Incorrect, try again!");
				text_field_welcome_password.setText("Incorrect, try again!");
			}	
		}
	}
	
	@FXML
	void welcome_exit(ActionEvent event) {
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		stage.close();
	}
	
	@FXML // This method is called by the FXMLLoader when initialization is complete
	void initialize() {
		assert button_welcome_login != null: "fx:id= \"button_welcome_exit\" was not injected: check your FXML file 'Welcome.fxml'.";
		assert text_field_welcome_username != null: "fx:id= \"text_field_welcome_username\" was not injected: check your FXML file 'Welcome.fxml'.";
		assert text_field_welcome_password != null: "fx:id= \"text_field_welcome_password\" was not injected: check your FXML file 'Welcome.fxml'.";
		
	}
	
}
