package eu.pl.snk.senseibunny.museomaster.controllers.AdminControllers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import eu.pl.snk.senseibunny.museomaster.R
import eu.pl.snk.senseibunny.museomaster.databinding.FragmentAddUserBinding


class AddUserFragment : Fragment() {

    private lateinit var binding: FragmentAddUserBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddUserBinding.inflate(inflater, container, false)
        val positions = arrayOf("Pracownik","Pracownik Techniczny","Kurator" )
        val adapter: ArrayAdapter<String?> = ArrayAdapter<String?>(requireContext(), android.R.layout.simple_spinner_item, positions)
        binding.position.adapter=adapter
        return (binding.root)
    }


}