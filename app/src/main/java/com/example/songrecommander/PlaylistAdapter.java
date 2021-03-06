package com.example.songrecommander;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.songrecommander.R;
import com.example.songrecommander.SongData;

import java.util.List;

public class PlaylistAdapter extends ArrayAdapter<SongData> {
    private Activity context;
    private List<SongData> songDataList;
    public PlaylistAdapter(Activity context,List<SongData> songDataList)
    {
        super(context,R.layout.playlist_layout,songDataList);
        this.context = context;
        this.songDataList = songDataList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.playlist_layout,null,true);
        TextView title = listViewItem.findViewById(R.id.title);
        TextView artist = listViewItem.findViewById(R.id.artist);
        LinearLayout layout = listViewItem.findViewById(R.id.list_item);
        TextView mood_info = listViewItem.findViewById(R.id.mood_info);
        TextView song_id = listViewItem.findViewById(R.id.song_id);
        SongData song  = songDataList.get(position);
        title.setText(song.getTitle());

        artist.setText(song.getA_name());
        song_id.setText(song.getSpot_id());
        mood_info.setText(song.getMood_info());
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Playing "+song.getTitle(),Toast.LENGTH_SHORT).show();
                Intent spotifyIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("spotify:track:"+song_id.getText().toString()+ ":play"));
                context.startActivity(spotifyIntent);
            }
        });
        return listViewItem;
    }
}
