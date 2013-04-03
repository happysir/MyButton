package com.example.myan;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		 JMQButton animButtons=(JMQButton)findViewById(R.id.animButtons);  
	        animButtons.setOnButtonClickListener(new JMQButton.OnButtonClickListener() {  
	              
	            @Override  
	            public void onButtonClick(View v, int id) {  

	            		Log.d("JMQ","id="+id);
	            }  
	        });  
	}

	

}
