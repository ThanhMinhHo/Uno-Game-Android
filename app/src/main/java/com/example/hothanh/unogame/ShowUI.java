package com.example.hothanh.unogame;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class ShowUI {
    public ShowUI(Activity activity)
    {
        _activity = activity;
    }
    private Activity _activity;

    public void showCardRemainOnDeck(Deck deck) {
        //---show the number of cards remain on deck ---
        int cardRemain;
        String cardOrCards;
        TextView cardsRemainOnDeck = (TextView) _activity.findViewById(R.id.cardsRemainOnDeck);

        cardRemain = deck.getDeckOfCard().size();

        cardOrCards = (cardRemain > 1) ? "cards" : "card";

        cardsRemainOnDeck.setText(cardRemain + " " + cardOrCards + " remaining in deck");
    }
    public void showOpponentCard(Player player) {
        int cardRemains;
        String cardOrCards;
        //--- show the text about opponent card---
        TextView opponentCardRemain = (TextView) _activity.findViewById(R.id.opponentCardleft);
        cardRemains = player.getCardRemainOnHand();
        cardOrCards = (cardRemains > 1) ? "cards" : "card";
        opponentCardRemain.setText("Your opponent (the " + player.getName() + ") has " + cardRemains + " " + cardOrCards + " remaining ");
    }
    public void showTopPile( Pile pile)
    {
        //---show the top of the pile card ---

        Button button = _activity.findViewById(R.id.topOfPileButton);
        ConvertValue convert = new ConvertValue();


        int intColor = convert.getColor(pile.getCurrentCardOnPile().getColor());


        button.setBackgroundColor(intColor);

        //Get the enum Number postion of the top current card on pile

        int position = convert.getNumber(pile.getCurrentCardOnPile().getNumber());
        //Number.valueOf(_pile.getCurrentCardOnPile().getNumber().toString()).ordinal();

        button.setText(Integer.toString(position));
        button.setTextSize(70);

    }
    public GridView showCardsGridView(Player player)
    {
        //---Select card on hand using grid view ---
        GridView gridView = (GridView) _activity.findViewById(R.id.gridviewSelectCardToPlay);


        // Create adapter to set value for grid view
        SelectCardToPlayAdapter selectCardToPlay = new SelectCardToPlayAdapter(_activity, player.getCards());

        gridView.setAdapter(selectCardToPlay);
        return gridView;
    }
    public Button showPickUpCardFromDeck() {

        Button button = _activity.findViewById(R.id.pickUpCardFromDeck);
        return button;

    }
    public void ShowAlertDialog(String player)
    {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(_activity);

        // Add the Play again buttons
        builder.setPositiveButton(R.string.play_again, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //Start game again
                GameScene game = new GameScene(_activity);
                game.Start();
            }
        });
        // Add the Exit button
        builder.setNegativeButton(R.string.exit, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
        String text;
        if(player == "User")
        {
            text = "Congraduation! You has won the game";
        }
        else if(player == "Computer")
        {
            text = "You lost, good luck next time";
        }
        else
        {
            text = "Draw, there are no card on the deck!";
        }
        builder.setMessage(text)
                .setTitle(R.string.title);

        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();
        dialog.show();
    }




}
