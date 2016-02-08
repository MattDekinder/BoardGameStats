package com.example.matt.boardgamestats;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapterPlayerScore extends BaseAdapter {
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
            convertView = inflater.inflate(R.layout.player_score_object, null);
        }
        TextView text1 = (TextView) convertView.findViewById(R.id.playerName);
        text1.setText(playerDataModel.get(position).getName());
        EditText score = (EditText) convertView.findViewById(R.id.score);
        score.setText(String.valueOf(playerDataModel.get(position).getScore()));
        return convertView;
    }

    public ListAdapterPlayerScore(Context context, ArrayList<PlayerModel> playerDataList)
    {

        super();
        this.inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.playerDataModel = playerDataList;

    }
}
