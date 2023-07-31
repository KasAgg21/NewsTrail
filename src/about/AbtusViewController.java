package about;

import java.awt.Desktop;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class AbtusViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    void goemail(MouseEvent event) {
    	try {
            
            Desktop.getDesktop().mail(new URI("kagg219@gmail.com"));
            
    		
            
    	} catch (Exception e1) 
    	{
            e1.printStackTrace();
        } 

    }

    @FXML
    void gogithub(MouseEvent event) {
try {
            
            Desktop.getDesktop().browse(new URI("https://github.com/KasAgg21"));
            
    		
            
    	} catch (Exception e1) 
    	{
            e1.printStackTrace();
        }

    }

    @FXML
    void golinkdin(MouseEvent event) {
try {
            
            Desktop.getDesktop().browse(new URI("https://www.linkedin.com/in/kashish-aggarwal-689a04231/"));
            
    		
            
    	} catch (Exception e1) 
    	{
            e1.printStackTrace();
        }

    }

    @FXML
    void gowebsite(MouseEvent event) {
    	
    		try {
                
                Desktop.getDesktop().browse(new URI("https://www.realjavaonline.com/"));
                
        		
                
        	} catch (Exception e1) 
        	{
                e1.printStackTrace();
            }
	

    }

    @FXML
    void initialize() {

    }

}
