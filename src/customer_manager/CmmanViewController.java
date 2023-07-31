package customer_manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import paper_master.MySQLConnector;

public class CmmanViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cmbare;

    @FXML
    private Button dosub;

    @FXML
    private Label lblres;

    @FXML
    private ListView<String> lstpna;

    @FXML
    private ListView<String> lstppr;

    @FXML
    private ListView<String> lstspn;

    @FXML
    private ListView<String> lstspp;

    @FXML
    private TextField txtadd;

    @FXML
    private DatePicker dos;

    @FXML
    private TextField txteid;

    @FXML
    private TextField txthaw;

    @FXML
    private TextField txtmob;

    @FXML
    private TextField txtnam;

    Connection con;
    PreparedStatement pst;
    String spapnam=" ";
    String spapppr=" ";
    @FXML
    void dofetch(ActionEvent event) {
		lstspn.getItems().clear();
		lstspp.getItems().clear();
    	String cname="0";
    	String mob=txtmob.getText();;
    	String spap="0";
    	String sppr="0";
    	String ar="0";
    	String hknam="0";
    	String em="0";
    	String add="0";
    	try {
			pst=con.prepareStatement("select * from customers where mobile=?");
			pst.setString(1,mob);
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				
				cname=table.getString("cname");
				txtnam.setText(cname);
				
		    	spap=table.getString("spapers");
		        ArrayList<String> pnlst = new ArrayList<>();
		        String[] pnar = spap.split(",");
		        pnlst.addAll(Arrays.asList(pnar));
		        lstspn.getItems().addAll(pnlst);
		        
		        sppr=table.getString("sprices");
		        ArrayList<String> pprlst = new ArrayList<>();
		        String[] pprar = sppr.split(",");
		        pprlst.addAll(Arrays.asList(pprar));
		        lstspp.getItems().addAll(pprlst);
		    	
		    	ar=table.getString("area");
		    	cmbare.setValue(ar);
		    	
		    	
		    	em=table.getString("email");
		    	txteid.setText(em);
		    	
		    	add=table.getString("address");
		    	txtadd.setText(add);
		    	
		    	java.sql.Date d=table.getDate("dos");
		    	java.time.LocalDate localDate = d.toLocalDate();
		    	DatePicker datePicker = new DatePicker();

		    	dos.setValue(localDate);
			}
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void dormpname(MouseEvent event) {
    	if(event.getClickCount()==2)
    	{
    		int idx=lstspn.getSelectionModel().getSelectedIndex();
    		lstspn.getItems().remove(lstspn.getSelectionModel().getSelectedItem());
    		lstspp.getItems().remove(lstspp.getItems().get(idx));
    	}

    }

    @FXML
    void dormpprice(MouseEvent event) {
    	if(event.getClickCount()==2)
    	{
    		int idx=lstspp.getSelectionModel().getSelectedIndex();
    		lstspp.getItems().remove(lstspp.getSelectionModel().getSelectedItem());
    		lstspn.getItems().remove(lstspn.getItems().get(idx));
    	}

    }

    @FXML
    void doselpname(MouseEvent event) {
    	if(event.getClickCount()==2)
    	{
    		int idx=lstpna.getSelectionModel().getSelectedIndex();
    		lstspn.getItems().add(lstpna.getSelectionModel().getSelectedItem());
    		lstspp.getItems().add(lstppr.getItems().get(idx));
    	}
    }

    @FXML
    void doselpprice(MouseEvent event) {
    	if(event.getClickCount()==2)
    	{
    		int idx=lstppr.getSelectionModel().getSelectedIndex();
    		lstspp.getItems().add(lstppr.getSelectionModel().getSelectedItem());
    		lstspn.getItems().add(lstpna.getItems().get(idx));
    	}

    }

    @FXML
    void dosubscribe(ActionEvent event) {
    	dopapernameandprice();
    	String mob=txtmob.getText();
    	String cname=txtnam.getText();
    	String spap=spapnam;
    	String sppr=spapppr;
    	String ar=cmbare.getSelectionModel().getSelectedItem();
    	String hknam=txthaw.getText();
    	String em=txteid.getText();
    	String add=txtadd.getText();
    	LocalDate dosi= dos.getValue();
    	java.sql.Date dt=java.sql.Date.valueOf(dosi);
    	try {
			pst=con.prepareStatement("insert into customers values (?,?,?,?,?,?,?,?,?)");
			pst.setString(1, mob);
			pst.setString(2, cname);
			pst.setString(3, spap);
			pst.setString(4, sppr);
			pst.setString(5, ar);
			pst.setString(6, hknam);
			pst.setString(7, em);
			pst.setString(8, add);
			pst.setDate(9, dt);
			pst.executeUpdate();
			lblres.setText("Subscribed...");
			//dofillhname();
		} catch (Exception e) {
			e.printStackTrace();
		}

    }

    
    
    @FXML
    void dounsub(ActionEvent event) {
    	String mob=txtmob.getText();
    	try {
			pst=con.prepareStatement("delete from customers where mobile=?");
			pst.setString(1, mob);
			pst.executeUpdate();
			txtmob.clear();
			txtadd.clear();
			txteid.clear();
			txthaw.clear();
			txtnam.clear();
			cmbare.getItems().clear();
			lstspn.getItems().clear();
			lstspp.getItems().clear();
			dos.setValue(null);
		} catch (Exception e) {
			e.printStackTrace();
		}

    }

    @FXML
    void doupdate(ActionEvent event) {
    	dopapernameandprice();
    	System.out.println(spapnam+"-"+spapppr);
    	String mob=txtmob.getText();
    	String cname=txtnam.getText();
    	String spap=spapnam;
    	String sppr=spapppr;
    	String ar=cmbare.getSelectionModel().getSelectedItem();
    	String hknam=txthaw.getText();
    	String em=txteid.getText();
    	String add=txtadd.getText();
    	LocalDate dosi= dos.getValue();
    	java.sql.Date dt=java.sql.Date.valueOf(dosi);
    	try {
			pst=con.prepareStatement("update customers set cname=?,spapers=?,sprices=?,area=?,hawker=?,email=?,address=? where mobile=?");
			pst.setString(1, cname);
			pst.setString(2, spap);
			pst.setString(3, sppr);
			pst.setString(4, ar);
			pst.setString(5, hknam);
			pst.setString(6, em);
			pst.setString(7, add);
			pst.setString(8, mob);
			pst.executeUpdate();
			lblres.setText("Updated...");
			//dofillcname();
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    void dofillarea()
    {
    	cmbare.getItems().clear();
    	try {
			pst=con.prepareStatement("select distinct area from customers");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				String aname=table.getString("area");
				System.out.println(aname);
				cmbare.getItems().addAll(aname);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
    }
    
    
    
    @FXML
    void doviewhawkers(ActionEvent event) {
    	String are=cmbare.getSelectionModel().getSelectedItem();
    	try {
			pst=con.prepareStatement("select hname from hawkers where alloareas like ? ");
			pst.setString(1, "%"+are+"%");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				String hname=table.getString("hname");
				System.out.println(hname);
				txthaw.setText(hname);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}


    }
    
    void dofillpname()
    {
    	lstpna.getItems().clear();
    	try {
			pst=con.prepareStatement("select paper,price from papers");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				String papern=table.getString("paper");
				float ppr=table.getFloat("price");
				System.out.println(papern+"-"+ppr);
				lstpna.getItems().addAll(papern);
				lstppr.getItems().add(String.valueOf(ppr));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
    }
    
    void dopapernameandprice()
    {
    	ObservableList<String> selppna=lstspn.getItems();
		String selpna="";
		for (int i = 0; i < selppna.size(); i++) {
			selpna+=selppna.get(i)+",";
		}
		if(selpna.endsWith(","))
    		selpna=selpna.substring(0,selpna.length()-1);
		spapnam=selpna;
		System.out.println(selpna);
		
		ObservableList<String> selpprc=lstspp.getItems();
		String selppnrc="";
		for (int i = 0; i < selppna.size(); i++) {
			selppnrc+=selpprc.get(i)+",";
		}
		if(selppnrc.endsWith(","))
    		selppnrc=selppnrc.substring(0,selppnrc.length()-1);

		spapppr=selppnrc;
		
    }
    

    @FXML
    void initialize() {
        con = MySQLConnector.doConnect();
        if(con==null)
        	System.out.println("Connection Problem");
        else
        	{System.out.println("Connected");}
        dofillpname();
        dofillarea();
        
        

    }

}
