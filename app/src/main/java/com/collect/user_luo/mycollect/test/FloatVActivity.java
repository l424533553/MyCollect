package com.collect.user_luo.mycollect.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.collect.user_luo.mycollect.R;

public class FloatVActivity extends Activity implements OnClickListener
{

	operatePanel op;
	private Button show, hide;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main22);

		buidView();
	}

	private void buidView()
	{
		Log.d("lq", "FloatVActivity buidView");
		show = (Button) findViewById(R.id.show);
		hide = (Button) findViewById(R.id.hide);


		op = new operatePanel(this);

		op.setOnClickListener(this);

		show.setOnClickListener(new ButtonClick());
		hide.setOnClickListener(new ButtonClick());	
	}

	class ButtonClick implements OnClickListener
	{
		@Override
		public void onClick(View v)
		{
			Log.d("lq", "FloatVActivity onClick v:" + v);
			if (v.getId() == R.id.show){
				op.show();
			} else if (v.getId() == R.id.hide){
				op.hide();
			}

		}

	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu)
//	{
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.activity_main, menu);
//		return true;
//	}

	@Override
	public void onClick(View v)
	{
		Log.d("lq", "FloatVActivity onClick v:" + v);
		Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show();
	}

}
