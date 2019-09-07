package views;

import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import models.AppointmentDB;

/**
 *
 * @author Michael Farmer
 */
public class AppointmentAddController implements Initializable {

    @FXML
    private TextField customerName;
    
    @FXML
    private ComboBox contact;
    
    @FXML
    private TextField location;
    
    @FXML 
    private DatePicker date;
    
    @FXML
    private ComboBox time;
    
    @FXML
    private ComboBox type;
    
    private final ObservableList<String> contacts = FXCollections.observableArrayList("Joe John", "Fred New", "Nina Fung");
    
    private final ObservableList<String> times = FXCollections.observableArrayList("8:00 AM", "9:00 AM","10:00 AM","11:00 AM","12:00 PM","1:00 PM",
            "2:00 PM","3:00 PM","4:00 PM");
    
    private final ObservableList<String> types = FXCollections.observableArrayList("Pesentation","Scrum","Financial","Other");
    
    private ObservableList<String> errors = FXCollections.observableArrayList();
    
    public boolean handleAddAppointment(int id) {
        errors.clear();
        int aptContact = contact.getSelectionModel().getSelectedIndex();
        int aptType = type.getSelectionModel().getSelectedIndex();
        int aptTime = time.getSelectionModel().getSelectedIndex();
        LocalDate ld = date.getValue();
        if(!validateContact(aptContact)||!validateType(aptType)||!validateTime(aptTime)||!validateDate(ld)) {
            return false;
        }
        if(AppointmentDB.saveAppointment(id, types.get(aptType), contacts.get(aptContact), location.getText(), ld.toString(), times.get(aptTime))) {
            return true;
        }
        if(AppointmentDB.overlappingAppointment(-1, location.getText(), ld.toString(), times.get(aptTime))) {
            errors.add("Overlapping Appointments");
            return false;
        } else {
            errors.add("Database Error");
            return false;
        }
    }
    
    public boolean validateContact(int aptContact) {
        if(aptContact == -1) {
            errors.add("A Contact must be selected");
            return false;
        } else {
            return true;
        }
    }
    
    public boolean validateType(int aptType) {
        if(aptType == -1) {
            errors.add("An Appointment Type must be selected");
            return false;
        } else {
            return true;
        }
    }
    
    public boolean validateTime(int aptTime) {
        if(aptTime == -1) {
            errors.add("An Appointment Time must be selected");
            return false;
        } else {
            return true;
        }
    }
    
    public boolean validateDate(LocalDate aptDate) {
        if(aptDate == null) {
            errors.add("An Appointment Date must be selected");
            return false;
        } else {
            return true;
        }
    }
    
    public String displayErrors(){
        String s = "";
        if(errors.size() > 0) {
            for(String err : errors) {
                s = s.concat(err);
            }
            return s;
        } else {
            s = "Database Error";
            return s;
        }
    }
    
    @FXML
    public void handleLocation() {
        String c = contact.getSelectionModel().getSelectedItem().toString();
        if(c.equals("Joe John")) {
            location.setText("New York");
        } else if(c.equals("Fred New")) {
            location.setText("Phoenix");
        } else if(c.equals("Nina Fung")) {
            location.setText("London");
        }
    }
    
    public void populateCustomerName(String name) {
        customerName.setText(name);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        contact.setItems(contacts);
        time.setItems(times);
        type.setItems(types);
        date.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || 
                    date.getDayOfWeek() == DayOfWeek.SATURDAY || 
                    date.getDayOfWeek() == DayOfWeek.SUNDAY ||
                    date.isBefore(LocalDate.now()));
                if(date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY || date.isBefore(LocalDate.now())) {
                    setStyle("-fx-background-color: #949494;");
                }
            }
        });
    }     
}
