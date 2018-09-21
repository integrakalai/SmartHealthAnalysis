package smarthealthanalytics.com.smarthealthanalytics;

import android.app.LauncherActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.acl.Group;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import smarthealthanalytics.com.smarthealthanalytics.adapter.Adapter;
import smarthealthanalytics.com.smarthealthanalytics.adapter.CustomExpandableListAdapter;


public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    ImageView dashboard;
    RelativeLayout dash, notify, setting, info;
    ImageView rt_Arrow,img_home;
    DrawerLayout drawer;
    ArrayList<String> aList = new ArrayList<String>();
    HashMap<String, String> contact = new HashMap<>();
    StringBuffer sb = new StringBuffer();
    BufferedReader br = null;
    ArrayList<String> messages = new ArrayList<String>();
    String icon;
    ImageView iconsset;
    int selectedposition;

    //added expNDABLE LISTVIEWW
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    // String[][] listDataHeader = new String[][]{};
    List<String> listTitles;
    List<String> add;
    ArrayList<ArrayList<String>> listDataHeader = new ArrayList<ArrayList<String>>();
  //  ArrayList<ArrayList<String>> listDataChild = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> infoheader = new ArrayList<ArrayList<String>>();
    String itemclicked,header;
    HashMap<String, List<String>> listDataChild;
    int position;
    TextView header_title;
    private int lastExpandedPosition = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//expandable listview//

        //ADDED EXPANDABLE LISTVIEW//

      //  int position = getIntent().getExtras().getInt("pos");
        Log.d("possecondactivity", String.valueOf(position));
        // listDataHeader = new ArrayList<String>();
        listTitles = new ArrayList<String>();
        add = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        preparedlist();

        expListView = (ExpandableListView) findViewById(R.id.simple_expandable_listview);
        header_title = (TextView) findViewById(R.id.txt_headertitle);


        // setting list adapter

        expListView.setDividerHeight(2);
        //expListView.setGroupIndicator(null);
        expListView.setClickable(true);

        listAdapter = new smarthealthanalytics.com.smarthealthanalytics.adapter.ExpandableListAdapter(Dashboard.this, listDataHeader.get(1), listDataChild);
        expListView.setAdapter(listAdapter);

        header_title.setText(listTitles.get(1));


        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    expListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;

            }
        });



        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Toast.makeText(Dashboard.this, "child clicked", Toast.LENGTH_SHORT).show();
                if(expandableListView.isGroupExpanded(i))
                {

                    // Do your Staff
                }
                else{

                    // Expanded ,Do your Staff

                }

                return false;
            }
        });


    //END OF EXPANDABLE LISTVIEW//


        rt_Arrow = (ImageView) findViewById(R.id.arrow);
        img_home = (ImageView) findViewById(R.id.image_home);
        int defaultposition=1;
        Adapter ad = new Adapter(messages, Dashboard.this);
        final ListView list = (ListView) findViewById(R.id.listv);
        list.setAdapter(ad);

      //  ArrayAdapter<String> adapter = new ArrayAdapter<String>(Dashboard.this, R.layout.listing, messages);
      //  ListView list = (ListView) findViewById(R.id.listv);
      //  list.setAdapter(adapter);


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        rt_Arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        img_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Dashboard.this, "clicked home", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Dashboard.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        //code starts for expandable listview//


        try {

            JSONObject jo = new JSONObject(loadJSONFromAsset());
            JSONArray JA = jo.getJSONArray("modules");
            for (int i = 0; i < JA.length(); i++) {
                JSONObject jop = JA.getJSONObject(i);
                 icon=jop.getString("icon");
                 messages.add(icon);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }







     list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


                Toast.makeText(Dashboard.this, "TEST List View", Toast.LENGTH_SHORT).show();
                Log.d("Position ", "" + position);
                selectedposition = position;
                for (int i = 0; i < list.getChildCount(); i++) {
                    if (position == i) {
                        list.getChildAt(i).setBackgroundResource(R.color.blue);
                    } else {
                        list.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
                    }
                }
                // Intent intent=new Intent(Dashboard.this,ExpandableListview.class);
                //  intent.putExtra("pos", position); // Pass Id
                // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                // startActivity(intent);

                //selectedposition=position;

                if (selectedposition < listDataHeader.size()) {
                    Toast.makeText(Dashboard.this, "position0", Toast.LENGTH_SHORT).show();
                    listAdapter = new smarthealthanalytics.com.smarthealthanalytics.adapter.ExpandableListAdapter(Dashboard.this, listDataHeader.get(selectedposition), listDataChild);
                    expListView.setAdapter(listAdapter);
                    header_title.setText(listTitles.get(selectedposition));
                    // String getnotify= listDataHeader.get(0);
                    // Log.d("getnotify",getnotify);

                } else {
                    Toast.makeText(Dashboard.this, "no position has been selected", Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    private void preparedlist() {

        try {
            // listDataHeader = new ArrayList<String>();
            //  listDataChild = new HashMap<String, List<String>>();
            JSONObject jo = new JSONObject(loadJSONFromAsset());
            JSONArray JA = jo.getJSONArray("modules");
            Log.d("LENGTH", String.valueOf(JA.length()));


            for (int i = 0; i < JA.length(); i++) {
                JSONObject jop = JA.getJSONObject(i);
                String title = jop.getString("title");
                Log.d("title", title);
                listTitles.add(title);
                JSONArray jac = jop.getJSONArray("items");

                Log.d("arraycount", String.valueOf(jac.length()));

                // Initialise subitemarray

                //String[] subitemarray={};
                //  String[] subitemarray = new String[]{};
                // List<String> subitemarray;
                ArrayList<String> subitemarray= new ArrayList<String>();
                for (int j = 0; j < jac.length(); j++) {
                    JSONObject jc = jac.getJSONObject(j);
                    String subtitle = jc.getString("title");


                    // listDataHeader.add(subtitle);
                    Log.d("subtitle",subtitle);

                    // add title to subitemarray

                    subitemarray.add(subtitle);

                    ArrayList<String> finalsubitemarray= new ArrayList<String>();

                    List<String> pSubItemArrayList = new ArrayList<String>();
                    if(jc.has("items"))
                    {
                        JSONArray finalnodearr=jc.getJSONArray("items");

                        for (int k = 0; k < finalnodearr.length(); k++)
                        {
                            String finalstring=finalnodearr.getString(k);
                            Log.d("finalnode",finalstring);
                            //listDataChild.put(listDataHeader.get(0), listDataHeader);
                            finalsubitemarray.add(finalstring);
                            Log.d("finalsubitemarray", String.valueOf(finalsubitemarray));
                        }
                    }
                    //pSubItemArrayList.add(String.valueOf(jop.getJSONArray("items")));
                    listDataChild.put(subtitle, finalsubitemarray);
                    // listDataHeader.add(jc.getString("title"));
                //  listDataChild.add(finalsubitemarray);
                    // Log.d("subitems", String.valueOf(fjjdf));*/

                    Log.d("listDataChild", String.valueOf(finalsubitemarray.size()));
                    //  listDataChild.put(listDataHeader.get(0), finalsubitemarray); // Header, Child data
                }

                // add subitemarray to listDataHeader

                listDataHeader.add(subitemarray);


                Log.d("subitemarray", String.valueOf(subitemarray));



            }

            int getsize= listDataHeader.size();
            Log.d("getsize", String.valueOf(getsize));

            // adapter = new myExpandableAdapter(this, listDataChild, listDataHeader, expListView);
            // expListView.setAdapter(adapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getBaseContext().getAssets().open("accordion_menu.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
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
        getMenuInflater().inflate(R.menu.main, menu);
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

