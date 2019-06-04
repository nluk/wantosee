package pl.nluk.wantosee.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_mystey_attraction.view.*
import pl.nluk.wantosee.R
import pl.nluk.wantosee.connectivity.rest.other.PhotosEndpoint
import pl.nluk.wantosee.models.Attraction


typealias OnAttractionLongclick = (attraction: Attraction) -> Unit

class MysteryAttractionAdapter(val attractions: List<Attraction>, val onAttractionClicked: OnAttractionLongclick) :
    RecyclerView.Adapter<MysteryAttractionAdapter.AttractionViewHolder>() {


    var likes: MutableList<Boolean> = ArrayList(attractions.size)

    fun dataChanged() {
        resetLikes()
        notifyDataSetChanged()
    }


    fun resetLikes() {
        likes = ArrayList(attractions.size)
        for (i in 0..attractions.size) likes.add(false)
    }

    override fun onBindViewHolder(holder: AttractionViewHolder, position: Int) {
        holder.bindItems(attractions[position], likes[position])
        holder.itemView.setOnLongClickListener {
            it.likeAnim.visibility = View.VISIBLE
            it.likeAnim.playAnimation()
            likes[holder.adapterPosition] = true
            onAttractionClicked(attractions[holder.adapterPosition])
            true
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttractionViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_mystey_attraction, parent, false)
        return AttractionViewHolder(v)
    }

    override fun getItemCount(): Int = attractions.size

    class AttractionViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindItems(attraction: Attraction, isLiked: Boolean) {
            itemView.likeAnim.visibility = View.GONE
            if (isLiked) itemView.likeAnim.visibility = View.VISIBLE

            Picasso.get()
                .load(PhotosEndpoint.getUrlForReference(attraction.imageReference))
                .into(itemView.attractionImage)
        }
    }

}