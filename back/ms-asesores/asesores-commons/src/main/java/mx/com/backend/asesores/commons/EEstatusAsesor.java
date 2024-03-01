package mx.com.backend.asesores.commons;

import lombok.Getter;

/**
 * The Enum EEstatusAsesor.
 */

@Getter
public enum EEstatusAsesor {

    /** The activo. */
    ACTIVO(1L, "CANCELADA AUTOTRASPASO"),

    /** The inactivo. */
    INACTIVO(2L, "CIERRE AUTOTRASPASO");

    /** The id estatus asesor. */
    private long idEstatusAsesor;

    /** The descripcion. */
    private String descripcion;

    /**
     * Instantiates a new e estatus asesor.
     *
     * @param idEstatusAsesor the id estatus asesor
     * @param descripcion the descripcion
     */
    EEstatusAsesor(long idEstatusAsesor, String descripcion) {
        this.idEstatusAsesor = idEstatusAsesor;
        this.descripcion = descripcion;
    }
}
