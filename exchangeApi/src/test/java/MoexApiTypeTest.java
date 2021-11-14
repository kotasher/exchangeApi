import api.ApiFactory;
import api.enums.ApiType;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

import static org.junit.Assert.assertTrue;

public class MoexApiTypeTest {
    @Test
    public void testHistory() throws URISyntaxException, IOException, InterruptedException, ParseException {
        final var factory = new ApiFactory();
        final var api = factory.createApi(ApiType.MOEX);
        System.out.println(api.getHistory("SBERP"));
        assertTrue(true);
    }


}
