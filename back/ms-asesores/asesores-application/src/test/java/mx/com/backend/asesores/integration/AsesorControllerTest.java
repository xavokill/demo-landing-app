package mx.com.backend.asesores.integration;

import mx.com.backend.asesores.base.AsesorControllerBaseTest;
import mx.com.backend.asesores.dto.TrAsesorDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * The Class AsesorControllerTest.
 */
class AsesorControllerTest extends AsesorControllerBaseTest {

    /**
     * Test crear asesor.
     */
    @Test
    void testCrearAsesor() {
    when(asesorService.crearAsesor(any(TrAsesorDto.class))).thenReturn(this.asesorDto);
    ResponseEntity<TrAsesorDto> responseEntity = this.asesorControllerTest.crearAsesor(this.asesorDto);
    assertNotNull(responseEntity, "Respuesta nula");
    assertNotNull(responseEntity.getBody(), "Body nulo");
    assertNotNull(responseEntity.getStatusCode(), "Código de estatus nulo");
    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode(), "Valor de entrada distinto a valor esperado");
    assertEquals(this.asesorDto, responseEntity.getBody(), "Valor de entrada distinto a valor esperado");
  }
}
