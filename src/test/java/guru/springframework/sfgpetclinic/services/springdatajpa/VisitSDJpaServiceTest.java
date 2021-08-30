package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
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

    @DisplayName("Test FindAll")
    @Test
    void findAll() {
        when(repo.findAll()).thenReturn(Set.of(new Visit(), new Visit(), new Visit()));
        Set<Visit> foundVisits = service.findAll();
        assertThat(foundVisits).isNotNull();
        assertThat(foundVisits).hasSize(3);
        verify(repo).findAll();
    }

    @DisplayName("Test FindByID")
    @Test
    void findById() {
        when(repo.findById(1L)).thenReturn(Optional.of(visit));
        Visit foundVisit = service.findById(1L);
        assertThat(foundVisit).isNotNull();
        verify(repo).findById(anyLong());
    }

    @DisplayName("Test FindByIDBDD")
    @Test
    void findByIdBDD() {
        // Given
        given(repo.findById(1L)).willReturn(Optional.of(visit));

        // When
        Visit foundVisit = service.findById(1L);

        // Then
        assertThat(foundVisit).isNotNull();
        then(repo).should().findById(anyLong());
        then(repo).shouldHaveNoMoreInteractions();
//        verify(repo).findById(anyLong());
    }

    @DisplayName("Test SaveByObject")
    @Test
    void save() {
        when(repo.save(any(Visit.class))).thenReturn(visit);
        Visit savedVisit = service.save(visit);
        verify(repo).save(any(Visit.class));
        assertThat(savedVisit).isEqualTo(visit);
    }

    @DisplayName("Test DeleteByObject")
    @Test
    void delete() {
        service.delete(visit);
        verify(repo).delete(visit);
    }

    @DisplayName("Test DeleteByID")
    @Test
    void deleteById() {
        service.deleteById(1L);
        verify(repo).deleteById(1L);
    }
}