
import org.apache.http.client.HttpClient;
import org.apache.http.*;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.net.URI;

public class DiscordNotif {


    DiscordNotif() throws Exception {
    }
    public static void sendNotif(String message) throws Exception {
        String url = "https://discordapp.com/api/webhooks/1014319311197847593/jjY11oRqtES_FS7lz330mqi_4rSl-zA_rNvcg2yDySriqStqmuZtntLsF8dKY1sQvrEW";
        ToJSON toJSON = new ToJSON();
        toJSON.setContent(message);

        HttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(URI.create(url));
        post.setHeader("Content-Type", "application/json");
        StringEntity stringEntity = new StringEntity(toJSON.getJSON(), "UTF-8");
        stringEntity.setContentType("application/json");
        post.setEntity(stringEntity);
        HttpResponse response = httpClient.execute(post);
    }
}
