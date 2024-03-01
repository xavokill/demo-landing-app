package mx.com.backend.asesores.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import mx.com.backend.asesores.service.ApplicationService;

/**
 * Created by.
 */
@RestController
@RequestMapping("")
@Api(value = "Applciation Asesores")
public class ApplicationController {

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(ApplicationController.class);

    /** The application service. */
    @Autowired
    private ApplicationService applicationService;

    /**
     * Gets the version.
     *
     * @return the version
     */
    @GetMapping(produces = "application/json", value = { "/version" })
    public String getVersion() {
            return "1.0";
    }
}
