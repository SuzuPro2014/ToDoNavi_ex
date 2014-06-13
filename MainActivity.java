package com.google.bmi;

import java.math.BigDecimal;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void BMI_Cal (View view) {

		EditText Tall = (EditText) findViewById(R.id.et_Tall);

	    EditText Wait = (EditText) findViewById(R.id.et_Wait);

	    TextView r_BMI = (TextView) findViewById(R.id.tv_BMI);
	    TextView r_State = (TextView) findViewById(R.id.tv_State);
	    TextView r_Ideal = (TextView) findViewById(R.id.tv_Ideal);

	    double tall = Double.valueOf(Tall.getText().toString()) / 100;
	    double wait =  Double.valueOf(Wait.getText().toString());

	    double bmi = wait / (tall * tall);
	    double h_wait = tall * tall * 22;

	    BigDecimal c = new BigDecimal(String.valueOf(bmi));
	    bmi = c.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

	    c = new BigDecimal(String.valueOf(h_wait));
	    h_wait = c.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

	    String r_bmi = Double.toString(bmi);

	    String r_wait = Double.toString(h_wait);

	    String text = "";

	    if (bmi >= 30.0) {
	    	text = "高度肥満";
	    } else if (bmi >= 25.0) {
	    	text = "肥満";
	    } else if (bmi >= 18.5) {
	    	text = "標準";
	    } else {
	    	text = "やせ";
	    }

	    r_BMI.setText("BMI指数：" + r_bmi);
	    r_State.setText("肥満状態：" + text);
	    r_Ideal.setText("理想体重：" + r_wait);

	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
