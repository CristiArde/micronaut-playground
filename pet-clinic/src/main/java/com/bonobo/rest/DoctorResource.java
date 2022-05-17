package com.bonobo.rest.errors;


import com.bonobo.rest.error.BadRequestAlertException;
import com.bonobo.service.DoctorService;
import com.bonobo.service.dto.DoctorDto;
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
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.packtpub.micronaut.domain.Vet}.
 */
@Controller("/api")
public class DoctorResource {

    private final Logger log = LoggerFactory.getLogger(DoctorResource.class);

    private static final String ENTITY_NAME = "vet";

    @Value("${micronaut.application.name}")
    private String applicationName;

    private final DoctorService doctorService;

    public DoctorResource(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    /**
     * {@code POST  /vets} : Create a new vet.
     *
     * @param doctorDto the doctorDto to create.
     * @return the {@link HttpResponse} with status {@code 201 (Created)} and with body the new doctorDto, or with status {@code 400 (Bad Request)} if the vet has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Post("/vets")
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<DoctorDto> createVet(@Body DoctorDto doctorDto) throws Exception {
        log.debug("REST request to save Vet : {}", doctorDto);
        if (doctorDto.getId() != null) {
            throw new BadRequestAlertException("A new vet cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DoctorDto result = doctorService.save(doctorDto);
        URI location = new URI("/api/vets/" + result.getId());
        return HttpResponse.created(result).headers(headers -> {
            headers.location(location);
            HeaderUtil.createEntityCreationAlert(headers, applicationName, true, ENTITY_NAME, result.getId().toString());
        });
    }

    /**
     * {@code PUT  /vets} : Updates an existing vet.
     *
     * @param doctorDto the doctorDto to update.
     * @return the {@link HttpResponse} with status {@code 200 (OK)} and with body the updated doctorDto,
     * or with status {@code 400 (Bad Request)} if the doctorDto is not valid,
     * or with status {@code 500 (Internal Server Error)} if the doctorDto couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @Put("/vets")
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<DoctorDto> updateVet(@Body DoctorDto doctorDto) throws Exception {
        log.debug("REST request to update Vet : {}", doctorDto);
        if (doctorDto.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DoctorDto result = doctorService.save(doctorDto);
        return HttpResponse.ok(result).headers(headers ->
                HeaderUtil.createEntityUpdateAlert(headers, applicationName, true, ENTITY_NAME, doctorDto.getId().toString()));
    }

    /**
     * {@code GET  /vets} : get all the vets.
     *
     * @return the {@link HttpResponse} with status {@code 200 (OK)} and the list of vets in body.
     */
    @Get("/vets")
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse<List<DoctorDto>> getAllVets(HttpRequest request) throws Exception {
        log.debug("REST request to get a page of Vets");
        List<DoctorDto> list = (List<DoctorDto>) doctorService.findAll();
        return HttpResponse.ok(list);
    }

    /**
     * {@code GET  /vets/:id} : get the "id" vet.
     *
     * @param id the id of the doctorDto to retrieve.
     * @return the {@link HttpResponse} with status {@code 200 (OK)} and with body the doctorDto, or with status {@code 404 (Not Found)}.
     */
    @Get("/vets/{id}")
    @ExecuteOn(TaskExecutors.IO)
    public Optional<DoctorDto> getVet(@PathVariable Long id) throws Exception {
        log.debug("REST request to get Vet : {}", id);

        return doctorService.findOne(id);
    }

    /**
     * {@code DELETE  /vets/:id} : delete the "id" vet.
     *
     * @param id the id of the doctorDto to delete.
     * @return the {@link HttpResponse} with status {@code 204 (NO_CONTENT)}.
     */
    @Delete("/vets/{id}")
    @ExecuteOn(TaskExecutors.IO)
    public HttpResponse deleteVet(@PathVariable Long id) throws Exception {
        log.debug("REST request to delete Vet : {}", id);
        doctorService.delete(id);
        return HttpResponse.noContent().headers(headers -> HeaderUtil.createEntityDeletionAlert(headers, applicationName, true, ENTITY_NAME, id.toString()));
    }
}
