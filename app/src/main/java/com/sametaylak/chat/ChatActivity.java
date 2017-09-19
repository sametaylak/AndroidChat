package com.sametaylak.chat;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    public static Intent newIntent(Context ctx, String username) {
        Intent intent = new Intent(ctx, ChatActivity.class);
        intent.putExtra("username", username);
        return intent;
    }

    String username;

    EditText messageText;
    Button send;
    ListView listView;

    MessagesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        FirebaseDatabase.getInstance().getReference("Messages")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        Message inMessage = dataSnapshot.getValue(Message.class);

                        if (!inMessage.getName().equals(username)) // Kendi mesajlarımızı iki defa göstermemek için
                            adapter.add(inMessage);
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

        username = getIntent().getStringExtra("username");

        ArrayList<Message> listOfMessages = new ArrayList<>();
        adapter = new MessagesAdapter(this, listOfMessages);

        listView = (ListView) findViewById(R.id.message_list);
        listView.setAdapter(adapter);

        send = (Button) findViewById(R.id.send);
        messageText = (EditText) findViewById(R.id.message);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = messageText.getText().toString();
                Message msg = new Message(username, text);

                FirebaseDatabase.getInstance().getReference("Messages")
                        .push()
                        .setValue(msg);

                adapter.add(msg);

                messageText.getText().clear();
            }
        });
    }
}
