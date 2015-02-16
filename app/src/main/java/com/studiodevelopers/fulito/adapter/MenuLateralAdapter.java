package com.studiodevelopers.fulito.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.studiodevelopers.fulito.model.GenericItem;

import java.util.List;

/**
 * Created by Ignacio on 13/02/2015.
 */
public class MenuLateralAdapter extends GenericArrayAdapter<GenericItem> {
    private List<GenericItem> listOptions;
    private Context context;
    public MenuLateralAdapter(Context context, List<GenericItem> listOptions) {
        super(context, listOptions);
        this.listOptions=listOptions;
        this.context=context;
    }

    @Override
    public void drawText(TextView textView, GenericItem item) {
        textView.setText(item.getTittle());
    }

    @Override
    public void setImagenOption(ImageView imageView, GenericItem item) {
        imageView.setImageResource(item.getImage());
    }
}
