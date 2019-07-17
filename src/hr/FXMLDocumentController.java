/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr;

import java.net.URL;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

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
    private Button addButton;
    
    @FXML
    public void searchNameButtonOnAction(ActionEvent action){
        conn = DBConnection.getConnection();
        String name = serchNameTextField.getText().trim();
        animalList = new Animal().getRestrictedList(conn, name);
        
        setAniTable(animalList);
    }
    
    public void addAnimalButtonOnAction(ActionEvent action) throws Exception{
        AddAnimal addAnimal = new AddAnimal();
        Stage addStage = new Stage();
        addAnimal.start(addStage);
    }

    @FXML
    public void deleteAnimalOnAction(ActionEvent action){
         Integer rowIndex=animalTable.getSelectionModel().getSelectedIndex();
         
         if(rowIndex<0){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error");
             alert.setContentText("No record is chosen...");
             alert.showAndWait();
             return;
         }
         
         Integer animalId = animalTable.getSelectionModel().getSelectedItem().getAnimalId();
         conn = DBConnection.getConnection();
         
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Confirmation");
         alert.setContentText("Do you really want delete the record");
         Optional<ButtonType> result = alert.showAndWait();
         
         if(result.get() == ButtonType.OK){
             Integer res = new Animal().removeAnimal(conn, animalId);
             if(res>0){
                 Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                 alert1.setTitle("Confirmation");
                 alert1.setContentText("The record has been removed...");
                 alert1.showAndWait();
             }
         } 
    }
    
    public void updateAnimalButtonOnAction(ActionEvent action) throws Exception{
        Integer rowIndex=animalTable.getSelectionModel().getSelectedIndex();
         
         if(rowIndex<0){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setTitle("Error");
             alert.setContentText("No record is chosen...");
             alert.showAndWait();
             return;
         }
         
        Integer animalId = animalTable.getSelectionModel().getSelectedItem().getAnimalId();
         
        UpdateAnimal updateAnimal = new UpdateAnimal();
        UpdateAnimalController.animalId = animalId;
        Stage updateStage = new Stage();
        updateAnimal.start(updateStage);
    }
}
