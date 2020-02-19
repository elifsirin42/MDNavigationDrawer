package com.example.mdnavigationdrawer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;


import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.myToolbar);
        if(myToolbar!=null){

            setSupportActionBar(myToolbar);

        }

        final DrawerLayout drawerLayout = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,myToolbar,R.string.open,R.string.close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                Toast.makeText(MainActivity.this,"Opened",Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                Toast.makeText(MainActivity.this,"Closed",Toast.LENGTH_SHORT).show();

            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        NavigationView navigationView = findViewById(R.id.myNavigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                drawerLayout.closeDrawers();

                switch (item.getItemId()){

                    case R.id.build:
                        Toast.makeText(MainActivity.this,"Build is selected",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.colorize:
                        Toast.makeText(MainActivity.this,"Colorize is selected",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.place:
                        //We want to transition to fragment in instead of making Toast Text when you press the button.
                        MyFragmentClass myFragment = new MyFragmentClass();
                        FragmentTransaction fragmentTransaction =
                                getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container,myFragment);
                        //Look at stackoverflor for learn the addToBackStack method.
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                        break;
                }


                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.my_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }
}
