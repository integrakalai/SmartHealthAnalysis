package smarthealthanalytics.com.smarthealthanalytics;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ExpandableListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import smarthealthanalytics.com.smarthealthanalytics.adapter.ExpandableListAdapter;

public class ExpandableListview extends Activity {
    //Expandable listview//

   /* ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    JSONObject obj, subObj;
    JSONArray subcatarray, jarray;
    private myExpandableAdapter adapter;*/




    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    // String[][] listDataHeader = new String[][]{};
    List<String> listTitles;
    List<String> add;
    ArrayList<ArrayList<String>> listDataHeader = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>> infoheader = new ArrayList<ArrayList<String>>();
    String itemclicked,header;
    HashMap<String, List<String>> listDataChild;
    int position;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expandablelistview);
        int position = getIntent().getExtras().getInt("pos");
        Log.d("possecondactivity", String.valueOf(position));
       // listDataHeader = new ArrayList<String>();
        listTitles = new ArrayList<String>();
        add = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        preparedlist();

      /*  ArrayList<String> tempdataheader=new ArrayList<String>();
      ArrayList<String> tempstringdata= listDataHeader.get(0);
       for(int l=0;l<tempstringdata.size();l++){
          tempdataheader.add(tempstringdata.get(l));
      }*/

       // ArrayList<String> tempstringdata= listDataHeader.get(0);
      //  ArrayList<String> tempstringdata2= listDataHeader.get(4);
      //  infoheader.add(tempstringdata)
        expListView = (ExpandableListView) findViewById(R.id.simple_expandable_listview);


        // setting list adapter

        expListView.setDividerHeight(2);
        expListView.setGroupIndicator(null);
        expListView.setClickable(true);



        if(position==0){
            Toast.makeText(this, "position0", Toast.LENGTH_SHORT).show();


           listAdapter = new ExpandableListAdapter(this,listDataHeader.get(0), listDataChild);
            expListView.setAdapter(listAdapter);
           // String getnotify= listDataHeader.get(0);
           // Log.d("getnotify",getnotify);

        }else if(position==1){
            Toast.makeText(this, "position1", Toast.LENGTH_SHORT).show();
            listAdapter = new ExpandableListAdapter(this,listDataHeader.get(1), listDataChild);
            expListView.setAdapter(listAdapter);

        }else if(position==2){
            Toast.makeText(this, "position2", Toast.LENGTH_SHORT).show();
            listAdapter = new ExpandableListAdapter(this,listDataHeader.get(2), listDataChild);
            expListView.setAdapter(listAdapter);

        }else if(position==3){
            Toast.makeText(this, "position3", Toast.LENGTH_SHORT).show();
            listAdapter = new ExpandableListAdapter(this,listDataHeader.get(3), listDataChild);
            expListView.setAdapter(listAdapter);
            //listAdapter = new ExpandableListAdapter(this,listDataHeader.get(0), listDataChild);

        }else if(position==4){
            Toast.makeText(this, "position4", Toast.LENGTH_SHORT).show();
            listAdapter = new ExpandableListAdapter(this,listDataHeader.get(4), listDataChild);
            expListView.setAdapter(listAdapter);

        }else{
            Toast.makeText(this, "no position has been selected", Toast.LENGTH_SHORT).show();

        }



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
                   //listDataChild.put(listDataHeader.get(i), pSubItemArrayList);
                    // listDataHeader.add(jc.getString("title"));

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
    }

