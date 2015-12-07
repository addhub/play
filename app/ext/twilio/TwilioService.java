package ext.twilio;// You may want to be more specific in your imports

import java.util.*;
import com.twilio.sdk.*;
import com.twilio.sdk.resource.factory.*;
import com.twilio.sdk.resource.instance.*;
import com.twilio.sdk.resource.list.*;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class TwilioService {
    // Find your Account Sid and Token at twilio.com/user/account
    public static final String ACCOUNT_SID = "AC73b3243b165da3eabaf2a14183cbcb78";
    public static final String AUTH_TOKEN = "[AuthToken]";

    private final TwilioClient twilioClient=new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);


    public void sendSMS(String to, String body) throws TwilioRestException {
        TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

        // Build the parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("To", to));
        params.add(new BasicNameValuePair("From", "+13478481804"));
        params.add(new BasicNameValuePair("Body", body));

        MessageFactory messageFactory = client.getAccount().getMessageFactory();
        Message message = messageFactory.create(params);
        System.out.println(message.getSid());
    }
}
