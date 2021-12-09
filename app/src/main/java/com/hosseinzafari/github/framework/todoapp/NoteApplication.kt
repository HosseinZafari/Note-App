package com.hosseinzafari.github.framework.todoapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/*

@created in 07/12/2021 - 7:35 PM
@project Todo App
@author Hossein Zafari 
@email  hosseinzafari2000@gmail.com 
*/


@HiltAndroidApp
class NoteApplication : Application() {
    companion object {
        const val DB_NAME = "note_db"
    }

}