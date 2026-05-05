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

    @FXML
    private TableView<Student> studentsTable;
    @FXML
    private TableColumn<Student, String> surnameColumn;
    @FXML
    private TableColumn<Student, String> nameColumn;
    @FXML
    private TableColumn<Student, String> thirdColumn;
    @FXML
    private TableColumn<Student, Integer> ageColumn;
    @FXML
    private TableColumn<Student, String> groupColumn;
    @FXML
    private Label label1;

    private final ObservableList<Student> students = FXCollections.observableArrayList();

    @FXML
    protected void initialize() {
        students.add(new Student("Романович", "Пин-124", "Даниил", "Лебедев", 20));
        students.add(new Student("Петрович", "ИС-123", "Алексей", "Зайцев", 21));

        surnameColumn.setCellValueFactory(item -> item.getValue().surnameProperty());
        nameColumn.setCellValueFactory(item -> item.getValue().nameProperty());
        ageColumn.setCellValueFactory(item -> item.getValue().ageProperty().asObject());
        thirdColumn.setCellValueFactory(item -> item.getValue().thirdnameProperty());
        groupColumn.setCellValueFactory(item -> item.getValue().groupProperty());

        studentsTable.setItems(students);

        studentsTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        label1.setText(newValue.toString());
                    } else {
                        label1.setText("");
                    }
                }
        );
    }

    @FXML
    void add_click(ActionEvent event) {
        Student student = new Student();
        try {
            showDialog(student);
            students.add(student);
        } catch (IOException e) {
            label1.setText("Ошибка загрузки окна");
        }
    }

    @FXML
    void edit_click(ActionEvent event) {
        Student student = studentsTable.getSelectionModel().getSelectedItem();
        if (student != null) {
            try {
                showDialog(student);
                studentsTable.refresh();
            } catch (IOException e) {
                label1.setText("Ошибка загрузки окна");
            }
        } else {
            label1.setText("Не выбрана строка для редактирования");
        }
    }

    @FXML
    void del_click(ActionEvent event) {
        int selectedIndex = studentsTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            studentsTable.getItems().remove(selectedIndex);
            label1.setText("Строка удалена");
        } else {
            label1.setText("Не выбрана строка для удаления");
        }
    }

    @FXML
    void exit_click(ActionEvent actionEvent) {
        System.exit(0);
    }

    private void showDialog(Student student) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("create-view.fxml"));
        Parent root = loader.load();

        Stage addStage = new Stage();
        addStage.setTitle("Информация о студенте");
        addStage.initModality(Modality.APPLICATION_MODAL);
        addStage.initOwner(Application.getStartStage());

        Scene scene = new Scene(root);
        addStage.setScene(scene);

        NewStudentController controller = loader.getController();
        controller.setDialogStage(addStage);
        controller.setStudent(student);

        addStage.showAndWait();
    }
}