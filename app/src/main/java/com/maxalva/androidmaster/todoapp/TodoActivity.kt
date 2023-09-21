package com.maxalva.androidmaster.todoapp

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.maxalva.androidmaster.R

class TodoActivity : AppCompatActivity() {

    private lateinit var rvCategories: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter

    private lateinit var rvTasks: RecyclerView
    private lateinit var tasksAdapter: TasksAdapter

    private lateinit var fabAddTask: FloatingActionButton


    private val categories = listOf(
        TaskCategory.Others,
        TaskCategory.Personal,
        TaskCategory.Business,
    )

    private val tasks = mutableListOf(
        Task("Task Others", TaskCategory.Others),
        Task("Task Personal", TaskCategory.Personal),
        Task("Task Business", TaskCategory.Business),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)

        initComponents()
        initListeners()
        initUI()
    }

    private fun initListeners() {
        fabAddTask.setOnClickListener { showDialog() }
    }

    private fun showDialog() {
        val dialog = Dialog(this);
        dialog.setContentView(R.layout.dialog_add_task)

        val btnAddTask = dialog.findViewById<Button>(R.id.btnAddTask)
        val etTask = dialog.findViewById<EditText>(R.id.etTask)
        val rgCategories = dialog.findViewById<RadioGroup>(R.id.rgCategories)

        btnAddTask.setOnClickListener {
            val selectedId = rgCategories.checkedRadioButtonId
            val selectedRadioButton = rgCategories.findViewById<RadioButton>(selectedId)
            val selectedCategory: TaskCategory = when (selectedRadioButton.text) {
                getString(R.string.business) -> TaskCategory.Business
                getString(R.string.personal) -> TaskCategory.Personal
                else -> TaskCategory.Others
            }

            if (etTask.text.isEmpty()) return@setOnClickListener

            tasks.add(Task(etTask.text.toString(), selectedCategory))
            updateTasks()
            dialog.hide()

        }

        dialog.show()
    }

    private fun updateTasks() {
        val selectedCategories = categories.filter { it.isSelected }

        val filteredTasks = if (selectedCategories.isEmpty()) {
            tasks
        } else {
            tasks.filter { task -> selectedCategories.contains(task.category) }
        }

        tasksAdapter.tasks = filteredTasks

        tasksAdapter.notifyDataSetChanged()
    }

    private fun initUI() {
        categoriesAdapter =
            CategoriesAdapter(categories) { position -> onCategorySelected(position) }
        rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoriesAdapter

        tasksAdapter = TasksAdapter(tasks) { position -> onTaskSelected(position) }
        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = tasksAdapter
    }

    private fun onTaskSelected(position: Int) {
        tasks[position].isSelected = !tasks[position].isSelected
        updateTasks();
    }

    private fun onCategorySelected(position: Int) {
        categories[position].isSelected = !categories[position].isSelected
        categoriesAdapter.notifyItemChanged(position)
        updateTasks()
    }

    private fun initComponents() {
        rvCategories = findViewById(R.id.rvCategories)
        rvTasks = findViewById(R.id.rvTasks)
        fabAddTask = findViewById(R.id.fabAddTask)
    }
}