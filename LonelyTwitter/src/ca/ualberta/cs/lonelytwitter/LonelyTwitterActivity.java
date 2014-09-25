package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;
import java.util.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import ca.ualberta.cs.lonelytwitter.data.GsonDataManager;
import ca.ualberta.cs.lonelytwitter.data.IDataManager;

public class LonelyTwitterActivity extends Activity {
	public final static String EXTRA_MESSAGE = "Pass average";
	public final static String EXTRA_NUMBER = "Pass number";
	private IDataManager dataManager;
	//public final static String EXTRA_MESSAGE = "com.example.dbsigurd_mytodolist";
	private EditText bodyText;

	private ListView oldTweetsList;

	private ArrayList<Tweet> tweets;

	private ArrayAdapter<Tweet> tweetsViewAdapter;
	private Summary mySummary;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		dataManager = new GsonDataManager(this);
		mySummary = new Summary();
		
		bodyText = (EditText) findViewById(R.id.body);
		oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);
	}

	@Override
	protected void onStart() {
		super.onStart();

		tweets = dataManager.loadTweets();
		tweetsViewAdapter = new ArrayAdapter<Tweet>(this,
				R.layout.list_item, tweets);
		oldTweetsList.setAdapter(tweetsViewAdapter);
	}

	public void save(View v) {

		String text = bodyText.getText().toString();

		Tweet tweet = new Tweet(new Date(), text);
		tweets.add(tweet);

		tweetsViewAdapter.notifyDataSetChanged();

		bodyText.setText("");
		dataManager.saveTweets(tweets);
	}
	public void seeSummary(View v){
		
		Intent intent = new Intent(this,SeeSummary.class);
		long leng = getAverageLength();
		long num = getNumber();
		intent.putExtra(EXTRA_MESSAGE, leng);
		intent.putExtra(EXTRA_NUMBER, num);
		startActivity(intent);
		
	}
	
	
	public void createSummary(){
		mySummary.setAvglength(getAverageLength());
		mySummary.setAvgNumTweet(getNumber());
	}
	private long getNumber(){
		long add= tweets.size();
		long num = 0;
		num = add+num;
		mySummary.setAvgNumTweet(num);
		return num;
	
	}
	private long getAverageLength(){
		long tweetSum =0;
		for (int i=0; i<tweets.size();i++){
			tweetSum= tweetSum + tweets.get(i).getTweetBody().length();
		}
		
		tweetSum= tweetSum/tweets.size();//tweet sum is now average
		mySummary.setAvglength(tweetSum);
		return tweetSum;
	}
	public void clear(View v) {

		tweets.clear();
		tweetsViewAdapter.notifyDataSetChanged();
		dataManager.saveTweets(tweets);
	}
}