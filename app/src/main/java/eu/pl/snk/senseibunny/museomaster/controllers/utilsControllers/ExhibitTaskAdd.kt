package eu.pl.snk.senseibunny.museomaster.controllers.utilsControllers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import eu.pl.snk.senseibunny.museomaster.R
import eu.pl.snk.senseibunny.museomaster.databinding.ActivityDetailAndAddTaskBinding
import eu.pl.snk.senseibunny.museomaster.databinding.ActivityExhibitTaskAddBinding

class ExhibitTaskAdd : AppCompatActivity() {

    private lateinit var binding: ActivityExhibitTaskAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityExhibitTaskAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}