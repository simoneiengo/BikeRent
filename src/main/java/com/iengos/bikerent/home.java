package com.iengos.bikerent;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.ListView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

public class home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    // Array of Info
    ArrayList<InfoRow> Row_data = new ArrayList<InfoRow>();
    private SwipeMenuListView listview;
    MyAdapter adapter;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Fill list of item
        Row_data.add(new InfoRow("9 Luglio 2016", "Terminato", "Bike n 9"));
        Row_data.add(new InfoRow("8 Giugno 2016", "Terminato", "Bike n 1"));
        Row_data.add(new InfoRow("14 Ottobre 2015", "20min", "Bike n 2"));

        adapter = new MyAdapter(this, Row_data);
        listview = (SwipeMenuListView) findViewById(R.id.content).findViewById(R.id.listview);
        listview.setAdapter(adapter);

        //SWYPE ACTION
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                //test item
                SwipeMenuItem testItem = new SwipeMenuItem(getApplicationContext());
                testItem.setBackground(new ColorDrawable(Color.rgb(0xA1, 0x3F, 0x22)));
                testItem.setWidth(dp2px(90));
                testItem.setIcon(R.drawable.ic_menu_share);
                menu.addMenuItem(testItem);

                //delete item
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
                deleteItem.setWidth(dp2px(90));
                deleteItem.setIcon(R.drawable.ic_delete_black);
                menu.addMenuItem(deleteItem);
            }
        };

        listview.setMenuCreator(creator);

        listview.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // testItem
                        break;
                    case 1:
                        // delete
                        //TODO:crasha se c'è un solo ultimo item
                        Row_data.remove(index);
                        listview.requestLayout();
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
    }

    private int dp2px(int dip){
        float scale = getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
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
        getMenuInflater().inflate(R.menu.home, menu);
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
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        LayoutInflater inflater = (LayoutInflater)  this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View mainContainer = findViewById(R.id.main_container);     // select container
        View oldContent = findViewById(R.id.content);               // select old content

        if (id == R.id.nav_rent) {
            ((ViewGroup) mainContainer).removeView(oldContent);         // remove old content
            View newContent = inflater.inflate(R.layout.content_home, null);   // select new content
            ((ViewGroup) mainContainer).addView(newContent);            // add new content
        } else if (id == R.id.nav_gallery) {
            ((ViewGroup) mainContainer).removeView(oldContent);         // remove old content
            View newContent = inflater.inflate(R.layout.content_request, null);   // select new content
            ((ViewGroup) mainContainer).addView(newContent);            // add new content
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_about) {
            dialog = new Dialog(home.this);
            dialog.setContentView(R.layout.rules_popup);        // set popup layout
            dialog.setTitle("Regulament");
            dialog.show();                                      // open popup
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_bug) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
