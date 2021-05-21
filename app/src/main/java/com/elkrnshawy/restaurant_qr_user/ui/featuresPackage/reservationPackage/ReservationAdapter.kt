package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.reservationPackage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.elkrnshawy.restaurant_qr_user.R
import com.elkrnshawy.restaurant_qr_user.databinding.ViewCategoryBinding
import com.elkrnshawy.restaurant_qr_user.databinding.ViewProductBinding
import com.elkrnshawy.restaurant_qr_user.databinding.ViewReservationBinding
import com.elkrnshawy.restaurant_qr_user.models.categoryPackage.CategoryItem
import com.elkrnshawy.restaurant_qr_user.models.myReservationPackage.MyReservationData
import com.elkrnshawy.restaurant_qr_user.models.productPackage.ProductItem

public class ReservationAdapter<T>(private val mItemsList: ArrayList<T>,
                                   private val mItemClickListener: (View, Int) -> Unit
) : RecyclerView.Adapter<ReservationAdapter.RestaurantViewHolder<T>>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context);
        val itemBinding = DataBindingUtil.inflate<ViewReservationBinding>(
            layoutInflater,
            R.layout.view_reservation,
            parent,
            false
        )
        return RestaurantViewHolder(itemBinding, mItemClickListener)
    }

    override fun onBindViewHolder(
        holder: ReservationAdapter.RestaurantViewHolder<T>, position: Int) {
        holder.bind(mItemsList[position])
    }

    override fun getItemCount(): Int = mItemsList.size

    public fun getItem(position: Int): T = mItemsList[position]

    public fun getItems(): List<T> = mItemsList

    public fun addMoreItems(items: List<T>) {
        var startPosition = 0
        var endPosition = mItemsList.size + items.size

        if (mItemsList.size != 0) {
            startPosition = mItemsList.size
        }

        mItemsList.addAll(items)
        notifyItemRangeChanged(startPosition, endPosition)
    }

    public fun setItems(items: List<T>) {
        mItemsList.clear()
        mItemsList.addAll(items)
        notifyDataSetChanged()
    }

    class RestaurantViewHolder<T>(var itemBinding: ViewReservationBinding, mItemClickListener: (View, Int) -> Unit) :
        RecyclerView.ViewHolder(itemBinding.root) {

        init {
            itemBinding.root.setOnClickListener {
                mItemClickListener(it, adapterPosition)
            }
        }

        fun bind(item: T) {
            itemBinding.vmItem=item as MyReservationData
        }
    }


}