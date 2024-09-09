package home.work;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Objects;

public class jsonFileTest {

    ObjectMapper mapper = new ObjectMapper();;
    ClassLoader classLoader = jsonFileTest.class.getClassLoader();

    @Test
    void validateUsersJsonTest() throws Exception {
        try (Reader reader = new InputStreamReader(Objects
                .requireNonNull(classLoader.getResourceAsStream("user.json")))) {

            JsonNode actualData = mapper.readTree(reader);
            Assertions.assertEquals(100, actualData.get("userId").asInt());
            Assertions.assertEquals("test@test.com", actualData.get("userEmail").asText());
            Assertions.assertEquals("Male", actualData.get("userGender").asText());
            Assertions.assertEquals(15000, actualData.get("balance").asInt());

            JsonNode address = actualData.get("address");
            Assertions.assertEquals("Bugs kingdom", address.get("street").asText());
            Assertions.assertEquals(6, address.get("homeNum").asInt());
            Assertions.assertEquals(6, address.get("flatNum").asInt());
            Assertions.assertEquals(2, address.get("floor").asInt());

        }
    }
}