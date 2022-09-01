import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.io.InputStream;

public class DiscordNotif {
    String url = "https://discordapp.com/api/webhooks/1014319311197847593/jjY11oRqtES_FS7lz330mqi_4rSl-zA_rNvcg2yDySriqStqmuZtntLsF8dKY1sQvrEW";

    DiscordNotif() throws Exception {

        ToJSON toJSON = new ToJSON();
        toJSON.setContent("testing");

        HttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost();
        post.setHeader("Content-Type", "application/json");
        StringEntity stringEntity = new StringEntity(toJSON.getJSON().toString(), ContentType.APPLICATION_JSON);
    }



}
