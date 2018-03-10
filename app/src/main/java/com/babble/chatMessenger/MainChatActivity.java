package com.babble.chatMessenger;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainChatActivity extends AppCompatActivity {

    // TODO: Add member variables here:
    private String mDisplayName;
    private ListView mChatListView;
    private EditText mInputText;
    private ImageButton mSendButton;
    private DatabaseReference mDatabaseReference;
    private ChatListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chat);

        // TODO: Set up the display name and get the Firebase reference
        setupDisplayName();
        mDatabaseReference=FirebaseDatabase.getInstance().getReference();
        // Link the Views in the layout to the Java code
        mInputText = (EditText) findViewById(R.id.messageInput);
        mSendButton = (ImageButton) findViewById(R.id.sendButton);
        mChatListView = (ListView) findViewById(R.id.chat_list_view);

        // TODO: Send the message when the "enter" button is pressed
        mInputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                sendMessage();
                return true;
            }
        });

        mInputText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                sendMessage();
                return true;
            }
        });

        // TODO: Add an OnClickListener to the sendButton to send a message
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    // TODO: Retrieve the display name from the Shared Preferences
    private void setupDisplayName(){
        SharedPreferences prefs=getSharedPreferences(RegisterActivity.CHAT_PREFS,MODE_PRIVATE);
        mDisplayName=prefs.getString(RegisterActivity.DISPLAY_NAME_KEY,null);
        if(mDisplayName==null){
            mDisplayName="Anonymous";
        }
    }

    private void sendMessage() {
        Log.d("Babble","I've sent some text");
        // TODO: Grab the text the user typed in and push the message to Firebase
        String input=mInputText.getText().toString();
        if(!input.equals("")){
            InstantMessage chat=new InstantMessage(input,mDisplayName);
            mDatabaseReference.child("messages").push().setValue(chat);
            mInputText.setText("");
        }
    }

    // TODO: Override the onStart() lifecycle method. Setup the adapter here.


    @Override
    protected void onStart() {
        super.onStart();
        mAdapter=new ChatListAdapter(this,mDatabaseReference,mDisplayName);
        mChatListView.setAdapter(mAdapter);
    }

    @Override
    public void onStop() {
        super.onStop();
        // TODO: Remove the Firebase event listener on the adapter.
        mAdapter.cleanup();
    }

}
