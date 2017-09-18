package com.example.reproduceMe;

import java.util.ArrayList;


import android.net.Uri;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.view.Menu;
import android.view.Window;
import android.widget.ListView;

public class Player extends ListActivity {
	
	public ArrayList<Song> songList = new ArrayList<Song>();
	private SongsAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_player);
		
		searchSongs();
		
		initPlayer();				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.player, menu);
		return true;
	}
	
	private void searchSongs(){
		
		//Content resolver to read media files from phone
		ContentResolver contentResolver = getContentResolver();
		Uri uri = android.provider.MediaStore.Audio.Media.INTERNAL_CONTENT_URI ;
		Cursor cursor = contentResolver.query(uri, null, null, null, null);
		if (cursor == null) {
		  // query failed, handle error.
		} else if (!cursor.moveToFirst()) {
		  // no media on the device
		} else {
		    int titleColumn = cursor.getColumnIndex(android.provider.MediaStore.Audio.Media.TITLE);
		    int idColumn = cursor.getColumnIndex(android.provider.MediaStore.Audio.Media._ID);
		    int authorColumn = cursor.getColumnIndex(android.provider.MediaStore.Audio.Media.ARTIST );
		    do {
		       long thisId = cursor.getLong(idColumn);
		       String thisTitle = cursor.getString(titleColumn);
		       String thisArtist = cursor.getString(authorColumn);
		       // creating the song and adding it to array
		       songList.add(new Song(thisArtist, thisTitle));
		       
		    } while (cursor.moveToNext());
		}
		
		cursor.close();
		
	}
	
	private void initPlayer(){
		ListView songListView = (ListView) findViewById(android.R.id.list);
		adapter = new SongsAdapter(this, songList);
		songListView.setAdapter(adapter);
		
	}

}
