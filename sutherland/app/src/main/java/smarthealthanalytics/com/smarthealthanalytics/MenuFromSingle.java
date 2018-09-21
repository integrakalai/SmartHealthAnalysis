package smarthealthanalytics.com.smarthealthanalytics;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;



public class MenuFromSingle extends Activity {

    String string;
    Context context;
    Map<Integer,MenuItem> mainMenu = new TreeMap<>();
    Context myContext;

    public MenuFromSingle(String string){
        this.string = string;

        try {
            JSONObject jsonObject = new JSONObject(string);
            JSONArray menuArray = jsonObject.getJSONArray("modules");
            if(menuArray!=null){
            for (int i=0; i< menuArray.length();i++) {
                JSONObject mainObj = (JSONObject) menuArray.get(i);
                mainMenu.put(mainObj.getInt("order"), new MenuItem(mainObj.getString("title"), mainObj.getString("icon"), mainObj.getInt("order"), mainObj.get("items")));
                Log.d("fdgfgfgfg", String.valueOf(menuArray.length()));
                // Toast.makeText(g, menuArray.length(), Toast.LENGTH_SHORT).show();
            }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public  MenuItem getMainMenuItem(int key){
        return  mainMenu.get(key);
    }

    public List<DummyContent.DummyItem> getMainMenu(){
        List<DummyContent.DummyItem> ITEMS = new ArrayList<DummyContent.DummyItem>();
        for(Map.Entry<Integer,MenuItem> menu : mainMenu.entrySet())
        {
                ITEMS.add(new DummyContent.DummyItem(Integer.toString(menu.getKey()),((MenuItem)menu.getValue()).title));

        }

        return ITEMS;
    }





}
