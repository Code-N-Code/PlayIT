package com.codencode.playit;

public class SongInfo {
    private String songName;
    private String artistName;
    private String songURL;
    private boolean playing;

    SongInfo()
    {

    }

    SongInfo(String songName , String artistName , String songURL)
    {
        this.songName = songName;
        this.artistName = artistName;
        this.songURL = songURL;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getSongURL() {
        return songURL;
    }

    public void setSongURL(String songURL) {
        this.songURL = songURL;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }
}
