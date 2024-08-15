package com.example.noteapp2.repository

import com.example.noteapp2.data.db.Note
import com.example.noteapp2.data.db.NoteDao
import javax.inject.Inject

class NoteRepository@Inject constructor(val noteDao: NoteDao) {

    suspend fun getAll(): List<Note> {
        return noteDao.getAll()
    }
    suspend fun getFavorites()=noteDao.getFavorites()
    suspend fun insert(note: Note)=noteDao.insert(note)
    suspend fun update(note: Note)=noteDao.update(note)
    suspend fun deleteNote(note: Note)=noteDao.deleteNote(note)
    suspend fun getById(id: Int)=noteDao.getById(id)


}