package ru.geekbrains.seminar6_hw.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.aspectj.weaver.ast.Not;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.seminar6_hw.model.Note;
import ru.geekbrains.seminar6_hw.repository.NoteRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
@Log
public class NoteController {
    private final NoteRepository repository;

    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note){
        log.info("Note was created: " + note);
        return new ResponseEntity<>(repository.save(note), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Note>> getAll(){
        log.info("\nUsing getAllTasks: \n" + repository.findAll()
                .toString()
                .replaceAll("\\), ", "), \n"));
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);}

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id){
        return new ResponseEntity<>(repository.findById(id).orElseThrow(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> update(@PathVariable Long id,@RequestBody Note note){
        Optional<Note> noteOptional = repository.findById(id);
        if(noteOptional.isPresent()){
            Note result = noteOptional.get();
            result.setTitle(note.getTitle());
            result.setDescription(note.getDescription());
            result.setDateOfCreate(note.getDateOfCreate());
            log.info("Note with id = " + id + " was updated: " + note);
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Note> deleteNote(@PathVariable Long id){
        Optional<Note> noteOptional = repository.findById(id);
        if(noteOptional.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }




}
