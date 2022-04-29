package views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import models.ViewTransitionModelInterface;
import models.ConcordModel;

public class CreateAccountController {
		
		ViewTransitionModelInterface model;
		ConcordModel newmodel;
	
		public void setModel(ViewTransitionModelInterface newModel, ConcordModel secondnewmodel) {
			model = newModel;
			newmodel=secondnewmodel;
		}

	    @FXML
	    private TextField usernameLabel;

	    @FXML
	    private TextField passwordLabel;

	    @FXML
	    private TextField nameLabel;

	    @FXML
	    void onClickSubmit(ActionEvent event) {
	    	ConcordModel diskf=newmodel.ReadFromDisk();
	    	if (usernameLabel.textProperty().get()=="" || passwordLabel.textProperty().get()=="" || nameLabel.textProperty().get()=="") {
	    		model.showError();
	    	}
	    	else {
	    		newmodel.CreateUser(nameLabel.textProperty().get(), usernameLabel.textProperty().get(), passwordLabel.textProperty().get(), diskf);
	    		model.showLogin();
	    	}
	       
	 

	    }

	}



