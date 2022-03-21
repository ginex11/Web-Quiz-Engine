package engine.Completion;

import com.fasterxml.jackson.annotation.JsonIgnore;
import engine.Quiz;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "completions")
@Data
@NoArgsConstructor

public class Completion {

    @Id
    @GeneratedValue
    @JsonIgnore
    private Long quizID;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)*/
    private Long id;

    @Column
    private Date completedAt;

    @JsonIgnore
    @Column
    private String author;

    public Completion(Quiz quiz, Date completedAt, String author) {
        this.id = quiz.getId();
        this.completedAt = completedAt;
        this.author = author;

    }
}
