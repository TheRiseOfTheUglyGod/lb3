package ru.kafpin.lb3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;               // ← обязательно для @FXML
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewStudentController {

    @FXML public TextField surnameField;
    @FXML public TextField nameField;
    @FXML public TextField thirdnameField;
    @FXML public TextField ageField;
    @FXML public TextField groupField;
    @FXML public TextField cityField;

    private Stage dialogStage;
    private Student student;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setStudent(Student student) {
        this.student = student;
        surnameField.setText(student.getSurname());
        nameField.setText(student.getName());
        thirdnameField.setText(student.getThirdname());
        ageField.setText(String.valueOf(student.getAge()));
        groupField.setText(student.getGroup());
        cityField.setText(student.getCity());
    }

    @FXML
    void ok_click(ActionEvent event) {
        if (!isInputValid()) {
            return;
        }
        student.setSurname(surnameField.getText().trim());
        student.setName(nameField.getText().trim());
        student.setThirdname(thirdnameField.getText().trim());
        student.setAge(Integer.parseInt(ageField.getText().trim()));
        student.setGroup(groupField.getText().trim());
        student.setCity(cityField.getText().trim());
        dialogStage.close();
    }

    @FXML
    void cancel_click(ActionEvent event) {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (isEmpty(surnameField)) errorMessage += "Поле 'Фамилия' не может быть пустым!\n";
        if (isEmpty(nameField)) errorMessage += "Поле 'Имя' не может быть пустым!\n";
        if (isEmpty(thirdnameField)) errorMessage += "Поле 'Отчество' не может быть пустым!\n";
        if (isEmpty(groupField)) errorMessage += "Поле 'Группа' не может быть пустым!\n";
        if (isEmpty(cityField)) errorMessage += "Поле 'Город' не может быть пустым!\n";

        if (isEmpty(ageField)) {
            errorMessage += "Поле 'Возраст' не может быть пустым!\n";
        } else {
            try {
                int age = Integer.parseInt(ageField.getText().trim());
                if (age <= 0 || age > 150) {
                    errorMessage += "Возраст должен быть положительным числом (1-150)!\n";
                }
            } catch (NumberFormatException e) {
                errorMessage += "Возраст должен быть целым числом!\n";
            }
        }

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка ввода");
            alert.setHeaderText("Пожалуйста, исправьте неверные поля");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }

    private boolean isEmpty(TextField field) {
        return field.getText() == null || field.getText().trim().isEmpty();
    }
}