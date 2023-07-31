package cst_panel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import paper_master.MySQLConnector;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;


public class Cst_PanelViewController {
	@FXML
    private ComboBox<String> cmbara;

    @FXML
    private ComboBox<String> cmbpap;

    @FXML
    private Label lblResp;

    @FXML
    void doara(ActionEvent event) {

    }
    
    @FXML
    void doarafet(ActionEvent event) {
    	tableData.getColumns().clear();
    	TableColumn<HawkerBean, String> name=new TableColumn<HawkerBean, String>("Customer Name");//any thing
    	name.setCellValueFactory(new PropertyValueFactory<>("hname")); //name of column 
    	//name.setMinWidth(150);
    	
    	TableColumn<HawkerBean, String> mobile=new TableColumn<HawkerBean, String>(" Mobile No");//any thing
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	mobile.setMinWidth(50);
    	
    	TableColumn<HawkerBean, String> spapers=new TableColumn<HawkerBean, String>("Papers");//any thing
    	spapers.setCellValueFactory(new PropertyValueFactory<>("alloareas"));
    	spapers.setMinWidth(50);
    	
    	TableColumn<HawkerBean, String> hawker=new TableColumn<HawkerBean, String>("Hawker Name");//any thing
    	hawker.setCellValueFactory(new PropertyValueFactory<>("doj"));
    	hawker.setMinWidth(50);
    	
    	TableColumn<HawkerBean, String> em=new TableColumn<HawkerBean, String>("EmailId");//any thing
    	em.setCellValueFactory(new PropertyValueFactory<>("em"));
    	em.setMinWidth(50);

    	
    	tableData.getColumns().addAll(new ArrayList<>(Arrays.asList(name, mobile, spapers, hawker, em)));
    	tableData.setItems(Fetchbasedarea());
    	
    	lblResp.setText("Records Fetched Successfully...");

    }
    
    ObservableList<HawkerBean> Fetchbasedarea() 
    {
    	ObservableList<HawkerBean>	aary=FXCollections.observableArrayList();
    	String sela=cmbara.getSelectionModel().getSelectedItem();
    	try {
    	   	
    		pst = con.prepareStatement("select * from customers where area=?");
    		pst.setString(1, sela);
    		ResultSet table=pst.executeQuery();
    	
    		while(table.next()) {
	    		String mno=table.getString("mobile");
	    		String name = table.getString("cname");
	    		String pap=table.getString("spapers");
	    		String hwk=table.getString("hawker");
	    		String em=table.getString("email");
	    		
	    		HawkerBean ref=new HawkerBean(name, mno, pap, hwk, em);
	    		aary.addAll(ref);
    		}
    	
    	}
    	catch(Exception ex) { ex.printStackTrace(); }
    		return aary;
    }
    
    ObservableList<HawkerBean> Fetchbasedpaper() 
    {
    	ObservableList<HawkerBean>	aary=FXCollections.observableArrayList();
    	String sela=cmbpap.getSelectionModel().getSelectedItem();
    	try {
    		pst = con.prepareStatement("select * from customers where spapers like ?");
			pst.setString(1, "%"+sela+"%");
    		ResultSet table=pst.executeQuery();
    	
    		while(table.next()) {
	    		String mno=table.getString("mobile");
	    		String name = table.getString("cname");
	    		String ara=table.getString("area");
	    		String hwk=table.getString("hawker");
	    		String em=table.getString("email");
	    		
	    		HawkerBean ref=new HawkerBean(name, mno, ara, hwk, em);
	    		aary.addAll(ref);
    		}
    	
    	}
    	catch(Exception ex) { ex.printStackTrace(); }
    		return aary;
    }


    @FXML
    void dopap(ActionEvent event) {

    }

    @FXML
    void dopapfet(ActionEvent event) {
    	tableData.getColumns().clear();
    	TableColumn<HawkerBean, String> name=new TableColumn<HawkerBean, String>("Customer Name");//any thing
    	name.setCellValueFactory(new PropertyValueFactory<>("hname")); //name of column 
    	//name.setMinWidth(150);
    	
    	TableColumn<HawkerBean, String> mobile=new TableColumn<HawkerBean, String>(" Mobile No");//any thing
    	mobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
    	mobile.setMinWidth(50);
    	
    	TableColumn<HawkerBean, String> area=new TableColumn<HawkerBean, String>("Area");//any thing
    	area.setCellValueFactory(new PropertyValueFactory<>("alloareas"));
    	area.setMinWidth(50);
    	
    	TableColumn<HawkerBean, String> hawker=new TableColumn<HawkerBean, String>("Hawker Name");//any thing
    	hawker.setCellValueFactory(new PropertyValueFactory<>("doj"));
    	hawker.setMinWidth(50);
    	
    	TableColumn<HawkerBean, String> em=new TableColumn<HawkerBean, String>("Email Id");//any thing
    	em.setCellValueFactory(new PropertyValueFactory<>("em"));
    	em.setMinWidth(50);

    	
    	tableData.getColumns().addAll(new ArrayList<>(Arrays.asList(name, mobile, area, hawker, em)));
    	tableData.setItems(Fetchbasedpaper());
    	
    	lblResp.setText("Records Fetched Successfully...");

    }

    void dofillarea()
    {
    	cmbara.getItems().clear();
    	try {
			pst=con.prepareStatement("select distinct area from customers");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				String aname=table.getString("area");
				System.out.println(aname);
				cmbara.getItems().addAll(aname);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
    }
    
    void dofillpaper()
    {
    	cmbpap.getItems().clear();
    	try {
			pst=con.prepareStatement("select distinct paper from papers");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				String papern=table.getString("paper");
				System.out.println(papern);
				cmbpap.getItems().addAll(papern);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
    }
    
    @FXML
    private TableView<HawkerBean> tableData;

    @FXML
    void doexcel(ActionEvent event) {
    	
    	 FileChooser fileChooser = new FileChooser();
         fileChooser.setTitle("Save Excel File");
         fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Excel Files", "*.xlsx"));
         File file = fileChooser.showSaveDialog(new Stage());
         
         if (file != null) {
             try {
                 writeExcel(file, tableData.getItems());
                 lblResp.setText("Data Exported to Excel Successfully...");
             } catch (IOException e) {
                 lblResp.setText("Error exporting data to Excel.");
                 e.printStackTrace();
             }
         }
    }

    private void writeExcel(File file, ObservableList<HawkerBean> data) throws IOException {
        try (Writer writer = new BufferedWriter(new FileWriter(file))) {
            String header = "Customer Name, Mobile No, Paper, Area, Hawker Name, Email Id\n";
            writer.write(header);
            for (HawkerBean bean : data) {
                String row = bean.getHname() + "," + bean.getMobile() + "," + bean.getAlloareas() + ","
                        + bean.getDoj() + "," + bean.getEm() + "\n";
                writer.write(row);
            }
        }
    }
    	
    	
    	
    Connection con;
    PreparedStatement pst;  
    
   
    @SuppressWarnings("deprecation")
	@FXML
    void initialize() {
    	con = MySQLConnector.doConnect();
    	if(con==null) { System.out.println("Invalid Connection"); }
    	else { System.out.println("Connected");	}
    	tableData.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    	dofillarea();
    	dofillpaper();
    }
}
