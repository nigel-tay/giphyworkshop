package sg.nus.edu.iss.giphytest.services;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

@Service
public class GiphyService {

    @Value("${giphy.api.key}")
    private String GIPHY_API_KEY;
    
    private String GIPHY_URL = "https://api.giphy.com/v1/gifs/search";
    /**
     * api_key: String
     * q: String (Search)
     */

     /**
      * data[0].images.fixed_height.url
      */


    public List<String> getGifUrlBySearch(String search) {
        String url = UriComponentsBuilder
                        .fromUriString(GIPHY_URL)
                        .queryParam("api_key", GIPHY_API_KEY)
                        .queryParam("q", search)
                        .toUriString();
        
        RestTemplate rTemplate = new RestTemplate();

        ResponseEntity<String> gifUrl = rTemplate.getForEntity(url, String.class);
        StringReader sr = new StringReader(gifUrl.getBody());
        JsonReader jReader = Json.createReader(sr);
        JsonObject json = jReader.readObject();
        List<String> urlList = json.getJsonArray("data")
                            .stream()
                            .map(js -> js.asJsonObject())
                            .map(jo -> jo.getJsonObject("images"))
                            .map(jo -> jo.getJsonObject("fixed_height"))
                            .map(jo -> jo.getString("url"))
                            .toList();
        return urlList;
    }
}
