package ui;

import java.io.File;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import model.Billboard;
import model.InfrastructureDepartment;

public class BillboardGUI {
	@FXML
    private BorderPane mainPanel;
	
	@FXML
    private MenuBar menuBar;

    @FXML
    private Menu menuFile;

    @FXML
    private MenuItem miAddBillboard;

    @FXML
    private MenuItem mishowBillboard;

    @FXML
    private MenuItem miImportBillboard;

    @FXML
    private MenuItem miExportDangerousReport;

    @FXML
    private MenuItem miClose;

    @FXML
    private Menu menuHelp;
    
    @FXML
    private MenuItem miAbout;
    
    @FXML
    private TableView<Billboard> tvBillboards;
    
    @FXML
    private TableColumn<Billboard, String> tcWidth;

    @FXML
    private TableColumn<Billboard, String> tcHeight;

    @FXML
    private TableColumn<Billboard, String> tcInUse;

    @FXML
    private TableColumn<Billboard, String> tcBrand;
	
	private InfrastructureDepartment infrastructureDepartment;
	
	public BillboardGUI(InfrastructureDepartment id) {
		infrastructureDepartment = id;
	}
	
	public void initialize() {
    	//the method (initialize) is called several times by diferents fxml file loads 
    }
	
	private void initializeTableView() {
    	ObservableList<Billboard> observableList;
    	observableList = FXCollections.observableArrayList(infrastructureDepartment.getBillboards());
    	
    	tvBillboards.setItems(observableList);
    	tcWidth.setCellValueFactory(new PropertyValueFactory<Billboard,String>("width")); //the tableview search for a method called getWidth
    	tcHeight.setCellValueFactory(new PropertyValueFactory<Billboard,String>("height")); //the tableview search for a method called getHeight
    	tcInUse.setCellValueFactory(new PropertyValueFactory<Billboard,String>("inUse")); //the tableview search for a method called getInUse
    	tcBrand.setCellValueFactory(new PropertyValueFactory<Billboard,String>("brand")); //the tableview search for a method called getBrand
    }
	
	@FXML
    public void loadBillboardList(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("billboardList.fxml"));
		
		fxmlLoader.setController(this);
		Parent billboardListPane = fxmlLoader.load();
    	
		mainPanel.getChildren().clear();
    	mainPanel.setCenter(billboardListPane);
    	initializeTableView();
    }
	
	@FXML
    public void addBillboard(ActionEvent event) {
    	//add contact in the model
    	try {
    		infrastructureDepartment.addContact(txtName.getText(),txtEmail.getText());
	    	
	    	//clean the fields in the gui
	    	txtName.setText("");
	    	txtEmail.setText("");
	    	
	    	//show the success message
	    	labMsg.setText("The contact was added succesfully!");
    	} catch(IOException e) {
    		e.printStackTrace();
    		labMsg.setText("The contact was not added");
    	}
    }
	
	@FXML
    public void showAbout(ActionEvent event) {
	    Alert alert = new Alert(AlertType.INFORMATION);
	    alert.setTitle("Infrastructure Department Program");
	    alert.setHeaderText("Credits");
	    alert.setContentText("Santiago Arévalo\nAlgorithms II");
	    alert.showAndWait();
    }
	
	public void importData(String fileName) {
		FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Open Resource File");
    	File file = fileChooser.showOpenDialog(mainPanel.getScene().getWindow());
    	if(file != null) {
    		try {
    			infrastructureDepartment.importBillboards(file.getAbsolutePath());
    			Alert alert = new Alert(AlertType.INFORMATION);
    		    alert.setTitle("Import Contacts");
    		    alert.setHeaderText(null);
    		    alert.setContentText("The billboards file have been imported");
    		    alert.showAndWait();
    		} catch(IOException io) {
    			Alert alert = new Alert(AlertType.ERROR);
    		    alert.setTitle("Import Contacts");
    		    alert.setHeaderText(null);
    		    alert.setContentText("The billboards file have not been imported");
    		    alert.showAndWait();
    		}
    	}
	}
	
	public void exportDangerousBillboardReport(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Save Resource File");
    	File file = fileChooser.showSaveDialog(mainPanel.getScene().getWindow());
    	if(file != null) {
    		try {
    			infrastructureDepartment.exportDangerousBillboardReport(file.getAbsolutePath());
    			Alert alert = new Alert(AlertType.INFORMATION);
    		    alert.setTitle("Export Contacts");
    		    alert.setHeaderText(null);
    		    alert.setContentText("The report file has been exported");
    		    alert.showAndWait();
    		} catch(IOException io) {
    			Alert alert = new Alert(AlertType.ERROR);
    		    alert.setTitle("Export Contacts");
    		    alert.setHeaderText(null);
    		    alert.setContentText("The report file has not been exported");
    		    alert.showAndWait();
    		}
    	}
	}
}
