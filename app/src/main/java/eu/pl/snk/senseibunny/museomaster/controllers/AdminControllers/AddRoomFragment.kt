package eu.pl.snk.senseibunny.museomaster.controllers.AdminControllers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import eu.pl.snk.senseibunny.museomaster.R
import eu.pl.snk.senseibunny.museomaster.databinding.FragmentAddRoomBinding
import eu.pl.snk.senseibunny.museomaster.databinding.FragmentAddUserBinding

class AddRoomFragment : Fragment() {

    private lateinit var binding: FragmentAddRoomBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddRoomBinding.inflate(inflater, container, false)
        val types = arrayOf("Exhibit room","Storage room" )
        val adapter: ArrayAdapter<String?> = ArrayAdapter<String?>(requireContext(), android.R.layout.simple_spinner_item, types)
        binding.typeRoom.adapter=adapter
        return (binding.root)
    }

}