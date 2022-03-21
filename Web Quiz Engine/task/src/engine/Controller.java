package engine;

import engine.Completion.Completion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.persistence.GenerationType;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class Controller {
    private final Result correct = new Result(true, "Good!");
    private final Result wrong = new Result(false, "Bad!");
    @Autowired
    Service service;


    @GetMapping("/api/quizzes")
    public ResponseEntity<Page<Quiz>> getQuizzes(@RequestParam Integer page) {
        return new ResponseEntity<>(service.findAllWithPaging(page), HttpStatus.OK);
    }

    @GetMapping("/api/quizzes/completed")
    public ResponseEntity<Page<Completion>> getCompletions(Authentication auth, @RequestParam Integer page) {
        return new ResponseEntity<>(service.getCompletedcompletionzesByUser(auth.getName(),page), HttpStatus.OK);
    }

    @PostMapping("/api/quizzes")
    public ResponseEntity<Quiz> createQuiz(@RequestBody @Valid Quiz quiz, Authentication auth) {
        quiz.setAuthor(auth.getName());
        service.save(quiz);
        return new ResponseEntity<>(quiz, HttpStatus.OK);
    }

    @GetMapping("/api/quizzes/{id}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable long id) {
        Optional<Quiz> data = service.findById(id);
        return new ResponseEntity<>(data.orElse(null), data.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    @DeleteMapping("/api/quizzes/{id}")
    public ResponseEntity<Quiz> deleteQuiz(@PathVariable long id, Authentication auth) {
        Optional<Quiz> data = service.findById(id);
        if (data.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Quiz quiz = data.get();
        if (quiz.getAuthor().equals(auth.getName())) {
            service.delete(quiz);
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("api/quizzes/{id}/solve")
    public ResponseEntity<Result> solveQuiz(@PathVariable long id, @RequestBody(required = false) Answer answerBody, Authentication auth) {
        Optional<Quiz> data = service.findById(id);
        if (data.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        if (data.get().answerIsCorrect(Optional.ofNullable(answerBody.getAnswer()).get())) {

            Completion completion = new Completion(data.get(), Calendar.getInstance().getTime(),auth.getName());
            service.save(completion);
        }
        return new ResponseEntity<>(
                data.get().answerIsCorrect(Optional.ofNullable(answerBody.getAnswer()).get()) ? correct : wrong, HttpStatus.OK
        );
    }
}
