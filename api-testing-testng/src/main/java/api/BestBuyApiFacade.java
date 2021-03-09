package api;

import api.services.ServicesApiClient;
import api.stores.StoresApiClient;

public class BestBuyApiFacade {

    private final String apiUrl;
    private ServicesApiClient servicesApiClient;
    private StoresApiClient storesApiClient;

    public BestBuyApiFacade(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public ServicesApiClient getServicesApiClient() {
        if (servicesApiClient == null) {
            servicesApiClient = new ServicesApiClient(apiUrl);
        }
        return servicesApiClient;
    }

    public StoresApiClient getStoresApiClient() {
        if (storesApiClient == null) {
            storesApiClient = new StoresApiClient(apiUrl);
        }
        return storesApiClient;
    }
}

