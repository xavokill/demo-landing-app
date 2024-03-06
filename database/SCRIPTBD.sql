-- Generated by Oracle SQL Developer Data Modeler 23.1.0.087.0806
--   at:        2023-10-20 17:39:00 CST
--   site:      Oracle Database 11g
--   type:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

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

ALTER TABLE tr_asesor
    ADD CONSTRAINT tr_asesor_tc_estatus_asesor_fk FOREIGN KEY ( fk_id_estatus_asesor )
        REFERENCES tc_estatus_asesor ( id_estatus_asesor );

ALTER TABLE tr_prospecto
    ADD CONSTRAINT tr_prospecto_tr_asesor_fk FOREIGN KEY ( fk_id_asesor )
        REFERENCES tr_asesor ( id_asesor );



-- Oracle SQL Developer Data Modeler Summary Report: 
-- 
-- CREATE TABLE                             3
-- CREATE INDEX                             1
-- ALTER TABLE                              7
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0

CREATE SEQUENCE  "SEQ_TR_ASESOR"  MINVALUE 1 MAXVALUE 9999999999999999 INCREMENT BY 1 START WITH 1 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
CREATE SEQUENCE  "SEQ_TR_PROSPECTO"  MINVALUE 1 MAXVALUE 9999999999999999 INCREMENT BY 1 START WITH 1 NOCACHE  NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;

INSERT INTO tc_estatus_asesor VALUES (1,'ACTIVO','01');
INSERT INTO tc_estatus_asesor VALUES (2,'INACTIVO','02');


--FEBRERO

CREATE SEQUENCE  "SEQ_TR_CODIGO_AUTENTICACION"  MINVALUE 1 MAXVALUE 9999999999999999 INCREMENT BY 1 START WITH 1;

CREATE TABLE TR_CODIGO_AUTENTICACION (
    ID_CODIGO_AUTENTICACION     			NUMBER(10) NOT NULL,
    CODIGO_AUTENTICACION        			VARCHAR2(100 CHAR) NOT NULL,
    FK_ID_MEDIO_AUTENTICACION  				NUMBER(10) NOT NULL,
    DESTINATARIO                			VARCHAR2(100 CHAR) NOT NULL,
    FECHA_CREACION_CODIGO_AUTENTICACION  	DATE NOT NULL,
    CURP                                    VARCHAR2(18 CHAR) NOT NULL,
    FK_ID_ASESOR                          NUMBER(10) NOT NULL
);

COMMENT ON COLUMN TR_CODIGO_AUTENTICACION.ID_CODIGO_AUTENTICACION IS
    'IDENTIFICADOR UNICO DE CODIGO DE AUTENTICACION';

COMMENT ON COLUMN TR_CODIGO_AUTENTICACION.CODIGO_AUTENTICACION IS
    'CODIGO DE AUTENTICACION GENERADO ALEATORIAMENTE';

COMMENT ON COLUMN TR_CODIGO_AUTENTICACION.FK_ID_MEDIO_AUTENTICACION IS
    'ID UNICO MEDIO AUTENTICACION RELACIONADPO CON TC MEDIO AUTENTICACION';

COMMENT ON COLUMN TR_CODIGO_AUTENTICACION.DESTINATARIO IS
    'DESTINATARIO POR EL CUAL SE AUTENTICO EL PROSPECTO';

COMMENT ON COLUMN TR_CODIGO_AUTENTICACION.FECHA_CREACION_CODIGO_AUTENTICACION IS
    'FECHA CREACION DEL CODIGO DE AUTENTICACION';
    
COMMENT ON COLUMN TR_CODIGO_AUTENTICACION.CURP IS
    'CURP CON LA CUAL SE AUTENTICO EL PROSPECTO';
    
COMMENT ON COLUMN TR_CODIGO_AUTENTICACION.FK_ID_ASESOR IS
    'ID UNICO ASESOR RELACIONADPO CON TR ASESOR';

CREATE TABLE TC_MEDIO_AUTENTICACION (
    ID_MEDIO_AUTENTICACION                  NUMBER(10) NOT NULL,
    DESCRIPCION_MEDIO_AUTENTICACION         VARCHAR2(100 CHAR) NOT NULL,
    CLAVE                                   VARCHAR2(50 CHAR)
);

COMMENT ON COLUMN TC_MEDIO_AUTENTICACION.ID_MEDIO_AUTENTICACION IS
    'IDENTIFICADOR UNICO DE MEDIO AUTENTICACION';

COMMENT ON COLUMN TC_MEDIO_AUTENTICACION.DESCRIPCION_MEDIO_AUTENTICACION IS
    'DESCRIPCION DEL MEDIO DE AUTENTICACION';

COMMENT ON COLUMN TC_MEDIO_AUTENTICACION.CLAVE IS
    'CLAVE DEL MEDIO AUTENTICACION';
    
ALTER TABLE TC_MEDIO_AUTENTICACION ADD CONSTRAINT TC_MEDIO_AUTENTICACION_PK PRIMARY KEY ( ID_MEDIO_AUTENTICACION );

ALTER TABLE TR_CODIGO_AUTENTICACION
    ADD CONSTRAINT TR_CODIGO_AUTEN_TC_MEDIO_AUTEN_FK FOREIGN KEY ( FK_ID_MEDIO_AUTENTICACION )
        REFERENCES TC_MEDIO_AUTENTICACION ( ID_MEDIO_AUTENTICACION );
        
ALTER TABLE TR_CODIGO_AUTENTICACION
    ADD CONSTRAINT TR_CODIGO_AUTEN_TR_ASESOR FOREIGN KEY ( FK_ID_ASESOR )
        REFERENCES TR_ASESOR ( ID_ASESOR );
        
INSERT INTO TC_MEDIO_AUTENTICACION VALUES (1,'SMS','01');
INSERT INTO TC_MEDIO_AUTENTICACION VALUES (2,'EMAIL','02');

COMMIT;