package eu.pl.snk.senseibunny.museomaster.controllers.CuratorControllers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import eu.pl.snk.senseibunny.museomaster.R
import eu.pl.snk.senseibunny.museomaster.databinding.FragmentCreateExhibitionBinding


class CreateExhibitionFragment : Fragment() {

    private lateinit var binding: FragmentCreateExhibitionBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateExhibitionBinding.inflate(inflater, container, false)
        val rooms = arrayOf("SalaA3","SalaA4","SalaA5", "SalaA6" )
        val adapter: ArrayAdapter<String?> = ArrayAdapter<String?>(requireContext(), android.R.layout.simple_spinner_item, rooms)
        binding.roomName.adapter=adapter
        return (binding.root)
    }

}