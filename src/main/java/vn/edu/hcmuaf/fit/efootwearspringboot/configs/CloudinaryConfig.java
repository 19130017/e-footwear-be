package vn.edu.hcmuaf.fit.efootwearspringboot.configs;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    private final String CLOUD_NAME = "df9jg2cis";
    private final String API_KEY = "838954483372342";
    private final String API_SECRET = "_Mi1k1k2SGS3R_93XrzOd1jtL68";

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", CLOUD_NAME);
        config.put("api_key", API_KEY);
        config.put("api_secret", API_SECRET);
        return new Cloudinary(config);
    }
}
