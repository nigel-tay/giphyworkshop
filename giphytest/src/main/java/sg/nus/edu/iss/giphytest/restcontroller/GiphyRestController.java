package sg.nus.edu.iss.giphytest.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sg.nus.edu.iss.giphytest.services.GiphyService;

@Controller
@RequestMapping(path="/api")
public class GiphyRestController {

    @Autowired
    private GiphyService gService;
    
    @GetMapping(path="/searchgif")
    @ResponseBody
    @CrossOrigin
    public ResponseEntity<List<String>> getGifBySearch(@RequestParam String search) {
        return ResponseEntity.ok(gService.getGifUrlBySearch(search));
    }

}
