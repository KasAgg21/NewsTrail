module NPproj {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.base;
	requires javafx.graphics;
	requires java.desktop;
	
	opens application to javafx.graphics, javafx.fxml;
	opens k2 to javafx.graphics, javafx.fxml;
	opens paper_master to javafx.graphics, javafx.fxml;
	opens hawker_manager to javafx.graphics, javafx.fxml;
	opens customer_manager to javafx.graphics, javafx.fxml;
	opens bill_gen to javafx.graphics, javafx.fxml;
	opens bill_coll to javafx.graphics, javafx.fxml;
	opens hk_table to javafx.graphics, javafx.fxml,javafx.base;
	opens about to javafx.graphics, javafx.fxml,javafx.base;
	opens cst_panel to javafx.graphics, javafx.fxml,javafx.base;
	opens bill_panel to javafx.graphics, javafx.fxml,javafx.base;
	opens admin_panel to javafx.graphics, javafx.fxml,javafx.base;
	opens admin_login to javafx.graphics, javafx.fxml,javafx.base;
}
