package ru.kafpin.lb3;

import javafx.beans.property.*;

public class Student {
    private final StringProperty surname;
    private final StringProperty name;
    private final StringProperty thirdname;
    private final IntegerProperty age;
    private final StringProperty group;
    private final StringProperty city;

    public Student() {
        this("", "", "", "", "", 0);
    }

    public Student(String surname, String name, String thirdname, String group,
                   String city, int age) {
        this.surname = new SimpleStringProperty(surname);
        this.name = new SimpleStringProperty(name);
        this.thirdname = new SimpleStringProperty(thirdname);
        this.group = new SimpleStringProperty(group);
        this.city = new SimpleStringProperty(city);
        this.age = new SimpleIntegerProperty(age);
    }

    // фамилия
    public String getSurname() { return surname.get(); }
    public StringProperty surnameProperty() { return surname; }
    public void setSurname(String surname) { this.surname.set(surname); }

    // имя
    public String getName() { return name.get(); }
    public StringProperty nameProperty() { return name; }
    public void setName(String name) { this.name.set(name); }

    // отчество
    public String getThirdname() { return thirdname.get(); }
    public StringProperty thirdnameProperty() { return thirdname; }
    public void setThirdname(String thirdname) { this.thirdname.set(thirdname); }

    // возраст
    public int getAge() { return age.get(); }
    public IntegerProperty ageProperty() { return age; }
    public void setAge(int age) { this.age.set(age); }

    // группа
    public String getGroup() { return group.get(); }
    public StringProperty groupProperty() { return group; }
    public void setGroup(String group) { this.group.set(group); }

    // город
    public String getCity() { return city.get(); }
    public StringProperty cityProperty() { return city; }
    public void setCity(String city) { this.city.set(city); }

    @Override
    public String toString() {
        return String.format("Студент: %s %s %s, группа %s, город %s, возраст %d",
                getSurname(), getName(), getThirdname(), getGroup(), getCity(), getAge());
    }
}