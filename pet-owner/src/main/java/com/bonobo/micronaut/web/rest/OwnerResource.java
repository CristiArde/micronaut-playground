package com.bonobo.micronaut.web.rest;

import com.bonobo.micronaut.service.dto.OwnerDto;
import com.bonobo.micronaut.service.impl.OwnerService;
import com.bonobo.micronaut.util.PaginationUtil;
import io.micronaut.data.model.Page;
import io.micronaut.data.model.Pageable;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.uri.UriBuilder;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;

import java.util.List;
import java.util.Optional;

@Controller("/api")
public class OwnerResource extends BaseResource<OwnerService>{


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
}
