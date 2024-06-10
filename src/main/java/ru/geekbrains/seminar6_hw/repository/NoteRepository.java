package ru.geekbrains.seminar6_hw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.seminar6_hw.model.Note;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {
    //Optional<Note> findById(Long id);

    @Modifying
    @Query("UPDATE notes SET description = :description WHERE id = :id")
    void changeDescription(long id, String description);

}
