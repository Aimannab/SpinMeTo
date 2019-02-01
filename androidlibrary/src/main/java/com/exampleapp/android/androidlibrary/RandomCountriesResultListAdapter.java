package com.exampleapp.android.androidlibrary;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.sqlbrite3.QueryObservable;

/**
 * Created by Aiman Nabeel on 09/11/2018.
 */
public class RandomCountriesResultListAdapter extends RecyclerView.Adapter<RandomCountriesResultListAdapter.RandomCountriesViewHolder> {

    private Context mContext;
    private Cursor mCursor;
    private int mCount;

    public RandomCountriesResultListAdapter(Context context, Cursor cursor) {
        this.mContext = context;
        this.mCursor = cursor;
    }

    @Override
    public RandomCountriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Getting the RecyclerView item layout
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.random_countries_list_item, parent, false);
        return new RandomCountriesViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RandomCountriesViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position))
            return;
        //Calling Favorite Movie names here
        String name = mCursor.getString(mCursor.getColumnIndex(CountriesDBContract.RandomCountriesList.COLUMN_RANDOM_COUNTRY_NAME));
        holder.nameTextView.setText(name);

    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }


    /**
     * Inner class to hold the views needed to display a single item in the recycler-view
     */
    class RandomCountriesViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;

        public RandomCountriesViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.country_name_text_view);
        }

    }

    //Method swapCursor for implemeting in FavoriteListActivity class
    public Cursor swapCursor(Cursor c) {
        // check if this cursor is the same as the previous cursor (mCursor)
        if (mCursor == c) {
            return null; // bc nothing has changed
        }
        Cursor temp = mCursor;
        this.mCursor = c; // new cursor value assigned

        //check if this is a valid cursor, then update the cursor
        if (c != null) {
            this.notifyDataSetChanged();
        }
        return temp;
    }
}
