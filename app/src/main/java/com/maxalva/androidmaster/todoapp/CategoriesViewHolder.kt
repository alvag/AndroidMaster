package com.maxalva.androidmaster.todoapp

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.maxalva.androidmaster.R

class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val tvCategoryName = view.findViewById<TextView>(R.id.tvCategoryName)
    private val divider = view.findViewById<View>(R.id.divider)
    private val viewContainer = view.findViewById<CardView>(R.id.viewContainer)

    fun render(category: TaskCategory, onItemSelected: (Int) -> Unit) {

        val color = if (category.isSelected) {
            R.color.todo_background_card
        } else {
            R.color.todo_background_disabled
        }

        viewContainer.setCardBackgroundColor(
            ContextCompat.getColor(
                viewContainer.context,
                color
            )
        )

        itemView.setOnClickListener { onItemSelected(layoutPosition) }

        when (category) {
            TaskCategory.Business -> {
                tvCategoryName.text = "Negocios"
                divider.setBackgroundColor(
                    ContextCompat.getColor(
                        divider.context,
                        R.color.todo_business_category
                    )
                )
            }

            TaskCategory.Others -> {
                tvCategoryName.text = "Otros"
                divider.setBackgroundColor(
                    ContextCompat.getColor(
                        divider.context,
                        R.color.todo_other_category
                    )
                )
            }

            TaskCategory.Personal -> {
                tvCategoryName.text = "Personal"
                divider.setBackgroundColor(
                    ContextCompat.getColor(
                        divider.context,
                        R.color.todo_personal_category
                    )
                )
            }
        }
    }

}