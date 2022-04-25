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
    var is_done:Int=0 //karena bakal masalah penulisan query nya

){
    @PrimaryKey(autoGenerate = true)
    var uuid:Int =0
}