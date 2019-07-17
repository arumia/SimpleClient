/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr;

import java.net.URL;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author thdtm
 */
public class FXMLDocumentController implements Initializable {
    
    private Connection conn;
    private ObservableList animalList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn = DBConnection.getConnection();
        animalList = new Animal().getAll(conn);
                //set up of columns
        setAniTable(animalList);
    }    
    private void setAniTable(ObservableList<Animal> animalList){
        animalIDColumn.setCellValueFactory(new PropertyValueFactory<>("animalId"));
        animalNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        animalAdressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        animalZooColumn.setCellValueFactory(new PropertyValueFactory<>("zooId"));
        animalFoodColumn.setCellValueFactory(new PropertyValueFactory<>("foodType"));
        animalVaccineColumn.setCellValueFactory(new PropertyValueFactory<>("vaccination"));
        animalcheckColumn.setCellValueFactory(new PropertyValueFactory<>("lastcheckdate"));
        animalCharacterColumn.setCellValueFactory(new PropertyValueFactory<>("character"));
        animalDesColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        animalTable.setItems(animalList);
    }
       @FXML
    private TableView<Animal> animalTable;

    @FXML
    private TableColumn<Animal, Integer> animalIDColumn;

    @FXML
    private TableColumn<Animal, String> animalNameColumn;

    @FXML
    private TableColumn<Animal, String> animalAdressColumn;

    @FXML
    private TableColumn<Animal, Integer> animalZooColumn;

    @FXML
    private TableColumn<Animal, String> animalFoodColumn;

    @FXML
    private TableColumn<Animal, String> animalVaccineColumn;

    @FXML
    private TableColumn<Animal, Timestamp> animalcheckColumn;

    @FXML
    private TableColumn<Animal, String> animalCharacterColumn;

    @FXML
    private TableColumn<Animal, String> animalDesColumn;
    
    @FXML
    private TextField serchNameTextField;
        
    @FXML
    private Button serchButton;
    
    @FXML
    public void searchNameButtonOnAction(ActionEvent action){
        conn = DBConnection.getConnection();
        String name = serchNameTextField.getText().trim();
        animalList = new Animal().getRestrictedList(conn, name);
        
        setAniTable(animalList);
    };
}
