package eu.pl.snk.senseibunny.museomaster.adapters

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import eu.pl.snk.senseibunny.museomaster.databinding.CustomExhibitPopupBinding
import eu.pl.snk.senseibunny.museomaster.databinding.ExhibitListItemBinding
import eu.pl.snk.senseibunny.museomaster.models.Exhibit
import eu.pl.snk.senseibunny.museomaster.models.Model
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.ArrayList

class ExhibitAdapter(private val exhibitsList: ArrayList<Exhibit>) : RecyclerView.Adapter<ExhibitAdapter.ExhibitViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExhibitViewHolder{
        val view = ExhibitListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExhibitViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExhibitViewHolder, position: Int) {
        val exhibit = exhibitsList[position]
        holder.bind(exhibit)
    }

    override fun getItemCount(): Int {
        return exhibitsList.size
    }

    inner class ExhibitViewHolder(val itemBinding: ExhibitListItemBinding) : RecyclerView.ViewHolder(itemBinding.root){
        private val ExNameTextView : TextView = itemBinding.ExNameTextView
        private val TopicTextView : TextView = itemBinding.TopicTextView

        init{
            itemBinding.detailsIcon.setOnClickListener{
                val exhibit = exhibitsList[adapterPosition]
                showPopup(exhibit)
            }
        }

        fun bind(exhibit: Exhibit){
            ExNameTextView.text = exhibit.nazwa_zabytku_tf
            TopicTextView.text = exhibit.tematyka_tf
        }

        fun showPopup(exhibit: Exhibit){
            val popupBinding: CustomExhibitPopupBinding = CustomExhibitPopupBinding.inflate(LayoutInflater.from(itemBinding.root.context))
            val popupView: View = popupBinding.root
            val popupWindow = PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true)

            // Set the focusable property to true
            popupWindow.isFocusable = true

            // Set an OnTouchListener to consume touch events
            popupView.setOnTouchListener { _, _ -> true }

            popupBinding.nameTextView.text = "Name:" + exhibit.nazwa_zabytku_tf
            popupBinding.periodTextView.text = "Period:" + exhibit.okres_powstawnia_tf
            popupBinding.TopicTextView.text = "Topic:" + exhibit.tematyka_tf
            popupBinding.authorTextView.text = "Author:" + exhibit.tworca_tf
            popupBinding.roomTextView.text = "Current Storage Room:" + exhibit.akt_miej_przech_tf
            popupBinding.descTextView.text = "Description:" + exhibit.opis_ta

            popupBinding.closeButton.setOnClickListener {
                popupWindow.dismiss()
            }

            popupBinding.deleteButton.setOnClickListener {
                val position = adapterPosition
                if(position != RecyclerView.NO_POSITION){
                    exhibitsList.removeAt(position)
                    notifyItemRemoved(position)
                    popupWindow.dismiss()
                }
                runBlocking {
                    withContext(Dispatchers.IO) {
                        Model.getInstanceWC().dataBaseDriver.deleteExhibit(exhibit.idZabytku)
                    }
                }
            }

            // Show the popup window in the center of the screen
            popupWindow.showAtLocation(itemBinding.root, Gravity.CENTER, 0, 0)

        }
    }
}