package davidrajchenberg.junoworkshop.com.junodriverfeaturesprototype.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import davidrajchenberg.junoworkshop.com.junodriverfeaturesprototype.R;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Context context = MapsActivity.this;
    private GoogleMap mMap;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private LatLng new_york;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        if(getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

       sideMenuInit();
        navDrawerNavigation();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        new_york = new LatLng(40.714266, -74.000476);
        mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory
                .fromResource(R.drawable.lilcar)).position(new_york).title("Driver"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new_york, 13.5f));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_tool_bar_menu, menu);
        //cancelItem = menu.findItem(R.id.action_cancel_wash);
        return true;
    }

    private void navDrawerNavigation(){
        final NavigationView navigationView = findViewById(R.id.navView);
        View navHeader = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.account_nav:
                        Log.i("navOptions", "account selected");
                        Toast.makeText(context, "Account selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.earnings_nav:
                        Log.i("navOptions", "earnings selected");
                        Toast.makeText(context, "Earnings selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.promotions_nav:
                        Log.i("navOptions", "Promotions selected");
                        Toast.makeText(context, "Promotions selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.performance_nav:
                        Log.i("navOptions", "Performance selected");
                        Toast.makeText(context, "Performance selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.docs_nav:
                        //Take user to Documents.java
                        Log.i("navOptions", "My Documents selected");
                        startActivity(new Intent(MapsActivity.this, Documents.class));
                        return true;
                    case R.id.settings_nav:
                        Log.i("navOptions", "Settings selected");
                        Toast.makeText(context, "Settings selected", Toast.LENGTH_SHORT).show();
                        return true;
                }
                return false;
            }
        });
    }

    private void sideMenuInit(){
        Toolbar mCustomToolBar = findViewById(R.id.custom_tool_bar);
        setSupportActionBar(mCustomToolBar);
        mDrawerLayout = findViewById(R.id.home);
        mDrawerLayout.setBackgroundColor(getResources().getColor(R.color.pure_white));
        mToggle = new ActionBarDrawerToggle(MapsActivity.this, mDrawerLayout
                , R.string.open ,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    public void centerLocation(View v){
        //center location (on car)
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new_york, 13.5f));
    }

    public void onOffClick(View v){
        notThereYet();
    }

    public void onStatusClick(View v){
        notThereYet();
    }

    private void notThereYet(){
        Toast.makeText(MapsActivity.this, "Not there yet ;)", Toast.LENGTH_LONG)
                .show();
    }
}
