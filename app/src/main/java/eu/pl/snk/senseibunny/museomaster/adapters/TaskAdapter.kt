package eu.pl.snk.senseibunny.museomaster.adapters

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import eu.pl.snk.senseibunny.museomaster.databinding.CustomReportPopupBinding
import eu.pl.snk.senseibunny.museomaster.databinding.ReportListItemBinding
import eu.pl.snk.senseibunny.museomaster.databinding.TaskItemBinding
import eu.pl.snk.senseibunny.museomaster.models.Model
import eu.pl.snk.senseibunny.museomaster.models.Report
import eu.pl.snk.senseibunny.museomaster.models.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class TaskAdapter(private val taskList: ArrayList<Task>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = TaskItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.bind(task)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    inner class TaskViewHolder(val itemBinding: TaskItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

//        init {
//            itemBinding.otherIcon.setOnClickListener {
//                val report = reportList[adapterPosition]
//                showPopup(report)
//            }
//        }
        fun bind(task: Task) {
            itemBinding.nameTextView.text=task.nazwaUzytkownikaNadajacego
        }

//        fun showPopup(report: Report) {
//            val popupBinding: CustomReportPopupBinding = CustomReportPopupBinding.inflate(
//                LayoutInflater.from(itemBinding.root.context))
//            val popupView: View = popupBinding.root
//            val popupWindow = PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)
//
//
//            // Set the focusable property to true
//            popupWindow.isFocusable = true
//
//            // Set an OnTouchListener to consume touch events
//            popupView.setOnTouchListener { _, _ -> true }
//
//            popupBinding.name.text="Username: "+ report.nazwaUzytkownika
//            popupBinding.desc.text="Description: "+ report.opis
//
//            popupBinding.closeButton.setOnClickListener {
//                popupWindow.dismiss()
//            }
//
//            popupBinding.deleteButton.setOnClickListener {
//                val position = adapterPosition
//                if (position != RecyclerView.NO_POSITION) {
//
//                    reportList.removeAt(position)
//                    notifyItemRemoved(position)
//                    popupWindow.dismiss()
//                }
//                runBlocking {
//                    // Launch a coroutine in the IO dispatcher
//                    withContext(Dispatchers.IO) {
//                        Model.getInstanceWC().dataBaseDriver.deleteReport(report.idReportu)
//
//                    }
//
//                }
//            }
//
//            // Show the popup window in the center of the screen
//            popupWindow.showAtLocation(itemBinding.root, Gravity.CENTER, 0, 0)
//        }
    }
}