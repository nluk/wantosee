package pl.nluk.wantosee.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_attraction.view.*
import kotlinx.android.synthetic.main.item_mystey_attraction.view.attractionImage
import pl.nluk.wantosee.R
import pl.nluk.wantosee.connectivity.rest.other.PhotosEndpoint
import pl.nluk.wantosee.models.Attraction

typealias AttractionClickCallback = (attractionId: Long) -> Unit

class AttractionAdapter(
    val attractions: List<Attraction>,
    val countryMap: Map<Long, String>,
    val onAttractionClicked: AttractionClickCallback
) : RecyclerView.Adapter<AttractionAdapter.AttractionViewHolder>() {


    override fun onBindViewHolder(holder: AttractionViewHolder, position: Int) {
        val attraction = attractions[position]
        val countryName = countryMap[attraction.countryId]
        holder.bindItems(attraction, countryName)
        holder.itemView.setOnClickListener {
            onAttractionClicked(attractions[holder.adapterPosition].id)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_attraction, parent, false)
        return AttractionViewHolder(v)
    }

    override fun getItemCount(): Int = attractions.size

    class AttractionViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItems(attraction: Attraction, countryName: String?) {

            itemView.attractionName.text = attraction.name
            itemView.attractionCountry.text = countryName ?: ""
            Picasso.get()
                .load(PhotosEndpoint.getUrlForReference(attraction.imageReference))
                .into(itemView.attractionImage)
        }
    }

}