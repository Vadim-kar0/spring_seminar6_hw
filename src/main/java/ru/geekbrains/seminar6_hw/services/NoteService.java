package ru.geekbrains.seminar6_hw.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.seminar6_hw.exception.NoteNotFoundException;
import ru.geekbrains.seminar6_hw.model.Note;
import ru.geekbrains.seminar6_hw.repository.NoteRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository repository;

    public void addSomethingToNote(Long id,String something){
        Note note = repository.findById(id).orElseThrow(() -> new NoteNotFoundException());
        if(note != null && note.getDescription() != null){
            String description = note.getDescription();
            note.setDescription(description + "Something");
        }
    }

    public List<Note> getAllNotes(){return repository.findAll();}

    public Optional<Note> getNoteById(Long id){
        return repository.findById(id);
    }

    public Note save(Note note){
        return repository.save(note);
    }

    public void deleteNote(Long id){repository.deleteById(id); }
}
