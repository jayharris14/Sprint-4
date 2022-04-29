package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.ViewTransitionalModel;
import models.ConcordModel;
import views.MainController;

public class Main extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		ConcordModel model= new ConcordModel();
		FXMLLoader loader=new FXMLLoader();
		loader.setLocation(Main.class.getResource("../views/MainView.fxml"));
		BorderPane view=loader.load();
		MainController cont=loader.getController();
		ViewTransitionalModel vm= new ViewTransitionalModel(view, model);
		ConcordModel cm=new ConcordModel();
		cont.setModel(vm, cm);
		
		Scene s=new Scene(view);
		stage.setScene(s);
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}