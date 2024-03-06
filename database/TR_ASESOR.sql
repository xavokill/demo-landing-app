CREATE TABLE tr_asesor (
    id_asesor                  NUMBER(10) NOT NULL,
    nombre_asesor              VARCHAR2(100 CHAR),
    apellido_paterno_asesor    VARCHAR2(100 CHAR),
    apellido_materno_asesor    VARCHAR2(100 CHAR),
    correo_electronico_asesor  VARCHAR2(200 CHAR) NOT NULL,
    contrasenia_asesor         VARCHAR2(250 CHAR) NOT NULL,
    fecha_creacion_asesor      DATE NOT NULL,
    fecha_actualizacion_asesor DATE,
    fecha_estatus_asesor       DATE,
    fk_id_estatus_asesor       NUMBER(10) NOT NULL,
    numero_empleado_asesor     VARCHAR2(50 CHAR) NOT NULL
);

COMMENT ON COLUMN tr_asesor.id_asesor IS
    'IDENTIFICADOR UNICO DE ASESOR';

COMMENT ON COLUMN tr_asesor.nombre_asesor IS
    'NOMBRE DEL ASESOR';

COMMENT ON COLUMN tr_asesor.apellido_paterno_asesor IS
    'APELLIDO PATERNO DEL ASESOR';

COMMENT ON COLUMN tr_asesor.apellido_materno_asesor IS
    'APELLIDO MATERNO DEL ASESOR';

COMMENT ON COLUMN tr_asesor.correo_electronico_asesor IS
    'CORREO ELECTRONICO DEL ASESOR';

COMMENT ON COLUMN tr_asesor.contrasenia_asesor IS
    'CONTRASENIA DEL ASESOR';

COMMENT ON COLUMN tr_asesor.fecha_creacion_asesor IS
    'FECHA DE CREACION DEL ASESOR';

COMMENT ON COLUMN tr_asesor.fecha_actualizacion_asesor IS
    'FECHA DE LA ULTIMA ACTUALIZACION DEL REGISTRO ASESOR';

COMMENT ON COLUMN tr_asesor.fecha_estatus_asesor IS
    'FECHA ESTATUS DEL ASESOR';

COMMENT ON COLUMN tr_asesor.fk_id_estatus_asesor IS
    'ESTATUS DEL ASESOR';

COMMENT ON COLUMN tr_asesor.numero_empleado_asesor IS
    'NUMERO DE EMPLEADO DEL ASESOR';

CREATE INDEX nombre_asesor_idxv1 ON
    tr_asesor (
        nombre_asesor
    ASC );

ALTER TABLE tr_asesor ADD CONSTRAINT tr_asesor_pk PRIMARY KEY ( id_asesor );

ALTER TABLE tr_asesor ADD CONSTRAINT correo_electronico_asesor_uk UNIQUE ( correo_electronico_asesor );

ALTER TABLE tr_asesor ADD CONSTRAINT numero_empleado_asesor_uk UNIQUE ( numero_empleado_asesor );

ALTER TABLE tr_asesor
    ADD CONSTRAINT tr_asesor_tc_estatus_asesor_fk FOREIGN KEY ( fk_id_estatus_asesor )
        REFERENCES tc_estatus_asesor ( id_estatus_asesor );