CREATE TABLE tc_estatus_asesor (
    id_estatus_asesor   NUMBER(10) NOT NULL,
    descripcion_estatus VARCHAR2(100 CHAR) NOT NULL,
    clave               VARCHAR2(50 CHAR)
);

COMMENT ON COLUMN tc_estatus_asesor.id_estatus_asesor IS
    'IDENTIFICADOR UNICO DE ESTATUS ASESOR';

COMMENT ON COLUMN tc_estatus_asesor.descripcion_estatus IS
    'DESCRIPCION DE ESTATUS ASESOR ';

COMMENT ON COLUMN tc_estatus_asesor.clave IS
    'CLAVE DEL ESTATUS ASESOR';

ALTER TABLE tc_estatus_asesor ADD CONSTRAINT tc_estatus_asesor_pk PRIMARY KEY ( id_estatus_asesor );
