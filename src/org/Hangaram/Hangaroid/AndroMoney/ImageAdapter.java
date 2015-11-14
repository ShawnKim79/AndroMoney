package org.Hangaram.Hangaroid.AndroMoney;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private int thumbWidth;
    private int thumbHeight;

    public ImageAdapter(Context c, Integer[] imageIds, int thumbWidth, int thumbHeight ) {
        mContext = c;
        mThumbIds = imageIds;
        this.thumbWidth = thumbWidth;
        this.thumbHeight = thumbHeight;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }
    
    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams( new GridView.LayoutParams( thumbWidth, thumbHeight ) );
            imageView.setScaleType( ImageView.ScaleType.CENTER_CROP );
            imageView.setPadding(0, 0, 0, 0);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.airplane_tourism, R.drawable.anniversary,
            R.drawable.bank, R.drawable.carrepair,
            R.drawable.drugs, R.drawable.fastfood
    };
}
