package smarthealthanalytics.com.smarthealthanalytics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class myExpandableAdapter extends BaseExpandableListAdapter {
    private Context context;

    private List<String> listDataHeader;
    private LayoutInflater inflater;
    int lastExpandedGroupPosition = -1;
    private HashMap<String, List<String>> listDataChild;
    ExpandableListView expandableList;
    public myExpandableAdapter(Context context, HashMap<String, List<String>> listDataChild, List<String> listDataHeader,ExpandableListView expandableList) {

        this.context =context;
        this.listDataChild = listDataChild;
        this.expandableList = expandableList;
        this.listDataHeader = listDataHeader;
    }


    @Override

    public void onGroupExpanded(int groupPosition) {

        if (groupPosition != lastExpandedGroupPosition) {
            expandableList.collapseGroup(lastExpandedGroupPosition);
        }
        super.onGroupExpanded(groupPosition);
        lastExpandedGroupPosition = groupPosition;
    }
    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {

        return listDataChild.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition)
    {        return listDataChild.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        View row = convertView;

        CustomHolder holder = null;


        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.parent_items, parent, false);

            holder = new CustomHolder();

            holder.txtParentList = (TextView) row.findViewById(R.id.txtParentList);

            row.setTag(holder);
        } else {
            holder = (CustomHolder) row.getTag();
        }

        String s = listDataHeader.get(groupPosition);
        holder.txtParentList.setText(s);

        return row;
    }

    static class CustomHolder {
        TextView txtParentList;

    }
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        View row = convertView;

        CustomCHolder holderc ;

        if (row == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.child_items, parent, false);

            holderc = new CustomCHolder();
            holderc.txtChildList = (TextView) row.findViewById(R.id.txtChildList);

            row.setTag(holderc);
        } else {
            holderc = (CustomCHolder) row.getTag();
        }

        return row;
    }

    static class CustomCHolder {
        TextView txtChildList;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }}