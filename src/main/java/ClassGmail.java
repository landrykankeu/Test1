import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.ListMessagesResponse;
import com.google.api.services.gmail.model.Message;

import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.*;

/* class to demonstrate use of Gmail list labels API */
public class ClassGmail {
    /**
     * Application name.
     */
    private static final String APPLICATION_NAME = "test API Gmail";
    /**
     * Global instance of the JSON factory.
     */
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    /**
     * Directory to store authorization tokens for this application.
     */
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(GmailScopes.GMAIL_READONLY);
    private static final String CREDENTIALS_FILE_PATH = "../resources/client_secret.json";

    /**
     * Creates an authorized Credential object.
//     *
//     * @param HTTP_TRANSPORT The network HTTP Transport.
//     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Gmail authorize ()
           throws  IOException,GeneralSecurityException {
        // Load client secrets.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
                new InputStreamReader(Objects.requireNonNull(ClassGmail.class.getResourceAsStream("client_secret.json"))));
        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();

        // Build a new authorized API client service with credentials loaded up
        NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        return  new Gmail.Builder(httpTransport, JSON_FACTORY,
                new AuthorizationCodeInstalledApp(flow, receiver).authorize("user"))
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
    public List<Message> getMessages () throws IOException, GeneralSecurityException {
        // Print the labels in the user's account.
        List<Message> tempMessages = new ArrayList<Message>();
        String user = "kankeulandry26@gmail.com";
        List<Message> messagesResponse = (List<Message>) authorize().users().messages().list(user).setMaxResults(5L).execute().getMessages();
        for(Message mail : messagesResponse){
            Message fullMail  = authorize().users().messages().get(user,mail.getId()).setFormat("full").execute();
            if (fullMail != null) {
                tempMessages.add(fullMail);
            }
        }
        return tempMessages;
    }
}