package application;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class BookableRoom extends Room{
	private Connection connect;
    private PreparedStatement prepare;

	public BookableRoom(int roomId, int roomNum, int buildingNum, String buildingName, String bookableFlag) {
		super(roomId, roomNum, buildingNum, buildingName, bookableFlag);
	}

	@Override
	public void book(String insertBooking) {
		connect = Database.connectDB();
        prepare = connect.prepareStatement(insertBooking);
        
        //modify database booking status table
        prepare.setString(1, availableCourse_course.getText());
        prepare.setString(2, availableCourse_description.getText());
        prepare.setString(3, availableCourse_degree.getText());

        prepare.executeUpdate();

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Message");
        alert.setHeaderText(null);
        alert.setContentText("Successfully Booked!");
        alert.showAndWait();
	}

}
