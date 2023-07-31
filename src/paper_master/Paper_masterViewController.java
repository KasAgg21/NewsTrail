package paper_master;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Paper_masterViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnped;

    @FXML
    private Button btnpel;

    @FXML
    private Button btnpls;

    @FXML
    private Button btnpse;

    @FXML
    private ComboBox<String> cmbpna;

    @FXML
    private TextField txtppr;
    
    @FXML
    private Label lblres;
    
    Connection con;
    PreparedStatement pst;

    @FXML
    void doedit(ActionEvent event) {
    	String pname=cmbpna.getSelectionModel().getSelectedItem();
    	float per=Float.parseFloat(txtppr.getText());
    	try {
			pst=con.prepareStatement("update papers set price=? where paper=?");
			pst.setFloat(1,per);
			pst.setString(2,pname);
			pst.executeUpdate();
			lblres.setText("Edited");
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void doeliminate(ActionEvent event) {
    	String pname=cmbpna.getSelectionModel().getSelectedItem().toString();
    	try {
			pst=con.prepareStatement("delete from papers where paper=?");
			pst.setString(1, pname);
			pst.executeUpdate();
			txtppr.clear();
			lblres.setText("Deleted");
			dofillpname();
		} catch (Exception e) {
			e.printStackTrace();
		}

    }

    @FXML
    void dolist(ActionEvent event) {
    	String pname=cmbpna.getSelectionModel().getSelectedItem().toString();
    	float per= Float.parseFloat(txtppr.getText());
    	try {
			pst=con.prepareStatement("insert into papers values (?,?)");
			pst.setString(1, pname);
			pst.setFloat(2, per);
			pst.executeUpdate();
			lblres.setText("Listed");
			dofillpname();
		} catch (Exception e) {
			e.printStackTrace();
		}

    }

    @FXML
    void dopapersearch(ActionEvent event) {
    	String pname=cmbpna.getSelectionModel().getSelectedItem();
    	float per=0;
    	try {
			pst=con.prepareStatement("select price from papers where paper=?");
			pst.setString(1,pname);
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				per=table.getFloat("price");
				txtppr.setText(per+"");
			}
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	

    }
    
    void dofillpname()
    {
    	cmbpna.getItems().clear();
    	try {
			pst=con.prepareStatement("select distinct paper from papers");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				String papern=table.getString("paper");
				System.out.println(papern);
				cmbpna.getItems().addAll(papern);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
    }

    @FXML
    void initialize() {
        con=MySQLConnector.doConnect();
        if(con==null)
        	System.out.println("Connection Problem");
        else
        	System.out.println("Connected");
        dofillpname();
    }

}
