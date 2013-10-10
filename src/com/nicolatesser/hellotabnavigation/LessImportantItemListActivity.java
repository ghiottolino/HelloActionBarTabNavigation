package com.nicolatesser.hellotabnavigation;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.nicolatesser.hellonavigation.R;

/**
 * An activity representing a list of LessImportantItems. This activity has
 * different presentations for handset and tablet-size devices. On handsets, the
 * activity presents a list of items, which when touched, lead to a
 * {@link LessImportantItemDetailActivity} representing item details. On
 * tablets, the activity presents the list of items and item details
 * side-by-side using two vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link LessImportantItemListFragment} and the item details (if present) is a
 * {@link LessImportantItemDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link LessImportantItemListFragment.Callbacks} interface to listen for item
 * selections.
 */
public class LessImportantItemListActivity extends FragmentActivity implements
		LessImportantItemListFragment.Callbacks, ActionBar.TabListener {

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lessimportantitem_list);

		// Set up the action bar to show tabs.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// for each of the sections in the app, add a tab to the action bar.
		actionBar.addTab(actionBar.newTab().setText("Items")
						.setTabListener(this), false);
		actionBar.addTab(actionBar.newTab().setText("Less Important Items")
				.setTabListener(this), false);
		actionBar.setDisplayShowTitleEnabled(false);

		actionBar.setSelectedNavigationItem(1);

		if (findViewById(R.id.lessimportantitem_detail_container) != null) {
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;

			// In two-pane mode, list items should be given the
			// 'activated' state when touched.
			((LessImportantItemListFragment) getSupportFragmentManager()
					.findFragmentById(R.id.lessimportantitem_list))
					.setActivateOnItemClick(true);
		}

		// TODO: If exposing deep links into your app, handle intents here.
	}

	@Override
	protected void onResume() {
		super.onResume();
		final ActionBar actionBar = getActionBar();
		actionBar.setSelectedNavigationItem(1);
	}

	/**
	 * Callback method from {@link LessImportantItemListFragment.Callbacks}
	 * indicating that the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(String id) {
		if (mTwoPane) {
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			Bundle arguments = new Bundle();
			arguments
					.putString(LessImportantItemDetailFragment.ARG_ITEM_ID, id);
			LessImportantItemDetailFragment fragment = new LessImportantItemDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.lessimportantitem_detail_container, fragment)
					.commit();

		} else {
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			Intent detailIntent = new Intent(this,
					LessImportantItemDetailActivity.class);
			detailIntent.putExtra(LessImportantItemDetailFragment.ARG_ITEM_ID,
					id);
			startActivity(detailIntent);
		}
	}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction arg1) {
		if (tab.getText().equals("Items")) {
			Intent detailIntent = new Intent(this,
 ItemListActivity.class);
			startActivity(detailIntent);
		}
		// else if (tab.getText().equals("Less Important Items")) {
		// Intent detailIntent = new Intent(this, ItemListActivity.class);
		// startActivity(detailIntent);
		// }
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub

	}

}
