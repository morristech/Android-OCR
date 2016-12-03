package cenkgun.com.android_ocr.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.brnunes.swipeablerecyclerview.SwipeableRecyclerViewTouchListener;

import java.util.ArrayList;

import cenkgun.com.android_ocr.FaturaAdapter;
import cenkgun.com.android_ocr.R;
import cenkgun.com.android_ocr.Tools.Tools;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Tools.ApplicationContext = getApplicationContext();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, FaturaEkleActivity.class);
                startActivity(i);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initData();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        finish();
        startActivity(new Intent(MainActivity.this,MainActivity.class));
    }

    private void initData() {

        LinearLayout card = (LinearLayout) findViewById(R.id.card_yok);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        data = Tools.faturas;

        if (!data.isEmpty()) {

            card.setVisibility(GONE);

            final RecyclerView.Adapter adapter = new FaturaAdapter(data);
            recyclerView.setAdapter(adapter);

            SwipeableRecyclerViewTouchListener swipeTouchListener =
                    new SwipeableRecyclerViewTouchListener(recyclerView,
                            new SwipeableRecyclerViewTouchListener.SwipeListener() {
                                @Override
                                public boolean canSwipeLeft(int position) {
                                    return true;
                                }

                                @Override
                                public boolean canSwipeRight(int position) {
                                    return true;
                                }

                                @Override
                                public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                    for (int position : reverseSortedPositions) {
                                        data.remove(position);
                                        adapter.notifyItemRemoved(position);
                                    }
                                    adapter.notifyDataSetChanged();
                                }

                                @Override
                                public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                    for (int position : reverseSortedPositions) {
                                        data.remove(position);
                                        adapter.notifyItemRemoved(position);
                                    }
                                    adapter.notifyDataSetChanged();
                                }
                            });

            recyclerView.addOnItemTouchListener(swipeTouchListener);
        } else {
            card.setVisibility(View.VISIBLE);
        }


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
        getMenuInflater().inflate(R.menu.menu_anasayfa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.action_ayarlar) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            return true;
        } else if (id == R.id.action_yenile) {

            Toast.makeText(this, "Faturalar güncellendi...", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}

