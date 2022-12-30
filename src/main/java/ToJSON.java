
import com.fasterxml.jackson.databind.ObjectMapper;

public class ToJSON {
    private ObjectMapper mapper;
    private String jsonstring;
    private Message message;
    ToJSON () {
        mapper = new ObjectMapper();
        message = new Message();
    }

    public void setContent(String content) {
        message.content = content;
        message.username = "Bot";
    }

    public void setUsername(String username) {
        message.username = username;
    }

    public String getJSON() throws Exception {
        jsonstring = this.mapper.writeValueAsString(message);
        return jsonstring;
    }
}
