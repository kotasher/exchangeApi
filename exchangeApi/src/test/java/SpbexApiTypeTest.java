import api.factory.ApiFactory;
import api.enums.ApiType;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

import static org.junit.Assert.assertTrue;

public class SpbexApiTypeTest {
    @Test
    public void testHistory() throws URISyntaxException, IOException, InterruptedException, ParseException {
        final var factory = new ApiFactory();
        final var api = factory.createApi(ApiType.SPBEX);
        System.out.println(api.getHistory("KD"));
        assertTrue(true);
    }


}
