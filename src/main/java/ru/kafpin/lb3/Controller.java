package ru.kafpin.lb3;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML private TableView<Student> studentsTable;
    @FXML private TableColumn<Student, String> surnameColumn;
    @FXML private TableColumn<Student, String> nameColumn;
    @FXML private TableColumn<Student, String> thirdnameColumn;
    @FXML private TableColumn<Student, Integer> ageColumn;
    @FXML private TableColumn<Student, String> groupColumn;
    @FXML private TableColumn<Student, String> cityColumn;
    @FXML private Label lblLog;

    private final ObservableList<Student> students = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        // Начальные данные
        students.add(new Student("Иванов", "Миша", "Петрович", "Пин-124", "Муром", 20));
        students.add(new Student("Петров", "Леша", "Сергеевич", "ИС-123", "Владимир", 21));

        // Привязка колонок к свойствам
        surnameColumn.setCellValueFactory(cellData -> cellData.getValue().surnameProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        thirdnameColumn.setCellValueFactory(cellData -> cellData.getValue().thirdnameProperty());
        ageColumn.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asObject());
        groupColumn.setCellValueFactory(cellData -> cellData.getValue().groupProperty());
        cityColumn.setCellValueFactory(cellData -> cellData.getValue().cityProperty());

        studentsTable.setItems(students);

        studentsTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        showStudent(newValue);
                    } else {
                        lblLog.setText("");
                    }
                });
    }

    private void showStudent(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append("Студент ");
        sb.append(student.getSurname()).append(" ");
        sb.append(student.getName()).append(" ");
        sb.append(student.getThirdname()).append(", ");
        sb.append("группа ").append(student.getGroup()).append(", ");
        sb.append("город ").append(student.getCity()).append(", ");
        sb.append("возраст ").append(student.getAge()).append(" лет.");
        lblLog.setText(sb.toString());
    }

    @FXML
    void onAdd(ActionEvent event) throws IOException {
        Student student = new Student();
        showDialog(student);
        students.add(student);
    }

    @FXML
    void onEdit(ActionEvent event) throws IOException {
        Student selected = studentsTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            showDialog(selected);
            studentsTable.refresh();
        } else {
            lblLog.setText("Не выбрана строка для редактирования");
        }
    }

    @FXML
    void onDelete(ActionEvent event) {
        int index = studentsTable.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            studentsTable.getItems().remove(index);
            lblLog.setText("Строка удалена");
        } else {
            lblLog.setText("Не выбрана строка для удаления");
        }
    }

    @FXML
    void exit_click(ActionEvent event) {
        System.exit(0);
    }

    private void showDialog(Student student) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("new-student.fxml"));
        Parent root = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.setTitle("Информация о студенте");
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initOwner(Main.getPrimaryStage());

        Scene scene = new Scene(root);
        dialogStage.setScene(scene);

        NewStudentController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setStudent(student);

        dialogStage.showAndWait();
    }
}