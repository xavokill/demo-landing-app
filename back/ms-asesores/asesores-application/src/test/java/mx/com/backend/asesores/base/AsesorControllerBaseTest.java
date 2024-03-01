package mx.com.backend.asesores.base;

import org.apache.struts.mock.MockHttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import mx.com.backend.asesores.controller.AsesorController;
import mx.com.backend.asesores.dto.TrAsesorDto;
import mx.com.backend.asesores.service.AsesorService;

/**
 * The Class AsesorControllerBaseTest.
 */
@WebMvcTest(AsesorControllerBaseTest.class)
public class AsesorControllerBaseTest {

  /** The administracion usuario controller. */
  @InjectMocks
  protected AsesorController asesorControllerTest;

  /** The asesor service. */
  @Mock
  protected AsesorService asesorService;

  /** The request. */
  protected MockHttpServletRequest request;

  /** The asesor dto. */
  protected TrAsesorDto asesorDto;

    /**
     * Inits the.
     */
    @BeforeEach
    void init() {
    MockitoAnnotations.openMocks(this);
    this.asesorDto = new TrAsesorDto(1L, "NOMBRE", "APELLIDO PATERNO", "APELLIDO MATERNO", "CORREO ELECTRONICO",
        "CONTRASENIA", null, null, null, 1L, "NAPAM000102");
  }
}
