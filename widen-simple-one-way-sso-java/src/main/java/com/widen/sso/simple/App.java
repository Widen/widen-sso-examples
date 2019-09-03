package com.widen.sso.simple;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@SpringBootApplication
@Controller
public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    @Value("${com.widen.sso.simple.endpoint}")
    private String endpoint;

    @Value("${com.widen.sso.simple.shared-secret}")
    private String sharedSecret;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @GetMapping("/")
    String app() {
        return "app";
    }

    @GetMapping("/post")
    String post(Model model) {
        model.addAttribute("endpoint", endpoint);
        model.addAllAttributes(getUserFields());
        return "post";
    }

    @GetMapping("/get")
    String get(Model model) {
        String queryParams = getUserFields().entrySet()
                                            .stream()
                                            .map(entry -> String.format("%s=%s", entry.getKey(), entry.getValue()))
                                            .collect(Collectors.joining("&"));
        model.addAttribute("endpointWithParams", String.format("%s?%s", endpoint, queryParams));
        return "get";
    }

    private Map<String, String> getUserFields() {
        // Using a TreeMap to ensure fields are sorted in ascending order by key
        TreeMap<String, String> fields = new TreeMap<>();
        fields.put("timestamp", new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US).format(new Date()));
        fields.put("guid", "123456789");
        fields.put("email", "example@widen.com");
        fields.put("first_name", "Example");
        fields.put("last_name", "User");
        fields.put("roles", "General Role");
        log.info("fields = {}", fields);

        // Calculating the signature...

        // First join all the field values without a delimiter (in ascending order by key)
        String fieldString = String.join("", fields.values());
        log.info("fieldString = {}", fieldString);

        // Append the shared secret to the joined field String
        String fieldStringWithSharedSecret = fieldString + sharedSecret;
        log.info("fieldStringWithSharedSecret = {}", fieldStringWithSharedSecret);

        // Calculate md5 hash of the field String with shared secret
        String signature = DigestUtils.md5Hex(fieldStringWithSharedSecret);
        log.info("signature = {}", signature);

        fields.put("signature", signature);
        return fields;
    }
}
