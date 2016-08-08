package com.jjws.testanim;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.zbar.lib.CaptureActivity;

public class ScanResultActivity extends Activity implements OnClickListener{
	private ImageView iv_title_back;
	private TextView scan_result_xs;
	private String result = "sk test";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_scan_result);
		
		iv_title_back = (ImageView)findViewById(R.id.iv_title_back1);
		iv_title_back.setOnClickListener(this);
		scan_result_xs = (TextView) findViewById(R.id.scan_result_xs1);
		Intent it = getIntent();
		if(it != null) {
			Bundle extras = it.getExtras();
			if (extras != null) {
				result = extras.getString("result");
			}
		}
		initDate();
	}

	private void initDate() {
		// TODO Auto-generated method stub
		scan_result_xs.setText(result);
	}

	@Override
	public void onClick(View v) {
      switch (v.getId()) {
	case R.id.iv_title_back1:
		Intent intent = new Intent(ScanResultActivity.this,CaptureActivity.class);
		startActivity(intent);
		break;

	default:
		break;
	}		
	}
	
	
	
	
	
	
	
	
	
}
