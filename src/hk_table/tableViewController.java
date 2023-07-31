package hk_table;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import paper_master.MySQLConnector;


public class tableViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Label lblResp;

    @FXML
    private TableView<HawkerBean> tableData;

    @FXML
    void doFetch(ActionEvent event) {
    	TableColumn<HawkerBean, String> name=new TableColumn<HawkerBean, String>("Hawker Name");//any thing
    	name.setCellValueFactory(new PropertyValueFactory<>("hname")); //name of column 
    	//name.setMinWidth(150);
    	
    	TableColumn<HawkerBean, String> mobile=new TableColumn<HawkerBean, String>("Hawker Mobile No");//any thing
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	mobile.setMinWidth(50);
    	
    	TableColumn<HawkerBean, String> alloarea=new TableColumn<HawkerBean, String>("Allocated Areas");//any thing
    	alloarea.setCellValueFactory(new PropertyValueFactory<>("alloareas"));
    	alloarea.setMinWidth(50);
    	
    	TableColumn<HawkerBean, String> doj=new TableColumn<HawkerBean, String>("Date of joining");//any thing
    	doj.setCellValueFactory(new PropertyValueFactory<>("doj"));
    	doj.setMinWidth(50);
    	
    	tableData.getColumns().addAll(new ArrayList<>(Arrays.asList(name, mobile, alloarea, doj)));
    	tableData.setItems(FetchAllHawkers());
    	
    	lblResp.setText("Records Fetched Successfully...");
    }
    
    Connection con;
    PreparedStatement pst;  
    
    ObservableList<HawkerBean> FetchAllHawkers() 
    {
    	ObservableList<HawkerBean>	ary=FXCollections.observableArrayList();
    	try {
    	   	
    		pst = con.prepareStatement("select * from hawkers");
    		ResultSet table=pst.executeQuery();
    	
    		while(table.next()) {
	    		String mno=table.getString("mobile");
	    		String name = table.getString("hname");
	    		String DOJ = String.valueOf(table.getDate("doj").toLocalDate());
	    		String alloarea=table.getString("alloareas");
	    		System.out.println(alloarea);
	    		
	    		HawkerBean ref=new HawkerBean(name, mno, alloarea, DOJ);
	    		ary.add(ref);
    		}
    	
    	}
    	catch(Exception ex) { ex.printStackTrace(); }
    		return ary;
    }
   
    @FXML
    void initialize() {
    	con = MySQLConnector.doConnect();
    	if(con==null) { System.out.println("Invalid Connection"); }
    	else { System.out.println("Connected");	}
    }
}
