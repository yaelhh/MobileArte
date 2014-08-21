package com.example.clientarte;

import java.io.IOException;

import com.keyes.youtube.YouTubeId;
import com.keyes.youtube.YouTubeUtility;

import dominio.Obra;
import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

//public class VideoActivity extends Activity implements OnErrorListener, OnPreparedListener {
//	 
//String SrcPath = "rtsp://v5.cache1.c.youtube.com/CjYLENy73wIaLQnhycnrJQ8qmRMYESARFEIJbXYtZ29vZ2xlSARSBXdhdGNoYPj_hYjnq6uUTQw=/0/0/0/video.3gp";
////String SrcPath = "https://www.youtube.com/watch?v=LXtPklmWcZA";
// 
//   /** Called when the activity is first created. */
//   @Override
//   public void onCreate(Bundle savedInstanceState) {
//       super.onCreate(savedInstanceState);
//       setContentView(R.layout.activity_video);
//       VideoView myVideoView = (VideoView)findViewById(R.id.myvideoview);
//      // myVideoView.setVideoURI(Uri.parse(SrcPath));
//       myVideoView.setVideoPath(SrcPath);
//       myVideoView.setMediaController(new MediaController(this));
//       myVideoView.requestFocus();
//       myVideoView.start();
//
//
//   }
//
//@Override
//public void onPrepared(MediaPlayer mp) {
//	// TODO Auto-generated method stub
//	
//}
//
//@Override
//public boolean onError(MediaPlayer mp, int what, int extra) {
//	// TODO Auto-generated method stub
//	return false;
//}
//}


public class VideoActivity extends Activity  {
	
	TextView videoIdTextView = null;
	Button viewVideoButton = null;
	private Obra obra;
	
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.activity_video);
		 
		 videoIdTextView = (TextView)findViewById(R.id.youtubeIdText);
		 viewVideoButton = (Button)findViewById(R.id.viewVideoButton);
		
		obra= new Obra();
		obra= getIntent().getParcelableExtra("obra");
	//	 viewVideoButton.setOnClickListener(new View.OnClickListener() {
			
			
//			@Override
//			public void onClick(View v) {
				String videoId = obra.getNombre().toString();
				//String videoId = videoIdTextView.getText().toString();
				
				if (videoId == null || videoId.trim().equals("")){
					return;
				}else{
					startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/results?search_query="+videoId.trim())));
				    Log.i("Video", "Video Playing....");
				}
				
				
				 
				    //startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.youtube.com/watch?v=lqkprg6Mi6I")));
				

				    
				 
				
				//Intent lVideoIntent = new Intent (null, Uri.parse ("ytv://"+videoId), VideoActivity.this, OpenYouTubePlayerActivity.class);
				//Intent lVideoIntent = new Intent (null, Uri.parse ("ytv://"+videoId), VideoActivity.this, OpenYouTubePlayerActivity.class);
				//startActivity(lVideoIntent);
				
//				Intent lVideoIntent = new Intent (null, Uri.parse ("http://www.youtube.com/watch?v="+videoId+".3gp"), VideoActivity.this, OpenYouTubePlayerActivity.class);
//				lVideoIntent.putExtra("dato",("http://www.youtube.com/watch?v="+videoId+".3gp"));
				
				
				
//				Intent lVideoIntent = new Intent (null, Uri.parse ("https://www.youtube.com/results?search_query="+videoId), VideoActivity.this, OpenYouTubePlayerActivity.class);
//				lVideoIntent.putExtra("dato",("https://www.youtube.com/results?search_query="+videoId));
				
				//Intent enviar = new Intent (VideoActivity.this, OpenYouTubePlayerActivity.class);
				//enviar.putExtra ("dato", Uri.parse ("ytv://"+videoId));
//				startActivity(lVideoIntent);
				
				
//				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(videoId));
//				Intent intent
//				intent.setDataAndType(Uri.parse(videoId), "video/3gp");
//				startActivity(intent);
				
//		 }
//	 });
	 }
	 
	
	
}

