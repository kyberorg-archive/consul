package example.app.services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.annotation.PostConstruct;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.app.domain.Image;
import example.app.external.BrowserlessClient;
import example.app.external.BrowserlessClient.BrowserlessContext;
import example.app.external.BrowserlessClient.LargestImageRequest;
import example.app.repositories.ImageRepository;

@Service
public class ImageService  {
	private final ImageRepository imageRepository;
	
	private final BrowserlessClient browserlessClient;

	private String getLargestImageScript;

	public ImageService(ImageRepository imageRepository, BrowserlessClient browserlessClient) {
		this.imageRepository = imageRepository;
		this.browserlessClient = browserlessClient;
	}

	@PostConstruct
	public void initialize() throws IOException {
		getLargestImageScript = IOUtils.toString(getClass().getResourceAsStream("/getLargestImage.js"), StandardCharsets.UTF_8.name());
	}
	
	public Image findLargestImage(String url) {
		BrowserlessContext browserlessContext = new BrowserlessContext(url);
		LargestImageRequest largestImageRequest = new LargestImageRequest(getLargestImageScript, browserlessContext);
		
		BrowserlessClient.ImageInfo imageInfo = browserlessClient.findLargestImage(largestImageRequest);
		
		Image image = new Image();
		
		image.setSourceUrl(url);
		image.setImageUrl(imageInfo.getUrl());
		
		return imageRepository.save(image);
	}
}
