package it.polito.cowapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class LateralMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener{

    private String[] groups = {"Anna's Birthday", "Trip to London", "Dinner with Italian guys"};
    private ListView list;
    private String description = "Lorem ipsum dolor sit amet, consectetur ...";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lateral_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        list = (ListView) findViewById(R.id.lv_groups);
        list.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {return groups.length;}
            @Override
            public Object getItem(int position) {return groups[position];}
            @Override
            public long getItemId(int position) {return 0;}
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null)
                    convertView = getLayoutInflater().inflate(R.layout.group_item,parent,false);
                TextView tv = (TextView) convertView.findViewById(R.id.group_name);
                tv.setText(groups[position]);
                tv = (TextView) convertView.findViewById(R.id.group_description);
                tv.setText(description);
                tv = (TextView)convertView.findViewById(R.id.group_summary1);
                String tmp = String.valueOf(position*1.00);
                tv.setText("+"+ tmp + "€");
                tv = (TextView)convertView.findViewById(R.id.group_summary2);
                tmp = String.valueOf(position*2.00);
                tv.setText("-"+ tmp+ "€");
                return convertView;
            }
        });


    }

    @Override
    public void onClick(View view){
        Intent intent = new Intent(view.getContext(),GroupActivity.class);
        intent.putExtra("activity","R.id.navigation_home");
        startActivity(intent);
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
        getMenuInflater().inflate(R.menu.lateral_menu, menu);
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

        if (id == R.id.group_section) {
            //Intent intent = new Intent(getApplicationContext(),LateralMenu.class);
            //startActivity(intent);
        } else if (id == R.id.personal_section) {
            Toast.makeText(getApplicationContext(), "PersonalSectionActivity", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.add_group) {
            Toast.makeText(getApplicationContext(), "AddGroupActivity", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
