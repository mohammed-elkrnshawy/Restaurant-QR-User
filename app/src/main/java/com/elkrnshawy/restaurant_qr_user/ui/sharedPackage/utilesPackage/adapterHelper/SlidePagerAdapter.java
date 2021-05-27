package com.elkrnshawy.restaurant_qr_user.ui.sharedPackage.utilesPackage.adapterHelper;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.elkrnshawy.restaurant_qr_user.models.restaurantPackage.RestaurantItem;
import com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.restaurantDetailsPackage.aboutPackage.AboutFragment;
import com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.restaurantDetailsPackage.categoryPackage.CategoryFragment;
import com.elkrnshawy.restaurant_qr_user.ui.featuresPackage.restaurantDetailsPackage.servicesPackage.ServicesFragment;

import org.jetbrains.annotations.NotNull;

public class SlidePagerAdapter extends FragmentStateAdapter {
    private RestaurantItem item;
    private int tableNumber;
    private ServicesFragment servicesFragment;
    private CategoryFragment categoryFragment;
    private AboutFragment aboutFragment;

    public SlidePagerAdapter(FragmentActivity fa, RestaurantItem item, int tableNumber) {
        super(fa);
        this.item=item;
        this.tableNumber=tableNumber;
    }

    @NotNull
    @Override
    public Fragment createFragment(int position) {
        Bundle args = new Bundle();
        args.putSerializable("RestaurantObject", item);
        args.putSerializable("TableNumber", tableNumber);

        if (position==2){
            if (servicesFragment==null){
                servicesFragment=new ServicesFragment();
            }
            servicesFragment.setArguments(args);
            return servicesFragment;
        }else if (position==1){
            if (categoryFragment==null){
                categoryFragment=new CategoryFragment();
            }
            categoryFragment.setArguments(args);
            return categoryFragment;
        }else {
            if (aboutFragment==null){
                aboutFragment=new AboutFragment();
            }
            aboutFragment.setArguments(args);
            return aboutFragment;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}