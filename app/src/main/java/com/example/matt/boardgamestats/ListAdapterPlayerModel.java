package com.example.matt.boardgamestats;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Matt on 8/16/2015.
 */

public class ListAdapterPlayerModel extends BaseAdapter {
    ArrayList<PlayerModel> playerDataModel;
    private LayoutInflater inflater;

    @Override
    public long getItemId(int position)
    {

        return position;
    }

    @Override
    public Object getItem(int position)
    {

        return playerDataModel.get(position);
    }

    @Override
    public int getCount()
    {
        if (playerDataModel == null){
            return 0;
        }
        else{
            return playerDataModel.size();
        }

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.player_object, null);
        }
        TextView text1 = (TextView) convertView.findViewById(R.id.playerName);
        text1.setText(playerDataModel.get(position).getName());
        //text2.setText(Integer.toString(dataModel.get(position).getScore()));
        CheckBox cb = (CheckBox) convertView.findViewById(R.id.checkBox1);
        if(playerDataModel.get(position).getIsChecked() == 1)
            cb.setChecked(true);
        else
            cb.setChecked(false);
        return convertView;
    }

    public ListAdapterPlayerModel(Context context, ArrayList<PlayerModel> playerDataList)
    {

        super();
        this.inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.playerDataModel = playerDataList;

    }
}
