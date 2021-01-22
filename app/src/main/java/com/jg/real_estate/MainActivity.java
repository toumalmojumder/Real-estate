package com.jg.real_estate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.jg.real_estate.fragment.AddHomeFragment;
import com.jg.real_estate.fragment.AllHomeFragment;
import com.jg.real_estate.fragment.ContactUsFragment;
import com.jg.real_estate.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.nav_home:

                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MainFragment()).commit();
                        break;
                    case R.id.nav_all_home:

                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AllHomeFragment()).commit();
                        break;
                    case R.id.nav_add_home:

                       getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AddHomeFragment()).commit();
                        break;
                    case R.id.nav_maps:

                        Intent intent = new Intent(MainActivity.this,MapsActivity.class);
                        startActivity(intent);
                         // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MapsFragment()).commit();
                        break;
                    case R.id.nav_contact_us:

                          getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ContactUsFragment()).commit();
                        break;


                }
                //To Close drawer after taping menu item
                drawerLayout.closeDrawer(GravityCompat.START);

                return true;
            }
        });


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MainFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

    }
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }


}