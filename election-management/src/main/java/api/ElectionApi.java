package api;

import domain.ElectionService;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ElectionApi {
    private final ElectionService service;

    public ElectionApi(ElectionService service) {
        this.service = service;
    }

    public void submit() {
        service.submit();
    }
}
