package com.example.hothanh.unogame;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SelectCardToPlayAdapter extends BaseAdapter {

    private  Context mContext;
    private  List<Card> cards;

    public SelectCardToPlayAdapter(Context context, List<Card> listCard) {
        cards = new ArrayList<Card>();
        this.mContext = context;
        this.cards.addAll(listCard);
    }

    // 2
    @Override
    public int getCount() {
        return cards.size();
    }

    // 3
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 4
    @Override
    public Object getItem(int position) {
        return null;
    }

    // 5
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ConvertValue convertValue = new ConvertValue() ;

        TextView text = new TextView(mContext);
        //Get the card base on the position on gridview
        Card card = cards.get(position);


        int index = convertValue.getNumber(card.getNumber());
        text.setText(Integer.toString(index));
        text.setTextSize(70);



        int intColor = convertValue.getColor(card.getColor());

        text.setBackgroundColor(intColor);
        return text;
    }

}