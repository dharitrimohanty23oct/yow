package com.yow.www.yowapplication.activity;

import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.yow.www.yowapplication.R;
import com.yow.www.yowapplication.frgament.AccountFragment;

public class DashBoardActivity extends AppCompatActivity implements OnMapReadyCallback, LocationListener {
    private Toolbar mToolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    LinearLayout mLinear,header,lnHome;
    TextView mTextView;
    // Google Map
    private GoogleMap googleMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board1);
        mToolbar = (Toolbar) findViewById(R.id.action_bar);
        setSupportActionBar(mToolbar);
        // getSupportActionBar().setDisplayShowHomeEnabled(true);
        // getSupportActionBar().setLogo(R.mipmap.ic_launcher);
       // getSupportActionBar().setDisplayShowTitleEnabled(false);
        mLinear=(LinearLayout)mToolbar.findViewById(R.id.lnTitle);
        mTextView=(TextView)mToolbar.findViewById(R.id.tvTitle);
        mLinear.setVisibility(View.VISIBLE);
        mTextView.setVisibility(View.GONE);
        navigationView=(NavigationView)findViewById(R.id.navigation_view_left);
        View headerview = navigationView.getHeaderView(0);
        header=(LinearLayout)headerview.findViewById(R.id.drawer_list);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        lnHome=(LinearLayout)findViewById(R.id.lnHome);
        lnHome.setVisibility(View.VISIBLE);
        try {
            // Loading map
            initilizeMap();

        } catch (Exception e) {
            e.printStackTrace();
        }

       // setDrawerLayout(); 
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                mToolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Write code to perform action when drawer is closed.
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                // Write code to perform action when drawer is sliding.
                super.onDrawerSlide(drawerView, slideOffset);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Write code to perform action when drawer is opened.
                super.onDrawerOpened(drawerView);
            }
        };
        actionBarDrawerToggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.black));
        // Set the actionbarToggle to drawer layout.
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        // Calling sync state is necessary or else your hamburger icon wont show up.
        actionBarDrawerToggle.syncState();

        setupDrawerContent(navigationView);

        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.closeDrawers(); //close drawer

                AccountFragment selectedFragment = new AccountFragment();
                mLinear.setVisibility(View.GONE);
                mTextView.setVisibility(View.VISIBLE);
                mTextView.setText("Your Account");
                lnHome.setVisibility(View.GONE);
                // selectedFragment.setTitle(String.valueOf(menuItem.getTitle()));
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, selectedFragment);
                fragmentTransaction.commit();
            }
        });

    }

    private void initilizeMap() {
        if (googleMap == null) {
            MapFragment mapFragment = (MapFragment) getFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);;

            // check if map is created successfully or not
            if (googleMap == null) {
              //  Toast.makeText(getApplicationContext(), "Sorry! unable to create maps", Toast.LENGTH_SHORT)                        .show();
            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        initilizeMap();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_multi_select, menu);
        return true;
    }
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // Toggle the checked state of menuItem.
                      //  menuItem.setChecked(!menuItem.isChecked());

                        // Close the drawer
                        drawerLayout.closeDrawers();

                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
   /*     // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass=null;
        switch(menuItem.getItemId()) {
            case R.id.rlv_nav:
                fragmentClass =AccountFragment.class;
                break;
          *//*  case R.id.nav_second_fragment:
                fragmentClass = SecondFragment.class;
                break;
            case R.id.nav_third_fragment:
                fragmentClass = ThirdFragment.class;
                break;
            default:
                fragmentClass = FirstFragment.class;*//*
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawer.closeDrawers();*/
        switch (menuItem.getItemId()) {

          /*  case R.id.rlv_nav:
                AccountFragment selectedFragment = new AccountFragment();
                mLinear.setVisibility(View.GONE);
                mTextView.setVisibility(View.VISIBLE);
                mTextView.setText("Your Account");
               // selectedFragment.setTitle(String.valueOf(menuItem.getTitle()));
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, selectedFragment);
                fragmentTransaction.commit();
                break;*/
        }




    }

    public void checkPermission(){
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
           setUp();
        } else {
         //   Toast.makeText(this,"error_permission_map", Toast.LENGTH_LONG).show();
        }
    }
    public void setUp(){
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        googleMap.setMyLocationEnabled(true);
        googleMap.setTrafficEnabled(true);
        googleMap.setIndoorEnabled(true);
        googleMap.setBuildingsEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);

       /* LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String bestProvider = locationManager.getBestProvider(criteria, true);
        Location location = locationManager.getLastKnownLocation(bestProvider);
        if (location != null) {
            onLocationChanged(location);
        }
        locationManager.requestLocationUpdates(bestProvider, 20000, 0, this);*/
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap=map;
        // DO WHATEVER YOU WANT WITH GOOGLEMAP
        checkPermission();
    }

    @Override
    public void onLocationChanged(Location location) {
      /*  double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
        googleMap.addMarker(new MarkerOptions().position(latLng));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));*/

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
