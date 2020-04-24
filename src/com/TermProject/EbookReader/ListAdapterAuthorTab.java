
package com.TermProject.EbookReader;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class ListAdapterAuthorTab extends BaseExpandableListAdapter {

    @Override
    public boolean areAllItemsEnabled()
    {
        return true;
    }

    private Context context;

    private ArrayList<String> groups;

    private ArrayList<ArrayList<String>> children;
    
    int counter[];
    
    public ListAdapterAuthorTab(Context context, ArrayList<String> groups,
            ArrayList<ArrayList<String>> children) {
        this.context = context;
        this.groups = groups;
        
        int sze = groups.size();
        this.children = children;
        
        for(int j=1;j<=sze+1;j++)
        	children.add(new ArrayList<String>());
        
       
        counter = new int[3];
        for(int i=0;i<3;i++)
        	counter[i] = 0 ;
        
    }
    public void addItem_for_child(int aiD,String book_name) 
    {
    	children.get(aiD).add(book_name);
    }
    /**
     * A general add method, that allows you to add a Vehicle to this list
     * 
     * Depending on if the category opf the vehicle is present or not,
     * the corresponding item will either be added to an existing group if it 
     * exists, else the group will be created and then the item will be added
     * @param vehicle
     */
    public void addItem(ArrayList<String> item) {
           children.add(new ArrayList<String>());
    }
    
/*    private void show_toast(String book_name) {
		// TODO Auto-generated method stub
    	Toast.makeText(this, " The book name is  "+book_name, Toast.LENGTH_LONG).show();
		
	} */

	public String getChild(int groupPosition, int childPosition) {
        return children.get(groupPosition).get(childPosition);
    }
    
    public String getGroupName(int groupPosition)
    {
    	return groups.get(groupPosition);
    }
    
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    
    // Return a child view. You can load your custom layout here.
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,View convertView, ViewGroup parent) 
    {
        String book_name =  getChild(groupPosition, childPosition);
        if (convertView == null)
        {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.child_layout_author_tab, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.tvChild);
        tv.setText(book_name);

        // Depending upon the child type, set the imageTextView01
    /*    tv.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        if (vehicle_name.equalsIgnoreCase("Car")) {
            tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.car, 0, 0, 0);
        } else if (vehicle_name.equalsIgnoreCase("Truck")) {
            tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.bus, 0, 0, 0);
        } else if (vehicle_name.equalsIgnoreCase("Bi-Cycle")) {
            tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.bike, 0, 0, 0);
        } */
        return convertView;
    } 

    public int getChildrenCount(int groupPosition) {
        return children.get(groupPosition).size();
    }

    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    public int getGroupCount() {
        return groups.size();
    }

    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    // Return a group view. You can load your custom layout here.
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
            ViewGroup parent) {
        String group = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.group_layout_author_tab, null);
        }
        TextView tv = (TextView) convertView.findViewById(R.id.tvGroup);
        tv.setText(group);
        return convertView;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isChildSelectable(int arg0, int arg1) {
        return true;
    }

}
