package com.becheer.donation.configs;

import com.becheer.donation.utils.TaleUtils;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class Config {
    private static Properties properties = TaleUtils.getPropFromFile("src/main/resources/application.properties");

    private static Config instance;

    public String imageRoot;
    public String docRoot;

    public String getImageRoot() {
        return imageRoot;
    }

    public String getDocRoot() {
        return docRoot;
    }

    public Config(){
        this.imageRoot=properties.getProperty("resource.imageRoot");
        this.docRoot=properties.getProperty("resource.docRoot");
    }

    public static Config getConfig(){
        if (instance==null){
            instance=new Config();
        }
        return instance;
    }
}
