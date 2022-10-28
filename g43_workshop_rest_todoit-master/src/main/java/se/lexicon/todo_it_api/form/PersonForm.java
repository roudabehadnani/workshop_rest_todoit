package se.lexicon.todo_it_api.form;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PersonForm {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;

}
