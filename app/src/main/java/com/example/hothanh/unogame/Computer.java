package com.example.hothanh.unogame;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Computer extends Player
{
    public Computer(String name,int id)
    {
        super(name,id);
    }

    public void AutomaticPickupCard(Pile pile, Deck deck, Computer computer,Activity activity)
    {
        Card cardToPlay = null;
        //Select card to play
        List<Card> cardAbleToPlay = new ArrayList<Card>();
        for( Card cardToCompare: getCards())
        {
            if(CheckMatchingCard(pile,cardToCompare)== true)
            {
                cardAbleToPlay.add(cardToCompare);
            }
        }

        if(cardAbleToPlay.size() > 0)//Some cards are matching
        {
            //the computer should automatically place any card at random from its hand
            Random rand = new Random();
            int cardToPickPosition = rand.nextInt(cardAbleToPlay.size());
            cardToPlay = cardAbleToPlay.get(cardToPickPosition);
            //Selected card in Player hand
            SelectedCardInPlayerHand(pile,cardToPlay);

        }
        else //Pick up a card from the deck.
        {
            Card card = deck.getCard();
            //Check if there is no card in the deck.
            if(deck.getCard() ==null)
            {
                ShowUI show = new ShowUI(activity);
                show.ShowAlertDialog("draw");
            }
            else
            {
                computer.PickUpCardFromDeck(card);
            }
        }
    }

}
