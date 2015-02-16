package com.studiodevelopers.fulito.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.studiodevelopers.fulito.R;
import com.studiodevelopers.fulito.model.Equipo;

import java.util.List;

/**
 * Created by Ignacio on 14/02/2015.
 */
public class EquipoAdapter extends RecyclerView.Adapter<EquipoAdapter.ViewHolder> {
    private List<Equipo> mEquipos;
    DisplayImageOptions options;
    Context context;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ImageView mImageView;
        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView)view.findViewById(R.id.textView);
            mImageView=(ImageView)view.findViewById(R.id.imageView);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public EquipoAdapter(List<Equipo> mEquipos,Context context) {
        this.mEquipos = mEquipos;
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .displayer(new RoundedBitmapDisplayer(20))
                .build();
        this.context=context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public EquipoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_equipo, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mEquipos.get(position).getNameEquipo());
        //ImageLoader imageLoader=;

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).defaultDisplayImageOptions(options). build();

        ImageLoader.getInstance().init(config);

        ImageSize targetSize = new ImageSize(50, 50);



        ImageLoader.getInstance().displayImage(mEquipos.get(position).getImagenEquipo(), holder.mImageView, options, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                System.out.println("start "+imageUri);
            }
            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                System.out.println(failReason.getCause());
            }
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                System.out.println("ok");
                //holder.mImageView.setImageBitmap(loadedImage);

                int width = loadedImage.getWidth();
                int height = loadedImage.getHeight();
                int newWidth = 50;
                int newHeight = 50;

                // calculate the scale - in this case = 0.4f
                float scaleWidth = ((float) newWidth) / width;
                float scaleHeight = ((float) newHeight) / height;

                // createa matrix for the manipulation
                Matrix matrix = new Matrix();
                // resize the bit map
                matrix.postScale(scaleWidth, scaleHeight);

                // recreate the new Bitmap
                Bitmap resizedBitmap = Bitmap.createBitmap(loadedImage, 0, 0,
                        width, height, matrix, true);
                holder.mImageView.setImageBitmap(resizedBitmap);

            }
            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                System.out.println("cancelado");
            }
        }, new ImageLoadingProgressListener() {
            @Override
            public void onProgressUpdate(String imageUri, View view, int current, int total) {
                System.out.println("progress...");
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mEquipos.size();
    }
}
