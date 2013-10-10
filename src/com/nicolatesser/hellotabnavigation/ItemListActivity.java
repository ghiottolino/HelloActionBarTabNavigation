package com.nicolatesser.hellotabnavigation;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.nicolatesser.hellonavigation.R;

/**
 * An activity representing a list of Items. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link ItemDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link ItemListFragment} and the item details (if present) is a
 * {@link ItemDetailFragment}.
 * <p>
 * This activity also implements the required {@link ItemListFragment.Callbacks}
 * interface to listen for item selections.
 */
public class ItemListActivity extends SherlockFragmentActivity implements
		ItemListFragment.Callbacks {

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_list);

		// Set up the action bar to show tabs.
		final ActionBar actionBar = getSupportActionBar();
		actionBar.setCustomView(R.layout.ab_custom);

		// LayoutInflater inflator = (LayoutInflater) this
		// .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// View v = inflator.inflate(R.layout.ab_custom, null);
		// actionBar.setCustomView(v);

		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayUseLogoEnabled(false);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(true);

		findViewById(R.id.btn3).setPressed(true);
		findViewById(R.id.btn4).setPressed(false);
		findViewById(R.id.btn5).setPressed(true);

		//
		// findViewById(R.id.btn1).setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(View view) {
		// Intent activity1 = new Intent(getApplicationContext(),
		// ItemListActivity.class);
		// startActivity(activity1);
		// }
		// });
		//
		// findViewById(R.id.btn2)
		// .setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(View view) {
		// Intent activity2 = new Intent(getApplicationContext(),
		// LessImportantItemListActivity.class);
		// startActivity(activity2);
		// }
		// });

		findViewById(R.id.btn3).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent activity3 = new Intent(getApplicationContext(),
						ItemListActivity.class);
				startActivity(activity3);
			}
		});

		findViewById(R.id.btn4).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent activity4 = new Intent(getApplicationContext(),
						LessImportantItemListActivity.class);
				startActivity(activity4);
			}
		});

		findViewById(R.id.btn5).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent activity5 = new Intent(getApplicationContext(),
						ItemListActivity.class);
				startActivity(activity5);
			}
		});

		// actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		// actionBar.setDisplayShowTitleEnabled(false);
		// actionBar.setDisplayShowCustomEnabled(true);
		// LayoutInflater inflator = (LayoutInflater) this
		// .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// View v = inflator.inflate(R.layout.ab_custom, null);
		// actionBar.setCustomView(v);



		if (findViewById(R.id.item_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;

			// In two-pane mode, list items should be given the
			// 'activated' state when touched.
			((ItemListFragment) getSupportFragmentManager().findFragmentById(
					R.id.item_list)).setActivateOnItemClick(true);
		}

		// TODO: If exposing deep links into your app, handle intents here.
	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	/**
	 * Callback method from {@link ItemListFragment.Callbacks} indicating that
	 * the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(String id) {
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			Bundle arguments = new Bundle();
			arguments.putString(ItemDetailFragment.ARG_ITEM_ID, id);
			ItemDetailFragment fragment = new ItemDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.item_detail_container, fragment).commit();

		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			Intent detailIntent = new Intent(this, ItemDetailActivity.class);
			detailIntent.putExtra(ItemDetailFragment.ARG_ITEM_ID, id);
			startActivity(detailIntent);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// switch (item.getItemId()) {
//		case R.id.btn1:
//			Intent activity1 = new Intent(this, ItemListActivity.class);
//			startActivity(activity1);
//			return true;
//		case R.id.btn2:
//			Intent activity2 = new Intent(this,
//					LessImportantItemListActivity.class);
//			startActivity(activity2);
//			return true;
//		}
		return super.onOptionsItemSelected(item);
	}

}
