package com.codencode.playit;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {

    ArrayList<SongInfo> songs;
    Context context;
    MediaPlayer mediaPlayer;
    ViewHolder playingSongHolder;
    int playingSongIndex;
    SongAdapter(Context context , ArrayList<SongInfo> song , MediaPlayer mediaPlayer)
    {
        this.context = context;
        this.songs = song;
        this.mediaPlayer = mediaPlayer;
        playingSongIndex = -1;
        playingSongHolder = null;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View songView = LayoutInflater.from(context).inflate(R.layout.song_layout , parent , false);

        return new ViewHolder(songView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final SongInfo singleSong = songs.get(position);

        holder.songName.setText(singleSong.getSongName());
        holder.artistName.setText(singleSong.getArtistName());

        holder.imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(playingSongIndex != -1 && playingSongIndex != position)
                {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    mediaPlayer.release();
                    mediaPlayer = null;
                    playingSongHolder.imgButton.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
                    songs.get(playingSongIndex).setPlaying(false);
                    playingSongIndex = -1;
                }

                if(playingSongIndex == position)
                {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    mediaPlayer.release();
                    mediaPlayer = null;
                    playingSongHolder.imgButton.setImageResource(R.drawable.ic_play_circle_outline_black_24dp);
                    songs.get(playingSongIndex).setPlaying(false);
                    playingSongIndex = -1;
                }
                else
                {
                    try{
                        playingSongHolder = holder;
                        playingSongIndex = position;
                        holder.imgButton.setImageResource(R.drawable.ic_pause_circle_outline_black_24dp);
                        songs.get(position).setPlaying(true);
                        mediaPlayer = new MediaPlayer();
                        mediaPlayer.setDataSource(singleSong.getSongURL());
                        mediaPlayer.prepareAsync();
                        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                mediaPlayer.start();
                            }
                        });


                    }
                    catch (IOException e)
                    {
                        Toast.makeText(context, ""+e, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView songName ;
        TextView artistName;
        ImageView imgButton;

        public ViewHolder(View itemView) {
            super(itemView);

            songName = itemView.findViewById(R.id.song_name);
            artistName = itemView.findViewById(R.id.artist_name);
            imgButton = itemView.findViewById(R.id.play_pause_button);
        }
    }
}
