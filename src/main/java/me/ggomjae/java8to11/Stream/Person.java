package me.ggomjae.java8to11.Stream;

public class Person {

    private Integer number;

    private String name;

    private boolean study;

    public Person(Integer number, String name, boolean study) {
        this.number = number;
        this.name = name;
        this.study = study;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isClosed() {
        return study;
    }

    public void setStudy(boolean study) {
        this.study = study;
    }
}
