package com.example.hothanh.unogame;


public class Card
{
    public Card(Colour color, Number number)
    {
        _color = color;
        _number = number;
    }
    private Colour _color;
    private Number _number;


    public Colour getColor() {
        return _color;
    }

    public void setColor(Colour _color) {
        this._color = _color;
    }

    public Number getNumber() {
        return _number;
    }

    public void setNumber(Number _number) {
        this._number = _number;
    }
}
