package pl.nluk.wantosee.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import pl.nluk.wantosee.R
import pl.nluk.wantosee.models.Attraction


class AttractionsAdapter(val attractions : List<Attraction>) : RecyclerView.Adapter<AttractionsAdapter.AttractionViewHolder>() {


    override fun onBindViewHolder(holder: AttractionViewHolder, position: Int) {
        holder.bindItems(attractions[position])
//        holder.itemView.setOnClickListener {
//            onFriendClick(friendsList[holder.adapterPosition])
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_attraction, parent, false)
        return AttractionViewHolder(v)
    }

    override fun getItemCount(): Int = attractions.size

    class AttractionViewHolder(view : View) : RecyclerView.ViewHolder(view){


        fun bindItems(attraction : Attraction){

        }
    }

}