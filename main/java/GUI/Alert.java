package GUI;

public class Alert {

    public void alertInvalidInput() {
        javafx.scene.control.Alert invalidInput = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        invalidInput.setTitle("Invalid input");
        invalidInput.setHeaderText("The introduced variable is wrong");
        invalidInput.setContentText("Please introduce x as the used variable.");
        invalidInput.showAndWait();
    }

    public void alertInvalidSelection() {
        javafx.scene.control.Alert invalidSelection = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        invalidSelection.setTitle("Invalid selection");
        invalidSelection.setHeaderText("Invalid selection of the radiobuttons.");
        invalidSelection.setContentText("Please select one input.");
        invalidSelection.showAndWait();
    }

    public void alertWrongPowerChar() {
        javafx.scene.control.Alert wrongPowerChar = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        wrongPowerChar.setTitle("Invalid input");
        wrongPowerChar.setHeaderText("The introduced power sign is wrong");
        wrongPowerChar.setContentText("Please introduce the sign ^ for powers of x.");
        wrongPowerChar.showAndWait();
    }

    public void alertValueContainsDigits() {
        javafx.scene.control.Alert valueWithDigits = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        valueWithDigits.setTitle("Invalid input");
        valueWithDigits.setHeaderText("The introduced value contains digits");
        valueWithDigits.setContentText("Please introduce a value containing only integers.");
        valueWithDigits.showAndWait();
    }
}
