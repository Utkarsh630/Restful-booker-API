package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader
{
    private static Properties properties = new Properties();
    private static final String filePath = System.getProperty("user.dir")+"/src/main/java/config/Config.properties";
    protected static final Logger logger = LogManager.getLogger(ConfigLoader.class);

    static {
        BufferedReader reader;
        try{
            reader = new BufferedReader(new FileReader(filePath));
//            properties = new Properties();
            try{
                logger.info("Reading property file");
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                logger.error("Not able to read the file");
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            logger.error("Configuration file not found");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static String getBaseUri(){
        String base_uri = properties.getProperty("BASE_URL");
        if(base_uri!=null){
            logger.info("Base URL found: {}",base_uri);
            return base_uri;
        }else {
            logger.error("Base Url not specified in the Config.properties file.");
            throw new RuntimeException("Base Url not specified in the Config.properties file.");

        }
    }

}
