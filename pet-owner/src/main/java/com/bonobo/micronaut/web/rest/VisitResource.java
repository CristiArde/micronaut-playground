package com.bonobo.micronaut.web.rest;

import com.bonobo.micronaut.service.dto.VisitDto;
import com.bonobo.micronaut.service.impl.VisitService;
import com.bonobo.micronaut.util.HeaderUtil;
import com.bonobo.micronaut.util.PaginationUtil;
import com.bonobo.micronaut.util.ResourceUtil;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.uri.UriBuilder;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Controller("/api")
public class VisitResource extends BaseResource<VisitService> {
    private static final String ENTITY_NAME = "Visit";

    public VisitResource(VisitService service) {
        super(VisitResource.class, service);
    }


    @Get("/visits")
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<List<VisitDto>> getAllVisits(HttpRequest<List<VisitDto>> request, Pageable pageable) {
        this.log.debug("REST request to get a page of Visits");
        Page<VisitDto> page = this.service.findAll(pageable);
        return HttpResponse.ok(page.getContent()).headers(headers ->
                PaginationUtil.generatePaginationHttpHeaders(headers, UriBuilder.of(request.getPath()), page));
    }

    @Get("/visits/{id}")
    @ExecuteOn(TaskExecutors.IO)
    public Optional<VisitDto> getVisit(@PathVariable Long id) {
        log.debug("REST request to get Visit : {}", id);
        return this.service.findOne(id);
    }

    @Post("/visits")
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<VisitDto> createVisit(@Body VisitDto visitDto) throws URISyntaxException {
        log.debug("REST request to save Visit : {}", visitDto);
        ResourceUtil.checkNoId(visitDto.getId(), ENTITY_NAME);
        VisitDto result = this.service.save(visitDto);
        URI location = new URI("/api/visits/" + result.getId());
        return HttpResponse.created(result).headers(headers -> {
            headers.location(location);
            HeaderUtil.createEntityCreationAlert(headers, applicationName, true, ENTITY_NAME, result.getId().toString());
        });
    }

    @Put("/visits")
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<VisitDto> updateVisit(@Body VisitDto visitDto) throws URISyntaxException {
        log.debug("REST request to update Visit : {}", visitDto);
        ResourceUtil.checkId(visitDto.getId(), ENTITY_NAME);
        VisitDto result = service.save(visitDto);
        return HttpResponse.ok(result).headers(headers ->
                HeaderUtil.createEntityUpdateAlert(headers, applicationName, true, ENTITY_NAME, visitDto.getId().toString()));
    }

    @Delete("/visits/{id}")
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<Object> deleteVisit(@PathVariable Long id) {
        log.debug("REST request to delete Visit : {}", id);
        service.delete(id);
        return HttpResponse.noContent().headers(headers -> HeaderUtil.createEntityDeletionAlert(headers, applicationName, true, ENTITY_NAME, id.toString()));
    }
}
