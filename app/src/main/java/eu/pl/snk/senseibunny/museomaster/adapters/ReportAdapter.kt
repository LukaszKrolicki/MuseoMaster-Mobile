package eu.pl.snk.senseibunny.museomaster.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import eu.pl.snk.senseibunny.museomaster.adapters.ReportAdapter.ReportViewHolder
import eu.pl.snk.senseibunny.museomaster.databinding.ReportListItemBinding
import eu.pl.snk.senseibunny.museomaster.databinding.UserListItemBinding
import eu.pl.snk.senseibunny.museomaster.models.Report

class ReportAdapter(private val reportList: List<Report>) : RecyclerView.Adapter<ReportViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val view = ReportListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ReportViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        val report = reportList[position]
        holder.bind(report)
    }

    override fun getItemCount(): Int {
        return reportList.size
    }

    inner class ReportViewHolder(val itemBinding: ReportListItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        private val titleTextView: TextView = itemBinding.nameTextView

        fun bind(report: Report) {
            titleTextView.text = report.nazwaUzytkownika
        }
    }
}