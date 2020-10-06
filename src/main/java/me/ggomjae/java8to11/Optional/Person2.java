package me.ggomjae.java8to11.Optional;

import java.util.Optional;

public class Person2 {

    private Integer number;

    private String name;

    private boolean study;

    public Progress progress;

    public Person2(Integer number, String name, boolean study) {
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

    /*
    이렇게 해서 Server2에서 getProgress를 해서 부르면 NullPointerException 이 나오지

    public Progress getProgress(){
        return this.progress;
    }
    */

    public Optional<Progress> getProgress() {

         /*
            최대한 리턴타입으로만 써라. 박스안에 담는 건데 이건 Null일수도 있고 아닐수도 있다는 뜻.
            만약 ofNullable 이 아닌 of 만 온다면 - Optional.of(value) null이 아닌것이 와야한다는뜻.
            null 이 오면 NullPointerException 이 나옴.

            return Optional.ofNullable(progress);

            또한 Optional 이라고 해서 null을 리턴하지말자
            -.ifPresent() 를 해서 Optional이 null인지 확인하는데 그냥 null보내는건... 차라리 empty();를

            return null;
         */

        return Optional.empty();
    }
}
