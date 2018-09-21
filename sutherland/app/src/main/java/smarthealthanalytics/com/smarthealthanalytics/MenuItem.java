package smarthealthanalytics.com.smarthealthanalytics;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.TreeMap;

public class MenuItem {

    String title;
    String icon;
    Object object;
    public MenuItem(String title, String icon, int order, Object object){
        this.title = title;
        this.icon = icon;
        if (object instanceof JSONArray){
            Map<Integer,Object> map = new TreeMap<>();
            JSONArray jsonArayy = (JSONArray) object;
            for(int i =0 ; i < jsonArayy.length();i++){

                try {
                    JSONObject jsonObject =  jsonArayy.getJSONObject(i);
                    MenuChildItem childItem;
                    if(jsonObject.has("items")){
                       // Log.v("checkObject","hasObject");
                        childItem = new MenuChildItem(jsonObject.getString("title"),jsonObject.get("items"));
                    }else{
                        childItem = new MenuChildItem(jsonObject.getString("title"),null);
                    }
                    map.put(jsonObject.getInt("order"), childItem);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
            this.object = map;

        }else {
            this.object = object;
        }

    }
    public String gettitle(){
        return  title;
    }
    public String geticon(){
        return  icon;
    }
    public Object getobject(){
        return  object;
    }



}
