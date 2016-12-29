package com.example.android.miwok;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mike on 8/19/2016.
 */
public class WordAdapter extends ArrayAdapter<Word> {
    int mColorResourceId;
    public WordAdapter(Activity context, ArrayList<Word> words, int colorResourceId){
        // Here, we initialize the ArrayAdapters internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, words);
        mColorResourceId = colorResourceId;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        Word currentWord = getItem(position);

        // Find the ImageView in the list_item.xml layout with the ID icon_Image_View
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

        // Set the image view for the play button on each slide in the list view
        ImageView playBtn = (ImageView) listItemView.findViewById(R.id.playBtn);
        playBtn.setImageResource(R.drawable.ic_play_arrow_white_24dp);

        if(currentWord.hasImage()) {
            // Set this image on the list_item view
            imageView.setImageResource(currentWord.getImageResourceId());
            // Set visibility of ImageView to VISIBLE if image exists
            imageView.setVisibility(View.VISIBLE);
        } else {
            // Set visibility of ImageView to GONE if no Image
            imageView.setVisibility(View.GONE);
        }

        // Set Theme color for the list item
        View textContainer = listItemView.findViewById(R.id.text_container);
        // Find the color that Resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        // Set the background color of the text container
        textContainer.setBackgroundColor(color);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        // Get the version name from the current AndroidFlavor object and set this text on the vNameTextView
        miwokTextView.setText(currentWord.getMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        // Get the version number from the current AndroidFlavor object and set this text on the vNameTextView
        defaultTextView.setText(currentWord.getDefaultTranslation());

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        return listItemView;
    }
}
