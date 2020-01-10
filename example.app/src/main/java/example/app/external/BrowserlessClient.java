package example.app.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("browserless") // по тэгу browserless достанет его адрес из консула
public interface BrowserlessClient {
	@PostMapping("/function")
    ImageInfo findLargestImage(LargestImageRequest request);

    /**
     * дата класс результата выполнения запроса к Browserless’у со ссылкой на изображение
     */
	class ImageInfo {
        private String url;

        public String getUrl() {
            return url;
        }
    }

    /**
     * хранит скрипт, который передается в Browserless на выполнение, и объект запроса
     */
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

    /**
     * дата класс запроса со ссылкой на страницу
     */
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
