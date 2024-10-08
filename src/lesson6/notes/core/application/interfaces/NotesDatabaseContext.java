package lesson6.notes.core.application.interfaces;

import lesson6.notes.core.domain.Note;

import java.util.Collection;

public interface NotesDatabaseContext {
    Collection<Note> getAll();
    boolean saveChanges();
}
