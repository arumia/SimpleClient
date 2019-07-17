/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    

}
