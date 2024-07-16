package com.example.bluest.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class pageAdapter extends FragmentStateAdapter {
    ArrayList<Fragment> arrayList;
    public pageAdapter(@NonNull FragmentActivity fragmentActivity, ArrayList<Fragment> fragmentArrayList) {
        super(fragmentActivity);
        this.arrayList=fragmentArrayList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return arrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }
}
