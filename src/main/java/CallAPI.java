
import java.io.IOException;
import java.lang.invoke.CallSite;
import java.net.URI;
import java.net.http.*;
public class CallAPI{
    private HttpRequest request;
    private HttpResponse <String> response;
    public CallAPI(){
        request = HttpRequest.newBuilder()
                .uri(URI.create("https://billboard-api2.p.rapidapi.com/hot-100?date=2019-05-11&range=1-10"))
                .header("X-RapidAPI-Key", "c31bbfaacamsh31985e3ccbec6f5p18e1cejsn3f1f7ad6d591")
                .header("X-RapidAPI-Host", "billboard-api2.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
    }
    public String getData(){
        try{
            response = HttpClient.newHttpClient().send(request,HttpResponse.BodyHandlers.ofString());
        }catch(IOException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return response.body();
    }

}

