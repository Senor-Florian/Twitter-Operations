package credentials;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ParseCredentials {

    public Credentials read() {
        ClassLoader classLoader = getClass().getClassLoader();
        byte[] db = new byte[0];
        try {
            db = Files.readAllBytes(Paths.get(classLoader.getResource("credentials.json").getPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        Credentials credentials = null;
        try {
            credentials = objectMapper.readValue(db, Credentials.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return credentials;
    }
}
