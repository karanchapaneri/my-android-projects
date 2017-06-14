package com.example.karanc.allied1;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.karanc.Others.Credits;
import com.example.karanc.Others.Grievances;
import com.example.karanc.Others.News;
import com.example.karanc.Others.Reviews;
import com.example.karanc.Others.ReviewsSnA;
import com.example.karanc.admission.Branches;
import com.example.karanc.admission.Colleges;
import com.example.karanc.admission.Exams;

import java.io.File;

public class NewUserProfile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    static FloatingActionButton profnewfabmale;
    static FloatingActionButton profnewfabfemale;

    RelativeLayout baselayout;
    LinearLayout headerlayout;
    Fragment fragment = null;
    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

final ImageView welcomeiv= (ImageView) findViewById(R.id.namaskar);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        headerlayout= (LinearLayout) findViewById(R.id.header);

//

        baselayout = (RelativeLayout) findViewById(R.id.relativenew);
        baselayout.setVisibility(View.VISIBLE);


/*
        TextView welcometv= (TextView) findViewById(R.id.welcometv);
        welcometv.setText("Welcome to Home Screen:  "+ MainActivity.email);




        */
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_new);
        View header = navigationView.inflateHeaderView(R.layout.nav_header_new_user_profile);
        profnewfabmale= (FloatingActionButton) header.findViewById(R.id.profile_new_male);
        profnewfabfemale= (FloatingActionButton) header.findViewById(R.id.profile_new_female);
        profnewfabfemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profnewfabmale.setImageResource(R.drawable.girl);
                profnewfabfemale.setImageResource(R.drawable.maleavatar);


            }
        });

        profnewfabmale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profnewfabmale.setImageResource(R.drawable.maleavatar);
                profnewfabfemale.setImageResource(R.drawable.girl);
            }
        });


        navigationView.setNavigationItemSelectedListener(this);


        Animation anim = AnimationUtils.loadAnimation(NewUserProfile.this, R.anim.translate);
        anim.setInterpolator((new AccelerateDecelerateInterpolator()));

    welcomeiv.setVisibility(View.VISIBLE);
        welcomeiv.setAnimation(anim);

        anim.setAnimationListener(new Animation.AnimationListener(){
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                welcomeiv.setVisibility(View.GONE);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.new_user_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fm = getSupportFragmentManager();

        switch (id) {

            case R.id.nav_colleges:
                fragment = new Colleges();
                title = "Colleges";
                break;

            case R.id.nav_branches:
                fragment = new Branches();
                title = "Branches";
                break;


            //baselayout.setVisibility(View.INVISIBLE);

            case R.id.nav_reviews:
                fragment = new Reviews();
                title = "Reviews";
                break;

            case R.id.nav_news:
                fragment = new News();
                title = "News";
                break;


            case R.id.nav_grievances:
                fragment = new Grievances();
                title = "Grievances";
                break;

            case R.id.nav_credits:
                fragment = new Credits();
                title = "Credits";
                break;

            case R.id.nav_chat:
                fragment = new com.example.karanc.chat.MainActivity();
                title = "Chat";
                break;
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.relativenew, fragment);
            fragmentTransaction.commit();
            // set the toolbar title
            getSupportActionBar().setTitle(title);

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            return true;
        }

        return true;
    }
}
