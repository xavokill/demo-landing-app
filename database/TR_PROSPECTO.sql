CREATE TABLE tr_prospecto (
    id_prospecto                 NUMBER(10) NOT NULL,
    correo_electronico_prospecto VARCHAR2(200 CHAR) NOT NULL,
    curp_prospecto               VARCHAR2(18 CHAR) NOT NULL,
    telefono_prosepcto           VARCHAR2(10 CHAR),
    fecha_creacion_prosepcto     DATE NOT NULL,
    fk_id_asesor                 NUMBER(10) NOT NULL
);

COMMENT ON COLUMN tr_prospecto.id_prospecto IS
    'IDENTIFICADOR UNICO DEL PROSPECTO';

COMMENT ON COLUMN tr_prospecto.correo_electronico_prospecto IS
    'CORREO ELECTRONICO DEL PROSPECTO';

COMMENT ON COLUMN tr_prospecto.curp_prospecto IS
    'CURP DEL PROSEPCTO';

COMMENT ON COLUMN tr_prospecto.fecha_creacion_prosepcto IS
    'FECHA CREACION PROSEPCTO';

COMMENT ON COLUMN tr_prospecto.fk_id_asesor IS
    'IDENTIFICADOR UNICO DEL ASESOR ASIGNADO';

ALTER TABLE tr_prospecto ADD CONSTRAINT tr_prospecto_pk PRIMARY KEY ( id_prospecto );

ALTER TABLE tr_prospecto
    ADD CONSTRAINT tr_prospecto_tr_asesor_fk FOREIGN KEY ( fk_id_asesor )
        REFERENCES tr_asesor ( id_asesor );