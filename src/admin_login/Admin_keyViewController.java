package admin_login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class Admin_keyViewController {

    @FXML
    private Button btnlgn;


    @FXML
    private Label lblpass;
    @FXML
    private PasswordField txtpass;
    String s1="admin123";
    @FXML
    void dologin(ActionEvent event) {
    	String s2=txtpass.getText();
    	if(s1.equals(s2))
    		gotopanel();
    	else
    		lblpass.setText("Inccorect Passkey");
    }
    void gotopanel()
    {
    	try
   	 	{
   		 	Parent root=FXMLLoader.load(getClass().getResource("/admin_panel/Admin_panView.fxml")); 
   		 	Scene scene=new Scene(root);
   		 	Stage stage=new Stage();
   		 	stage.setScene(scene);
   		 	stage.show();
   		 	
   		//to hide the opened window
			 
			Scene scene1=(Scene)lblpass.getScene();
			   scene1.getWindow().hide();
			 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
    }

}

