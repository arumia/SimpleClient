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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author thdtm
 */
public class UpdateAnimalController implements Initializable {

    static int animalId;
    
    @FXML
    private TextField addCharacter;

    @FXML
    private TextField addDescription;

    @FXML
    private Button updateAnimalButton;

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
    private Animal getAnimalData(){
        Animal animal = new Animal();
        animal.setAnimalId(animalId);
        try{
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
    public void updateAnimalButtonOnAction(ActionEvent action) throws Exception{
        Connection conn = DBConnection.getConnection();
        Integer res;
        Animal animal;
        animal = getAnimalData();
        if (animal != null) {
            res = animal.updateRowAnimal(conn, animal);
            if (res > 0) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Question");
                alert.setContentText("Do you want update?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {
                    clearAnimalScene();
                } else {
                    closeAnimalScene();
                }
            }
        }
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
    
    private void clearAnimalScene() {
        addCharacter.setText("");
        addDescription.setText("");
    }
    
    private void closeAnimalScene() {
        Stage addNewEmployee = (Stage) closeAnimalButton.getScene().getWindow();
        addNewEmployee.close();
    }
}
