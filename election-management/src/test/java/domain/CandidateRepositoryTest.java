package domain;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.*;

public abstract class CandidateRepositoryTest {

    public abstract CandidateRepository repository();

    @Test
    void save() {
        var domain = Instancio.create(Candidate.class);
        repository().save(domain);

        Optional<Candidate> result = repository().findById(domain.id());

        assertTrue(result.isPresent());
        assertEquals(domain, result.get());
    }

    @Test
    void findAll() {
        var candidates = Instancio.stream(Candidate.class).limit(10).toList();

        repository().save(candidates);

        List<Candidate> result = repository().findAll();

        assertEquals(candidates.size(), result.size());
    }

    @Test
    void findByName() {
        var candidate1 = Instancio.create(Candidate.class);
        var candidate2 = Instancio.of(Candidate.class).set(field("familyName"), "Poiani").create();
        var query = new CandidateQuery.Builder().name("POI").build();

        repository().save(List.of(candidate1, candidate2));

        List<Candidate> result = repository().find(query);

        assertEquals(1, result.size());
        assertEquals(candidate2, result.get(0));
    }
}