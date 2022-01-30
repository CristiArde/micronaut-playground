package com.bonobo.micronaut.web.rest;

import com.bonobo.micronaut.service.dto.PetDto;
import com.bonobo.micronaut.service.impl.PetService;
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
public class PetResource extends BaseResource<PetService> {
    private static final String ENTITY_NAME = "Pet";
    public PetResource(PetService service) {
        super(PetResource.class, service);
    }

    @Get("/pets")
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<List<PetDto>> getAllPets(HttpRequest<List<PetDto>> request, Pageable pageable) {
        this.log.debug("REST request to get a page of Pets");
        Page<PetDto> page = service.findAll(pageable);
        return HttpResponse.ok(page.getContent()).headers(headers ->
                PaginationUtil.generatePaginationHttpHeaders(headers, UriBuilder.of(request.getPath()), page));
    }

    @Get("/pets/{id}")
    @ExecuteOn(TaskExecutors.IO)
    public Optional<PetDto> getPet(@PathVariable Long id) {
        log.debug("REST request to get Pet : {}", id);
        return this.service.findOne(id);
    }

    @Post("/pets")
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<PetDto> createPet(@Body PetDto petDto) throws URISyntaxException{
        log.debug("REST request to save Pet : {}", petDto);
        ResourceUtil.checkNoId(petDto.getId(), ENTITY_NAME);
        PetDto result = service.save(petDto);
        URI location = new URI("/api/pets/" + result.getId());
        return HttpResponse.created(result).headers(headers -> {
            headers.location(location);
            HeaderUtil.createEntityCreationAlert(headers, applicationName, true, ENTITY_NAME, result.getId().toString());
        });
    }

    @Put("/pets")
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<PetDto> updatePet(@Body PetDto petDto) {
        log.debug("REST request to update Pet : {}", petDto);
        ResourceUtil.checkId(petDto.getId(), ENTITY_NAME);
        PetDto result = service.save(petDto);
        return HttpResponse.ok(result).headers(headers -> {
            HeaderUtil.createEntityUpdateAlert(headers, applicationName, true,
                    ENTITY_NAME, petDto.getId().toString());
        });
    }

    @Delete("/pets/{id}")
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<Object> deletePet(@PathVariable Long id) {
        log.debug("REST request to delete Pet : {}", id);
        service.delete(id);
        return HttpResponse.noContent().headers(headers ->
                HeaderUtil.createEntityDeletionAlert(headers, applicationName,
                        true, ENTITY_NAME, id.toString()));
    }
}
