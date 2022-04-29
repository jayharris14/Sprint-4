package models;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import views.CreateAccountController;
import views.MainController;
import views.UserPageController;

public class ViewTransitionalModel implements ViewTransitionModelInterface
{

		
		BorderPane mainview;
		ConcordModel model;
		
		public ViewTransitionalModel(BorderPane view, ConcordModel newModel)
		{
			mainview=view;
			model=newModel;

		}
		public void showUser() {
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(ViewTransitionalModel.class.getResource("../views/UserPageView.fxml"));
			try {
				Pane view=loader.load();
				mainview.setCenter(view);
				UserPageController cont=loader.getController();
				cont.setModel(model);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void showLogin() {
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(ViewTransitionalModel.class.getResource("../views/MainView.fxml"));
			try {
				Pane view=loader.load();
				mainview.setCenter(view);
				MainController cont=loader.getController();
				cont.setModel(this, model);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void showCreateAccount() {
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(ViewTransitionalModel.class.getResource("../views/CreateAccountView.fxml"));
			try {
				Pane view=loader.load();
				mainview.setCenter(view);
				CreateAccountController cont=loader.getController();
				cont.setModel(this, model);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void showError() {
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(ViewTransitionalModel.class.getResource("../views/ErrorCreateAccountView.fxml"));
			try {
				Pane view=loader.load();
				mainview.setCenter(view);
				CreateAccountController cont=loader.getController();
				cont.setModel(this, model);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void showMainError() {
			FXMLLoader loader=new FXMLLoader();
			loader.setLocation(ViewTransitionalModel.class.getResource("../views/ErrorMainView.fxml"));
			try {
				Pane view=loader.load();
				mainview.setCenter(view);
				MainController cont=loader.getController();
				cont.setModel(this, model);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
}
