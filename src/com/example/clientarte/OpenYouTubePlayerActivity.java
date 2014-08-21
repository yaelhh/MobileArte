package com.example.clientarte;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class OpenYouTubePlayerActivity extends Activity{
	//private String SrcPath= "https://www.youtube.com/watch?v=0uk5u_x_U0Y";// = "rtsp://v5.cache1.c.youtube.com/CjYLENy73wIaLQnhycnrJQ8qmRMYESARFEIJbXYtZ29vZ2xlSARSBXdhdGNoYPj_hYjnq6uUTQw=/0/0/0/video.3gp";
	//String SrcPath = "rtsp://v5.cache1.c.youtube.com/CjYLENy73wIaLQnhycnrJQ8qmRMYESARFEIJbXYtZ29vZ2xlSARSBXdhdGNoYPj_hYjnq6uUTQw=/0/0/0/video.3gp";
	
	private String titulo;
	/** Called when the activity is first created. */
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_abrirvideo);
		
		//Intent intent = getIntent();
		//titulo= intent.getStringExtra("dato");
		
		//titulo = getIntent().getStringExtra("dato");
		titulo = getIntent().getDataString();
		VideoView myVideoView = (VideoView)findViewById(R.id.myvideoview);
		myVideoView.setVideoURI(Uri.parse(titulo));
		//myVideoView.setVideoPath(titulo);
		myVideoView.setMediaController(new MediaController(this));
		myVideoView.requestFocus();
		myVideoView.start();
		
		


	}

}
