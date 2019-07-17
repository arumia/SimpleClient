/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr;

/**
 *
 * @author thdtm
 */
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class Animal {

    public Integer getAnimalId() {
        return animalId;
    }

    public void setAnimalId(Integer animalId) {
        this.animalId = animalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getZooId() {
        return zooId;
    }

    public void setZooId(Integer zooId) {
        this.zooId = zooId;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getVaccination() {
        return vaccination;
    }

    public void setVaccination(String vaccination) {
        this.vaccination = vaccination;
    }

    public Timestamp getLastcheckdate() {
        return lastcheckdate;
    }

    public void setLastcheckdate(Timestamp lastcheckdate) {
        this.lastcheckdate = lastcheckdate;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    private Integer animalId;
    private String name;
    private String address;
    private Integer zooId;
    private String foodType;
    private String vaccination;
    private Timestamp lastcheckdate;
    private String character;
    private String description;     
    
    public ObservableList<Animal> getAll(Connection conn){
        ObservableList<Animal> listAnimal = FXCollections.observableArrayList();
        
        String sql = "SELECT ANIMAL_ID,NAME,ADDRESS,ZOO_ID,FOOD_TYPE,VACCINATION,LAST_CHECKU_DATE,"
                + "CHARACTERISTIC,DESCRIPTION FROM ANIMAL order by animal_id";
        
        Statement stmt;
        ResultSet rs;

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Animal animal = new Animal();
                animal.animalId = rs.getInt(1);
                animal.name = rs.getString(2);
                animal.address = rs.getString(3);
                animal.zooId = rs.getInt(4);
                animal.foodType = rs.getString(5);
                animal.vaccination = rs.getString(6);
		animal.lastcheckdate = rs.getTimestamp(7);
                animal.character = rs.getString(8);
                animal.description = rs.getString(9);
                
                System.out.println("AnimalId: " + animal.getAnimalId().toString() + " Animal Name: " 
                        + animal.getName());
                listAnimal.add(animal);
            }
        } catch (SQLException exc) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errots with data access");
            alert.setContentText("Details: " + exc.getMessage());
            alert.showAndWait();
        }

        return listAnimal;
        
    }
    public ObservableList<Animal> getRestrictedList(Connection conn, String pinName){
           ObservableList<Animal> listAnimal = FXCollections.observableArrayList();
        
        String sql = "SELECT ANIMAL_ID,NAME,ADDRESS,ZOO_ID,FOOD_TYPE,VACCINATION,LAST_CHECKU_DATE,"
                + "CHARACTERISTIC,DESCRIPTION FROM ANIMAL"
                + " WHERE UPPER(NAME) like ? ORDER BY ANIMAL_ID";
        
        PreparedStatement stmt;
        ResultSet rs;

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%"+pinName.toUpperCase()+"%");
            
            rs = stmt.executeQuery();
            
            listAnimal=getAnimalList(rs);
            }
         catch (SQLException exc) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Errots with data access");
            alert.setContentText("Details: " + exc.getMessage());
            alert.showAndWait();
        }
        
        return listAnimal;
        
    }
     private ObservableList<Animal> getAnimalList(ResultSet rs) throws SQLException{
        ObservableList<Animal> listAnimal = FXCollections.observableArrayList();
        
        while (rs.next()) {
                Animal animal = new Animal();
                animal.animalId = rs.getInt(1);
                animal.name = rs.getString(2);
                animal.address = rs.getString(3);
                animal.zooId = rs.getInt(4);
                animal.foodType = rs.getString(5);
                animal.vaccination = rs.getString(6);
		animal.lastcheckdate = rs.getTimestamp(7);
                animal.character = rs.getString(8);
                animal.description = rs.getString(9);
                
                System.out.println("animalId: " + animal.getAnimalId().toString() + " animal Name: " 
                        + animal.getName());
                listAnimal.add(animal);
            }
        return listAnimal;
    }

}

