package engine;

import engine.Completion.Completion;
import engine.Completion.CompletionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    Repository repository;

    @Autowired
    CompletionRepository completionRepository;

    public void save(Quiz quiz) {
        repository.save(quiz);
    }

    public Optional<Quiz> findById(long id) {
        return repository.findById(id);
    }

    public List<Quiz> findAll() {
        return (List<Quiz>) repository.findAll();
    }

    public void delete(Quiz quiz) {
        repository.delete(quiz);
    }

    public Page<Quiz> findAllWithPaging(Integer pageNo) {
        Pageable paging = PageRequest.of(pageNo, 10);

        Page<Quiz> pagedResult = repository.findAll(paging);

        if (pagedResult.hasContent()) {
            return pagedResult;
        } else {
            return pagedResult;
        }
    }

    public Page<Completion> getCompletedcompletionzesByUser(String userName, Integer page) {
        Pageable paging = PageRequest.of(page, 10);//, Sort.by("completedAt").descending()
        Page<Completion> pagedResult = completionRepository.getCompletedcompletionzesByUser(userName, paging);
        if (pagedResult.hasContent()) {
            return pagedResult;
        } else {
            return pagedResult;
        }
    }

    public void save(Completion completion) {
        completionRepository.save(completion);
    }
}