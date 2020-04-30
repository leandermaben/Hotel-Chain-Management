package application;

import javafx.fxml.FXML;
import utils.State;

public class ScheduleController {
	private State state;
    
	@FXML
    private HeaderController headerController;
    
    @FXML
    private SideBarController sideBarController;

    
	public State getState() {
				return state;
	}
	
	public void setState(State state) {
			this.state = state;
			headerController.setState(state);
			if(state.getClearance().equals("employee"))
				headerController.getBackbtn().setVisible(false);
				sideBarController.setState(state);
			}
}
