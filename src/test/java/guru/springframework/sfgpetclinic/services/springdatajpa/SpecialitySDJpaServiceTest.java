package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    @InjectMocks
    SpecialitySDJpaService specialitySDJpaService;

    @DisplayName("TDD delete by id")
    @Test
    void deleteById() {
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(1L);

        verify(specialtyRepository, times(2)).deleteById(1L);
    }

    @DisplayName("BDD delete by id")
    @Test
    void deleteByIdBDD() {
        // given - none

        // when
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(1L);

        // then
        then(specialtyRepository).should(times(2)).deleteById(anyLong());
    }

    @DisplayName("TDD delete by id at least once")
    @Test
    void deleteByIdAtLeast() {

        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(1L);

        verify(specialtyRepository, atLeastOnce()).deleteById(1L);
    }

    @DisplayName("BDD delete by id at least once")
    @Test
    void deleteByIdAtLeastBDD() {
        // given - none

        // when
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(1L);

        // then
        then(specialtyRepository).should(atLeastOnce()).deleteById(anyLong());
    }

    @DisplayName("TDD at most")
    @Test
    void deleteByIdAtMost() {
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(1L);

        verify(specialtyRepository, atMost(5)).deleteById(1L);
    }

    @DisplayName("BDD at most")
    @Test
    void deleteByIdAtMostBDD() {
        // given - none

        // when
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(1L);

        // then
        then(specialtyRepository). should(atMost(5)).deleteById(anyLong());
    }

    @DisplayName("TDD never")
    @Test
    void deleteByIdNever() {
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(1L);

        verify(specialtyRepository, atLeast(2)).deleteById(1L);
        verify(specialtyRepository, never()).deleteById(5L);
    }

    @DisplayName("BDD never")
    @Test
    void deleteByIdNeverBDD() {
        // given - none

        // when
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(1L);

        // then
        then(specialtyRepository).should(atLeast(2)).deleteById(1L);
        then(specialtyRepository).should(never()).deleteById(5L);
    }

    @Test
    void testDelete() {
        // given

        // when
        specialitySDJpaService.delete(new Speciality());

        // then
        then(specialtyRepository).should().delete(any(Speciality.class));

    }
}