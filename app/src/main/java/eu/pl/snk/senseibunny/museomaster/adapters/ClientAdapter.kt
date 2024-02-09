package eu.pl.snk.senseibunny.museomaster.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import eu.pl.snk.senseibunny.museomaster.databinding.UserListItemBinding
import eu.pl.snk.senseibunny.museomaster.models.Client

class ClientAdapter(private val clientList: ArrayList<Client>) : RecyclerView.Adapter<ClientAdapter.ClientViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val view = UserListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ClientViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        val client = clientList[position]
        holder.bind(client)
    }

    override fun getItemCount(): Int {
        return clientList.size
    }

    inner class ClientViewHolder(val itemBinding: UserListItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        private val nameTextView: TextView = itemBinding.nameTextView
        private val emailTextView: TextView = itemBinding.secondNameTextView

        fun bind(client: Client) {
            nameTextView.text = client.getNazwaUzytkownika()
            emailTextView.text = client.emailPracownikaProperty()
            itemBinding.work.text = client.rola
        }
    }
}