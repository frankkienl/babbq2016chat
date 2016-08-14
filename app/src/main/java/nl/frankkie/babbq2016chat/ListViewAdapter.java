package nl.frankkie.babbq2016chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by FrankkieNL on 8/14/2016.
 */
public class ListViewAdapter extends BaseAdapter {

    ArrayList<ChatMessageModel> chatMessages;
    Context context;

    ListViewAdapter(Context context){
        this.context = context;
        chatMessages = new ArrayList<>();
    }

    public void addMessage(ChatMessageModel model){
        chatMessages.add(model);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return chatMessages.size();
    }

    @Override
    public Object getItem(int i) {
        return chatMessages.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ChatMessageModel model = (ChatMessageModel) getItem(i);
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.chat_list_item,viewGroup,false);
            ViewHolder vh = new ViewHolder();
            vh.firstLine = (TextView) view.findViewById(R.id.firstLine);
            vh.secondLine = (TextView) view.findViewById(R.id.secondLine);
            view.setTag(vh);
        }
        ViewHolder holder = (ViewHolder) view.getTag();
        holder.firstLine.setText(model.username + " - " + model.datetime);
        holder.secondLine.setText(model.message);
        return view;
    }

    public static class ViewHolder {
        TextView firstLine;
        TextView secondLine;
    }
}
