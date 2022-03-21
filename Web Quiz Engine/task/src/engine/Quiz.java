package engine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import engine.Completion.Completion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "quizzes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {

    @Id
    @GeneratedValue
    private long id;

    @NotBlank
    @Column
    private String title;

    @NotBlank
    @Column
    private String text;

    @NotNull
    @Size(min = 2)
    @Column
    @ElementCollection
    private List<String> options;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column
    @ElementCollection
    private List<Integer> answer = new ArrayList<>();

    @JsonIgnore
    @Column
    private String author;


    /*@OneToMany(mappedBy="quiz")
    private Set<Completion> completions;*/

    public boolean answerIsCorrect(List<Integer> answer) {
        return new HashSet<>(this.answer).equals(new HashSet<>(answer));
    }
}
