package com.example.bluest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.bluest.adapter.pageAdapter;
import com.example.bluest.bottombar.account;
import com.example.bluest.bottombar.home;
import com.example.bluest.bottombar.search;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//    BottomNavigationView bottomNavigationView;
//    home home;
//    account account;
//    location location;

    ArrayList<Fragment> fragmentArrayList=new ArrayList<>();
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager2 viewPager2=findViewById(R.id.viewPager2);
        BottomNavigationView bottomNavigationView=findViewById(R.id.navigation);
        fragmentArrayList.add(new home());
        fragmentArrayList.add(new account());
//        fragmentArrayList.add(new search());
        pageAdapter pageAdapter=new pageAdapter(this, fragmentArrayList);
        viewPager2.setAdapter(pageAdapter);
        viewPager2.setUserInputEnabled(false);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.frHome);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.frAccount);
                        break;
//                    case 2:
//                        bottomNavigationView.setSelectedItemId(R.id.frSearch);
//                        break;
                }
                super.onPageSelected(position);
            }
        });
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.frHome:
                        viewPager2.setCurrentItem(0);
                        break;
                    case R.id.frAccount:
                        viewPager2.setCurrentItem(1);
//                        break;
//                    case  R.id.frSearch:
//                        viewPager2.setCurrentItem(2);
                }
                return true;
            }
        });
//        bottomNavigationView=findViewById(R.id.bottomNavigationView);
//        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container, home).commit();
//        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.home:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,home).commit();
//                        return true;
//                    case R.id.account:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,account).commit();
//                        return true;
//                    case R.id.location:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,location).commit();
//                        return true;
//                }
//                return false;
//            }
//        });
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.option,menu);
//        return true;
//    }
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.logout:
//                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
//                FirebaseAuth.getInstance().signOut();
//                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//                finish();
//                return true;
//            case R.id.about:
//                startActivity(new Intent(this, activity_about.class));
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
}