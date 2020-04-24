package com.TermProject.EbookReader;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.Toast;

public class Author_Tab_Activity extends Activity
{
	private ListAdapterAuthorTab adapter;
	private ArrayList<String> author_name;
	private ArrayList<ArrayList<String>> book_name;
	private ArrayList<String> temp;
	SQLiteDatabase db;
	
	public void onCreate(Bundle savedInstanceState) 
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.author_tab_layout);
        
        db = openOrCreateDatabase("Challenge__EBookBook_Reader", MODE_PRIVATE , null);
        Cursor result = db.rawQuery("Select * from Author_table", null);
        book_name = new ArrayList<ArrayList<String>>();
        
        String a_name;
        int aiD=0; //count = result.getCount(),i=0;
        author_name= new ArrayList<String>();
        temp = new ArrayList<String>();
        
        if( result != null)
        {
        	result.moveToFirst();
        	while( !result.isAfterLast() )
			{
				a_name = result.getString(result.getColumnIndex("Author_name"));
				aiD = result.getInt(result.getColumnIndex("Aid"));
				author_name.add( a_name);
				
				child_item_fill(a_name,aiD);
				result.moveToNext();
			}
        	result.close();
        }
        else
        {
        	author_name.add("No Name Of Author Exists");
        } 
        
        db.close();
        
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.List_for_tab_author); 
        
        listView.setOnChildClickListener(new OnChildClickListener()
        {
            
            public boolean onChildClick(ExpandableListView arg0, View arg1, int arg2, int arg3, long arg4)
            {
                Toast.makeText(getBaseContext(), "Child clicked and its no is "+arg3, Toast.LENGTH_LONG).show();
                return false;
            }
        });
        
        listView.setOnGroupClickListener(new OnGroupClickListener()
        {
            
            public boolean onGroupClick(ExpandableListView arg0, View arg1, int arg2, long arg3)
            {
                Toast.makeText(getBaseContext(), "Group clicked and its no is "+arg2, Toast.LENGTH_LONG).show();
                return false;
            }
        }); 
        
     // Initialize the adapter with blank groups and children
        // We will be adding children on a thread, and then update the ListView
        adapter = new ListAdapterAuthorTab(this, author_name,book_name);

        // Set this blank adapter to the list view
        listView.setAdapter(adapter);

        
        
    }
	private void child_item_fill(String a_name,int aiD) 
	{
		ArrayList<String> temp1 = new ArrayList<String>();
		Cursor result1 = db.rawQuery("Select * from Book_description", null);
		
		if( result1 != null)
        {
			result1.moveToFirst();
			
			while( !result1.isAfterLast() )
			{
				String book_name;
				int AID = result1.getInt(result1.getColumnIndex("Aid"));
				if( AID == aiD )
				{
					book_name = result1.getString(result1.getColumnIndex("Book_name"));
					temp1.add(book_name);
				//	Toast.makeText(this,"The name of author is "+i+" and the name of book is "+book_name, Toast.LENGTH_LONG).show();
				//	adapter.addItem_for_child(i,book_name);
				//	Toast.makeText(this,"The Id of author is "+adapter.addItem(a_name, book_name), Toast.LENGTH_LONG).show();
				}
				 
				result1.moveToNext();
			} 
        	result1.close();
        }
        else
        {
        //	adapter.addItem(a_name, "No book Of this Author is available"); 
        } 
		
		book_name.add(temp1);
		
	}
}