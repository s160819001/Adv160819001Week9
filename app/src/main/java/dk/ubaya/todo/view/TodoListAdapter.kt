package dk.ubaya.todo.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import dk.ubaya.todo.R
import dk.ubaya.todo.databinding.TodoItemLayoutBinding
import dk.ubaya.todo.model.Todo
import kotlinx.android.synthetic.main.todo_item_layout.view.*

class TodoListAdapter(val todoList:ArrayList<Todo>, val adapterOnClick : (Todo) -> Unit)
    : RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>(),TodoCheckedChangeListener, TodoEditClickListener {
    class TodoViewHolder(var view: TodoItemLayoutBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.todo_item_layout, parent, false)

        val view=TodoItemLayoutBinding.inflate(inflater,parent,false)
        return TodoViewHolder(view)

    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.view.todo = todoList[position]
        holder.view.listener=this
        holder.view.editlistener=this
//        holder.view.imageEdit.setOnClickListener {
//            val action =
//                TodoListFragmentDirections.actionEditTodoFragment(todoList[position].uuid)
//
//            Navigation.findNavController(it).navigate(action)
//        }

//        holder.view.checkTask.setText(todoList[position].title.toString())

//        holder.view.checkTask.setOnCheckedChangeListener { compoundButton, isChecked ->
//            if(isChecked)
//                adapterOnClick(todoList[position])
//        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    fun updateTodoList(newTodoList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }

    override fun onTodoCheckedChange(cb: CompoundButton, isChecked: Boolean, obj: Todo) {
        if(isChecked)
            adapterOnClick(obj)
    }

    override fun onTodoEditClick(v: View) {
        val action =
            TodoListFragmentDirections.actionEditTodoFragment(v.tag.toString().toInt())

        Navigation.findNavController(v).navigate(action)
    }

}
