package ru.kafpin.lb3;

import javafx.beans.property.*;

public class Student {
    private final StringProperty thirdname;
    private final StringProperty group;
    private final StringProperty name;
    private final StringProperty surname;
    private final IntegerProperty age;

    public Student() {
        this("", "", "", "", 0);
    }

    public Student(String thirdname, String group, String name, String surname, int age) {
        this.thirdname = new SimpleStringProperty(thirdname);
        this.group = new SimpleStringProperty(group);
        this.name = new SimpleStringProperty(name);
        this.surname = new SimpleStringProperty(surname);
        this.age = new SimpleIntegerProperty(age);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSurname() {
        return surname.get();
    }

    public StringProperty surnameProperty() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname.set(surname);
    }

    public int getAge() {
        return age.get();
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public void setAge(int age) {
        this.age.set(age);
    }

    public String getThirdname() {
        return thirdname.get();
    }

    public StringProperty thirdnameProperty() {
        return thirdname;
    }

    public void setThirdname(String thirdname) {
        this.thirdname.set(thirdname);
    }

    public String getGroup() {
        return group.get();
    }

    public StringProperty groupProperty() {
        return group;
    }

    public void setGroup(String group) {
        this.group.set(group);
    }

    @Override
    public String toString() {
        return String.format("Студент: %s %s %s, группа %s, возраст %d",
                getSurname(), getName(), getThirdname(), getGroup(), getAge());
    }
}