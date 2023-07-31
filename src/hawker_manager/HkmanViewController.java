package hawker_manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import paper_master.MySQLConnector;

public class HkmanViewController {


    @FXML
    private ComboBox<String> cmbara;

    @FXML
    private ComboBox<String> cmbhnm;

    @FXML
    private ImageView imgaad;

    @FXML
    private Label lblpath;

    @FXML
    private Label lblres;

    @FXML
    private TextField txtadd;

    @FXML
    private TextField txtala;

    @FXML
    private TextField txtmob;

    Connection con;
    PreparedStatement pst;
    
    @FXML
    void doSearch(ActionEvent event) {
    	String hname=cmbhnm.getSelectionModel().getSelectedItem().toString();
    	String mob="0";
    	String add="0";
    	String alloc="0";
    	String path="0";
    	Date date = new Date();
    	String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
    	try {
			pst=con.prepareStatement("select * from hawkers where hname=?");
			pst.setString(1,hname);
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				mob=table.getString("mobile");
				txtmob.setText(mob);
				
				add=table.getString("address");
				txtadd.setText(add);
				
				alloc=table.getString("alloareas");
				txtala.setText(alloc);
				
				path=table.getString("picpath");
				lblpath.setText(path);
				imgaad.setImage(new Image(new FileInputStream(path)));
			}
    	} catch (SQLException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void doenroll(ActionEvent event) {
    	String hname=cmbhnm.getSelectionModel().getSelectedItem().toString();
    	String mob=txtmob.getText();
    	String add=txtadd.getText();
    	String alloc=txtala.getText();
    	String path=lblpath.getText();
    	Date date = new Date();
    	String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(date);
    	try {
			pst=con.prepareStatement("insert into hawkers values (?,?,?,?,?,?)");
			pst.setString(1, hname);
			pst.setString(2, mob);
			pst.setString(3, add);
			pst.setString(4, alloc);
			pst.setString(5, path);
			pst.setString(6, modifiedDate);
			pst.executeUpdate();
			lblres.setText("Enrolled");
			dofillhname();
		} catch (Exception e) {
			e.printStackTrace();
		}

    }

    @FXML
    void donew(ActionEvent event) {
    	cmbhnm.getItems().clear();
    	dofillhname();
    	txtmob.clear();
		txtadd.clear();
		txtala.clear();
		lblpath.setText("Path");
		imgaad.setImage(null);

    }

    @FXML
    void doremove(ActionEvent event) {
    	String hname=cmbhnm.getSelectionModel().getSelectedItem().toString();
    	try {
			pst=con.prepareStatement("delete from hawkers where hname=?");
			pst.setString(1, hname);
			pst.executeUpdate();
			txtmob.clear();
			txtadd.clear();
			txtala.clear();
			lblpath.setText("Path");
			lblres.setText("Deleted");
			dofillhname();
		} catch (Exception e) {
			e.printStackTrace();
		}

    }

    @FXML
    void doupdate(ActionEvent event) {
    	String hname=cmbhnm.getSelectionModel().getSelectedItem().toString();
    	String mob=txtmob.getText();
    	String add=txtadd.getText();
    	String alloc=txtala.getText();
    	String path=lblpath.getText();
    	try {
			pst=con.prepareStatement("update hawkers set mobile=?,address=?,alloareas=?,picpath=? where hname=?");
			pst.setString(5, hname);
			pst.setString(1, mob);
			pst.setString(2, add);
			pst.setString(3, alloc);
			pst.setString(4, path);
			pst.executeUpdate();
			lblres.setText("Edited");
			dofillhname();
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

	
    @FXML
    void showareas(ActionEvent event) {
    	String alloc=txtala.getText();
    	String salloc=cmbara.getSelectionModel().getSelectedItem();
    	if (!alloc.equals("a")) {
            // If 'alloc' is not "a", which means it already has some areas selected,
            // then append a comma before adding the new area.
            alloc += ",";
        }
    	
    	
        alloc += salloc;
        if(alloc.startsWith(","))
    		alloc=alloc.substring(0,alloc.length()-1);
        txtala.setText(alloc);
    }

    @FXML
    void douploadaadhar(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
   	 fileChooser.setTitle("Open Resource File");
   	 fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif") );
   	 File selectedFile = fileChooser.showOpenDialog(null);
   	 
   	 if (selectedFile != null) {
   	    lblpath.setText(selectedFile.getPath());
   	    Image img=new Image(selectedFile.toURI().toString());
   	    System.out.println(selectedFile.toURI().toString());
   	    try {
			imgaad.setImage(new Image(new FileInputStream(selectedFile)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   	    //picPrev.setImage(img);
   	 }
   	 else
   	 {
   		 lblpath.setText("nopic.jpg");
   	 }

    }
    
    void dofillhname()
    {
    	cmbhnm.getItems().clear();
    	try {
			pst=con.prepareStatement("select distinct hname from hawkers");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				String hname=table.getString("hname");
				System.out.println(hname);
				cmbhnm.getItems().addAll(hname);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
    }
    
    void doareas()
    {
    	String ar="";
    	try {
			pst=con.prepareStatement("select distinct area from customers");
			ResultSet table=pst.executeQuery();
			while(table.next())
			{
				ar=table.getString("area");
				cmbara.getItems().addAll(ar);
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
        	{System.out.println("Connected");}
    	dofillhname();
    	System.out.println("H");
    	doareas();
    	//ArrayList<String> areas=new ArrayList<String>(Arrays.asList("Amrik Singh Road","Teacher Colony","Green City-1","Green City-2","Green City-3","Green City-4"));
    	//cmbara.getItems().addAll(areas);

    }

}
