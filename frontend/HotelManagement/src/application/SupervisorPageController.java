package application;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import utils.State;

public class SupervisorPageController {
	@FXML
    private HeaderController headerController;
    @FXML
    private SideBarController sideBarController;
    
    private State state;
    
    public void setState(State st) {
    	state=st;
    	headerController.setState(state);
    	headerController.getBackbtn().setVisible(false);
    	sideBarController.setState(state);
    }
    @FXML
    void launchBooking() {
    	try {
    		ArrayList<Object> fx=state.getPage("Booking");
			((BookingController)fx.get(1)).setState(state);
    		state.getStage().setScene((Scene)fx.get(0));
    	}
    	catch(Exception e) 
    	{
    		e.printStackTrace();
    	}
    }


    @FXML
    void launchLogistics() {
    	try {
    		ArrayList<Object> fx=state.getPage("Logistics");
			((LogisticsController)fx.get(1)).setState(state);
    		state.getStage().setScene((Scene)fx.get(0));
    	}
    	catch(Exception e) 
    	{
    		e.printStackTrace();
    	}
    }
    @FXML
    void launchSchedule() {
    	try {
    		ArrayList<Object> fx=state.getPage("Schedule");
			((ScheduleController)fx.get(1)).setState(state);
    		state.getStage().setScene((Scene)fx.get(0));
    	}
    	catch(Exception e) 
    	{
    		e.printStackTrace();
    	}
    }
}
