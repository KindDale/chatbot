package com.aichatbot.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.aichatbot.dao.NotesDao;
import com.aichatbot.model.Note;

public class NotesController {
    private final NotesDao c2w_ai_notesDao = new NotesDao();

    // Add a new note to Firestore
    public void addNote(Note c2w_ai_note) {
        try {
            String documentId = String.valueOf(System.currentTimeMillis());
            c2w_ai_notesDao.addData("notes", documentId, c2w_ai_note);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            // Optionally: log to UI or notify user
        }
    }

    // Get all notes for a specific user
    public List<Note> getAllNotesForUser(String c2w_ai_userName) {
        try {
            return c2w_ai_notesDao.getDataList("notes", c2w_ai_userName);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return List.of(); // Empty list fallback
    }
}
