package com.sametaylak.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sametaylak on 19/09/2017.
 */

public class MessagesAdapter extends ArrayAdapter<Message> {
    public MessagesAdapter(Context context, ArrayList<Message> messages) {
        super(context, 0, messages);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Message msg = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_message, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView message = (TextView) convertView.findViewById(R.id.message);

        name.setText(msg.name);
        message.setText(msg.message);

        return convertView;
    }
}
