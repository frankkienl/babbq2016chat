package nl.frankkie.babbq2016chat;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by FrankkieNL on 8/14/2016.
 */
public class ChatFragment extends Fragment {

    FirebaseDatabase database;
    DatabaseReference myRef;
    EditText editText;
    Button btnSend;
    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("chats/babbq2016");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chat, container, false);
        btnSend = (Button) v.findViewById(R.id.chat_send);
        editText = (EditText) v.findViewById(R.id.chat_edittext);
        listView = (ListView) v.findViewById(android.R.id.list);
        listView.setAdapter(new ListViewAdapter(getActivity()));

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ChatMessageModel model = dataSnapshot.getValue(ChatMessageModel.class);
                addMessage(model);
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

        return v;
    }

    public void addMessage(ChatMessageModel model){
        ((ListViewAdapter)listView.getAdapter()).addMessage(model);
    }

    public void sendMessage(){
        String message = editText.getText().toString();
        editText.setText("");
        ChatMessageModel model = new ChatMessageModel(message);
        myRef.push().setValue(model);
    }
}
