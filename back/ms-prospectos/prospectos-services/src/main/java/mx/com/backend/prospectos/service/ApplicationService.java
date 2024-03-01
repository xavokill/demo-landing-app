package mx.com.backend.prospectos.service;

import java.util.List;
import mx.com.backend.prospectos.dto.ApplicationEntry;

/**
 * The Interface ApplicationService.
 */
public interface ApplicationService {

  /**
   * Creates the application item.
   *
   * @param request the request
   */
  public void createApplicationItem(ApplicationEntry request);

  /**
   * Gets the application items.
   *
   * @return the application items
   */
  public List<ApplicationEntry> getApplicationItems();
  
  public void enviarEmailMensage();

  public void enviarSmsMensage();

}
