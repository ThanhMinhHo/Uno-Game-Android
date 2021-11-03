package com.example.hothanh.unogame;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


      try
      {
          GameScene gameScene = new GameScene(this);
          gameScene.Start();
      }
      catch (Exception message)
      {

          ShowAlertDialog(message);
      }


    }
    private void ShowAlertDialog(Exception message)
    {
        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(this);


        builder.setMessage(message.getMessage())
                .setTitle(R.string.error);

        // 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();
        dialog.show();
    }



}