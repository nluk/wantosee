package pl.nluk.wantosee.fragments.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import androidx.annotation.LayoutRes
import pl.nluk.wantosee.models.Country


class CountryAdapter(context: Context, @LayoutRes private val layoutResource: Int, val allCountries: List<Country>) :
    ArrayAdapter<Country>(context, layoutResource, allCountries) {


    private val countries: MutableList<Country> = ArrayList()
    private val countriesFiler = CountryFilter()


    override fun getItemId(position: Int): Long {
        return countries[position].id
    }

    override fun getCount(): Int {
        return countries.size
    }

    override fun getItem(position: Int): Country {
        return countries[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return createViewFromResource(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return createViewFromResource(position, convertView, parent)
    }

    private fun createViewFromResource(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: TextView =
            convertView as TextView? ?: LayoutInflater.from(context).inflate(layoutResource, parent, false) as TextView
        view.text = countries[position].name
        return view
    }

    override fun getFilter(): Filter {
        return countriesFiler
    }

    inner class CountryFilter : Filter() {

        override fun convertResultToString(resultValue: Any?): CharSequence {
            return (resultValue as Country).name
        }

        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filterResult = FilterResults()
            val countrySuggestions: MutableList<Country> = ArrayList()
            constraint?.run {
                for (country in allCountries) {
                    if (country.name.toLowerCase().contains(constraint.toString(), ignoreCase = true)) {
                        countrySuggestions.add(country)
                    }
                }
                filterResult.values = countrySuggestions
                filterResult.count = countrySuggestions.size
            }

            return filterResult
        }


        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            countries.clear()
            if (results != null && results.count > 0) {
                for (suggestion in (results.values as List<*>)) {
                    if (suggestion is Country) countries.add(suggestion)
                }
                notifyDataSetChanged()
            } else if (constraint == null) {
                countries.addAll(allCountries)
                notifyDataSetInvalidated()
            }

        }


    }
}

