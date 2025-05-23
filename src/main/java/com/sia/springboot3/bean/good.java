package com.sia.springboot3.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "good")
public class good {
    private String code;

    public good(String code) {
        this.code = code;
    }

    public good() {
    }
}
