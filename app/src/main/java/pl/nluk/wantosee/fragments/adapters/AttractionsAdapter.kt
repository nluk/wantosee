package pl.nluk.wantosee.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import pl.nluk.wantosee.R
import pl.nluk.wantosee.models.Attraction
import pl.nluk.wantosee.utils.ClickCallback


class AttractionsAdapter( val attractions : List<Attraction>, val onAttractionClicked: ClickCallback)
    : RecyclerView.Adapter<AttractionsAdapter.AttractionViewHolder>() {


    override fun onBindViewHolder(holder: AttractionViewHolder, position: Int) {
        holder.bindItems(attractions[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_attraction, parent, false)
        //v.setOnClickListener(onAttractionClicked)
        return AttractionViewHolder(v)
    }

    override fun getItemCount(): Int = attractions.size

    class AttractionViewHolder(view : View) : RecyclerView.ViewHolder(view){


        fun bindItems(attraction : Attraction){

        }
    }

}