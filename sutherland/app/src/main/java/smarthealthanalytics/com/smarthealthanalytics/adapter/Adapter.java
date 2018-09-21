package smarthealthanalytics.com.smarthealthanalytics.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

import smarthealthanalytics.com.smarthealthanalytics.R;

public class Adapter extends BaseAdapter {
    ArrayList<String> messages;
    public Activity context;
    public LayoutInflater inflater;
    Context c;

    public  Adapter(ArrayList<String> messages, Activity context)

    {

        this.messages = messages;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class ViewHolder {
        ImageView image1;

    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        holder = new ViewHolder();
        convertView = inflater.inflate(R.layout.listing, null);
        holder.image1 = (ImageView) convertView.findViewById(R.id.image_seticons);
        String arrayvalue=messages.get(position);

        convertView.setTag(holder);

        holder = (ViewHolder) convertView.getTag();
        Resources res = context.getResources();
        int resID = res.getIdentifier(arrayvalue, "drawable", context.getPackageName());
        Drawable drawable = res.getDrawable(resID );
        holder.image1.setImageDrawable(drawable );





        return convertView;

    }
}