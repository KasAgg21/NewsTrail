package bill_panel;

import java.io.File;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import bill_panel.HawkerBean;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import paper_master.MySQLConnector;

public class Bill_panelViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lblResp;

    @FXML
    private ToggleGroup stat;
    
    @FXML
    private RadioButton radpd;

    @FXML
    private RadioButton radupd;

    @FXML
    private TableView<HawkerBean> tableData;

    @FXML
    private TextField txtmob;

    @FXML
    private TextField txttot;
    
    Connection con;
    PreparedStatement pst;  
    String sel="";
    float tot=0;

   
    ObservableList<HawkerBean> Fetchbasedstat() 
    {
    	ObservableList<HawkerBean>	aary=FXCollections.observableArrayList();
    	String sela=sel;
    	try {
    		pst = con.prepareStatement("select * from bills where billstatus=?");
			pst.setString(1, sela);
    		ResultSet table=pst.executeQuery();
    	
    		while(table.next()) {
	    		String mno=table.getString("mobile");
	    		String dtfr = table.getString("datefrom");
	    		String dtto=table.getString("dateto");
	    		String bt=table.getString("bill");
	    		
	    		HawkerBean ref=new HawkerBean(mno, dtfr,dtto , bt);
	    		aary.addAll(ref);
    		}
    	
    	}
    	catch(Exception ex) { ex.printStackTrace(); }
    		return aary;
    }
    
    @FXML
    void dosearchallbills(ActionEvent event) {
    	if(radpd.isSelected())
    		sel="1";
    	else
    		sel="0";
    	tableData.getColumns().clear();
    	TableColumn<HawkerBean, String> name=new TableColumn<HawkerBean, String>("Mobile");//any thing
    	name.setCellValueFactory(new PropertyValueFactory<>("hname")); //name of column 
    	//name.setMinWidth(150);
    	
    	TableColumn<HawkerBean, String> mobile=new TableColumn<HawkerBean, String>(" Date From");//any thing
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	mobile.setMinWidth(50);
    	
    	TableColumn<HawkerBean, String> area=new TableColumn<HawkerBean, String>("Date To");//any thing
    	area.setCellValueFactory(new PropertyValueFactory<>("alloareas"));
    	area.setMinWidth(50);
    	
    	TableColumn<HawkerBean, String> hawker=new TableColumn<HawkerBean, String>("Total Amount");//any thing
    	hawker.setCellValueFactory(new PropertyValueFactory<>("doj"));
    	hawker.setMinWidth(50);
    	

    	
    	tableData.getColumns().addAll(new ArrayList<>(Arrays.asList(name, mobile, area, hawker)));
    	tableData.setItems(Fetchbasedstat());
    	
    	lblResp.setText("Records Fetched Successfully...");
    	

    }

    ObservableList<HawkerBean> Fetchbasedmob() 
    {
    	ObservableList<HawkerBean>	aary=FXCollections.observableArrayList();
    	String sela=txtmob.getText();
    	try {
    		pst = con.prepareStatement("select * from bills where mobile=?");
			pst.setString(1, sela);
    		ResultSet table=pst.executeQuery();
    	
    		while(table.next()) {
	    		String dtfr = table.getString("datefrom");
	    		String dtto=table.getString("dateto");
	    		String bt=table.getString("bill");
	      		String bts=table.getString("billstatus");
	      		if(Integer.parseInt(bts)==1)
	      			bts="Paid";
	      		else
	      			bts="Pending";
	      		if(bts=="Pending")
	      		{
	      			tot+=Float.parseFloat(bt);
	      			System.out.println(bt);
	      		}
	    		
	    		HawkerBean ref=new HawkerBean(dtfr,dtto , bt,bts);
	    		aary.addAll(ref);
    		}
    	
    	}
    	catch(Exception ex) { ex.printStackTrace(); }
    		return aary;
    }
    
    @FXML
    void showallbills(ActionEvent event) {
    	tableData.getColumns().clear();
    	TableColumn<HawkerBean, String> name=new TableColumn<HawkerBean, String>("Date From");//any thing
    	name.setCellValueFactory(new PropertyValueFactory<>("hname")); //name of column 
    	//name.setMinWidth(150);
    	
    	TableColumn<HawkerBean, String> mobile=new TableColumn<HawkerBean, String>(" Date To");//any thing
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	mobile.setMinWidth(50);
    	
    	TableColumn<HawkerBean, String> area=new TableColumn<HawkerBean, String>("Total Amount");//any thing
    	area.setCellValueFactory(new PropertyValueFactory<>("alloareas"));
    	area.setMinWidth(50);
    	
    	TableColumn<HawkerBean, String> hawker=new TableColumn<HawkerBean, String>("Bill Status");//any thing
    	hawker.setCellValueFactory(new PropertyValueFactory<>("doj"));
    	hawker.setMinWidth(50);
    	

    	
    	tableData.getColumns().addAll(new ArrayList<>(Arrays.asList(name, mobile, area, hawker)));
    	tableData.setItems(Fetchbasedmob());
    	
    	lblResp.setText("Records Fetched Successfully...");
    	txttot.setText(tot+"");

    }

    @SuppressWarnings("deprecation")
	@FXML
    void initialize() {
    	con = MySQLConnector.doConnect();
    	if(con==null) { System.out.println("Invalid Connection"); }
    	else { System.out.println("Connected");	}
    	tableData.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

}
