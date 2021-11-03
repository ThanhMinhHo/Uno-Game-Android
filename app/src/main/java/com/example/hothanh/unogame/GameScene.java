package com.example.hothanh.unogame;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;
import java.util.List;
import java.util.Random;

public class GameScene
{
    //region Declare Variable
    private Computer _computer;
    private User _user;
    private Deck _deck;
    private Pile _pile;
    private Activity _mainActivity;
    private ShowUI _show;
    private static final int NUMBER_CARD_START = 7;
    private static final int NUMBER_PLAYERS = 2;
    //endregion


    //region  GameScene Construction
    public GameScene(Activity activity)
    {
        _mainActivity = activity;
        _show = new ShowUI(_mainActivity);
    }
    //endregion


    //region Public function
    public void Start()
    {
        CreatePlayers();
        CreateDeck();
        DistributedCard(NUMBER_CARD_START);
        OpenPileCard();
        int playerID = PlayerPlayFirst();
        int nextPlayer = runSequences(playerID);
        PlayGame(nextPlayer);

    }
    //endregions


    //region Private funcions
    private int runSequences(int currentPlayerID)
    {
        //Using this method when there are more than 2 player where it will be easily to extend.
        //Player 1 then player 2 and continue as clock.
        int nextID;
        int startID;
        if(currentPlayerID == NUMBER_PLAYERS)
        {
            nextID = 1;
        }
        else
        {
            nextID = currentPlayerID + 1;
        }
        return nextID;
    }
    private void CreatePlayers()
    {
        _user = new User("User",1);
        //Create the computer object
        _computer = new Computer("Computer",2);

    }
    private void CreateDeck()
    {
        //Create the deck object
        _deck = new Deck();
        List<Card> cards;
        //Put card into the deck
        cards = _deck.InitiallyCards();
        //Shuffle cards
        cards = _deck.ShuffleCards(cards);
        //Add card to the deck.
        _deck.AddCardsToDeck(cards);


    }
    private void DistributedCard(int NUMBER_CARD_START )
    {
        //Get 7 cards when start the game for each player
        for (int number = 0; number < NUMBER_CARD_START; number++) {
                //Switch between  them
                _user.addCard(_deck.getCard());
                _computer.addCard(_deck.getCard());
        }
    }
    private int PlayerPlayFirst()
    {
        Random random = new Random();
        return(random.nextInt(2)+ 1);

    }
    private void OpenPileCard()
    {
        // The next card from the deck becomes the first card on the pile.
        Card card = _deck.getCard();
        _pile = new Pile(card);

    }

    private void PlayGame(int nextPlayer)
    {

        if(_user.getId() == nextPlayer)
        {
            UserPlay();
        }
        else if (_computer.getId() == nextPlayer)
        {
            ComputerPlay();
        }
    }


    private void CheckWinner(Player player)
    {
        if(player.Winner())
        {
            _show.ShowAlertDialog(player.getName());
        }

    }
    private void ComputerPlay()
    {
        _computer.AutomaticPickupCard(_pile,_deck,_computer,_mainActivity);
        //Call Opponent to take turn
        OpponentTurn(_computer);


    }
    private void OpponentTurn(Player player)
    {
        //Check the player has won the game
        CheckWinner(player);
        //Set the opponent to take a turn.
        int nextPlayer = runSequences(player.getId());
        //The game continue play
        PlayGame(nextPlayer);
    }
    private void UserPlay()
    {
        //Show UI
        _show.showOpponentCard(_computer);
        _show.showTopPile(_pile);
        _show.showCardRemainOnDeck(_deck);
        GridView gridview =  _show.showCardsGridView(_user);
        Button button = _show.showPickUpCardFromDeck();
        //Set event handler
        selectCardOnHandEventHandler(gridview);
        pickUpCardFromDeckEventHandler(button);
    }

    private void selectCardOnHandEventHandler(GridView gridview)
    {
        //Listen to the user click to select the card

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                UserSelectedCard(position);
            }

        });

    }

    private void pickUpCardFromDeckEventHandler(Button button) {

        //Listen to the user click to pick up card from deck
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Card card = _deck.getCard();
                //Check if there is no card in the deck.
                if(card ==null)
                {
                    _show.ShowAlertDialog("draw");
                }
                else
                {
                    //User chose to pickup a card from the deck
                    _user.PickUpCardFromDeck(card);
                }
                //Call Opponent to take turn
                OpponentTurn(_user);
            }
        });
    }

    private void UserSelectedCard(int position)
    {
        //Get the card according to the position
        Card cardToPlay = _user.getCards().get(position);
        //Check if the user selected the card that is matching with the pile
        boolean isMatching = _user.CheckMatchingCard(_pile, cardToPlay);
        if(isMatching)
        {
            _user.SelectedCardInPlayerHand(_pile,cardToPlay);
            //Call Opponent to take turn
            OpponentTurn(_user);

        } else {
            CharSequence text = "This card is not matching with the pile! ";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(_mainActivity, text, duration);
            toast.show();
        }
    }
    //endregion
}
