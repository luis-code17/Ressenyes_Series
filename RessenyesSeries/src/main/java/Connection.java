
import java.net.http.HttpClient;
public class Connection {


    public static HttpClient getHttpClient() {
        return HttpClient.newHttpClient();
    }
}