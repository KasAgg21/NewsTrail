package bill_gen;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import paper_master.MySQLConnector;

public class BgenViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private DatePicker billfrom;

    @FXML
    private DatePicker billto;

    @FXML
    private ComboBox<String> cmbmob;

    @FXML
    private Label lbltbl;

    @FXML
    private TextField txtmsd;

    @FXML
    private TextField txtpap;

    @FXML
    private TextField txtprc;

    @FXML
    private TextField txttpr;
    

    @FXML
    private Label lblres;
    
    Connection con;
    PreparedStatement pst;
    float pr=0;
    @FXML
    void dogenbill(ActionEvent event) {
    	
        LocalDate blfr= billfrom.getValue();
    	java.sql.Date bfr=java.sql.Date.valueOf(blfr);
        LocalDate blto= billto.getValue();
    	java.sql.Date bto=java.sql.Date.valueOf(blto);
    	if(ChronoUnit.DAYS.between(blfr, blto)+1<=0)
    	{
    		lblres.setText("Cannot Make Bill of 0 Days");
    	}
    	float diff= ChronoUnit.DAYS.between(blfr, blto)+1-Float.parseFloat(txtmsd.getText());
    	float tot=pr*diff;
    	lbltbl.setText("Total Bill :- "+tot+"");
    	
    	String mob=cmbmob.getSelectionModel().getSelectedItem();
    	
    	//try to get date from table later
    	
    	try {
			pst=con.prepareStatement("insert into bills(mobile, datefrom, dateto, bill) values (?,?,?,?)");
			pst.setString(1, mob);
			pst.setDate(2, bfr);
			pst.setDate(3, bto);
			pst.setFloat(4, tot);
			pst.executeUpdate();
			lblres.setText("Generated and Saved");
		} catch (Exception e) {
			e.printStackTrace();
		}
    	

    }

    @FXML
    void dosearchlastbill(ActionEvent event) {
    	String mob=cmbmob.getSelectionModel().getSelectedItem();
    	String prc="";
    	float tot = 0;
    	try {
			pst=con.prepareStatement("select * from customers where mobile=?");
			pst.setString(1,mob );
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				txtpap.setText(table.getString("spapers"));
				prc=table.getString("sprices");
				txtprc.setText(prc);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
    	
        ArrayList<Float> pprlstFloat = new ArrayList<>();
        String[] pprar = prc.split(",");
        for (String value : pprar) {
            try {
                float floatValue = Float.parseFloat(value);
                tot+=floatValue;
            } catch (NumberFormatException e) {
                // Handle invalid input, if necessary
                System.out.println("Invalid float value: " + value);
            }
        }
        txttpr.setText(tot+"");
        pr=tot;

    }
    
    @FXML
    void showdet(ActionEvent event) {
    	

    }
    
    void dofillmobs()
    {
    	cmbmob.getItems().clear();
    	try {
			pst=con.prepareStatement("select mobile from customers");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				String mobile=table.getString("mobile");
				System.out.println(mobile);
				cmbmob.getItems().addAll(mobile);
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
        dofillmobs();

    }

}
