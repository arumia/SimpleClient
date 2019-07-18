/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author thdtm
 */
public class AddAnimalController implements Initializable {

    @FXML
    private TextField addName;

    @FXML
    private TextField addAddress;

    @FXML
    private TextField addFood;

    @FXML
    private TextField addVactin;

    @FXML
    private DatePicker addDate;

    @FXML
    private TextField addCharacter;

    @FXML
    private TextField addDescription;

    @FXML
    private Button addAnimalButton;

    @FXML
    private Button clearAnimalButton;

    @FXML
    private Button closeAnimalButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void closeAnimalButtonOnAction(ActionEvent event){
    Stage addNewEmployee = (Stage) closeAnimalButton.getScene().getWindow();
    addNewEmployee.close();
    }
    
    @FXML
    public void clearAnimalButtonOnAction(ActionEvent event){
       clearAnimalScene();
    }
    
    private Animal getAnimalData(){
        Animal animal = new Animal();
        
        try{
            
            if(addName.getText().trim().length()<1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The field name can't be empty");
            alert.showAndWait(); 
            return null;
            }
            else{
             animal.setName(addName.getText().trim());
            }
            if(addAddress.getText().trim().length()<1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The field Address can't be empty");
            alert.showAndWait(); 
            return null;
            }
            else{
             animal.setAddress(addAddress.getText().trim());
            }
            if(addFood.getText().trim().length()<1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The field Food can't be empty");
            alert.showAndWait(); 
            return null;
            }
            else{
             animal.setFoodType(addFood.getText().trim());
            }
            if(addVactin.getText().trim().length() != 1){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The field Vaccination can only one character");
            alert.showAndWait(); 
            return null;
            }
            else if (addVactin.getText().trim().equals("o") ||addVactin.getText().trim().equals("x") || addVactin.getText().trim().equals("O") || addVactin.getText().trim().equals("X")){
                if(addVactin.getText().trim().equals("o") ||addVactin.getText().trim().equals("x"))
                 animal.setVaccination(addVactin.getText().trim().toUpperCase());
                animal.setVaccination(addVactin.getText().trim());
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("The field Vaccination only O or X");
                alert.showAndWait(); 
                return null;
            }
            if((addDate.getValue())==null){
 /*             Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setContentText("The field Date can't be empty");
             alert.showAndWait(); */
             animal.setLastcheckdate(null);
             //return null;
            }
            else{
                animal.setLastcheckdate(Date.valueOf(addDate.getValue()));
            }
            if(addCharacter.getText().trim().length()<1){
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setContentText("The field Character can't be empty");
             alert.showAndWait(); 
             return null;
            }
            else{
                animal.setCharacter(addCharacter.getText().trim());
            }
            
            animal.setDescription(addDescription.getText().trim());

        } catch (NumberFormatException|NullPointerException exc) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect data, please correct and try again...");
            alert.setContentText("Details: " + exc.getMessage());
            alert.showAndWait();
        }
        
        return animal;
    }
    @FXML
    public void addAnimalButtonOnAction(ActionEvent event) {
        Connection conn = DBConnection.getConnection();
        Integer res;
        Animal animal;
        animal = getAnimalData();
        if (animal != null) {
            res = animal.insertRowAnimal(conn, animal);
            if (res > 0) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Question");
                alert.setContentText("Do you want add new one");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    clearAnimalScene();
                } else {
                    closeAnimalScene();
                }
            }

        }
    }
    private void closeAnimalScene() {
        Stage addNewEmployee = (Stage) closeAnimalButton.getScene().getWindow();
        addNewEmployee.close();
    }

    private void clearAnimalScene() {
        addName.setText("");
        addAddress.setText("");
        addFood.setText("");
        addVactin.setText("");
        addDate.setValue(null);
        addCharacter.setText("");
        addDescription.setText("");

    }
}