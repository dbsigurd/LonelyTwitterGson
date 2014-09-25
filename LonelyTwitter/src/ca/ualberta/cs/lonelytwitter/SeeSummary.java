package ca.ualberta.cs.lonelytwitter;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class SeeSummary extends Activity
{
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_see_summary);
		TextView avg = (TextView) findViewById(R.id.averageText);
		Intent intent = getIntent();
		long numberTweets = intent.getLongExtra(LonelyTwitterActivity.EXTRA_NUMBER,0);
		double averageTweets = intent.getLongExtra(LonelyTwitterActivity.EXTRA_MESSAGE, 0);
		TextView num = (TextView) findViewById(R.id.number);
		avg.setText(String.valueOf(averageTweets));
		num.setText(String.valueOf(numberTweets));
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.see_summary, menu);
		return true;
	}

}
