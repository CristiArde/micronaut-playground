package com.bonobo.micronaut.web.rest;

import com.bonobo.micronaut.service.dto.OwnerDto;
import com.bonobo.micronaut.service.impl.OwnerService;
import com.bonobo.micronaut.util.HeaderUtil;
import com.bonobo.micronaut.util.PaginationUtil;
import com.bonobo.micronaut.web.rest.errors.BadRequestException;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.http.uri.UriBuilder;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Controller("/api")
public class OwnerResource extends BaseResource<OwnerService> {

    private static final String ENTITY_NAME = "Owner";

    public OwnerResource(OwnerService service) {
        super(OwnerResource.class, service);
    }

    @Get("/owners")
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<List<OwnerDto>> getAllOwners(HttpRequest request, Pageable pageable) {
        this.log.debug("REST request to get a page of Owners");
        Page<OwnerDto> page = this.service.findAll(pageable);
        return HttpResponse.ok(page.getContent()).headers(headers ->
                PaginationUtil.generatePaginationHttpHeaders(headers, UriBuilder.of(request.getPath()), page));
    }

    @Get("/owners/{id}")
    @ExecuteOn(TaskExecutors.IO)
    public Optional<OwnerDto> getOwner(@PathVariable Long id) {
        log.debug("REST request to get Owner : {}", id);
        return this.service.findOne(id);
    }

    @Post("/owners")
    //This annotation specifies to execute this POST request on the I/O
    //thread pool.
    @ExecuteOn(TaskExecutors.IO)
    //The @Body annotation specifies that the ownerDTO method argument in
    //createOwner() is bound to the body of the incoming HTTP POST request
    public HttpResponse<OwnerDto> createOwner(@Body OwnerDto ownerDto) throws URISyntaxException {
        log.debug("REST request to save Owner : {}", ownerDto);
        if (ownerDto.getId() != null) {
            throw new BadRequestException("New owner cannot have an id. The ID is generated by DB engine.", ENTITY_NAME, "id_exists");
        }
        OwnerDto result = service.save(ownerDto);
        URI location = new URI("/api/owners/" + result.getId());
        //In the happy path scenario, the API will return HTTP
        //201 created. This response indicates that the request was executed successfully and
        //a resource was created
        return HttpResponse.created(result).headers(headers -> {
            headers.location(location);
            HeaderUtil.createEntityCreationAlert(headers, applicationName, true, ENTITY_NAME, result.getId().toString());
        });
    }

    @Put("/owners")
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<OwnerDto> updateOwner(@Body OwnerDto ownerDto) throws URISyntaxException {
        log.debug("REST request to update Owner : {}", ownerDto);
        if (ownerDto.getId() == null) {
            throw new BadRequestException("Invalid id", ENTITY_NAME, "id_null");
        }
        OwnerDto result = service.save(ownerDto);
        return HttpResponse.ok(result).headers(headers -> {
            HeaderUtil.createEntityUpdateAlert(headers, applicationName, true,
                    ENTITY_NAME, ownerDto.getId().toString());
        });
    }

    @Delete("/owners/{id}")
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<Object> deleteOwner(@PathVariable Long id) {
        log.debug("REST request to delete Owner : {}", id);
        service.delete(id);
        return HttpResponse.noContent().headers(headers ->
                HeaderUtil.createEntityDeletionAlert(headers, applicationName,
                        true, ENTITY_NAME, id.toString()));
    }

}