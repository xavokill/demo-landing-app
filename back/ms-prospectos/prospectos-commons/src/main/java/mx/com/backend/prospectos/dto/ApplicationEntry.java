package mx.com.backend.prospectos.dto;

import io.swagger.annotations.ApiModelProperty;
import java.util.Map;
import  lombok.*;

/**
 * Created by.
 */

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationEntry {

  /** The application content. */
  @ApiModelProperty(notes = "the key value alert content for application item description required to be entered by user into REST API ", required = true)
  private Map<String, String> applicationContent;

  /** The application code. */
  @ApiModelProperty(notes = "Applciation code required to be entered by user into REST API ", required = true)
  private String applicationCode;

}
