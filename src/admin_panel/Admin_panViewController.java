package admin_panel;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Admin_panViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void gotoabout(ActionEvent event) {
    	try
   	 	{
   		 	Parent root=FXMLLoader.load(getClass().getResource("/about/AbtusView.fxml")); 
   		 	Scene scene=new Scene(root);
   		 	Stage stage=new Stage();
   		 	stage.setScene(scene);
   		 	stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

    }

    @FXML
    void gotobill_coll(ActionEvent event) {
    	try
   	 	{
   		 	Parent root=FXMLLoader.load(getClass().getResource("/bill_coll/BillcollView.fxml")); 
   		 	Scene scene=new Scene(root);
   		 	Stage stage=new Stage();
   		 	stage.setScene(scene);
   		 	stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

    }

    @FXML
    void gotobill_gen(ActionEvent event) {
    	try
   	 	{
   		 	Parent root=FXMLLoader.load(getClass().getResource("/bill_gen/BgenView.fxml")); 
   		 	Scene scene=new Scene(root);
   		 	Stage stage=new Stage();
   		 	stage.setScene(scene);
   		 	stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

    }

    @FXML
    void gotobill_panel(ActionEvent event) {
    	try
   	 	{
   		 	Parent root=FXMLLoader.load(getClass().getResource("/bill_panel/Bill_panelView.fxml")); 
   		 	Scene scene=new Scene(root);
   		 	Stage stage=new Stage();
   		 	stage.setScene(scene);
   		 	stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

    }

    @FXML
    void gotocst_panel(ActionEvent event) {
    	try
   	 	{
   		 	Parent root=FXMLLoader.load(getClass().getResource("/cst_panel/Cst_PanelView.fxml")); 
   		 	Scene scene=new Scene(root);
   		 	Stage stage=new Stage();
   		 	stage.setScene(scene);
   		 	stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

    }

    @FXML
    void gotocus_mas(ActionEvent event) {
    	try
   	 	{
   		 	Parent root=FXMLLoader.load(getClass().getResource("/customer_manager/CmmanView.fxml")); 
   		 	Scene scene=new Scene(root);
   		 	Stage stage=new Stage();
   		 	stage.setScene(scene);
   		 	stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

    }

    @FXML
    void gotohk_man(ActionEvent event) {
    	try
   	 	{
   		 	Parent root=FXMLLoader.load(getClass().getResource("/hawker_manager/HkmanView.fxml")); 
   		 	Scene scene=new Scene(root);
   		 	Stage stage=new Stage();
   		 	stage.setScene(scene);
   		 	stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

    }

    @FXML
    void gotohk_table(ActionEvent event) {
    	try
   	 	{
   		 	Parent root=FXMLLoader.load(getClass().getResource("/hk_table/tableView.fxml")); 
   		 	Scene scene=new Scene(root);
   		 	Stage stage=new Stage();
   		 	stage.setScene(scene);
   		 	stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

    }

    @FXML
    void gotopaper_mas(ActionEvent event) {
    	try
   	 	{
   		 	Parent root=FXMLLoader.load(getClass().getResource("/paper_master/Paper_masterView.fxml")); 
   		 	Scene scene=new Scene(root);
   		 	Stage stage=new Stage();
   		 	stage.setScene(scene);
   		 	stage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

    }

    @FXML
    void initialize() {

    }

}
