package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository repo;

    @InjectMocks
    VisitSDJpaService service;

    Visit visit;

    @BeforeEach
    void setUp() {
        visit = new Visit();
    }

    @Test
    void findAll() {
        when(repo.findAll()).thenReturn(Set.of(new Visit(), new Visit(), new Visit()));
        Set<Visit> foundVisits = service.findAll();
        assertThat(foundVisits).isNotNull();
        verify(repo).findAll();
    }

    @Test
    void findById() {
        when(repo.findById(1L)).thenReturn(Optional.of(visit));
        Visit foundVisit = service.findById(1L);
        assertThat(foundVisit).isNotNull();
        verify(repo).findById(1L);
    }

    @Test
    void save() {
        service.save(visit);
        verify(repo).save(visit);
    }

    @Test
    void delete() {
        service.delete(visit);
        verify(repo).delete(visit);
    }

    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(repo).deleteById(1L);
    }
}