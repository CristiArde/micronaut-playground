package com.bonobo.rest;

import com.bonobo.rest.error.BadRequestAlertException;
import com.bonobo.service.SpecialtyService;
import com.bonobo.service.dto.SpecialtyDto;
import com.bonobo.util.HeaderUtil;
import io.micronaut.context.annotation.Value;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Controller("/api")
public class SpecialityResource {

    private final Logger log = LoggerFactory.getLogger(SpecialityResource.class);

    private static final String ENTITY_NAME = "specialty";

    @Value("${micronaut.application.name}")
    private String applicationName;

    private final SpecialtyService specialtyService;

    public SpecialityResource(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    /**
     * {@code POST  /specialties} : Create a new specialty.
     *
     * @param SpecialtyDto the SpecialtyDto to create.
     * @return the {@link HttpResponse} with status {@code 201 (Created)} and with body the new SpecialtyDto, or with status {@code 400 (Bad Request)} if the specialty has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Post("/specialties")
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<SpecialtyDto> createSpecialty(@Body SpecialtyDto specialtyDto) throws Exception {
        log.debug("REST request to save Specialty : {}", specialtyDto);
        if (specialtyDto.getId() != null) {
            throw new BadRequestAlertException("A new specialty cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SpecialtyDto result = specialtyService.save(specialtyDto);
        URI location = new URI("/api/specialties/" + result.getId());
        return HttpResponse.created(result).headers(headers -> {
            headers.location(location);
            HeaderUtil.createEntityCreationAlert(headers, applicationName, true, ENTITY_NAME, result.getId().toString());
        });
    }

    /**
     * {@code PUT  /specialties} : Updates an existing specialty.
     *
     * @param specialtyDto the SpecialtyDto to update.
     * @return the {@link HttpResponse} with status {@code 200 (OK)} and with body the updated SpecialtyDto,
     * or with status {@code 400 (Bad Request)} if the SpecialtyDto is not valid,
     * or with status {@code 500 (Internal Server Error)} if the SpecialtyDto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Put("/specialties")
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<SpecialtyDto> updateSpecialty(@Body SpecialtyDto specialtyDto) throws Exception {
        log.debug("REST request to update Specialty : {}", specialtyDto);
        if (specialtyDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SpecialtyDto result = specialtyService.save(specialtyDto);
        return HttpResponse.ok(result).headers(headers ->
                HeaderUtil.createEntityUpdateAlert(headers, applicationName, true, ENTITY_NAME, specialtyDto.getId().toString()));
    }

    /**
     * {@code GET  /specialties} : get all the specialties.
     *
     * @return the {@link HttpResponse} with status {@code 200 (OK)} and the list of specialties in body.
     */
    @Get("/specialties")
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<List<SpecialtyDto>> getAllSpecialties(HttpRequest request) throws Exception {
        log.debug("REST request to get a page of Specialties");
        List<SpecialtyDto> list = (List<SpecialtyDto>) specialtyService.findAll();
        return HttpResponse.ok(list);
    }

    /**
     * {@code GET  /specialties/:id} : get the "id" specialty.
     *
     * @param id the id of the SpecialtyDto to retrieve.
     * @return the {@link HttpResponse} with status {@code 200 (OK)} and with body the SpecialtyDto, or with status {@code 404 (Not Found)}.
     */
    @Get("/specialties/{id}")
    @ExecuteOn(TaskExecutors.IO)
    public Optional<SpecialtyDto> getSpecialty(@PathVariable Long id) throws Exception {
        log.debug("REST request to get Specialty : {}", id);

        return specialtyService.findOne(id);
    }

    /**
     * {@code DELETE  /specialties/:id} : delete the "id" specialty.
     *
     * @param id the id of the SpecialtyDto to delete.
     * @return the {@link HttpResponse} with status {@code 204 (NO_CONTENT)}.
     */
    @Delete("/specialties/{id}")
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse deleteSpecialty(@PathVariable Long id) throws Exception {
        log.debug("REST request to delete Specialty : {}", id);
        specialtyService.delete(id);
        return HttpResponse.noContent().headers(headers -> HeaderUtil.createEntityDeletionAlert(headers, applicationName, true, ENTITY_NAME, id.toString()));
    }
}

