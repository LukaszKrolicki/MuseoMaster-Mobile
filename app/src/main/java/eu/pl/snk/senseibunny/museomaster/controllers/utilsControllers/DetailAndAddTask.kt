package eu.pl.snk.senseibunny.museomaster.controllers.utilsControllers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import eu.pl.snk.senseibunny.museomaster.R
import eu.pl.snk.senseibunny.museomaster.databinding.ActivityDetailAndAddTaskBinding
import eu.pl.snk.senseibunny.museomaster.databinding.ActivityNormalWorkerBinding

class DetailAndAddTask : AppCompatActivity() {
    private lateinit var binding: ActivityDetailAndAddTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDetailAndAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}