package com.studiodevelopers.fulito.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.studiodevelopers.fulito.R;

import java.util.List;

/**
 * Created by Ignacio on 10/02/2015.
 */
public abstract  class GenericArrayAdapter<T> extends ArrayAdapter<T> {

    // Vars
    private LayoutInflater mInflater;

    public GenericArrayAdapter(Context context, List<T> objects) {
        super(context, R.layout.row_normal_list, objects);
        init(context);
    }

    // Headers
    public abstract void drawText(TextView textView, T object);
    public abstract void setImagenOption(ImageView imageView, T item);

    private void init(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.row_normal_list, parent, false);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        drawText(vh.textView, getItem(position));
        setImagenOption(vh.imageView,getItem(position));
        return convertView;
    }



    static class ViewHolder {

        TextView textView;
        ImageView imageView;
        private ViewHolder(View rootView) {
            textView = (TextView) rootView.findViewById(R.id.textViewOption);
            imageView=(ImageView) rootView.findViewById(R.id.imageViewIcon);
        }
    }
}