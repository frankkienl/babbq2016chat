package nl.frankkie.babbq2016chat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by FrankkieNL on 8/14/2016.
 */
public class ChatMessageModel {

    String username = "Android";
    String message = "empty message";
    String datetime = "Today";

    public ChatMessageModel(){}

    public ChatMessageModel(String message){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        username = "Android";
        this.message = message;
        datetime = sdf.format(new Date());
    }
}
