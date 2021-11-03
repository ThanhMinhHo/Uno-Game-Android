package com.example.hothanh.unogame;

import android.graphics.Color;

public class ConvertValue
{
    public int getColor(Colour colorParameter) {
        int color = 0;
        switch (colorParameter) {
            case RED:
                color = Color.RED;
                break;
            case BLUE:
                color = Color.BLUE;
                break;
            case GREEN:
                color = Color.GREEN;
                break;
            case YELLOW:
                color = Color.YELLOW;
                break;
        }
        return color;

    }
    public int getNumber(Number numberParameter)
    {
        int number = -1;
        switch(numberParameter)
        {
            case ZERO:
                number = 0;
                break;
            case ONE:
                number = 1;
                break;
            case TWO:
                number = 2;
                break;
            case THREE:
                number = 3;
                break;
            case FOUR:
                number = 4;
                break;
            case FIVE:
                number = 5;
                break;
            case SIX:
                number = 6;
                break;
            case SEVEN:
                number = 7;
                break;
            case EIGHT:
                number = 8;
                break;
            case NINE:
                number = 9;
                break;
        }
        return number;
    }


}
