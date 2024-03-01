package mx.com.backend.prospectos.service.impl;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.List;
import java.util.stream.Collectors;
import mx.com.backend.prospectos.dto.ApplicationEntry;
import mx.com.backend.prospectos.model.ApplicationItem;
import mx.com.backend.prospectos.repository.ApplicationRepository;
import mx.com.backend.prospectos.service.ApplicationService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by.
 */

@Component
public class ApplicationServiceImpl implements ApplicationService {

  /** The Constant log. */
  private static final Logger log = LoggerFactory.getLogger(ApplicationServiceImpl.class);

  /** The model mapper. */
  @Autowired
  private ModelMapper modelMapper;

  /** The application repository. */
  @Autowired
  private ApplicationRepository applicationRepository;

  /** The account sid. */
  @Value("${account.sid}")
  private String accountSid;

  /** The auth token. */
  @Value("${auth.token}")
  private String authToken;

  /** The telefono origen. */
  @Value("${telefono.origen}")
  private String telefonoOrigen;

  /** The email from. */
  @Value("${mail.from}")
  private String emailFrom;

  /** The Constant TELEFONO_DESTINATARIO. */
  private static final String TELEFONO_DESTINATARIO = "+524491186002";

  /** The Constant ASUNTO_EMAIL. */
  private static final String ASUNTO_EMAIL = "Codigo de verifiación";

  /** The Constant MENSAJE_SMS_EMAIL. */
  private static final String MENSAJE_SMS_EMAIL = "Tu codigo de verificación es el siguiente: ";

  /**
   * Creates the application item.
   *
   * @param request the request
   */
  @Transactional
  public void createApplicationItem(ApplicationEntry request) {
    ApplicationItem applicationItem = modelMapper.map(request, ApplicationItem.class);
    applicationRepository.save(applicationItem);
  }

  /**
   * Gets the application items.
   *
   * @return the application items
   */
  public List<ApplicationEntry> getApplicationItems() {
    List<ApplicationEntry> lstReturn = applicationRepository.findAll().stream()
        .map(applicationItem -> modelMapper.map(applicationItem, ApplicationEntry.class)).collect(Collectors.toList());
    return lstReturn;
  }

  /** The email sender. */
  @Autowired
  private JavaMailSender emailSender;

  /**
   * Enviar sms mensage.
   */
  @Override
  public void enviarSmsMensage() {
    Twilio.init(accountSid, authToken);
    Message.creator(new PhoneNumber(TELEFONO_DESTINATARIO), new PhoneNumber(telefonoOrigen), MENSAJE_SMS_EMAIL)
        .create();
  }

  /**
   * Enviar email mensage.
   */
  @Override
  public void enviarEmailMensage() {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(emailFrom);
    message.setTo("edgar.jasso@capgemini.com");
    message.setSubject(ASUNTO_EMAIL);
    message.setText(MENSAJE_SMS_EMAIL);
    emailSender.send(message);
  }
}
