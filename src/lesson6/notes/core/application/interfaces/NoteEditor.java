package lesson6.notes.core.application.interfaces;

import lesson6.notes.core.domain.Note;

public interface NoteEditor extends Editor<Note, Integer> {
    void printAll();
}
