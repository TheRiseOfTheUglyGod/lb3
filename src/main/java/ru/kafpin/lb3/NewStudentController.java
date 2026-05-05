package ru.kafpin.lb3;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewStudentController {
    public TextField thir_f;
    public TextField group_f;
    private Student student;
    public TextField name_f;
    public TextField sur_f;
    public TextField age_f;
    private Stage dialogStage;

    public void ok_click(ActionEvent actionEvent) {
        if (!isInputValid()) {
            return;
        }

        student.setAge(Integer.parseInt(age_f.getText().trim()));
        student.setName(name_f.getText().trim());
        student.setSurname(sur_f.getText().trim());
        student.setGroup(group_f.getText().trim());
        student.setThirdname(thir_f.getText().trim());
        dialogStage.close();
    }

    public void cancel_click(ActionEvent actionEvent) {
        dialogStage.close();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setStudent(Student student) {
        this.student = student;
        name_f.setText(student.getName());
        sur_f.setText(student.getSurname());
        age_f.setText(String.valueOf(student.getAge()));
        thir_f.setText(student.getThirdname());
        group_f.setText(student.getGroup());
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (name_f.getText() == null || name_f.getText().trim().isEmpty()) {
            errorMessage += "Поле 'Имя' не может быть пустым!\n";
        }
        if (sur_f.getText() == null || sur_f.getText().trim().isEmpty()) {
            errorMessage += "Поле 'Фамилия' не может быть пустым!\n";
        }
        if (thir_f.getText() == null || thir_f.getText().trim().isEmpty()) {
            errorMessage += "Поле 'Отчество' не может быть пустым!\n";
        }
        if (group_f.getText() == null || group_f.getText().trim().isEmpty()) {
            errorMessage += "Поле 'Группа' не может быть пустым!\n";
        }
        if (age_f.getText() == null || age_f.getText().trim().isEmpty()) {
            errorMessage += "Поле 'Возраст' не может быть пустым!\n";
        } else {
            try {
                int age = Integer.parseInt(age_f.getText().trim());
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
}