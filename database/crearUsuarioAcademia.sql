--ORACLE CON MOTOR DE VERSION 11G
 
    CREATE USER adminUserFullStack     
    IDENTIFIED BY adminUserFullStackPassword;
    
    GRANT CONNECT TO adminUserFullStack;
    
    GRANT CREATE SESSION TO adminUserFullStack;
    
    GRANT ALL PRIVILEGES TO adminUserFullStack;
    
    GRANT UNLIMITED TABLESPACE TO adminUserFullStack;

--ORACLE CON MOTOR DE VERSIÓN 12 C O SUPERIOR

    alter session set "_ORACLE_SCRIPT"=true;

    --EL USUARIO SERÍA adminUserFullStack Y EL PASS SERÍA adminUserFullStackPassword

    CREATE USER adminUserFullStack     
    IDENTIFIED BY adminUserFullStackPassword; 
    
    GRANT CONNECT TO adminUserFullStack;
    
    GRANT CREATE SESSION TO adminUserFullStack;
    
    GRANT ALL PRIVILEGES TO adminUserFullStack;
    
    GRANT UNLIMITED TABLESPACE TO adminUserFullStack;