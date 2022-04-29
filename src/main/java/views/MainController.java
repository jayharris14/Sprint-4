package views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import models.ConcordModel;
import models.ConcordServer;
import models.ViewTransitionModelInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MainController {
	
	ViewTransitionModelInterface model;
	ConcordModel concordmodel;
	
	
	public void setModel(ViewTransitionModelInterface newModel, ConcordModel newconcordmodel)
	{
		model=newModel;
		concordmodel=newconcordmodel;
	}
	
	@FXML
	private TextField userNameLabel;

	@FXML
	private TextField passWordLabel;


	

    @FXML
    void onClickLogIn(ActionEvent event) {
    	String c=concordmodel.verify(userNameLabel.textProperty().get(), passWordLabel.textProperty().get());
    	if (c=="permission granted") {
    		model.showUser();
    	}
    	else {
    		model.showMainError();
    		
    	}
        }
    @FXML
    void onClickCreateAccount(ActionEvent event) {
    	model.showCreateAccount();
    }
    

    }



