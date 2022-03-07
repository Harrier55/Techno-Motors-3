package com.example.techno_motors_3.one.ui.fragmentHome

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.techno_motors_3.R
import com.example.techno_motors_3.one.domain.PromotionEntity

class AdapterHomeFragment : RecyclerView.Adapter<AdapterHomeFragment.ViewHolder>() {

    private var promotionList: List<PromotionEntity> = mutableListOf()


    fun refreshList(promotionList: List<PromotionEntity>) {
        this.promotionList = promotionList as MutableList<PromotionEntity>
    }


    override fun getItemCount(): Int = promotionList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_promotion, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        var promotionEntity: PromotionEntity = promotionList[position]
        holder.title.text = promotionList[position].title
        holder.discount.text = promotionList[position].discount
        holder.description.text = promotionList[position].description

        Glide.with(holder.itemView.context)
            .load(promotionList[position].picture)
            .placeholder(R.drawable.techno_motors_360_270)
            .into(holder.picture)

    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.promotion_title_tv)
        var picture: ImageView = itemView.findViewById(R.id.promotion_picture)
        var discount: TextView = itemView.findViewById(R.id.promotion_discount_tv)
        var description: TextView = itemView.findViewById(R.id.promotion_description_tv)

    }
}