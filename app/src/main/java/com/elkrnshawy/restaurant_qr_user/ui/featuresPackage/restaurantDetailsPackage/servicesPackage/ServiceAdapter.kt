package com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.restaurantDetailsPackage.servicesPackage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.elkrnshawy.restaurant_qr_user.R
import com.elkrnshawy.restaurant_qr_user.databinding.ViewCategoryBinding
import com.elkrnshawy.restaurant_qr_user.databinding.ViewRestaurantBinding
import com.elkrnshawy.restaurant_qr_user.databinding.ViewServiceBinding
import com.elkrnshawy.restaurant_qr_user.models.categoryPackage.CategoryItem
import com.elkrnshawy.restaurant_qr_user.models.restaurantPackage.RestaurantItem
import com.elkrnshawy.restaurant_qr_user.models.servicePackage.ServiceItem

public class ServiceAdapter<T>(private val mItemsList: ArrayList<T>,
                               private val mItemClickListener: (View, Int) -> Unit
) : RecyclerView.Adapter<ServiceAdapter.RestaurantViewHolder<T>>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceAdapter.RestaurantViewHolder<T> {
        val layoutInflater = LayoutInflater.from(parent.context);
        val itemBinding = DataBindingUtil.inflate<ViewServiceBinding>(
            layoutInflater,
            R.layout.view_service,
            parent,
            false
        )
        return RestaurantViewHolder(itemBinding, mItemClickListener)
    }

    override fun onBindViewHolder(
            holder: ServiceAdapter.RestaurantViewHolder<T>, position: Int) {
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

    class RestaurantViewHolder<T>(var itemBinding: ViewServiceBinding, mItemClickListener: (View, Int) -> Unit) :
        RecyclerView.ViewHolder(itemBinding.root) {

        init {
            itemBinding.root.setOnClickListener {
                mItemClickListener(it, adapterPosition)
            }
        }

        fun bind(item: T) {
            itemBinding.vmItem=item as ServiceItem
        }
    }


}