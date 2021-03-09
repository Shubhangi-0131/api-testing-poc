import api.common.exception.InvalidResponseException;
import api.stores.StoresApiClient;
import api.stores.data.Store;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;

public class StoresApiTest {

    String apiUrl;

    @BeforeSuite
    public void getApiUrl() {
        apiUrl = System.getProperty("apiUrl");
    }

    @Test
    public void getStores_DefaultLimit_ShouldReturn10Results() throws InvalidResponseException {
        StoresApiClient client = new StoresApiClient(apiUrl);
        List<Store> storeList = client.getStores();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(storeList.size()).isEqualTo(10);
        softly.assertThat(storeList.stream().allMatch(store -> store.getId() > 0)).isTrue();
        softly.assertAll();
    }
}
