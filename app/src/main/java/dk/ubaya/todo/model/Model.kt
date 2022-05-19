package dk.ubaya.todo.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @ColumnInfo(name = "title")
    var title:String,
    @ColumnInfo(name = "notes")
    var notes:String,
    @ColumnInfo(name="priority")
    var priority:Int,

    @NonNull
    @ColumnInfo(name="is_done")
    var is_done:Int=0
    //karena Room ini abstraksi dari SQLite dan SQLite tidak support tipe data boolean
    // sehingga Room juga tidak support. Tetapi Room otomatis mengubah tipe data tersebut
    // menjadi integer dan merepresentasikan TRUE= 1 dan FALSE= 0


){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int =0
}