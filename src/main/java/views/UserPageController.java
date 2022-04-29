package views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import models.ConcordModel;
import models.Server;
import models.User;

public class UserPageController {
	
	ConcordModel concordmodel;
	
	public void setModel(ConcordModel model) {
		concordmodel=model;
		serverView.setItems(model.getuserservers(null));
	}
	 @FXML
	 private ListView<Server> serverView;

	 @FXML
	 private ListView<User> blockView;
	
	 @FXML
	 private TextField servernameLabel;


	 @FXML
	 void onClickButton(ActionEvent event) {
		 ConcordModel diskf=concordmodel.ReadFromDisk();
		 if (servernameLabel.textProperty().get()!="") {
			 concordmodel.CreateServer(servernameLabel.textProperty().get(), diskf, null);
			 
		 }

	    }
	

}

