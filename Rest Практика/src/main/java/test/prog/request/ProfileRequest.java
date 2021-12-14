package test.prog.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
public class ProfileRequest {

    @NotNull(message="Имя дожно быть задано")
    @Size(min=2, max=30)
    @Pattern(regexp = "(^[A-Z]{1}[a-z]{1,10}$)|(^[А-Я]{1}[а-я]{1,10}$)",
            message = "Поле имя заполненно неверно")
    private String firstName;

    @NotNull(message="Фамилия должна быть задана")
    @Size(min=2, max=30)
    @Pattern(regexp = "(^[A-Z]{1}[a-z]{1,10}$)|(^[А-Я]{1}[а-я]{1,10}$)",
            message = "Поле фамилия заполненно неверно")
    private String lastName;




    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


}