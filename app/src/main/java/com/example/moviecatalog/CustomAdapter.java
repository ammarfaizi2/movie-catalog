package com.example.moviecatalog;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

public class CustomAdapter extends BaseAdapter {
    Context context;
    List<String[]> listData;
    List<Integer> pics;
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, List<String[]> listData, List<Integer> pics) {
        this.listData = listData;
        this.pics = pics;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.activity_listview, null);
        TextView title = view.findViewById(R.id.movieTitle);
        TextView overview = view.findViewById(R.id.movieOverview);
        TextView releaseDate = view.findViewById(R.id.movieReleaseDate);
        ImageView pic = view.findViewById(R.id.moviePic);
        title.setText(listData.get(i)[0]);
        overview.setText(listData.get(i)[1]);
        releaseDate.setText(listData.get(i)[2]);
        new DownloadImageTask(pic).execute(listData.get(i)[3]);
        return view;
    }
}

class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}