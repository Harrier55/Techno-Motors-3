package com.example.techno_motors_3.one.ui.fragmentHomeMenu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.techno_motors_3.R
import com.example.techno_motors_3.one.domain.PromotionEntity
import java.lang.IllegalArgumentException

private const val TYPE_HEADER = 0
private const val TYPE_ITEMS = 1

/** В RecyclerView реализован Header, сейчас иам просто TextView, но можно что угодно поставить**/

class AdapterHomeFragment : RecyclerView.Adapter<BaseViewHolder>() {

    private var promotionList: List<PromotionEntity> = mutableListOf()


    fun refreshList(promotionList: List<PromotionEntity>) {
        this.promotionList = promotionList as MutableList<PromotionEntity>
    }


    override fun getItemCount(): Int = promotionList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when(viewType){
            TYPE_HEADER ->{
                val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_header, parent, false)
            HeaderViewHolder(itemView)
            }
            TYPE_ITEMS ->{
                val itemView =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_promotion, parent, false)
                ViewHolder(itemView)
            }
            else -> throw IllegalArgumentException(" Invalid viewType")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
//        var promotionEntity: PromotionEntity = promotionList[position]
        when (holder){
            is ViewHolder ->{
                holder.title.text = promotionList[position].title
                holder.discount.text = promotionList[position].discount
                holder.description.text = promotionList[position].description

                Glide.with(holder.itemView.context)
                    .load(promotionList[position].picture)
 //                   .placeholder(R.drawable.techno_motors_360_270)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(holder.picture)
            }
            is HeaderViewHolder ->{
                // todo для Header
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0){
            return TYPE_HEADER
        }
        return TYPE_ITEMS
    }
}

 class ViewHolder(itemView: View) :BaseViewHolder(itemView) {

     var title: TextView = itemView.findViewById(R.id.promotion_title_tv)
     var picture: ImageView = itemView.findViewById(R.id.promotion_picture)
     var discount: TextView = itemView.findViewById(R.id.promotion_discount_tv)
     var description: TextView = itemView.findViewById(R.id.promotion_description_tv)

    override fun bind(itemView: View) {
    }
}

class HeaderViewHolder(itemView: View): BaseViewHolder(itemView){
    override fun bind(itemView: View){
    }
}

abstract class BaseViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    abstract fun bind(itemView: View)
}