package com.example.matt.boardgamestats;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Matt on 8/16/2015.
 */

public class ListAdapter extends BaseAdapter {
    ArrayList<GameDataModel> dataModel;
    private LayoutInflater inflater;

    @Override
    public long getItemId(int position)
    {

        return position;
    }

    @Override
    public Object getItem(int position)
    {

        return dataModel.get(position);
    }

    @Override
    public int getCount()
    {
        if (dataModel == null){
            return 0;
        }
        else{
            return dataModel.size();
        }

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.gamedata_object, null);
        }
        TextView text1 = (TextView) convertView.findViewById(R.id.gameName);
        TextView text2 = (TextView) convertView.findViewById(R.id.gameWinner);
        text1.setText(dataModel.get(position).getName());
        text2.setText(dataModel.get(position).getWinner());
        //text2.setText(Integer.toString(dataModel.get(position).getScore()));

        return convertView;
    }

    public ListAdapter(Context context, ArrayList<GameDataModel> gameDataList)
    {

        super();
        this.inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.dataModel = gameDataList;

    }
}
