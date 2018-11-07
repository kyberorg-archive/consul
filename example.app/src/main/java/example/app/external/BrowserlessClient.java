package example.app.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("browserless")
public interface BrowserlessClient {
	@PostMapping("/function")
    ImageInfo findLargestImage(LargestImageRequest request);

	class ImageInfo {
        private String url;

        public String getUrl() {
            return url;
        }
    }

    class LargestImageRequest {
        private String code;
        private BrowserlessContext context;

        public LargestImageRequest(String code, BrowserlessContext context) {
            this.code = code;
            this.context = context;
        }

        public String getCode() {
            return code;
        }

        public BrowserlessContext getContext() {
            return context;
        }
    }

    class BrowserlessContext {
        private String url;

        public BrowserlessContext(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }
}
