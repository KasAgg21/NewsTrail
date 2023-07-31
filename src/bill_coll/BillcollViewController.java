package bill_coll;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import paper_master.MySQLConnector;

public class BillcollViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblamt;

    @FXML
    private Label lblfr;

    @FXML
    private Label lblres;

    @FXML
    private Label lblto;

    @FXML
    private TextField txtmob;
    
    
    Connection con;
    PreparedStatement pst;
    float a=0;
    @FXML
    void dofetchbilldetails(ActionEvent event) {
    	String mob=txtmob.getText();
    	try {
			pst=con.prepareStatement("select * from bills where billstatus=0 and mobile=?");
			pst.setString(1, mob);
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				lblamt.setText("Amount :- "+table.getString("bill"));
				a=Float.parseFloat(table.getString("bill"));
				lblfr.setText("Date From :- "+table.getString("datefrom"));
				lblto.setText("Date To :- "+table.getString("dateto"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}

    }

    @FXML
    void dopaymentdone(ActionEvent event) {
    	String mob=txtmob.getText();
    	
    	try {
			pst=con.prepareStatement("update bills set billstatus=1 where mobile=? and bill=?");
			pst.setString(1, mob);
			pst.setFloat(2, a);
			pst.executeUpdate();
			lblres.setText("Payment Done");
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void initialize() {
    	con=MySQLConnector.doConnect();
        if(con==null)
        	System.out.println("Connection Problem");
        else
        	System.out.println("Connected");

    }

}
