package com.example.hothanh.unogame;


public class Pile
{
    public Pile(Card firstCardOnPile)
    {
        _currentCardOnPile = firstCardOnPile;

    }
    private Card _currentCardOnPile;

    public Card getCurrentCardOnPile() {
        return _currentCardOnPile;
    }

    public void setCurrentCardOnPile(Card currentCardOnPile) {
        this._currentCardOnPile = currentCardOnPile;
    }
}
