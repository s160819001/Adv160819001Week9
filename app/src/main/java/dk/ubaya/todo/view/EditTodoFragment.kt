package dk.ubaya.todo.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import dk.ubaya.todo.R
import dk.ubaya.todo.databinding.FragmentEditTodoBinding
import dk.ubaya.todo.model.Todo
import dk.ubaya.todo.viewmodel.DetailTodoViewModel
import kotlinx.android.synthetic.main.fragment_create_todo.*

class EditTodoFragment : Fragment(),RadioClickListener,TodoSaveChangesListener {

    private lateinit var viewModel: DetailTodoViewModel
    private lateinit var dataBinding:FragmentEditTodoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding=DataBindingUtil.inflate<FragmentEditTodoBinding>(inflater,R.layout.fragment_edit_todo,container,false)
//        return inflater.inflate(R.layout.fragment_create_todo, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)
        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)
        dataBinding.radiolistener=this
        dataBinding.listener=this

//        btnAdd.setOnClickListener {
//            val radio =
//                view.findViewById<RadioButton>(rdoGroupPriority.checkedRadioButtonId)
//            viewModel.update(txtTitle.text.toString(), txtNotes.text.toString(),
//                radio.tag.toString().toInt(), uuid)
//            Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
//            Navigation.findNavController(it).popBackStack()
//        }
        observeViewModel()


//        txtJudulTodo.text = "Edit Todo"
//        btnAdd.text = "Save Changes"
    }

    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            dataBinding.todo=it
//            txtTitle.setText(it.title)
//            txtNotes.setText(it.notes)
//            when (it.priority) {
//                1 -> rdoLow.isChecked = true
//                2 -> rdoMedium.isChecked = true
//                else -> rdoHigh.isChecked = true
//            }

        })
    }

    override fun onRadioClick(v: View, obj: Todo) {
        obj.priority=v.tag.toString().toInt()
    }

    override fun onTodoSaveChanges(v: View, obj: Todo) {
        Log.d("cek",obj.toString())
        viewModel.update(obj.title,obj.notes,obj.priority,obj.uuid)
        Toast.makeText(v.context, "Todo updated", Toast.LENGTH_SHORT).show()
        Navigation.findNavController(v).popBackStack()
    }

}