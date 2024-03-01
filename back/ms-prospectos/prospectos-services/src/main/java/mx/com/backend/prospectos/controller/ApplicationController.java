package mx.com.backend.prospectos.controller;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import io.swagger.annotations.Api;
import mx.com.backend.prospectos.service.ApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by.
 */
@RestController
@RequestMapping("")
@Api(value = "Applciation prospectos")
public class ApplicationController {

  /** The Constant log. */
  private static final Logger log = LoggerFactory.getLogger(ApplicationController.class);

  /** The application service. */
  @Autowired
  private ApplicationService applicationService;

  @GetMapping(value = "/enviarSms")
  public ResponseEntity<String> enviarSms() {
    applicationService.enviarSmsMensage();
    return new ResponseEntity<String>("Mensaje enviado correctamente", HttpStatus.OK);
  }
  
  @GetMapping(value = "/enviarEmail")
  public ResponseEntity<String> enviarEmail() {
    applicationService.enviarEmailMensage();
    return new ResponseEntity<String>("Mensaje enviado correctamente", HttpStatus.OK);
  }
}
