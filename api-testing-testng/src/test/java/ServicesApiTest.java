import api.common.exception.InvalidResponseException;
import api.services.ServicesApiClient;
import api.services.data.Service;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ServicesApiTest {

    String apiUrl;

    @BeforeSuite
    public void getApiUrl() {
        apiUrl = System.getProperty("apiUrl");
    }

    @Test
    public void getServices_DefaultLimit_ShouldReturn10Results() throws InvalidResponseException {
        ServicesApiClient client = new ServicesApiClient(apiUrl);
        List<Service> services = client.getServices();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(services.size()).isEqualTo(10);
        softly.assertThat(services.stream().allMatch(service -> service.getId() > 0)).isTrue();
        softly.assertAll();
    }

    @Test
    public void createService_checkId_ShouldReturnService() throws InvalidResponseException {
        Date currentDate = new Date();
        ServicesApiClient client = new ServicesApiClient(apiUrl);
        Service createdService = client.createService(Service.builder().name("New Service").build());

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(createdService.getId()).isGreaterThanOrEqualTo(1);
        softly.assertThat(createdService.getName()).isEqualTo("New Service");
        softly.assertThat(createdService.getCreatedAt()).isAfterOrEqualTo(currentDate);
        softly.assertThat(createdService.getCreatedAt()).isEqualTo(createdService.getUpdatedAt());
        softly.assertAll();
    }

    @Test
    public void getServices_SortByNameAscending_ShouldReturnSortedResults() throws InvalidResponseException {
        ServicesApiClient client = new ServicesApiClient(apiUrl);
        List<Service> services = client.getServicesByQuery("$sort[name]=1");

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(services.size()).isEqualTo(10);
        softly.assertThat(services.stream().allMatch(service -> service.getId() > 0)).isTrue();
        var names = services.stream().map(Service::getName).sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        var sortedNames = new ArrayList<>(names);
        sortedNames.sort(Comparator.naturalOrder());
        softly.assertThat(names).isEqualTo(sortedNames);
        softly.assertAll();
    }
}
