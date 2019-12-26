package mufilbito.com.videoplayer.kotlinClasses.recyclerview

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import mufilbito.com.videoplayer.R

class CustomAdapter(val userList: ArrayList<Movie>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.kot_recycler_single_row, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        holder.bindItems(userList[position])

    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(user: Movie) {
            val textViewName = itemView.findViewById(R.id.list_title) as TextView
            val textViewAddress  = itemView.findViewById(R.id.list_description) as TextView
            textViewName.text = user.title
            textViewAddress.text = user.year.toString()
        }

    }


}