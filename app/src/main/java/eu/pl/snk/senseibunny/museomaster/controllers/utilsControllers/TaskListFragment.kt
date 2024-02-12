package eu.pl.snk.senseibunny.museomaster.controllers.utilsControllers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import eu.pl.snk.senseibunny.museomaster.R
import eu.pl.snk.senseibunny.museomaster.adapters.ReportAdapter
import eu.pl.snk.senseibunny.museomaster.adapters.TaskAdapter
import eu.pl.snk.senseibunny.museomaster.databinding.FragmentAddRoomBinding
import eu.pl.snk.senseibunny.museomaster.databinding.FragmentTaskListBinding
import eu.pl.snk.senseibunny.museomaster.models.Model
import eu.pl.snk.senseibunny.museomaster.models.Report
import eu.pl.snk.senseibunny.museomaster.models.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class TaskListFragment : Fragment() {

    private lateinit var binding: FragmentTaskListBinding
    private var tasks: ArrayList<Task> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaskListBinding.inflate(inflater, container, false)

        runBlocking {
            // Launch a coroutine in the IO dispatcher
            withContext(Dispatchers.IO) {
                Model.getInstance(context).setTasks("assigned")
                tasks = Model.getInstance(context).tasks
            }
        }

        val adapter= TaskAdapter(tasks)
        binding.recyclerView.adapter=adapter

        return (binding.root)
    }

 }