package example.app.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.app.domain.Image;
import example.app.services.ImageService;

@RestController
public class MainController {
	private static Logger log = LoggerFactory.getLogger(MainController.class);
	
	@Autowired
	private ImageService imageService;
	
	@Value("${test.property}")
	private String testProperty;
	
    @GetMapping("/largest-image")
    public ResponseEntity<Image> getTitle(@RequestParam("url") String url) {
		return ResponseEntity.ok(imageService.findLargestImage(url));
    }
	
    @GetMapping("/property")
    public ResponseEntity<String> getProperty() {
    	return ResponseEntity.ok(testProperty);
    }
}