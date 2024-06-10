package ru.geekbrains.seminar6_hw;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.geekbrains.seminar6_hw.exception.NoteNotFoundException;
import ru.geekbrains.seminar6_hw.model.Note;
import ru.geekbrains.seminar6_hw.repository.NoteRepository;
import ru.geekbrains.seminar6_hw.services.NoteService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class NoteServiceWithAnnotationUTest {
    @InjectMocks
    private NoteService service;

    @Mock
    private NoteRepository repository;


    @Test
    public void addDescriptionCorrectFlow(){

        Note noteSource = new Note();
        noteSource.setId(1L);
        noteSource.setTitle("1");
        noteSource.setDescription("");


        given(repository.findById(noteSource.getId())).willReturn(Optional.of(noteSource));
        given(repository.findById(2L)).willReturn(Optional.empty());

        assertThrows(NoteNotFoundException.class, () -> {service.addSomethingToNote(1L,"something");});

        verify(repository, never()).changeDescription(anyLong(),anyString());
    }



}
