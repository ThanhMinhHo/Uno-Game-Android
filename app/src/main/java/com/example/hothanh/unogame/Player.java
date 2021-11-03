package com.example.hothanh.unogame;

import java.util.ArrayList;
import java.util.List;

public abstract class Player
{
    public Player(String name,int id)
    {
       _name = name;
       _Cards = new ArrayList<Card>();
       _id = id;


    }
    private String _name;
    private List<Card> _Cards;
    private Boolean _myTurn = false;
    private int _id;
    public Boolean Winner()
    {
        return _Cards.isEmpty();
    }
    public boolean RemoveCard(Card card)
    {
       return _Cards.remove(card);
    }

    public  boolean CheckMatchingCard(Pile pile, Card cardToCompare)
    {
       boolean matching = false;
        //Compare the color
        if(cardToCompare.getColor() == pile.getCurrentCardOnPile().getColor())
        {
            matching = true;
        }
        //Compare the number
        if(cardToCompare.getNumber() == pile.getCurrentCardOnPile().getNumber())
        {
            matching = true;
        }

        return matching;

    }
    public void SelectedCardInPlayerHand(Pile pile, Card cardToPlay)
    {
        //Remove Card to draw from the player hand.
        RemoveCard(cardToPlay);
        //Set the card on the pile.
        pile.setCurrentCardOnPile(cardToPlay);

    }
    public void PickUpCardFromDeck(Card cardFromPile)
    {
        _Cards.add(cardFromPile);

    }


    public List<Card> getCards() {
        return _Cards;
    }

    public void addCard(Card card) {
        this._Cards.add(card);
    }

    public String getName() {
        return _name;
    }
    public int getCardRemainOnHand()
    {
        return _Cards.size();
    }

    public int getId() {
        return _id;
    }
}
