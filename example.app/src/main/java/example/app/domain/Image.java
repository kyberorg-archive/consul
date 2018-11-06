package example.app.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "images")
public class Image {
	@Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private String sourceUrl;

    @NotBlank
    private String imageUrl;
    
    public void setId(Long id) {
    	this.id = id;
    }
    
    public Long getId() {
    	return this.id;
    }
    
    public void setSourceUrl(String sourceUrl) {
    	this.sourceUrl = sourceUrl;
    }
    
    public String getSourceUrl() {
    	return this.sourceUrl;
    }
    
    public void setImageUrl(String imageUrl) {
    	this.imageUrl = imageUrl;
    }
    
    public String getImageUrl() {
    	return this.imageUrl;
    }
}
