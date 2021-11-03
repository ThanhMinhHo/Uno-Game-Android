package com.example.hothanh.unogame;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class Deck
{
    // Queue which orders the Cards in FIFO(First In First Out) manner.
    private Queue<Card> _DeckOfCard;

    public Deck()
    {
        _DeckOfCard = new LinkedList<>();
        //Initially a deck of cards which contain total 80 cards which consist of number and color.
    }



    public List<Card> InitiallyCards()
    {
        //initially local variable call listCard
        List<Card> _listCards = new ArrayList<>();

        //Using inner for loop to generate the combination of cards.
        for(Colour colour : Colour.values())
        {
            //Consisting of two each of the numbers 0 to 9 inclusive,
            for(Number number : Number.values())
            {
                _listCards.add(new Card(colour,number));
                _listCards.add(new Card(colour,number));
            }
        }
        //Shuffle Cards to mix the card up.
        return _listCards;


    }
    public List<Card> ShuffleCards(List<Card> listcards)
    {

        //The shuffle(List<?>) method is used to randomly permute the specified list.
        for(int i = 0; i<listcards.size();i++)
        {
            Collections.shuffle(listcards);
        }
        return listcards;

    }
    public void AddCardsToDeck(List<Card> cards)
    {
        _DeckOfCard.addAll(cards);
    }

    public Card getCard()
    {
        return _DeckOfCard.poll();
    }
    public Queue<Card> getDeckOfCard() {
        return _DeckOfCard;
    }
}
