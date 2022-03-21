package engine.Completion;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.core.Authentication;

@org.springframework.stereotype.Repository
public interface CompletionRepository extends PagingAndSortingRepository<Completion, Long> {
    @Query(value = "select * from COMPLETIONS c where c.author = ?1 order by c.COMPLETED_AT desc ", nativeQuery = true)
    Page<Completion> getCompletedcompletionzesByUser(String userName, Pageable pageable);

}
