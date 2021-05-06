CREATE OR REPLACE PACKAGE BODY PKG_SEGURIDAD AS
  PROCEDURE SP_NUEVO_SISTEMA(P_VDESCRIPCION S_GUIA.SISTEMA.VDESCRIPCION%TYPE,
                             P_VABREVIATURA S_GUIA.SISTEMA.VABREVIATURA%TYPE,
                             P_NESTADO      S_GUIA.SISTEMA.NESTADO%TYPE,
                             P_VPROGRAMA    S_GUIA.SISTEMA.VPROGRAMA%TYPE,
                             P_VFLAG_NIVEL  S_GUIA.SISTEMA.VFLAG_NIVEL%TYPE,
                             P_VUSUARIO     S_GUIA.SISTEMA.VRESCRE%TYPE) IS
  BEGIN
    INSERT INTO S_GUIA.SISTEMA
      (NCODSISTEMA,
       VDESCRIPCION,
       VABREVIATURA,
       NESTADO,
       VPROGRAMA,
       VFLAG_NIVEL,
       VRESCRE,
       DFECCRE,
       VRESACT,
       DFECACT)
    VALUES
      ((SELECT MAX(NCODSISTEMA) FROM S_GUIA.SISTEMA) + 1,
       P_VDESCRIPCION,
       P_VABREVIATURA,
       P_NESTADO,
       P_VPROGRAMA,
       P_VFLAG_NIVEL,
       P_VUSUARIO,
       sysdate,
       P_VUSUARIO,
       sysdate);
  END SP_NUEVO_SISTEMA;

  PROCEDURE SP_UPDATE_SISTEMA(P_NCODSISTEMA  S_GUIA.SISTEMA.NCODSISTEMA%TYPE,
                              P_VDESCRIPCION S_GUIA.SISTEMA.VDESCRIPCION%TYPE,
                              P_VABREVIATURA S_GUIA.SISTEMA.VABREVIATURA%TYPE,
                              P_NESTADO      S_GUIA.SISTEMA.NESTADO%TYPE,
                              P_VPROGRAMA    S_GUIA.SISTEMA.VPROGRAMA%TYPE,
                              P_VFLAG_NIVEL  S_GUIA.SISTEMA.VFLAG_NIVEL%TYPE,
                              P_VUSUARIO     S_GUIA.SISTEMA.VRESCRE%TYPE) IS
  BEGIN
    UPDATE S_GUIA.SISTEMA
       SET VDESCRIPCION = P_VDESCRIPCION,
           VABREVIATURA = P_VABREVIATURA,
           VPROGRAMA    = P_VPROGRAMA,
           NESTADO      = P_NESTADO,
           VFLAG_NIVEL  = P_VFLAG_NIVEL,
           VRESACT      = P_VUSUARIO,
           DFECACT      = sysdate
     WHERE NCODSISTEMA = P_NCODSISTEMA;
  END SP_UPDATE_SISTEMA;

  PROCEDURE SP_OBTENER_SISTEMAS(pCursor OUT sys_refcursor) AS
  BEGIN
    OPEN pCursor FOR
      SELECT S.NCODSISTEMA,
             UPPER(NVL(S.VDESCRIPCION, '')) VDESCRIPCION,
             S.VABREVIATURA,
             S.VPROGRAMA,
             S.NESTADO,
             P.VDESCRIPCION VESTADONOMBRE,
             S.VFLAG_NIVEL,
             S.VRESCRE,
             S.DFECCRE,
             S.VRESACT,
             S.DFECACT,
             PA.VDESCRIPCION VNIVELNOMBRE
        FROM S_GUIA.SISTEMA S
        LEFT JOIN S_GUIA.SISTEMA_PARAMETRO P ON P.VCODPARAMETRO =
                                                'ESTADO_SISTEMA'
                                            AND P.NID = S.NESTADO
        LEFT JOIN S_GUIA.SISTEMA_PARAMETRO PA ON PA.VCODPARAMETRO = 'NIVEL'
                                             AND PA.NID = S.VFLAG_NIVEL
       ORDER BY S.NCODSISTEMA ASC;
  
  END SP_OBTENER_SISTEMAS;

  PROCEDURE SP_OBTENER_SISTEMAS_ACTIVOS(pCursor OUT sys_refcursor) AS
  BEGIN
    OPEN pCursor FOR
      SELECT S.NCODSISTEMA,
             UPPER(NVL(S.VDESCRIPCION, '')) VDESCRIPCION,
             S.VABREVIATURA,
             S.VPROGRAMA,
             S.NESTADO,
             P.VDESCRIPCION VESTADONOMBRE,
             S.VFLAG_NIVEL,
             S.VRESCRE,
             S.DFECCRE,
             S.VRESACT,
             S.DFECACT,
             PA.VDESCRIPCION VNIVELNOMBRE
        FROM S_GUIA.SISTEMA S
        LEFT JOIN S_GUIA.SISTEMA_PARAMETRO P ON P.VCODPARAMETRO =
                                                'ESTADO_SISTEMA'
                                            AND P.NID = S.NESTADO
        LEFT JOIN S_GUIA.SISTEMA_PARAMETRO PA ON PA.VCODPARAMETRO = 'NIVEL'
                                             AND PA.NID = S.VFLAG_NIVEL
       WHERE S.NESTADO = 0
       ORDER BY S.NCODSISTEMA ASC;
  
  END SP_OBTENER_SISTEMAS_ACTIVOS;

  PROCEDURE SP_OBTENER_SISTEMAS_POR_ABRE(P_VABREVIATURA S_GUIA.SISTEMA.VABREVIATURA%TYPE,
                                         pCursor        OUT sys_refcursor) AS
  BEGIN
  
    OPEN pCursor FOR
      SELECT S.NCODSISTEMA,
             UPPER(NVL(S.VDESCRIPCION, '')) VDESCRIPCION,
             S.VABREVIATURA,
             S.VPROGRAMA,
             S.NESTADO,
             P.VDESCRIPCION VESTADONOMBRE,
             S.VFLAG_NIVEL,
             S.VRESCRE,
             S.DFECCRE,
             S.VRESACT,
             S.DFECACT,
             PA.VDESCRIPCION VNIVELNOMBRE
        FROM S_GUIA.SISTEMA S
      
        LEFT JOIN S_GUIA.SISTEMA_PARAMETRO P ON P.VCODPARAMETRO =
                                                'ESTADO_SISTEMA'
                                            AND P.NID = S.NESTADO
        LEFT JOIN S_GUIA.SISTEMA_PARAMETRO PA ON PA.VCODPARAMETRO = 'NIVEL'
                                             AND PA.NID = S.VFLAG_NIVEL
       WHERE S.VABREVIATURA = P_VABREVIATURA;
  
  END SP_OBTENER_SISTEMAS_POR_ABRE;

  PROCEDURE SP_OBTENER_SISTEMA_ID(P_NCODSISTEMA S_GUIA.SISTEMA.NCODSISTEMA%TYPE,
                                  pCursor       OUT sys_refcursor) AS
  BEGIN
    OPEN pCursor FOR
      SELECT S.NCODSISTEMA,
             UPPER(NVL(S.VDESCRIPCION, '')) VDESCRIPCION,
             S.VABREVIATURA,
             S.VPROGRAMA,
             S.NESTADO,
             P.VDESCRIPCION VESTADONOMBRE,
             S.VFLAG_NIVEL,
             S.VRESCRE,
             S.DFECCRE,
             S.VRESACT,
             S.DFECACT,
             PA.VDESCRIPCION VNIVELNOMBRE
        FROM S_GUIA.SISTEMA S
        LEFT JOIN S_GUIA.SISTEMA_PARAMETRO P ON P.VCODPARAMETRO =
                                                'ESTADO_SISTEMA'
                                            AND P.NID = S.NESTADO
        LEFT JOIN S_GUIA.SISTEMA_PARAMETRO PA ON PA.VCODPARAMETRO = 'NIVEL'
                                             AND PA.NID = S.VFLAG_NIVEL
       WHERE NCODSISTEMA = P_NCODSISTEMA;
  END SP_OBTENER_SISTEMA_ID;

  PROCEDURE SP_UPDATE_SISTEMA_ESTADO(P_NCODSISTEMA S_GUIA.SISTEMA.NCODSISTEMA%TYPE,
                                     P_NESTADO     S_GUIA.SISTEMA.NESTADO%TYPE,
                                     P_VUSUARIO    S_GUIA.SISTEMA.VRESCRE%TYPE) IS
  BEGIN
    UPDATE S_GUIA.SISTEMA
       SET NESTADO = P_NESTADO, VRESACT = P_VUSUARIO, DFECACT = sysdate
     WHERE NCODSISTEMA = P_NCODSISTEMA;
  END SP_UPDATE_SISTEMA_ESTADO;

  PROCEDURE SP_OBTENER_USUARIO_PERFIL_TOT(P_ValueSearch IN VARCHAR,
                                          P_TotalReg    OUT NUMBER) AS
    v_Search VARCHAR2(50);
    n_Total  INT;
  BEGIN
    IF LENGTH(LTRIM(P_ValueSearch)) > 0 THEN
      v_Search := '%' || P_ValueSearch || '%';
    ELSE
      v_Search := NULL;
    END IF;
  
    SELECT COUNT(VESTADONOMBRE)
      INTO n_Total
      FROM VW_USUARIO_SISTEMA
     WHERE VBUSCAR LIKE NVL(v_Search, VBUSCAR);
  
    P_TotalReg := NVL(n_Total, 0);
  END SP_OBTENER_USUARIO_PERFIL_TOT;

  PROCEDURE SP_OBTENER_USUARIO_PERFIL_PAG(P_PageSize    IN INT,
                                          P_PageIndex   IN INT,
                                          P_ValueSearch IN VARCHAR,
                                          P_SortColumn  IN VARCHAR,
                                          pCursor       OUT sys_refcursor) AS
    FirstIndex INT;
    LastIndex  INT;
    v_SQL      VARCHAR2(4000);
    v_Search   VARCHAR2(50);
  BEGIN
    LastIndex  := P_PageSize * (P_PageIndex + 1);
    FirstIndex := LastIndex - P_PageSize + 1;
  
    IF LENGTH(LTRIM(P_ValueSearch)) > 0 THEN
      v_Search := '%' || P_ValueSearch || '%';
    ELSE
      v_Search := NULL;
    END IF;
  
    OPEN pCursor FOR
      SELECT *
        FROM (SELECT a.*, ROWNUM AS rnum
                FROM (SELECT NCODSISTEMA,
                             VCODUSUARIO,
                             NESTADO,
                             VSISTEMANOMBRE,
                             VDESCRIPCION,
                             NCODFICHA,
                             NCODAREA,
                             VESTADONOMBRE,
                             AREA,
                             AREAABREV,
                             AREATRAB,
                             NOMBREPERFIL,
                             ESTTRAB,
                             VDIRELECTRONICA
                        FROM VW_USUARIO_SISTEMA
                       WHERE VBUSCAR LIKE NVL(v_Search, VBUSCAR)) a
              --                       WHERE VBUSCAR LIKE v_Search ) a
               WHERE ROWNUM <= LastIndex)
       WHERE rnum >= FirstIndex;
  END SP_OBTENER_USUARIO_PERFIL_PAG;

  PROCEDURE SP_OBTENER_USUARIO(pCursor OUT sys_refcursor) AS
  BEGIN
    OPEN pCursor FOR
      SELECT VCODUSUARIO,
             VDESCRIPCION,
             NESTADO,
             NCODAREA,
             DFECCREACION,
             DFECACTUALIZACION,
             VRESPONSABLE,
             VZONA,
             NCODFICHA,
             NINDICADOR,
             VPASS,
             DFECULTVER,
             DFECCLAVE,
             NCONEXION,
             VSUSTENTACION,
             VDOC,
             NUMERO_INTENTOS,
             DFEC_INICIO_VIGENCIA,
             DFEC_FIN_VIGENCIA,
             VFLAG_CAMBIAR_CLAVE
        FROM S_GUIA.USUARIO
       WHERE VCODUSUARIO IN ('LCASTRO', 'JMOYANO');
  END SP_OBTENER_USUARIO;

  PROCEDURE SP_OBTENER_USUARIO_PERFIL(pCursor OUT sys_refcursor) AS
  BEGIN
    OPEN pCursor FOR
      SELECT U.VCODUSUARIO,
             U.VDESCRIPCION,
             U.NESTADO,
             NCODAREA,
             DFECCREACION,
             DFECACTUALIZACION,
             VRESPONSABLE,
             VZONA,
             NCODFICHA,
             NINDICADOR,
             VPASS,
             DFECULTVER,
             DFECCLAVE,
             NCONEXION,
             VSUSTENTACION,
             VDOC,
             NUMERO_INTENTOS,
             DFEC_INICIO_VIGENCIA,
             DFEC_FIN_VIGENCIA,
             VFLAG_CAMBIAR_CLAVE,
             P.NCODPERFIL,
             P.NCODSISTEMA,
             UPPER(E.VDESCRIPCION) VPERFILNOMBRE,
             UPPER(S.VDESCRIPCION) VSISTEMANOMBRE
        FROM S_GUIA.USUARIO U
       INNER JOIN USUARIO_PERFIL_SISTEMA P ON U.VCODUSUARIO = P.VCODUSUARIO
       INNER JOIN PERFIL_SISTEMA E ON E.NCODSISTEMA = P.NCODSISTEMA
                                  AND E.NCODPERFIL = P.NCODPERFIL
       INNER JOIN SISTEMA S ON E.NCODSISTEMA = S.NCODSISTEMA
       WHERE U.VCODUSUARIO IN
             ('LCASTRO', 'JMOYANO', 'ABCD', 'ALDANA', 'MHERRERA');
  END SP_OBTENER_USUARIO_PERFIL;

  PROCEDURE SP_OBTENER_USUARIO_POR_USUARIO(P_VUSUARIO S_GUIA.USUARIO.VCODUSUARIO%TYPE,
                                           pCursor    OUT sys_refcursor) IS
  BEGIN
    OPEN pCursor FOR
      SELECT U.VCODUSUARIO,
             U.VDESCRIPCION,
             U.NESTADO,
             U.NCODAREA,
             U.DFECCREACION,
             U.DFECACTUALIZACION,
             U.VRESPONSABLE,
             U.VZONA,
             U.NCODFICHA,
             U.NINDICADOR,
             U.VPASS,
             U.DFECULTVER,
             U.DFECCLAVE,
             U.NCONEXION,
             U.VSUSTENTACION,
             U.VDOC,
             U.NUMERO_INTENTOS,
             U.DFEC_INICIO_VIGENCIA,
             U.DFEC_FIN_VIGENCIA,
             U.VFLAG_CAMBIAR_CLAVE,
             UPS.NCODSISTEMA,
             UPS.NCODPERFIL
        FROM S_GUIA.USUARIO U
        LEFT JOIN USUARIO_PERFIL_SISTEMA UPS ON UPS.VCODUSUARIO =
                                                U.VCODUSUARIO
       WHERE U.VCODUSUARIO = P_VUSUARIO;
  END SP_OBTENER_USUARIO_POR_USUARIO;

  PROCEDURE SP_OBTENER_USUARIO_SISTEMA_ID(P_VUSUARIO    S_GUIA.USUARIO.VCODUSUARIO%TYPE,
                                          P_NCODSISTEMA S_GUIA.USUARIO_PERFIL_SISTEMA.NCODSISTEMA%TYPE,
                                          pCursor       OUT sys_refcursor) IS
  BEGIN
    OPEN pCursor FOR
      SELECT U.VCODUSUARIO,
             U.VDESCRIPCION,
             U.NESTADO,
             U.NCODAREA,
             U.DFECCREACION,
             U.DFECACTUALIZACION,
             U.VRESPONSABLE,
             U.VZONA,
             U.NCODFICHA,
             U.NINDICADOR,
             U.VPASS,
             U.DFECULTVER,
             U.DFECCLAVE,
             U.NCONEXION,
             U.VSUSTENTACION,
             U.VDOC,
             U.NUMERO_INTENTOS,
             U.DFEC_INICIO_VIGENCIA,
             U.DFEC_FIN_VIGENCIA,
             U.VFLAG_CAMBIAR_CLAVE,
             UPS.NCODSISTEMA,
             UPS.NCODPERFIL
        FROM S_GUIA.USUARIO U
        LEFT JOIN USUARIO_PERFIL_SISTEMA UPS ON UPS.VCODUSUARIO =
                                                U.VCODUSUARIO
       WHERE U.VCODUSUARIO = P_VUSUARIO
         AND UPS.NCODSISTEMA = P_NCODSISTEMA;
  END SP_OBTENER_USUARIO_SISTEMA_ID;

  PROCEDURE SP_NUEVO_USUARIO(P_VCODUSUARIO  S_GUIA.USUARIO.VCODUSUARIO%TYPE,
                             P_VDESCRIPCION S_GUIA.USUARIO.VDESCRIPCION%TYPE,
                             P_NESTADO      S_GUIA.USUARIO.NESTADO%TYPE,
                             P_NCODAREA     S_GUIA.USUARIO.NCODAREA%TYPE,
                             --P_DFECCREACION      S_GUIA.USUARIO.DFECCREACION%TYPE,
                             --P_DFECACTUALIZACION S_GUIA.USUARIO.DFECACTUALIZACION%TYPE,
                             P_VRESPONSABLE S_GUIA.USUARIO.VRESPONSABLE%TYPE,
                             P_VZONA        S_GUIA.USUARIO.VZONA%TYPE,
                             P_NCODFICHA    S_GUIA.USUARIO.NCODFICHA%TYPE,
                             P_NINDICADOR   S_GUIA.USUARIO.NINDICADOR%TYPE,
                             P_VPASS        S_GUIA.USUARIO.VPASS%TYPE,
                             --P_DFECULTVER        S_GUIA.USUARIO.DFECULTVER%TYPE,
                             --P_DFECCLAVE         S_GUIA.USUARIO.DFECCLAVE%TYPE,
                             P_NCONEXION           S_GUIA.USUARIO.NCONEXION%TYPE,
                             P_VSUSTENTACION       S_GUIA.USUARIO.VSUSTENTACION%TYPE,
                             P_VDOC                S_GUIA.USUARIO.VDOC%TYPE,
                             P_VFLAG_CAMBIAR_CLAVE S_GUIA.USUARIO.VFLAG_CAMBIAR_CLAVE%TYPE,
                             P_NCODSISTEMA         S_GUIA.SISTEMA.NCODSISTEMA%TYPE,
                             P_NCODPERFIL          S_GUIA.USUARIO_PERFIL_SISTEMA.NCODPERFIL%TYPE) AS
  BEGIN
    INSERT INTO S_GUIA.USUARIO
      (VCODUSUARIO,
       VDESCRIPCION,
       NESTADO,
       NCODAREA,
       DFECCREACION,
       DFECACTUALIZACION,
       VRESPONSABLE,
       VZONA,
       NCODFICHA,
       NINDICADOR,
       VPASS,
       --DFECULTVER,
       --DFECCLAVE,
       NCONEXION,
       VSUSTENTACION,
       VDOC,
       VFLAG_CAMBIAR_CLAVE)
    VALUES
      (P_VCODUSUARIO,
       P_VDESCRIPCION,
       P_NESTADO,
       P_NCODAREA,
       --P_DFECCREACION,
       sysdate,
       --P_DFECACTUALIZACION,
       sysdate,
       P_VRESPONSABLE,
       P_VZONA,
       P_NCODFICHA,
       P_NINDICADOR,
       P_VPASS,
       --P_DFECULTVER,
       --P_DFECCLAVE,
       P_NCONEXION,
       P_VSUSTENTACION,
       P_VDOC,
       P_VFLAG_CAMBIAR_CLAVE);
  
    INSERT INTO SECURE_BD.LOG_AUDITA_BD
      (SISTEMA,
       FECHA,
       USUARIO,
       ACCION,
       SUSTENTO,
       USERCREA,
       IP,
       MAC,
       OBSERVACION,
       PERFIL,
       AREA,
       ESTUSER,
       DESCRIPCION,
       DATO1)
    VALUES
      (P_NCODSISTEMA,
       SYSDATE,
       P_VCODUSUARIO,
       'ALTER',
       P_VSUSTENTACION,
       P_VRESPONSABLE,
       ' ',
       ' ',
       ' ',
       P_NCODPERFIL,
       P_NCODAREA,
       1,
       P_VDESCRIPCION,
       ' ');
  
  END SP_NUEVO_USUARIO;

  PROCEDURE SP_UPDATE_USUARIO(P_VCODUSUARIO  S_GUIA.USUARIO.VCODUSUARIO%TYPE,
                              P_VDESCRIPCION S_GUIA.USUARIO.VDESCRIPCION%TYPE,
                              P_NESTADO      S_GUIA.USUARIO.NESTADO%TYPE,
                              P_NCODAREA     S_GUIA.USUARIO.NCODAREA%TYPE,
                              --P_DFECCREACION      S_GUIA.USUARIO.DFECCREACION%TYPE,
                              --P_DFECACTUALIZACION S_GUIA.USUARIO.DFECACTUALIZACION%TYPE,
                              P_VRESPONSABLE S_GUIA.USUARIO.VRESPONSABLE%TYPE,
                              P_VZONA        S_GUIA.USUARIO.VZONA%TYPE,
                              P_NCODFICHA    S_GUIA.USUARIO.NCODFICHA%TYPE,
                              P_NINDICADOR   S_GUIA.USUARIO.NINDICADOR%TYPE,
                              P_VPASS        S_GUIA.USUARIO.VPASS%TYPE,
                              --P_DFECULTVER        S_GUIA.USUARIO.DFECULTVER%TYPE,
                              --P_DFECCLAVE         S_GUIA.USUARIO.DFECCLAVE%TYPE,
                              P_NCONEXION           S_GUIA.USUARIO.NCONEXION%TYPE,
                              P_VSUSTENTACION       S_GUIA.USUARIO.VSUSTENTACION%TYPE,
                              P_VDOC                S_GUIA.USUARIO.VDOC%TYPE,
                              P_VFLAG_CAMBIAR_CLAVE S_GUIA.USUARIO.VFLAG_CAMBIAR_CLAVE%TYPE,
                              P_NCODSISTEMA         S_GUIA.SISTEMA.NCODSISTEMA%TYPE,
                              P_NCODPERFIL          S_GUIA.USUARIO_PERFIL_SISTEMA.NCODPERFIL%TYPE) AS
  BEGIN
    UPDATE S_GUIA.USUARIO
       SET VDESCRIPCION = P_VDESCRIPCION,
           NESTADO      = P_NESTADO,
           NCODAREA     = P_NCODAREA,
           --DFECCREACION      = P_DFECCREACION,
           DFECACTUALIZACION = sysdate,
           VRESPONSABLE      = P_VRESPONSABLE,
           VZONA             = P_VZONA,
           NCODFICHA         = P_NCODFICHA,
           NINDICADOR        = P_NINDICADOR,
           VPASS             = P_VPASS,
           --DFECULTVER        = P_DFECULTVER,
           --DFECCLAVE         = P_DFECCLAVE,
           NCONEXION           = P_NCONEXION,
           VSUSTENTACION       = P_VSUSTENTACION,
           VDOC                = P_VDOC,
           VFLAG_CAMBIAR_CLAVE = P_VFLAG_CAMBIAR_CLAVE,
           NUMERO_INTENTOS     = 0
     WHERE VCODUSUARIO = P_VCODUSUARIO;
  
    INSERT INTO SECURE_BD.LOG_AUDITA_BD
      (SISTEMA,
       FECHA,
       USUARIO,
       ACCION,
       SUSTENTO,
       USERCREA,
       IP,
       MAC,
       OBSERVACION,
       PERFIL,
       AREA,
       ESTUSER,
       DESCRIPCION,
       DATO1)
    VALUES
      (P_NCODSISTEMA,
       SYSDATE,
       P_VCODUSUARIO,
       'ALTER',
       P_VSUSTENTACION,
       P_VRESPONSABLE,
       ' ',
       ' ',
       ' ',
       P_NCODPERFIL,
       P_NCODAREA,
       1,
       P_VDESCRIPCION,
       ' ');
  
  END SP_UPDATE_USUARIO;

  PROCEDURE SP_UPDATE_USUARIO_ESTADO(P_VCODUSUARIO  S_GUIA.USUARIO.VCODUSUARIO%TYPE,
                                     P_NESTADO      S_GUIA.USUARIO.NESTADO%TYPE,
                                     P_VRESPONSABLE S_GUIA.USUARIO.VRESPONSABLE%TYPE,
                                     P_NCODSISTEMA  S_GUIA.USUARIO_PERFIL_SISTEMA.NCODSISTEMA%TYPE) IS
  
    V_SISTEMA       VARCHAR2(15);
    V_PERFIL        NUMERIC(3);
    V_AREA          NUMERIC(4);
    P_VDESCRIPCION  VARCHAR2(80);
    P_VSUSTENTACION VARCHAR2(500);
  BEGIN
  
    SELECT UPS.NCODSISTEMA,
           UPS.NCODPERFIL,
           U.NCODAREA,
           U.VDESCRIPCION,
           U.VSUSTENTACION
      INTO V_SISTEMA, V_PERFIL, V_AREA, P_VDESCRIPCION, P_VSUSTENTACION
      FROM S_GUIA.USUARIO U
      LEFT JOIN USUARIO_PERFIL_SISTEMA UPS ON UPS.VCODUSUARIO =
                                              U.VCODUSUARIO
     WHERE U.VCODUSUARIO = P_VCODUSUARIO
       AND UPS.NCODSISTEMA = P_NCODSISTEMA;
  
    UPDATE S_GUIA.USUARIO
       SET NESTADO           = P_NESTADO,
           VRESPONSABLE      = P_VRESPONSABLE,
           DFECACTUALIZACION = sysdate
     WHERE VCODUSUARIO = P_VCODUSUARIO;
  
    INSERT INTO SECURE_BD.LOG_AUDITA_BD
      (SISTEMA,
       FECHA,
       USUARIO,
       ACCION,
       SUSTENTO,
       USERCREA,
       IP,
       MAC,
       OBSERVACION,
       PERFIL,
       AREA,
       ESTUSER,
       DESCRIPCION,
       DATO1)
    VALUES
      (V_SISTEMA,
       SYSDATE,
       P_VCODUSUARIO,
       'ALTER',
       P_VSUSTENTACION,
       P_VRESPONSABLE,
       ' ',
       ' ',
       ' ',
       V_PERFIL,
       V_AREA,
       1,
       P_VDESCRIPCION,
       ' ');
  
  END SP_UPDATE_USUARIO_ESTADO;

  PROCEDURE SP_UPDATE_USUARIO_BLOQUEADO(P_VCODUSUARIO     S_GUIA.USUARIO.VCODUSUARIO%TYPE,
                                        P_NESTADO         S_GUIA.USUARIO.NESTADO%TYPE,
                                        P_NUMERO_INTENTOS S_GUIA.USUARIO.NUMERO_INTENTOS%TYPE,
                                        P_VRESPONSABLE    S_GUIA.USUARIO.VRESPONSABLE%TYPE,
                                        P_NCODSISTEMA     S_GUIA.USUARIO_PERFIL_SISTEMA.NCODSISTEMA%TYPE) IS
  
    V_SISTEMA       VARCHAR2(15);
    V_PERFIL        NUMERIC(3);
    V_AREA          NUMERIC(4);
    P_VDESCRIPCION  VARCHAR2(80);
    P_VSUSTENTACION VARCHAR2(500);
  BEGIN
  
    SELECT UPS.NCODSISTEMA,
           UPS.NCODPERFIL,
           U.NCODAREA,
           U.VDESCRIPCION,
           U.VSUSTENTACION
      INTO V_SISTEMA, V_PERFIL, V_AREA, P_VDESCRIPCION, P_VSUSTENTACION
      FROM S_GUIA.USUARIO U
      LEFT JOIN USUARIO_PERFIL_SISTEMA UPS ON UPS.VCODUSUARIO =
                                              U.VCODUSUARIO
     WHERE U.VCODUSUARIO = P_VCODUSUARIO
       AND UPS.NCODSISTEMA = P_NCODSISTEMA;
  
    UPDATE S_GUIA.USUARIO
       SET NESTADO           = P_NESTADO,
           NUMERO_INTENTOS   = P_NUMERO_INTENTOS,
           VRESPONSABLE      = P_VRESPONSABLE,
           DFECACTUALIZACION = sysdate
     WHERE VCODUSUARIO = P_VCODUSUARIO;
  
    INSERT INTO SECURE_BD.LOG_AUDITA_BD
      (SISTEMA,
       FECHA,
       USUARIO,
       ACCION,
       SUSTENTO,
       USERCREA,
       IP,
       MAC,
       OBSERVACION,
       PERFIL,
       AREA,
       ESTUSER,
       DESCRIPCION,
       DATO1)
    VALUES
      (V_SISTEMA,
       SYSDATE,
       P_VCODUSUARIO,
       'ALTER',
       P_VSUSTENTACION,
       P_VRESPONSABLE,
       ' ',
       ' ',
       ' ',
       V_PERFIL,
       V_AREA,
       1,
       P_VDESCRIPCION,
       ' ');
  
  END SP_UPDATE_USUARIO_BLOQUEADO;

  ---//---- START //-- USUARIO PERFIL SISTEMA
  PROCEDURE SP_OBTENER_USUARIO_SISTEMA(pCursor OUT sys_refcursor) IS
  BEGIN
    OPEN pCursor FOR
      SELECT UPS.NCODSISTEMA,
             U.VCODUSUARIO,
             U.NESTADO,
             UPPER(S.VDESCRIPCION) VSISTEMANOMBRE,
             U.VDESCRIPCION,
             U.NCODFICHA,
             U.NCODAREA,
             P.VDESCRIPCION VESTADONOMBRE
        FROM S_GUIA.USUARIO U
       INNER JOIN S_GUIA.SISTEMA_PARAMETRO P ON P.VCODPARAMETRO = 'ESTADO'
                                            AND P.NID = U.NESTADO
        LEFT JOIN USUARIO_PERFIL_SISTEMA UPS ON U.VCODUSUARIO =
                                                UPS.VCODUSUARIO
        LEFT JOIN SISTEMA S ON UPS.NCODSISTEMA = S.NCODSISTEMA
      --WHERE U.VCODUSUARIO IN ('LCASTRO', 'JMOYANO', 'ABCD', 'ALDANA', 'MHERRERA','ONEDEMO')
       GROUP BY UPS.NCODSISTEMA,
                U.VCODUSUARIO,
                U.NESTADO,
                S.VDESCRIPCION,
                U.VDESCRIPCION,
                U.NCODFICHA,
                U.NCODAREA,
                P.VDESCRIPCION;
  END SP_OBTENER_USUARIO_SISTEMA;

  PROCEDURE SP_OBTENER_USUA_PERF_SIST(pCursor OUT sys_refcursor) IS
  BEGIN
    OPEN pCursor FOR
      SELECT UPS.NCODPERFIL,
             UPS.NCODSISTEMA,
             U.VCODUSUARIO,
             U.NESTADO,
             --UPPER(PS.VDESCRIPCION) VPERFILNOMBRE,
             '' VPERFILNOMBRE,
             UPPER(S.VDESCRIPCION) VSISTEMANOMBRE,
             --'' VSISTEMANOMBRE,
             U.VDESCRIPCION,
             U.NCODFICHA,
             U.NCODAREA,
             UPS.VRESCRE,
             UPS.DFECCRE,
             UPS.VRESACT,
             UPS.DFECACT
      --,P.VDESCRIPCION VESTADONOMBRE
        FROM S_GUIA.USUARIO U
      --INNER JOIN S_GUIA.SISTEMA_PARAMETRO P ON P.VCODPARAMETRO = 'ESTADO' AND P.NID = U.NESTADO
        LEFT JOIN USUARIO_PERFIL_SISTEMA UPS ON U.VCODUSUARIO =
                                                UPS.VCODUSUARIO
        LEFT JOIN SISTEMA S ON UPS.NCODSISTEMA = S.NCODSISTEMA
       WHERE U.VCODUSUARIO IN
             ('LCASTRO', 'JMOYANO', 'ABCD', 'ALDANA', 'MHERRERA');
  END SP_OBTENER_USUA_PERF_SIST;

  PROCEDURE SP_OBTENER_USUA_PERF_SIST_USU(P_NCODSISTEMA S_GUIA.USUARIO_PERFIL_SISTEMA.NCODSISTEMA%TYPE,
                                          P_VCODUSUARIO S_GUIA.USUARIO_PERFIL_SISTEMA.VCODUSUARIO%TYPE,
                                          pCursor       OUT sys_refcursor) IS
  BEGIN
    OPEN pCursor FOR
      SELECT PS.NCODPERFIL,
             PS.NCODSISTEMA,
             UPS.VCODUSUARIO,
             NVL(UPS.NESTADO, 0) NESTADO,
             PS.VDESCRIPCION VPERFILNOMBRE,
             UPPER(S.VDESCRIPCION) VSISTEMANOMBRE,
             '' VDESCRIPCION,
             0 NCODFICHA,
             0 NCODAREA,
             UPS.VRESCRE,
             UPS.DFECCRE,
             UPS.VRESACT,
             UPS.DFECACT
        FROM PERFIL_SISTEMA PS
       INNER JOIN SISTEMA S ON PS.NCODSISTEMA = S.NCODSISTEMA
        LEFT JOIN USUARIO_PERFIL_SISTEMA UPS ON PS.NCODPERFIL =
                                                UPS.NCODPERFIL
                                            AND PS.NCODSISTEMA =
                                                UPS.NCODSISTEMA
                                            AND UPS.VCODUSUARIO =
                                                P_VCODUSUARIO
       INNER JOIN PERFIL_SISTEMA PS ON PS.NCODPERFIL = UPS.NCODPERFIL
                                   AND PS.NCODSISTEMA = UPS.NCODSISTEMA
       WHERE PS.NCODSISTEMA = P_NCODSISTEMA;
    --         AND UPS.NESTADO = 1;
  END SP_OBTENER_USUA_PERF_SIST_USU;

  PROCEDURE SP_OBTENER_USUA_PERF_SIST_COD(P_NCODPERFIL  S_GUIA.USUARIO_PERFIL_SISTEMA.NCODPERFIL%TYPE,
                                          P_NCODSISTEMA S_GUIA.USUARIO_PERFIL_SISTEMA.NCODSISTEMA%TYPE,
                                          P_VCODUSUARIO S_GUIA.USUARIO_PERFIL_SISTEMA.VCODUSUARIO%TYPE,
                                          pCursor       OUT sys_refcursor) IS
  BEGIN
    OPEN pCursor FOR
      SELECT UPS.NCODPERFIL,
             UPS.NCODSISTEMA,
             UPS.VCODUSUARIO,
             UPS.NESTADO,
             UPPER(PS.VDESCRIPCION) VPERFILNOMBRE,
             UPPER(S.VDESCRIPCION) VSISTEMANOMBRE,
             --U.VDESCRIPCION,
             '' VDESCRIPCION,
             --U.NCODFICHA,
             0 NCODFICHA,
             --U.NCODAREA,
             0 NCODAREA,
             UPS.VRESCRE,
             UPS.DFECCRE,
             UPS.VRESACT,
             UPS.DFECACT
      --FROM S_GUIA.USUARIO U
        FROM USUARIO_PERFIL_SISTEMA UPS
      --ON U.VCODUSUARIO = UPS.VCODUSUARIO 
       INNER JOIN PERFIL_SISTEMA PS ON UPS.NCODSISTEMA = PS.NCODSISTEMA
                                   AND UPS.NCODPERFIL = PS.NCODPERFIL
       INNER JOIN SISTEMA S ON PS.NCODSISTEMA = S.NCODSISTEMA
       WHERE UPS.NCODPERFIL = P_NCODPERFIL
         AND UPS.NCODSISTEMA = P_NCODSISTEMA
         AND UPS.VCODUSUARIO = P_VCODUSUARIO;
  END SP_OBTENER_USUA_PERF_SIST_COD;

  PROCEDURE SP_NUEVO_USUA_PERF_SIST(P_NCODPERFIL  S_GUIA.USUARIO_PERFIL_SISTEMA.NCODPERFIL%TYPE,
                                    P_NCODSISTEMA S_GUIA.USUARIO_PERFIL_SISTEMA.NCODSISTEMA%TYPE,
                                    P_VCODUSUARIO S_GUIA.USUARIO_PERFIL_SISTEMA.VCODUSUARIO%TYPE,
                                    P_NESTADO     S_GUIA.USUARIO_PERFIL_SISTEMA.NESTADO%TYPE,
                                    P_VUSUARIO    S_GUIA.USUARIO_PERFIL_SISTEMA.VRESCRE%TYPE) IS
  
  BEGIN
  
    INSERT INTO S_GUIA.USUARIO_PERFIL_SISTEMA
      (NCODPERFIL,
       NCODSISTEMA,
       VCODUSUARIO,
       NESTADO,
       VRESCRE,
       DFECCRE,
       VRESACT,
       DFECACT)
    VALUES
      (P_NCODPERFIL,
       P_NCODSISTEMA,
       P_VCODUSUARIO,
       P_NESTADO,
       P_VUSUARIO,
       sysdate,
       P_VUSUARIO,
       sysdate);
  
  END SP_NUEVO_USUA_PERF_SIST;

  PROCEDURE SP_UPDATE_USUA_PERF_SIST(P_NCODPERFIL  S_GUIA.USUARIO_PERFIL_SISTEMA.NCODPERFIL%TYPE,
                                     P_NCODSISTEMA S_GUIA.USUARIO_PERFIL_SISTEMA.NCODSISTEMA%TYPE,
                                     P_VCODUSUARIO S_GUIA.USUARIO_PERFIL_SISTEMA.VCODUSUARIO%TYPE,
                                     P_NESTADO     S_GUIA.USUARIO_PERFIL_SISTEMA.NESTADO%TYPE,
                                     P_VUSUARIO    S_GUIA.USUARIO_PERFIL_SISTEMA.VRESCRE%TYPE) IS
  
  BEGIN
    UPDATE S_GUIA.USUARIO_PERFIL_SISTEMA
       SET NESTADO = P_NESTADO, VRESACT = P_VUSUARIO, DFECACT = sysdate
     WHERE NCODPERFIL = P_NCODPERFIL
       AND NCODSISTEMA = P_NCODSISTEMA
       AND VCODUSUARIO = P_VCODUSUARIO;
  
  END SP_UPDATE_USUA_PERF_SIST;

  PROCEDURE SP_UPDATE_USUA_PERF_SIST_EST(P_NCODPERFIL  S_GUIA.USUARIO_PERFIL_SISTEMA.NCODPERFIL%TYPE,
                                         P_NCODSISTEMA S_GUIA.USUARIO_PERFIL_SISTEMA.NCODSISTEMA%TYPE,
                                         P_VCODUSUARIO S_GUIA.USUARIO_PERFIL_SISTEMA.VCODUSUARIO%TYPE,
                                         P_NESTADO     S_GUIA.USUARIO_PERFIL_SISTEMA.NESTADO%TYPE,
                                         P_VUSUARIO    S_GUIA.USUARIO_PERFIL_SISTEMA.VRESCRE%TYPE) IS
  BEGIN
    /*UPDATE S_GUIA.USUARIO_PERFIL_SISTEMA
      SET NESTADO = P_NESTADO, VRESACT = P_VUSUARIO, DFECACT = sysdate
    WHERE NCODPERFIL = P_NCODPERFIL
      AND NCODSISTEMA = P_NCODSISTEMA
      AND VCODUSUARIO = P_VCODUSUARIO;*/
  
    UPDATE S_GUIA.USUARIO_PERFIL_SISTEMA
       SET NCODPERFIL = P_NCODPERFIL,
           NESTADO    = P_NESTADO,
           VRESACT    = P_VUSUARIO,
           DFECACT    = sysdate
     WHERE NCODSISTEMA = P_NCODSISTEMA
       AND VCODUSUARIO = P_VCODUSUARIO;
  
  END SP_UPDATE_USUA_PERF_SIST_EST;

  ---//---- END //-- USUARIO PERFIL SISTEMA

  ---//---- START //-- PERFIL SISTEMA
  PROCEDURE SP_OBTENER_PERFIL_ACCION(P_NCODPERFIL  S_GUIA.PERFIL_ACCION.NCODPERFIL%TYPE,
                                     P_NCODSISTEMA S_GUIA.PERFIL_ACCION.NCODSISTEMA%TYPE,
                                     pCursor       OUT sys_refcursor) AS
  BEGIN
    OPEN pCursor FOR
      SELECT NCODPERFIL_ACCION,
             NCODPERFIL,
             NCODSISTEMA,
             NCODACCION_FORMULARIO
        FROM PERFIL_ACCION P
       WHERE P.NCODPERFIL = P_NCODPERFIL
         AND NCODSISTEMA = P_NCODSISTEMA;
    --ORDER BY P.NCODIGO;                                   
  END SP_OBTENER_PERFIL_ACCION;

  PROCEDURE SP_OBTENER_PERFIL_ACCION_COD(P_NCODPERFIL_ACCION S_GUIA.PERFIL_ACCION.NCODPERFIL%TYPE,
                                         pCursor             OUT sys_refcursor) AS
  BEGIN
    OPEN pCursor FOR
      SELECT NCODPERFIL_ACCION,
             NCODPERFIL,
             NCODSISTEMA,
             NCODACCION_FORMULARIO
        FROM PERFIL_ACCION P
       WHERE P.NCODPERFIL_ACCION = P_NCODPERFIL_ACCION;
  END SP_OBTENER_PERFIL_ACCION_COD;

  PROCEDURE SP_NUEVO_PERFIL_ACCION(NCODPERFIL_ACCION       S_GUIA.PERFIL_ACCION.NCODACCION_FORMULARIO%TYPE,
                                   P_NCODPERFIL            S_GUIA.PERFIL_ACCION.NCODPERFIL%TYPE,
                                   P_NCODSISTEMA           S_GUIA.PERFIL_ACCION.NCODSISTEMA%TYPE,
                                   P_NCODACCION_FORMULARIO S_GUIA.PERFIL_ACCION.NCODACCION_FORMULARIO%TYPE) IS
    valor INT;
  BEGIN
    --DBMS_OUTPUT.PUT_LINE(P_NCODSISTEMA);
  
    SELECT MAX(NCODPERFIL_ACCION) INTO valor FROM S_GUIA.PERFIL_ACCION;
  
    valor := NVL(valor, 0) + 1;
  
    --DBMS_OUTPUT.PUT_LINE(valor);
  
    INSERT INTO S_GUIA.PERFIL_ACCION
      (NCODPERFIL_ACCION, NCODPERFIL, NCODSISTEMA, NCODACCION_FORMULARIO)
    VALUES
      (valor, P_NCODPERFIL, P_NCODSISTEMA, P_NCODACCION_FORMULARIO);
  END SP_NUEVO_PERFIL_ACCION;

  PROCEDURE SP_UPDATE_PERFIL_ACCION(P_NCODPERFIL  S_GUIA.PERFIL_ACCION.NCODPERFIL%TYPE,
                                    P_NCODSISTEMA S_GUIA.PERFIL_ACCION.NCODSISTEMA%TYPE) IS
  BEGIN
    DELETE S_GUIA.PERFIL_ACCION P
     WHERE P.NCODPERFIL = P_NCODPERFIL
       AND NCODSISTEMA = P_NCODSISTEMA;
  END SP_UPDATE_PERFIL_ACCION;

  ---//---- END //-- PERFIL SISTEMA

  PROCEDURE SP_OBTENER_CENTRO(pCursor OUT sys_refcursor) AS
  BEGIN
    OPEN pCursor FOR
      SELECT NCODCENTRO,
             VNOMBRE,
             VDIRECCION,
             VABREVIATURA,
             NANXCENTRAL,
             DFECCREACION,
             DFECACTUALIZACION,
             VRESPONSABLE,
             NZONA,
             NESTADO,
             NCENTRO,
             NCONSUMO
        FROM S_GUIA.CENTRO;
  END SP_OBTENER_CENTRO;

  PROCEDURE SP_OBTENER_CENTRO_COD(P_NCODCENTRO S_GUIA.CENTRO.NCODCENTRO%TYPE,
                                  pCursor      OUT sys_refcursor) AS
  BEGIN
    OPEN pCursor FOR
      SELECT NCODCENTRO,
             VNOMBRE,
             VDIRECCION,
             VABREVIATURA,
             NANXCENTRAL,
             DFECCREACION,
             DFECACTUALIZACION,
             VRESPONSABLE,
             NZONA,
             NESTADO,
             NCENTRO,
             NCONSUMO
        FROM S_GUIA.CENTRO
       WHERE NCODCENTRO = P_NCODCENTRO;
  END SP_OBTENER_CENTRO_COD;

  PROCEDURE SP_OBTENER_AREA(pCursor OUT sys_refcursor) AS
  BEGIN
    OPEN pCursor FOR
      SELECT NCODAREA,
             NCODCENTRO,
             VDESCRIPCION,
             VABREVIATURA,
             NANEXO,
             CTIPAREA,
             NARESUPERIOR,
             DFECCREACION,
             DFECACTUALIZACION,
             VRESPONSABLE,
             NINDICADOR,
             NESTADO
        FROM S_GUIA.AREA;
  END SP_OBTENER_AREA;

  PROCEDURE SP_OBTENER_AREA_COD(P_NCODAREA S_GUIA.AREA.NCODAREA%TYPE,
                                pCursor    OUT sys_refcursor) AS
  BEGIN
    OPEN pCursor FOR
      SELECT NCODAREA,
             NCODCENTRO,
             VDESCRIPCION,
             VABREVIATURA,
             NANEXO,
             CTIPAREA,
             NARESUPERIOR,
             DFECCREACION,
             DFECACTUALIZACION,
             VRESPONSABLE,
             NINDICADOR,
             NESTADO
        FROM S_GUIA.AREA
       WHERE NCODAREA = P_NCODAREA;
  END SP_OBTENER_AREA_COD;

  PROCEDURE SP_OBTENER_TRABAJADOR(pCursor OUT sys_refcursor) AS
  BEGIN
    OPEN pCursor FOR
      SELECT NCODTRABAJADOR,
             NFICHA,
             VNOMBRES,
             VAPEPATERNO,
             VAPEMATERNO,
             NANEXO,
             NDIAONOMASTICO,
             NMESONOMASTICO,
             VCARGO,
             VDIRELECTRONICA,
             NCODAREA,
             VCODTIPO,
             DFECCREACION,
             DFECACTUALIZACION,
             VRESPONSABLE,
             DFECNACIMIENTO,
             DFECINGRESO,
             VUBICACION,
             NNIVEL,
             NNEXTEL,
             NCELULAR,
             NANEXOCER,
             VCODESTADO,
             NCODTIPO,
             NCENCOSTO,
             NCODLOCAL,
             NSUBAREA,
             IND_EMAIL,
             DFECCESE,
             VDNI,
             VTS,
             VNIVEL,
             VEC,
             NHIJOS,
             VDATO1,
             NDATO1,
             VDATO2,
             NDATO2,
             VTELEFONO,
             VDIRECCION,
             VAREAPERSONAL
        FROM S_GUIA.TRABAJADOR;
  END SP_OBTENER_TRABAJADOR;

  PROCEDURE SP_OBTENER_TRABAJADOR_COD(P_NCODTRABAJADOR S_GUIA.TRABAJADOR.NCODTRABAJADOR%TYPE,
                                      pCursor          OUT sys_refcursor) AS
  BEGIN
    OPEN pCursor FOR
      SELECT NCODTRABAJADOR,
             NFICHA,
             VNOMBRES,
             VAPEPATERNO,
             VAPEMATERNO,
             NANEXO,
             NDIAONOMASTICO,
             NMESONOMASTICO,
             VCARGO,
             VDIRELECTRONICA,
             NCODAREA,
             VCODTIPO,
             DFECCREACION,
             DFECACTUALIZACION,
             VRESPONSABLE,
             DFECNACIMIENTO,
             DFECINGRESO,
             VUBICACION,
             NNIVEL,
             NNEXTEL,
             NCELULAR,
             NANEXOCER,
             VCODESTADO,
             NCODTIPO,
             NCENCOSTO,
             NCODLOCAL,
             NSUBAREA,
             IND_EMAIL,
             DFECCESE,
             VDNI,
             VTS,
             VNIVEL,
             VEC,
             NHIJOS,
             VDATO1,
             NDATO1,
             VDATO2,
             NDATO2,
             VTELEFONO,
             VDIRECCION,
             VAREAPERSONAL
        FROM S_GUIA.TRABAJADOR
       WHERE P_NCODTRABAJADOR = NCODTRABAJADOR;
  END SP_OBTENER_TRABAJADOR_COD;

  PROCEDURE SP_OBTENER_TRABAJADOR_FICHA(P_NFICHA S_GUIA.TRABAJADOR.NFICHA%TYPE,
                                        pCursor  OUT sys_refcursor) AS
  BEGIN
    OPEN pCursor FOR
      SELECT T.NCODTRABAJADOR,
             T.NFICHA,
             T.VNOMBRES,
             T.VAPEPATERNO,
             T.VAPEMATERNO,
             T.NANEXO,
             T.NDIAONOMASTICO,
             T.NMESONOMASTICO,
             T.VCARGO,
             T.VDIRELECTRONICA,
             T.NCODAREA,
             T.VCODTIPO,
             T.DFECCREACION,
             T.DFECACTUALIZACION,
             T.VRESPONSABLE,
             T.DFECNACIMIENTO,
             T.DFECINGRESO,
             T.VUBICACION,
             T.NNIVEL,
             T.NNEXTEL,
             T.NCELULAR,
             T.NANEXOCER,
             T.VCODESTADO,
             T.NCODTIPO,
             T.NCENCOSTO,
             T.NCODLOCAL,
             T.NSUBAREA,
             T.IND_EMAIL,
             T.DFECCESE,
             T.VDNI,
             T.VTS,
             T.VNIVEL,
             T.VEC,
             T.NHIJOS,
             T.VDATO1,
             T.NDATO1,
             T.VDATO2,
             T.NDATO2,
             T.VTELEFONO,
             T.VDIRECCION,
             T.VAREAPERSONAL,
             A.VDESCRIPCION DESCRIPCIONAREA,
             decode(T.VCODESTADO, 'EIRC02', 'BAJA', 'ACTIVO') ESTTRAB
        FROM S_GUIA.TRABAJADOR T
       INNER JOIN AREA A ON A.NCODAREA = T.NCODAREA
       WHERE NFICHA = P_NFICHA
         AND T.IND_EMAIL = 0;
    --         AND T.Vcodestado = 'EIRC01';
  END SP_OBTENER_TRABAJADOR_FICHA;

  PROCEDURE SP_OBTENER_USUARIO_ACCION_COD(P_VCODUSUARIO S_GUIA.USUARIO.VCODUSUARIO%TYPE,
                                          pCursor       OUT sys_refcursor) AS
  BEGIN
    OPEN pCursor FOR
    /* SELECT M.VURL_FORMULARIO || SP.VDESCRIPCION
                        FROM USUARIO U
                       INNER JOIN USUARIO_PERFIL_SISTEMA UP ON UP.VCODUSUARIO =
                                                               U.VCODUSUARIO
                       INNER JOIN PERFIL_SISTEMA PS ON UP.NCODPERFIL = PS.NCODPERFIL
                                                   AND UP.NCODSISTEMA = PS.NCODSISTEMA
                       INNER JOIN PERFIL_ACCION PA ON PS.NCODPERFIL = PA.NCODPERFIL
                                                  AND PS.NCODSISTEMA = PA.NCODSISTEMA
                       INNER JOIN ACCION_FORMULARIO F ON PA.NCODACCION_FORMULARIO =
                                                         F.NCODACCION_FORMULARIO
                       INNER JOIN SISTEMA_MODULO_FORM M ON F.NCODSISTEMA = M.NCODSISTEMA
                                                       AND F.NCODMODULO = M.NCODMODULO
                                                       AND F.NCODFORMULARIO =
                                                           M.NCODFORMULARIO
                       INNER JOIN SISTEMA_PARAMETRO SP ON F.VCODPARAMETRO =
                                                          SP.VCODPARAMETRO
                                                      AND F.NCODIGO = SP.NCODIGO
                       WHERE U.VCODUSUARIO = P_VCODUSUARIO
                         AND U.NESTADO = 1
                         AND UP.NESTADO = 1
                         AND F.NESTADO = 1
                         AND M.NESTADO = 1;*/
    
      SELECT M.VURL_FORMULARIO || SP.VDESCRIPCION
        FROM USUARIO U
       INNER JOIN USUARIO_PERFIL_SISTEMA UP ON UP.VCODUSUARIO =
                                               U.VCODUSUARIO
       INNER JOIN PERFIL_SISTEMA PS ON UP.NCODPERFIL = PS.NCODPERFIL
                                   AND UP.NCODSISTEMA = PS.NCODSISTEMA
       INNER JOIN PERFIL_ACCION PA ON PS.NCODPERFIL = PA.NCODPERFIL
                                  AND PS.NCODSISTEMA = PA.NCODSISTEMA
       INNER JOIN ACCION_FORMULARIO F ON PA.NCODACCION_FORMULARIO =
                                         F.NCODACCION_FORMULARIO
       INNER JOIN SISTEMA_MODULO_FORM M ON F.NCODSISTEMA = M.NCODSISTEMA
                                       AND F.NCODMODULO = M.NCODMODULO
                                       AND F.NCODFORMULARIO =
                                           M.NCODFORMULARIO
       INNER JOIN SISTEMA_PARAMETRO SP ON F.VCODPARAMETRO =
                                          SP.VCODPARAMETRO
                                      AND F.NCODIGO = SP.NCODIGO
       WHERE U.VCODUSUARIO = P_VCODUSUARIO
         AND U.NESTADO = 1
         AND UP.NESTADO = 1
         AND F.NESTADO = 1
         AND M.NESTADO = 1
      UNION ALL
      SELECT SMF.VURL_FORMULARIO
        FROM USUARIO_PERFIL_SISTEMA UPS
       INNER JOIN PERFIL_ACCION PA ON PA.NCODSISTEMA = UPS.NCODSISTEMA
                                  AND PA.NCODPERFIL = UPS.NCODPERFIL
       INNER JOIN ACCION_FORMULARIO AF ON AF.NCODACCION_FORMULARIO =
                                          PA.NCODACCION_FORMULARIO
       INNER JOIN SISTEMA_MODULO_FORM SMF ON SMF.NCODSISTEMA =
                                             UPS.NCODSISTEMA
                                         AND SMF.NCODFORMULARIO =
                                             AF.NCODFORMULARIO
       INNER JOIN S_GUIA.SISTEMA_PARAMETRO P ON P.VCODPARAMETRO = 'ESTADO'
                                            AND P.NID = SMF.NESTADO
       INNER JOIN S_GUIA.SISTEMA S ON UPS.NCODSISTEMA = S.NCODSISTEMA
       INNER JOIN S_GUIA.SISTEMA_MODULO M ON SMF.NCODSISTEMA =
                                             M.NCODSISTEMA
                                         AND AF.NCODMODULO = M.NCODMODULO
                                         AND SMF.NCODMODULO = M.NCODMODULO
       INNER JOIN PERFIL_SISTEMA PS ON UPS.NCODPERFIL = PS.NCODPERFIL
                                   AND UPS.NCODSISTEMA = PS.NCODSISTEMA
       WHERE UPS.VCODUSUARIO = P_VCODUSUARIO
         AND SMF.NESTADO = 1
         AND S.NESTADO = 0
         AND M.NESTADO = 1
         AND PS.VESTADO = 1;
  
  END SP_OBTENER_USUARIO_ACCION_COD;

  PROCEDURE SP_OBTENER_PARAMETRO(P_VCODPARAMETRO S_GUIA.SISTEMA_PARAMETRO.VCODPARAMETRO%TYPE,
                                 pCursor         OUT sys_refcursor) AS
  BEGIN
    OPEN pCursor FOR
      SELECT S.VCODPARAMETRO,
             S.NCODIGO,
             NVL(S.VDESCRIPCION, '') VDESCRIPCION,
             S.NESTADO,
             S.NID,
             S.VVALOR,
             S.VRESCRE,
             S.DFECCRE,
             S.VRESACT,
             S.DFECACT,
             P.VDESCRIPCION AS NOMBREESTADO
        FROM S_GUIA.SISTEMA_PARAMETRO S
        LEFT JOIN S_GUIA.SISTEMA_PARAMETRO P ON P.VCODPARAMETRO = 'ESTADO'
                                            AND P.NID = S.NESTADO
       WHERE S.VCODPARAMETRO = P_VCODPARAMETRO
         AND S.NESTADO = 1;
  END SP_OBTENER_PARAMETRO;

  PROCEDURE SP_OBTENER_PARAMETRO_GENERAL(pCursor OUT sys_refcursor) AS
  BEGIN
    OPEN pCursor FOR
      SELECT S.VCODPARAMETRO,
             S.NCODIGO,
             NVL(S.VDESCRIPCION, '') VDESCRIPCION,
             S.NESTADO,
             S.NID,
             S.VVALOR,
             S.VRESCRE,
             S.DFECCRE,
             S.VRESACT,
             S.DFECACT,
             P.VDESCRIPCION AS NOMBREESTADO
        FROM S_GUIA.SISTEMA_PARAMETRO S
        LEFT JOIN S_GUIA.SISTEMA_PARAMETRO P ON P.VCODPARAMETRO = 'ESTADO'
                                            AND P.NID = S.NESTADO
       WHERE S.VVALOR IS NOT NULL
       ORDER BY S.NCODIGO;
  END SP_OBTENER_PARAMETRO_GENERAL;

  PROCEDURE SP_OBTENER_PARAMETRO_COD(P_VCODPARAMETRO S_GUIA.SISTEMA_PARAMETRO.VCODPARAMETRO%TYPE,
                                     P_NCODIGO       S_GUIA.SISTEMA_PARAMETRO.NCODIGO%TYPE,
                                     pCursor         OUT sys_refcursor) AS
  BEGIN
    OPEN pCursor FOR
      SELECT S.VCODPARAMETRO,
             S.NCODIGO,
             S.VDESCRIPCION,
             S.NESTADO,
             S.NID,
             S.VVALOR,
             S.VRESCRE,
             S.DFECCRE,
             S.VRESACT,
             S.DFECACT,
             P.VDESCRIPCION AS NOMBREESTADO
        FROM S_GUIA.SISTEMA_PARAMETRO S
        LEFT JOIN S_GUIA.SISTEMA_PARAMETRO P ON P.VCODPARAMETRO = 'ESTADO'
                                            AND P.NID = S.NESTADO
       WHERE S.VCODPARAMETRO = P_VCODPARAMETRO
         AND S.NCODIGO = P_NCODIGO;
  END SP_OBTENER_PARAMETRO_COD;

  PROCEDURE SP_UPDATE_PARAMETRO_COD(P_VCODPARAMETRO S_GUIA.SISTEMA_PARAMETRO.VCODPARAMETRO%TYPE,
                                    P_NCODIGO       S_GUIA.SISTEMA_PARAMETRO.NCODIGO%TYPE,
                                    P_VDESCRIPCION  S_GUIA.SISTEMA_PARAMETRO.VDESCRIPCION%TYPE,
                                    P_VVALOR        S_GUIA.SISTEMA_PARAMETRO.VVALOR%TYPE,
                                    P_NESTADO       S_GUIA.SISTEMA_PARAMETRO.NESTADO%TYPE,
                                    P_VUSUARIO      S_GUIA.SISTEMA_PARAMETRO.VRESACT%TYPE) AS
  BEGIN
    UPDATE S_GUIA.SISTEMA_PARAMETRO
       SET VDESCRIPCION = P_VDESCRIPCION,
           VVALOR       = P_VVALOR,
           --NESTADO      = P_NESTADO,
           VRESACT = P_VUSUARIO,
           DFECACT = sysdate
     WHERE VCODPARAMETRO = P_VCODPARAMETRO
       AND NCODIGO = P_NCODIGO;
  END SP_UPDATE_PARAMETRO_COD;

  PROCEDURE SP_OBTENER_PERFILSISTEMA(P_NCODSISTEMA S_GUIA.PERFIL_SISTEMA.NCODSISTEMA%TYPE,
                                     pCursor       OUT sys_refcursor) AS
    valor INT;
  BEGIN
    IF (P_NCODSISTEMA IS NULL) THEN
      valor := 0;
    ELSE
      valor := P_NCODSISTEMA;
    END IF;
  
    IF valor > 0 THEN
      valor := P_NCODSISTEMA;
    ELSE
      valor := NULL;
    END IF;
  
    OPEN pCursor FOR
      SELECT PS.NCODPERFIL,
             PS.NCODSISTEMA,
             UPPER(PS.VDESCRIPCION) VDESCRIPCION,
             --PS.VESTADO,
             CASE
               WHEN PS.VESTADO IN ('A', '1') THEN
                1
               WHEN PS.VESTADO IN ('I', '0') THEN
                0
             END VESTADO,
             --NVL(P.VDESCRIPCION, '') VESTADONOMBRE,
             CASE
               WHEN PS.VESTADO IN ('A', '1') THEN
                'ACTIVO'
               WHEN PS.VESTADO IN ('I', '0') THEN
                'INACTIVO'
               WHEN PS.VESTADO IN ('2') THEN
                'BLOQUEADO'
             END VESTADONOMBRE,
             UPPER(S.VDESCRIPCION) VSISTEMANOMBRE,
             PS.VRESCRE,
             PS.DFECCRE,
             PS.VRESACT,
             PS.DFECACT
        FROM S_GUIA.PERFIL_SISTEMA PS
       INNER JOIN S_GUIA.SISTEMA S ON PS.NCODSISTEMA = S.NCODSISTEMA
      --LEFT JOIN S_GUIA.SISTEMA_PARAMETRO P ON P.VCODPARAMETRO = 'ESTADO'
      --                                  AND P.NID = PS.VESTADO
       WHERE PS.NCODSISTEMA = NVL(valor, PS.NCODSISTEMA)
         AND S.NESTADO = 0
      --AND PS.VESTADO IN ('1', '0')
       ORDER BY PS.NCODSISTEMA, PS.NCODPERFIL;
  END SP_OBTENER_PERFILSISTEMA;

  PROCEDURE SP_OBTENER_PERFILSISTEMA_ACT(P_NCODSISTEMA S_GUIA.PERFIL_SISTEMA.NCODSISTEMA%TYPE,
                                         pCursor       OUT sys_refcursor) AS
    valor INT;
  BEGIN
    /*
    IF (P_NCODSISTEMA IS NULL) THEN
      valor := 0;
    ELSE
      valor := P_NCODSISTEMA;
    END IF;
    
    IF valor > 0 THEN
      valor := P_NCODSISTEMA;
    ELSE
      valor := NULL;
    END IF;*/
  
    OPEN pCursor FOR
      SELECT PS.NCODPERFIL,
             PS.NCODSISTEMA,
             UPPER(PS.VDESCRIPCION) VDESCRIPCION,
             --PS.VESTADO,
             CASE
               WHEN PS.VESTADO IN ('A', '1') THEN
                1
               WHEN PS.VESTADO IN ('I', '0') THEN
                0
             END VESTADO,
             --NVL(P.VDESCRIPCION, '') VESTADONOMBRE,
             CASE
               WHEN PS.VESTADO IN ('A', '1') THEN
                'ACTIVO'
               WHEN PS.VESTADO IN ('I', '0') THEN
                'INACTIVO'
               WHEN PS.VESTADO IN ('2') THEN
                'BLOQUEADO'
             END VESTADONOMBRE,
             UPPER(S.VDESCRIPCION) VSISTEMANOMBRE,
             PS.VRESCRE,
             PS.DFECCRE,
             PS.VRESACT,
             PS.DFECACT
        FROM S_GUIA.PERFIL_SISTEMA PS
       INNER JOIN S_GUIA.SISTEMA S ON PS.NCODSISTEMA = S.NCODSISTEMA
      --       WHERE PS.NCODSISTEMA = NVL(valor, PS.NCODSISTEMA)
       WHERE PS.NCODSISTEMA = P_NCODSISTEMA
         AND S.NESTADO = 0
         AND PS.VESTADO IN ('1', 'A')
       ORDER BY PS.NCODSISTEMA, PS.NCODPERFIL;
  END SP_OBTENER_PERFILSISTEMA_ACT;

  PROCEDURE SP_OBTENER_PERFIL_SISTEMA_TOT(P_ValueSearch IN VARCHAR,
                                          P_TotalReg    OUT NUMBER) AS
    v_Search VARCHAR2(50);
    n_Total  INT;
  BEGIN
    IF LENGTH(LTRIM(P_ValueSearch)) > 0 THEN
      v_Search := '%' || P_ValueSearch || '%';
    ELSE
      v_Search := NULL;
    END IF;
  
    SELECT COUNT(VESTADONOMBRE)
      INTO n_Total
      FROM VW_PERFIL_SISTEMA
     WHERE VBUSCAR LIKE NVL(v_Search, VBUSCAR);
  
    P_TotalReg := NVL(n_Total, 0);
  END SP_OBTENER_PERFIL_SISTEMA_TOT;

  PROCEDURE SP_OBTENER_PERFIL_SISTEMA_PAG(P_PageSize    IN INT,
                                          P_PageIndex   IN INT,
                                          P_ValueSearch IN VARCHAR,
                                          P_SortColumn  IN VARCHAR,
                                          pCursor       OUT sys_refcursor) AS
    FirstIndex INT;
    LastIndex  INT;
    v_SQL      VARCHAR2(4000);
    v_Search   VARCHAR2(50);
  BEGIN
    LastIndex  := P_PageSize * (P_PageIndex + 1);
    FirstIndex := LastIndex - P_PageSize + 1;
  
    IF LENGTH(LTRIM(P_ValueSearch)) > 0 THEN
      v_Search := '%' || P_ValueSearch || '%';
    ELSE
      v_Search := NULL;
    END IF;
  
    OPEN pCursor FOR
      SELECT *
        FROM (SELECT a.*, ROWNUM AS rnum
                FROM (SELECT NCODPERFIL,
                             NCODSISTEMA,
                             VDESCRIPCION,
                             VESTADO,
                             VESTADONOMBRE,
                             VSISTEMANOMBRE,
                             VRESCRE,
                             DFECCRE,
                             VRESACT,
                             DFECACT
                        FROM VW_PERFIL_SISTEMA
                       WHERE VBUSCAR LIKE NVL(v_Search, VBUSCAR)) a
               WHERE ROWNUM <= LastIndex)
       WHERE rnum >= FirstIndex
       order by VSISTEMANOMBRE;
  END SP_OBTENER_PERFIL_SISTEMA_PAG;

  PROCEDURE SP_OBTENER_PERFIL_SISTEMA_COD(P_NCODPERFIL  S_GUIA.PERFIL_SISTEMA.NCODPERFIL%TYPE,
                                          P_NCODSISTEMA S_GUIA.PERFIL_SISTEMA.NCODSISTEMA%TYPE,
                                          pCursor       OUT sys_refcursor) AS
  BEGIN
    OPEN pCursor FOR
      SELECT PS.NCODPERFIL,
             PS.NCODSISTEMA,
             PS.VDESCRIPCION VDESCRIPCION,
             --PS.VESTADO,
             CASE
               WHEN PS.VESTADO IN ('A', '1') THEN
                '1'
               WHEN PS.VESTADO IN ('I', '0') THEN
                '0'
               WHEN PS.VESTADO IN ('2') THEN
                '2'
             END VESTADO,
             --NVL(P.VDESCRIPCION, '') VESTADONOMBRE,
             CASE
               WHEN PS.VESTADO IN ('A', '1') THEN
                'ACTIVO'
               WHEN PS.VESTADO IN ('I', '0') THEN
                'INACTIVO'
               WHEN PS.VESTADO IN ('2') THEN
                'BLOQUEADO'
             END VESTADONOMBRE,
             UPPER(S.VDESCRIPCION) VSISTEMANOMBRE,
             PS.VRESCRE,
             PS.DFECCRE,
             PS.VRESACT,
             PS.DFECACT
        FROM S_GUIA.PERFIL_SISTEMA PS
       INNER JOIN S_GUIA.SISTEMA S ON PS.NCODSISTEMA = S.NCODSISTEMA
      /*LEFT JOIN S_GUIA.SISTEMA_PARAMETRO P ON P.VCODPARAMETRO = 'ESTADO'
                                                                                                        AND P.NID = PS.VESTADO*/
       WHERE PS.NCODPERFIL = P_NCODPERFIL
         AND PS.NCODSISTEMA = P_NCODSISTEMA;
    --AND PS.VESTADO IN ('1', '0');
  END SP_OBTENER_PERFIL_SISTEMA_COD;

  PROCEDURE SP_NUEVO_PERFIL_SISTEMA(P_NCODPERFIL   S_GUIA.PERFIL_SISTEMA.NCODPERFIL%TYPE,
                                    P_NCODSISTEMA  S_GUIA.PERFIL_SISTEMA.NCODSISTEMA%TYPE,
                                    P_VDESCRIPCION S_GUIA.PERFIL_SISTEMA.VDESCRIPCION%TYPE,
                                    P_VESTADO      S_GUIA.PERFIL_SISTEMA.VESTADO%TYPE,
                                    P_VUSUARIO     S_GUIA.PERFIL_SISTEMA.VRESCRE%TYPE,
                                    P_IDENTITY     OUT NUMBER) IS
  
    valor INT;
  BEGIN
  
    SELECT MAX(NCODPERFIL)
      INTO valor
      FROM S_GUIA.PERFIL_SISTEMA
     WHERE NCODSISTEMA = P_NCODSISTEMA;
  
    valor := NVL(valor, 0) + 1;
  
    --DBMS_OUTPUT.PUT_LINE(valor);
    P_IDENTITY := valor;
  
    INSERT INTO S_GUIA.PERFIL_SISTEMA
      (NCODPERFIL,
       NCODSISTEMA,
       VDESCRIPCION,
       VESTADO,
       VRESCRE,
       DFECCRE,
       VRESACT,
       DFECACT)
    VALUES
      (valor,
       P_NCODSISTEMA,
       P_VDESCRIPCION,
       P_VESTADO,
       P_VUSUARIO,
       sysdate,
       P_VUSUARIO,
       sysdate);
  END SP_NUEVO_PERFIL_SISTEMA;

  PROCEDURE SP_UPDATE_PERFIL_SISTEMA(P_NCODPERFIL   S_GUIA.PERFIL_SISTEMA.NCODPERFIL%TYPE,
                                     P_NCODSISTEMA  S_GUIA.PERFIL_SISTEMA.NCODSISTEMA%TYPE,
                                     P_VDESCRIPCION S_GUIA.PERFIL_SISTEMA.VDESCRIPCION%TYPE,
                                     P_VESTADO      S_GUIA.PERFIL_SISTEMA.VESTADO%TYPE,
                                     P_VUSUARIO     S_GUIA.PERFIL_SISTEMA.VRESCRE%TYPE) IS
  BEGIN
    UPDATE S_GUIA.PERFIL_SISTEMA
       SET VDESCRIPCION = P_VDESCRIPCION,
           VESTADO      = P_VESTADO,
           VRESACT      = P_VUSUARIO,
           DFECACT      = sysdate
     WHERE NCODSISTEMA = P_NCODSISTEMA
       AND NCODPERFIL = P_NCODPERFIL;
  END SP_UPDATE_PERFIL_SISTEMA;

  PROCEDURE SP_UPDATE_PERFIL_SISTEMA_ESTAD(P_NCODPERFIL  S_GUIA.PERFIL_SISTEMA.NCODPERFIL%TYPE,
                                           P_NCODSISTEMA S_GUIA.PERFIL_SISTEMA.NCODSISTEMA%TYPE,
                                           P_VESTADO     S_GUIA.PERFIL_SISTEMA.VESTADO%TYPE,
                                           P_VUSUARIO    S_GUIA.PERFIL_SISTEMA.VRESCRE%TYPE) IS
  BEGIN
    UPDATE S_GUIA.PERFIL_SISTEMA
       SET VESTADO = P_VESTADO, VRESACT = P_VUSUARIO, DFECACT = sysdate
     WHERE NCODSISTEMA = P_NCODSISTEMA
       AND NCODPERFIL = P_NCODPERFIL;
  END SP_UPDATE_PERFIL_SISTEMA_ESTAD;

  --------- START / PERFIL_ACCION ----------------------------------------/
  PROCEDURE SP_OBTENER_ACCION_FORM_PERFIL(P_NCODSISTEMA    S_GUIA.ACCION_FORMULARIO.NCODSISTEMA%TYPE,
                                          P_NCODMODULO     S_GUIA.ACCION_FORMULARIO.NCODMODULO%TYPE,
                                          P_NCODFORMULARIO S_GUIA.ACCION_FORMULARIO.NCODFORMULARIO%TYPE,
                                          P_NCODPERFIL     S_GUIA.PERFIL_ACCION.NCODPERFIL%TYPE,
                                          pCursor          OUT sys_refcursor) AS
  BEGIN
    OPEN pCursor FOR WITH PARAMETRO_ACCION_FORMULARIO AS(
      SELECT F.NCODACCION_FORMULARIO,
             F.NCODSISTEMA,
             F.NCODMODULO,
             F.NCODFORMULARIO,
             P.VCODPARAMETRO,
             P.NCODIGO,
             NVL(F.NESTADO, 0) NESTADO,
             P.VDESCRIPCION
        FROM SISTEMA_PARAMETRO P
        LEFT JOIN ACCION_FORMULARIO F ON F.VCODPARAMETRO = P.VCODPARAMETRO
                                     AND F.NCODIGO = P.NCODIGO
                                     AND F.NCODSISTEMA = P_NCODSISTEMA
                                     AND F.NCODMODULO = P_NCODMODULO
                                     AND F.NCODFORMULARIO =
                                         P_NCODFORMULARIO
       INNER JOIN SISTEMA_MODULO_FORM SMF ON SMF.NCODFORMULARIO =
                                             F.NCODFORMULARIO
                                         AND SMF.NCODSISTEMA =
                                             F.NCODSISTEMA
                                         AND SMF.NCODMODULO = F.NCODMODULO
                                         AND SMF.NESTADO = 1
       WHERE P.VCODPARAMETRO = 'ACCIONES'
         AND P.NESTADO = 1
         AND F.NESTADO = 1
       GROUP BY F.NCODACCION_FORMULARIO,
                F.NCODSISTEMA,
                F.NCODMODULO,
                F.NCODFORMULARIO,
                P.VCODPARAMETRO,
                P.NCODIGO,
                F.NESTADO,
                P.VDESCRIPCION
       ORDER BY P.NCODIGO)
      SELECT R.*, NVL(P.NCODACCION_FORMULARIO, 0) ACCIONPERFIL
        FROM PARAMETRO_ACCION_FORMULARIO R
        LEFT JOIN PERFIL_ACCION P ON R.NCODACCION_FORMULARIO =
                                     P.NCODACCION_FORMULARIO
                                 AND P.NCODPERFIL = P_NCODPERFIL;
  END SP_OBTENER_ACCION_FORM_PERFIL;

  PROCEDURE SP_OBTENER_ACC_FORM_PER_USU(P_NCODSISTEMA    S_GUIA.ACCION_FORMULARIO.NCODSISTEMA%TYPE,
                                        P_NCODMODULO     S_GUIA.ACCION_FORMULARIO.NCODMODULO%TYPE,
                                        P_NCODFORMULARIO S_GUIA.ACCION_FORMULARIO.NCODFORMULARIO%TYPE,
                                        P_NCODPERFIL     S_GUIA.PERFIL_ACCION.NCODPERFIL%TYPE,
                                        pCursor          OUT sys_refcursor) AS
  BEGIN
    OPEN pCursor FOR WITH PARAMETRO_ACCION_FORMULARIO AS(
      SELECT F.NCODACCION_FORMULARIO,
             --F.NCODSISTEMA,
             --F.NCODMODULO,
             --F.NCODFORMULARIO,
             P.VCODPARAMETRO,
             P.NCODIGO,
             NVL(F.NESTADO, 0) NESTADO,
             P.VDESCRIPCION
        FROM SISTEMA_PARAMETRO P
        LEFT JOIN ACCION_FORMULARIO F ON F.VCODPARAMETRO = P.VCODPARAMETRO
                                     AND F.NCODIGO = P.NCODIGO
                                     AND F.NCODSISTEMA = P_NCODSISTEMA
                                     AND F.NCODMODULO = P_NCODMODULO
                                     AND F.NCODFORMULARIO =
                                         P_NCODFORMULARIO
       WHERE P.VCODPARAMETRO = 'ACCIONES'
         AND P.NESTADO = 1
         AND F.NESTADO = 1
       ORDER BY P.NCODIGO)
      SELECT R.NCODACCION_FORMULARIO,
             R.VCODPARAMETRO,
             R.NCODIGO,
             R.NESTADO,
             R.VDESCRIPCION,
             NVL(P.NCODACCION_FORMULARIO, 0) ACCIONPERFIL
        FROM PARAMETRO_ACCION_FORMULARIO R
       INNER JOIN PERFIL_ACCION P ON R.NCODACCION_FORMULARIO =
                                     P.NCODACCION_FORMULARIO
                                 AND P.NCODPERFIL = P_NCODPERFIL;
  END SP_OBTENER_ACC_FORM_PER_USU;
  --------- START / SISTEMA_MODULO_FORM ----------------------------------/
  PROCEDURE SP_OBTENER_ACCION_FORMULARIO(P_NCODSISTEMA    S_GUIA.ACCION_FORMULARIO.NCODSISTEMA%TYPE,
                                         P_NCODMODULO     S_GUIA.ACCION_FORMULARIO.NCODMODULO%TYPE,
                                         P_NCODFORMULARIO S_GUIA.ACCION_FORMULARIO.NCODFORMULARIO%TYPE,
                                         pCursor          OUT sys_refcursor) AS
  BEGIN
    OPEN pCursor FOR
    /*SELECT F.NCODACCION_FORMULARIO,
                                                     F.NCODSISTEMA,
                                                     F.NCODMODULO,
                                                     F.NCODFORMULARIO,
                                                     F.VCODPARAMETRO,
                                                     F.NCODIGO,
                                                     F.NESTADO,
                                                     P.VDESCRIPCION
                                                FROM ACCION_FORMULARIO F
                                                LEFT JOIN SISTEMA_PARAMETRO P ON F.VCODPARAMETRO = P.VCODPARAMETRO
                                                                             AND F.NCODIGO = P.NCODIGO
                                               WHERE F.NCODSISTEMA = P_NCODSISTEMA
                                                 AND F.NCODMODULO = P_NCODMODULO
                                                 AND F.NCODFORMULARIO = P_NCODFORMULARIO
                                                 AND F.NESTADO = 1;*/
      SELECT F.NCODACCION_FORMULARIO,
             F.NCODSISTEMA,
             F.NCODMODULO,
             F.NCODFORMULARIO,
             P.VCODPARAMETRO,
             P.NCODIGO,
             NVL(F.NESTADO, 0) NESTADO,
             P.VDESCRIPCION,
             0 ACCIONPERFIL
        FROM SISTEMA_PARAMETRO P
        LEFT JOIN ACCION_FORMULARIO F ON F.VCODPARAMETRO = P.VCODPARAMETRO
                                     AND F.NCODIGO = P.NCODIGO
                                     AND F.NCODSISTEMA = P_NCODSISTEMA
                                     AND F.NCODMODULO = P_NCODMODULO
                                     AND F.NCODFORMULARIO =
                                         P_NCODFORMULARIO
       WHERE P.VCODPARAMETRO = 'ACCIONES'
         AND P.NESTADO = 1
       ORDER BY P.NCODIGO;
  END SP_OBTENER_ACCION_FORMULARIO;

  PROCEDURE SP_NUEVO_ACCION_FORMULARIO(P_NCODACCION_FORMULARIO S_GUIA.ACCION_FORMULARIO.NCODACCION_FORMULARIO%TYPE,
                                       P_NCODSISTEMA           S_GUIA.ACCION_FORMULARIO.NCODSISTEMA%TYPE,
                                       P_NCODMODULO            S_GUIA.ACCION_FORMULARIO.NCODMODULO%TYPE,
                                       P_NCODFORMULARIO        S_GUIA.ACCION_FORMULARIO.NCODFORMULARIO%TYPE,
                                       P_VCODPARAMETRO         S_GUIA.ACCION_FORMULARIO.VCODPARAMETRO%TYPE,
                                       P_NCODIGO               S_GUIA.ACCION_FORMULARIO.NCODIGO%TYPE,
                                       P_NESTADO               S_GUIA.ACCION_FORMULARIO.NESTADO%TYPE) IS
    valor INT;
  BEGIN
    --DBMS_OUTPUT.PUT_LINE(P_NCODSISTEMA);
  
    SELECT MAX(NCODACCION_FORMULARIO)
      INTO valor
      FROM S_GUIA.ACCION_FORMULARIO;
  
    valor := NVL(valor, 0) + 1;
  
    --DBMS_OUTPUT.PUT_LINE(valor);
  
    INSERT INTO S_GUIA.ACCION_FORMULARIO
      (NCODACCION_FORMULARIO,
       NCODSISTEMA,
       NCODMODULO,
       NCODFORMULARIO,
       NESTADO,
       VCODPARAMETRO,
       NCODIGO)
    VALUES
      (valor,
       P_NCODSISTEMA,
       P_NCODMODULO,
       P_NCODFORMULARIO,
       P_NESTADO,
       P_VCODPARAMETRO,
       P_NCODIGO);
  END SP_NUEVO_ACCION_FORMULARIO;

  PROCEDURE SP_UPDATE_ACCION_FORMULARIO(P_NCODACCION_FORMULARIO S_GUIA.ACCION_FORMULARIO.NCODACCION_FORMULARIO%TYPE,
                                        --P_NCODSISTEMA           S_GUIA.ACCION_FORMULARIO.NCODSISTEMA%TYPE,
                                        --P_NCODMODULO            S_GUIA.ACCION_FORMULARIO.NCODMODULO%TYPE,
                                        --P_NCODFORMULARIO        S_GUIA.ACCION_FORMULARIO.NCODFORMULARIO%TYPE,
                                        --P_VCODPARAMETRO         S_GUIA.ACCION_FORMULARIO.VCODPARAMETRO%TYPE,
                                        --P_NCODIGO               S_GUIA.ACCION_FORMULARIO.NCODIGO%TYPE,
                                        P_NESTADO S_GUIA.ACCION_FORMULARIO.NESTADO%TYPE) IS
  BEGIN
    UPDATE S_GUIA.ACCION_FORMULARIO
       SET NESTADO = P_NESTADO
     WHERE NCODACCION_FORMULARIO = P_NCODACCION_FORMULARIO;
    --WHERE NCODSISTEMA = P_NCODSISTEMA AND NCODMODULO = P_NCODMODULO AND NCODFORMULARIO = P_NCODFORMULARIO;     
  END SP_UPDATE_ACCION_FORMULARIO;

  PROCEDURE SP_OBTENER_SIS_MODULO_FORM_MEN(P_NCODSISTEMA S_GUIA.SISTEMA_MODULO_FORM.NCODSISTEMA%TYPE,
                                           P_NCODMODULO  S_GUIA.SISTEMA_MODULO_FORM.NCODMODULO%TYPE,
                                           pCursor       OUT sys_refcursor) AS
  BEGIN
    OPEN pCursor FOR
      SELECT
      /*S.VDESCRIPCION, M.VDESCRIPCION, */
       F.NCODSISTEMA,
       F.NCODMODULO,
       F.NCODFORMULARIO,
       F.VDESCRIPCION,
       F.VURL_FORMULARIO,
       F.NCODFORMULARIO_PADRE,
       F.NNIVEL_FORMULARIO,
       F.NORDEN_FORMULARIO,
       F.NESTADO
        FROM SISTEMA_MODULO_FORM F
       INNER JOIN SISTEMA_MODULO M ON F.NCODSISTEMA = M.NCODSISTEMA
                                  AND F.NCODMODULO = M.NCODMODULO
                                  AND M.NESTADO = 1
       INNER JOIN SISTEMA S ON M.NCODSISTEMA = S.NCODSISTEMA
                           AND S.NESTADO = 0
       WHERE F.NCODSISTEMA = P_NCODSISTEMA
         AND F.NCODMODULO = P_NCODMODULO
      --         AND F.NESTADO = 1
      --START WITH F.NCODFORMULARIO_PADRE = 0
      --CONNECT BY F.NCODFORMULARIO_PADRE = PRIOR F.NCODFORMULARIO
       ORDER BY F.NCODFORMULARIO_PADRE,
                F.NNIVEL_FORMULARIO,
                F.NORDEN_FORMULARIO;
  END SP_OBTENER_SIS_MODULO_FORM_MEN;

  PROCEDURE SP_OBTENER_SIS_MODULO_INICIO(P_VCODPARAMETRO S_GUIA.SISTEMA_PARAMETRO.VCODPARAMETRO%TYPE,
                                         P_VCODUSUARIO   S_GUIA.USUARIO_PERFIL_SISTEMA.VCODUSUARIO%TYPE,
                                         pCursor         OUT sys_refcursor) AS
  BEGIN
    OPEN pCursor FOR
    /*SELECT F.NCODSISTEMA,
                                                 F.NCODMODULO,
                                                 F.NCODFORMULARIO,
                                                 NVL(F.VDESCRIPCION, '') VDESCRIPCION,
                                                 F.NESTADO,
                                                 F.NCODFORMULARIO_PADRE,
                                                 F.NNIVEL_FORMULARIO,
                                                 F.NORDEN_FORMULARIO,
                                                 F.VURL_FORMULARIO,
                                                 UPPER(NVL(P.VDESCRIPCION, '')) VESTADONOMBRE,
                                                 UPPER(S.VDESCRIPCION) VSISTEMANOMBRE,
                                                 UPPER(M.VDESCRIPCION) VMODULONOMBRE,
                                                 F.VRESCRE,
                                                 F.DFECCRE,
                                                 F.VRESACT,
                                                 F.DFECACT
                                            FROM S_GUIA.SISTEMA_MODULO_FORM F
                                           INNER JOIN S_GUIA.SISTEMA S ON F.NCODSISTEMA = S.NCODSISTEMA
                                           INNER JOIN S_GUIA.SISTEMA_MODULO M ON F.NCODSISTEMA = M.NCODSISTEMA
                                                                             AND F.NCODMODULO = M.NCODMODULO
                                            LEFT JOIN S_GUIA.SISTEMA_PARAMETRO P ON P.VCODPARAMETRO = 'ESTADO'
                                                                                AND P.NID = M.NESTADO
                                           WHERE S.VABREVIATURA = P_VCODPARAMETRO
                                             AND F.NESTADO = 1;*/
      SELECT F.NCODSISTEMA,
             F.NCODMODULO,
             F.NCODFORMULARIO,
             NVL(F.VDESCRIPCION, '') VDESCRIPCION,
             F.NESTADO,
             F.NCODFORMULARIO_PADRE,
             F.NNIVEL_FORMULARIO,
             F.NORDEN_FORMULARIO,
             F.VURL_FORMULARIO,
             UPPER(NVL(P.VDESCRIPCION, '')) VESTADONOMBRE,
             UPPER(S.VDESCRIPCION) VSISTEMANOMBRE,
             UPPER(M.VDESCRIPCION) VMODULONOMBRE,
             F.VRESCRE,
             F.DFECCRE,
             F.VRESACT,
             F.DFECACT
        FROM USUARIO_PERFIL_SISTEMA UP
       INNER JOIN PERFIL_SISTEMA PS ON UP.NCODPERFIL = PS.NCODPERFIL
                                   AND UP.NCODSISTEMA = PS.NCODSISTEMA
       INNER JOIN S_GUIA.SISTEMA S ON PS.NCODSISTEMA = S.NCODSISTEMA
       INNER JOIN S_GUIA.SISTEMA_MODULO_FORM F ON S.NCODSISTEMA =
                                                  F.NCODSISTEMA
       INNER JOIN S_GUIA.SISTEMA_MODULO M ON F.NCODSISTEMA = M.NCODSISTEMA
                                         AND F.NCODMODULO = M.NCODMODULO
        LEFT JOIN S_GUIA.SISTEMA_PARAMETRO P ON P.VCODPARAMETRO = 'ESTADO'
                                            AND P.NID = M.NESTADO
       WHERE S.VABREVIATURA = P_VCODPARAMETRO
         AND F.NESTADO = 1
         AND UP.VCODUSUARIO = P_VCODUSUARIO;
  END SP_OBTENER_SIS_MODULO_INICIO;

  PROCEDURE SP_OBTENER_SIS_MOD_INICIO_COD(P_VCODPARAMETRO S_GUIA.SISTEMA_PARAMETRO.VCODPARAMETRO%TYPE,
                                          P_VCODUSUARIO   S_GUIA.USUARIO_PERFIL_SISTEMA.VCODUSUARIO%TYPE,
                                          pCursor         OUT sys_refcursor) AS
  BEGIN
    OPEN pCursor FOR
    
      SELECT SMF.NCODSISTEMA,
             SMF.NCODMODULO,
             SMF.NCODFORMULARIO,
             NVL(SMF.VDESCRIPCION, '') VDESCRIPCION,
             SMF.NESTADO,
             SMF.NCODFORMULARIO_PADRE,
             SMF.NNIVEL_FORMULARIO,
             SMF.NORDEN_FORMULARIO,
             SMF.VURL_FORMULARIO,
             UPPER(NVL(P.VDESCRIPCION, '')) VESTADONOMBRE,
             UPPER(S.VDESCRIPCION) VSISTEMANOMBRE,
             UPPER(M.VDESCRIPCION) VMODULONOMBRE,
             PS.NCODPERFIL,
             PS.VDESCRIPCION,
             SMF.NCODMODULO
        FROM USUARIO_PERFIL_SISTEMA UPS
       INNER JOIN PERFIL_ACCION PA ON PA.NCODSISTEMA = UPS.NCODSISTEMA
                                  AND PA.NCODPERFIL = UPS.NCODPERFIL
       INNER JOIN ACCION_FORMULARIO AF ON AF.NCODACCION_FORMULARIO =
                                          PA.NCODACCION_FORMULARIO
       INNER JOIN SISTEMA_MODULO_FORM SMF ON SMF.NCODSISTEMA =
                                             UPS.NCODSISTEMA
                                         AND SMF.NCODFORMULARIO =
                                             AF.NCODFORMULARIO
       INNER JOIN S_GUIA.SISTEMA_PARAMETRO P ON P.VCODPARAMETRO = 'ESTADO'
                                            AND P.NID = SMF.NESTADO
       INNER JOIN S_GUIA.SISTEMA S ON UPS.NCODSISTEMA = S.NCODSISTEMA
       INNER JOIN S_GUIA.SISTEMA_MODULO M ON SMF.NCODSISTEMA =
                                             M.NCODSISTEMA
                                         AND SMF.NCODMODULO = M.NCODMODULO
       INNER JOIN PERFIL_SISTEMA PS ON UPS.NCODPERFIL = PS.NCODPERFIL
                                   AND UPS.NCODSISTEMA = PS.NCODSISTEMA
       WHERE UPS.VCODUSUARIO = P_VCODUSUARIO
         AND SMF.NESTADO = 1
         AND M.NESTADO = 1
         AND S.NESTADO = 0
         AND AF.NCODSISTEMA = UPS.NCODSISTEMA
         AND AF.NCODMODULO = M.NCODMODULO
      --AND S.VABREVIATURA = 'SEG'
       GROUP BY SMF.NCODSISTEMA,
                SMF.NCODMODULO,
                SMF.NCODFORMULARIO,
                SMF.VDESCRIPCION,
                SMF.NESTADO,
                SMF.NCODFORMULARIO_PADRE,
                SMF.NNIVEL_FORMULARIO,
                SMF.NORDEN_FORMULARIO,
                SMF.VURL_FORMULARIO,
                P.VDESCRIPCION,
                S.VDESCRIPCION,
                M.VDESCRIPCION,
                PS.NCODPERFIL,
                PS.VDESCRIPCION,
                SMF.NCODMODULO
       ORDER BY SMF.NORDEN_FORMULARIO,
                SMF.NCODFORMULARIO,
                SMF.NCODFORMULARIO_PADRE;
  
    /*SELECT F.NCODSISTEMA,
           F.NCODMODULO,
           F.NCODFORMULARIO,
           NVL(F.VDESCRIPCION, '') VDESCRIPCION,
           F.NESTADO,
           F.NCODFORMULARIO_PADRE,
           F.NNIVEL_FORMULARIO,
           F.NORDEN_FORMULARIO,
           F.VURL_FORMULARIO,
           UPPER(NVL(P.VDESCRIPCION, '')) VESTADONOMBRE,
           UPPER(S.VDESCRIPCION) VSISTEMANOMBRE,
           UPPER(M.VDESCRIPCION) VMODULONOMBRE,
           PS.NCODPERFIL,
           PS.VDESCRIPCION
      FROM USUARIO_PERFIL_SISTEMA UP
     INNER JOIN PERFIL_SISTEMA PS ON UP.NCODPERFIL = PS.NCODPERFIL
                                 AND UP.NCODSISTEMA = PS.NCODSISTEMA
     INNER JOIN S_GUIA.SISTEMA S ON PS.NCODSISTEMA = S.NCODSISTEMA
     INNER JOIN S_GUIA.SISTEMA_MODULO_FORM F ON S.NCODSISTEMA =
                                                F.NCODSISTEMA
     INNER JOIN S_GUIA.SISTEMA_MODULO M ON F.NCODSISTEMA = M.NCODSISTEMA
                                       AND F.NCODMODULO = M.NCODMODULO
      LEFT JOIN S_GUIA.SISTEMA_PARAMETRO P ON P.VCODPARAMETRO = 'ESTADO'
                                          AND P.NID = M.NESTADO
    WHERE S.VABREVIATURA = P_VCODPARAMETRO AND F.NESTADO = 1 
     AND UP.VCODUSUARIO = P_VCODUSUARIO
     ORDER BY F.NCODFORMULARIO, F.NCODFORMULARIO_PADRE;*/
  
  END SP_OBTENER_SIS_MOD_INICIO_COD;

  PROCEDURE SP_OBTENER_SIS_MODULO_FORM(P_NCODSISTEMA S_GUIA.SISTEMA_MODULO_FORM.NCODSISTEMA%TYPE,
                                       P_NCODMODULO  S_GUIA.SISTEMA_MODULO_FORM.NCODMODULO%TYPE,
                                       pCursor       OUT sys_refcursor) AS
    valor1 INT;
    valor2 INT;
  BEGIN
  
    IF (P_NCODSISTEMA IS NULL) THEN
      valor1 := 0;
    ELSE
      valor1 := P_NCODSISTEMA;
    END IF;
  
    IF valor1 > 0 THEN
      valor1 := P_NCODSISTEMA;
    ELSE
      valor1 := NULL;
    END IF;
  
    IF (P_NCODMODULO IS NULL) THEN
      valor2 := 0;
    ELSE
      valor2 := P_NCODMODULO;
    END IF;
  
    IF valor2 > 0 THEN
      valor2 := P_NCODMODULO;
    ELSE
      valor2 := NULL;
    END IF;
  
    --DBMS_OUTPUT.PUT_LINE(valor);
  
    OPEN pCursor FOR
      SELECT F.NCODSISTEMA,
             F.NCODMODULO,
             F.NCODFORMULARIO,
             NVL(F.VDESCRIPCION, '') VDESCRIPCION,
             F.NESTADO,
             F.NCODFORMULARIO_PADRE,
             F.NNIVEL_FORMULARIO,
             F.NORDEN_FORMULARIO,
             F.VURL_FORMULARIO,
             UPPER(NVL(P.VDESCRIPCION, '')) VESTADONOMBRE,
             UPPER(S.VDESCRIPCION) VSISTEMANOMBRE,
             UPPER(M.VDESCRIPCION) VMODULONOMBRE,
             F.VRESCRE,
             F.DFECCRE,
             F.VRESACT,
             F.DFECACT
        FROM S_GUIA.SISTEMA_MODULO_FORM F
       INNER JOIN S_GUIA.SISTEMA S ON F.NCODSISTEMA = S.NCODSISTEMA
       INNER JOIN S_GUIA.SISTEMA_PARAMETRO P ON P.VCODPARAMETRO = 'ESTADO'
                                            AND P.NID = F.NESTADO
       INNER JOIN S_GUIA.SISTEMA_MODULO M ON F.NCODSISTEMA = M.NCODSISTEMA
                                         AND F.NCODMODULO = M.NCODMODULO
       WHERE F.NCODSISTEMA = NVL(valor1, F.NCODSISTEMA)
         AND F.NCODMODULO = NVL(valor2, F.NCODMODULO);
  END SP_OBTENER_SIS_MODULO_FORM;

  PROCEDURE SP_OBTENER_SIS_MODULO_FORM_TOT(P_ValueSearch IN VARCHAR,
                                           P_TotalReg    OUT NUMBER) AS
    v_Search VARCHAR2(50);
    n_Total  INT;
  BEGIN
    IF LENGTH(LTRIM(P_ValueSearch)) > 0 THEN
      v_Search := '%' || P_ValueSearch || '%';
    ELSE
      v_Search := NULL;
    END IF;
  
    SELECT COUNT(VESTADONOMBRE)
      INTO n_Total
      FROM VW_FORMULARIO_MODULO
     WHERE VBUSCAR LIKE NVL(v_Search, VBUSCAR);
  
    P_TotalReg := NVL(n_Total, 0);
  END SP_OBTENER_SIS_MODULO_FORM_TOT;

  PROCEDURE SP_OBTENER_SIS_MODULO_FORM_PAG(P_PageSize    IN INT,
                                           P_PageIndex   IN INT,
                                           P_ValueSearch IN VARCHAR,
                                           P_SortColumn  IN VARCHAR,
                                           pCursor       OUT sys_refcursor) AS
    FirstIndex INT;
    LastIndex  INT;
    v_SQL      VARCHAR2(4000);
    v_Search   VARCHAR2(50);
  BEGIN
    LastIndex  := P_PageSize * (P_PageIndex + 1);
    FirstIndex := LastIndex - P_PageSize + 1;
  
    IF LENGTH(LTRIM(P_ValueSearch)) > 0 THEN
      v_Search := '%' || P_ValueSearch || '%';
    ELSE
      v_Search := NULL;
    END IF;
  
    OPEN pCursor FOR
      SELECT *
        FROM (SELECT a.*, ROWNUM AS rnum
                FROM (SELECT NCODSISTEMA,
                             NCODMODULO,
                             NCODFORMULARIO,
                             VDESCRIPCION,
                             NESTADO,
                             NCODFORMULARIO_PADRE,
                             NNIVEL_FORMULARIO,
                             NORDEN_FORMULARIO,
                             VURL_FORMULARIO,
                             VESTADONOMBRE,
                             VSISTEMANOMBRE,
                             VMODULONOMBRE,
                             VRESCRE,
                             DFECCRE,
                             VRESACT,
                             DFECACT
                        FROM VW_FORMULARIO_MODULO
                       WHERE VBUSCAR LIKE NVL(v_Search, VBUSCAR)) a
               WHERE ROWNUM <= LastIndex)
       WHERE rnum >= FirstIndex
       order by NCODSISTEMA;
  END SP_OBTENER_SIS_MODULO_FORM_PAG;

  PROCEDURE SP_OBTENER_SIS_MODULO_FORM_COD(P_NCODSISTEMA    S_GUIA.SISTEMA_MODULO_FORM.NCODSISTEMA%TYPE,
                                           P_NCODMODULO     S_GUIA.SISTEMA_MODULO_FORM.NCODMODULO%TYPE,
                                           P_NCODFORMULARIO S_GUIA.SISTEMA_MODULO_FORM.NCODFORMULARIO%TYPE,
                                           pCursor          OUT sys_refcursor) AS
  BEGIN
    OPEN pCursor FOR
      SELECT F.NCODSISTEMA,
             F.NCODMODULO,
             F.NCODFORMULARIO,
             NVL(F.VDESCRIPCION, '') VDESCRIPCION,
             F.NESTADO,
             F.NCODFORMULARIO_PADRE,
             F.NNIVEL_FORMULARIO,
             F.NORDEN_FORMULARIO,
             F.VURL_FORMULARIO,
             UPPER(NVL(P.VDESCRIPCION, '')) VESTADONOMBRE,
             UPPER(S.VDESCRIPCION) VSISTEMANOMBRE,
             UPPER(M.VDESCRIPCION) VMODULONOMBRE,
             F.VRESCRE,
             F.DFECCRE,
             F.VRESACT,
             F.DFECACT
        FROM S_GUIA.SISTEMA_MODULO_FORM F
       INNER JOIN S_GUIA.SISTEMA S ON F.NCODSISTEMA = S.NCODSISTEMA
       INNER JOIN S_GUIA.SISTEMA_MODULO M ON F.NCODSISTEMA = M.NCODSISTEMA
                                         AND F.NCODMODULO = M.NCODMODULO
        LEFT JOIN S_GUIA.SISTEMA_PARAMETRO P ON P.VCODPARAMETRO = 'ESTADO'
                                            AND P.NID = M.NESTADO
       WHERE F.NCODSISTEMA = P_NCODSISTEMA
         AND F.NCODMODULO = P_NCODMODULO
         AND F.NCODFORMULARIO = P_NCODFORMULARIO;
  END SP_OBTENER_SIS_MODULO_FORM_COD;

  PROCEDURE SP_NUEVO_SIS_MODULO_FORM(P_NCODSISTEMA          S_GUIA.SISTEMA_MODULO_FORM.NCODSISTEMA%TYPE,
                                     P_NCODMODULO           S_GUIA.SISTEMA_MODULO_FORM.NCODMODULO%TYPE,
                                     P_NCODFORMULARIO       S_GUIA.SISTEMA_MODULO_FORM.NCODFORMULARIO%TYPE,
                                     P_VDESCRIPCION         S_GUIA.SISTEMA_MODULO_FORM.VDESCRIPCION%TYPE,
                                     P_NESTADO              S_GUIA.SISTEMA_MODULO_FORM.NESTADO%TYPE,
                                     P_NCODFORMULARIO_PADRE S_GUIA.SISTEMA_MODULO_FORM.NCODFORMULARIO_PADRE%TYPE,
                                     P_NNIVEL_FORMULARIO    S_GUIA.SISTEMA_MODULO_FORM.NNIVEL_FORMULARIO%TYPE,
                                     P_NORDEN_FORMULARIO    S_GUIA.SISTEMA_MODULO_FORM.NORDEN_FORMULARIO%TYPE,
                                     P_VURL_FORMULARIO      S_GUIA.SISTEMA_MODULO_FORM.VURL_FORMULARIO%TYPE,
                                     P_VUSUARIO             S_GUIA.SISTEMA_MODULO_FORM.VRESCRE%TYPE,
                                     P_IDENTITY             OUT NUMBER) IS
    valor INT;
  BEGIN
    --DBMS_OUTPUT.PUT_LINE(P_NCODSISTEMA);
  
    SELECT MAX(NCODFORMULARIO)
      INTO valor
      FROM S_GUIA.SISTEMA_MODULO_FORM
     WHERE NCODSISTEMA = P_NCODSISTEMA
       AND NCODMODULO = P_NCODMODULO;
  
    valor := NVL(valor, 0) + 1;
  
    P_IDENTITY := valor;
    --DBMS_OUTPUT.PUT_LINE(valor);
  
    INSERT INTO S_GUIA.SISTEMA_MODULO_FORM
      (NCODSISTEMA,
       NCODMODULO,
       NCODFORMULARIO,
       VDESCRIPCION,
       NESTADO,
       NCODFORMULARIO_PADRE,
       NNIVEL_FORMULARIO,
       NORDEN_FORMULARIO,
       VURL_FORMULARIO,
       VRESCRE,
       DFECCRE,
       VRESACT,
       DFECACT)
    VALUES
      (P_NCODSISTEMA,
       P_NCODMODULO,
       valor,
       P_VDESCRIPCION,
       P_NESTADO,
       P_NCODFORMULARIO_PADRE,
       P_NNIVEL_FORMULARIO,
       P_NORDEN_FORMULARIO,
       P_VURL_FORMULARIO,
       P_VUSUARIO,
       sysdate,
       P_VUSUARIO,
       sysdate);
  END SP_NUEVO_SIS_MODULO_FORM;

  PROCEDURE SP_UPDATE_SIS_MODULO_FORM(P_NCODSISTEMA          S_GUIA.SISTEMA_MODULO_FORM.NCODSISTEMA%TYPE,
                                      P_NCODMODULO           S_GUIA.SISTEMA_MODULO_FORM.NCODMODULO%TYPE,
                                      P_NCODFORMULARIO       S_GUIA.SISTEMA_MODULO_FORM.NCODFORMULARIO%TYPE,
                                      P_VDESCRIPCION         S_GUIA.SISTEMA_MODULO_FORM.VDESCRIPCION%TYPE,
                                      P_NESTADO              S_GUIA.SISTEMA_MODULO_FORM.NESTADO%TYPE,
                                      P_NCODFORMULARIO_PADRE S_GUIA.SISTEMA_MODULO_FORM.NCODFORMULARIO_PADRE%TYPE,
                                      P_NNIVEL_FORMULARIO    S_GUIA.SISTEMA_MODULO_FORM.NNIVEL_FORMULARIO%TYPE,
                                      P_NORDEN_FORMULARIO    S_GUIA.SISTEMA_MODULO_FORM.NORDEN_FORMULARIO%TYPE,
                                      P_VURL_FORMULARIO      S_GUIA.SISTEMA_MODULO_FORM.VURL_FORMULARIO%TYPE,
                                      P_VUSUARIO             S_GUIA.SISTEMA_MODULO_FORM.VRESCRE%TYPE) IS
  BEGIN
    UPDATE S_GUIA.SISTEMA_MODULO_FORM
       SET VDESCRIPCION         = P_VDESCRIPCION,
           NESTADO              = P_NESTADO,
           NNIVEL_FORMULARIO    = P_NNIVEL_FORMULARIO,
           NORDEN_FORMULARIO    = P_NORDEN_FORMULARIO,
           VURL_FORMULARIO      = P_VURL_FORMULARIO,
           NCODFORMULARIO_PADRE = P_NCODFORMULARIO_PADRE,
           VRESACT              = P_VUSUARIO,
           DFECACT              = sysdate
     WHERE NCODSISTEMA = P_NCODSISTEMA
       AND NCODMODULO = P_NCODMODULO
       AND NCODFORMULARIO = P_NCODFORMULARIO;
  END SP_UPDATE_SIS_MODULO_FORM;

  PROCEDURE SP_UPDATE_SIS_MODULO_FORM_EST(P_NCODSISTEMA    S_GUIA.SISTEMA_MODULO_FORM.NCODSISTEMA%TYPE,
                                          P_NCODMODULO     S_GUIA.SISTEMA_MODULO_FORM.NCODMODULO%TYPE,
                                          P_NCODFORMULARIO S_GUIA.SISTEMA_MODULO_FORM.NCODFORMULARIO%TYPE,
                                          P_NESTADO        S_GUIA.SISTEMA_MODULO_FORM.NESTADO%TYPE,
                                          P_VUSUARIO       S_GUIA.SISTEMA_MODULO_FORM.VRESCRE%TYPE) IS
  BEGIN
    UPDATE S_GUIA.SISTEMA_MODULO_FORM
       SET NESTADO = P_NESTADO, VRESACT = P_VUSUARIO, DFECACT = sysdate
     WHERE NCODSISTEMA = P_NCODSISTEMA
       AND NCODMODULO = P_NCODMODULO
       AND NCODFORMULARIO = P_NCODFORMULARIO;
    /*
     UPDATE S_GUIA.SISTEMA_MODULO_FORM
      SET NESTADO = P_NESTADO, VRESACT = P_VUSUARIO, DFECACT = sysdate
    WHERE NCODSISTEMA = P_NCODSISTEMA
      AND NCODMODULO = P_NCODMODULO
      AND NCODFORMULARIO_PADRE = P_NCODFORMULARIO;   */
  END SP_UPDATE_SIS_MODULO_FORM_EST;

  --------- END / SISTEMA_MODULO_FORM ----------------------------------/

  PROCEDURE SP_OBTENER_SISTEMA_MODULO(P_NCODSISTEMA S_GUIA.SISTEMA_MODULO.NCODSISTEMA%TYPE,
                                      pCursor       OUT sys_refcursor) AS
    valor INT;
  BEGIN
  
    IF (P_NCODSISTEMA IS NULL) THEN
      valor := 0;
    ELSE
      valor := P_NCODSISTEMA;
    END IF;
  
    IF valor > 0 THEN
      valor := P_NCODSISTEMA;
    ELSE
      valor := NULL;
    END IF;
  
    --DBMS_OUTPUT.PUT_LINE(valor);
  
    OPEN pCursor FOR
      SELECT M.NCODSISTEMA,
             M.NCODMODULO,
             UPPER(NVL(M.VDESCRIPCION, '')) VDESCRIPCION,
             M.NESTADO,
             UPPER(P.VDESCRIPCION) VESTADONOMBRE,
             UPPER(S.VDESCRIPCION) VSISTEMANOMBRE,
             M.VRESCRE,
             M.DFECCRE,
             M.VRESACT,
             M.DFECACT
        FROM S_GUIA.SISTEMA_MODULO M
       INNER JOIN S_GUIA.SISTEMA S ON M.NCODSISTEMA = S.NCODSISTEMA
        LEFT JOIN S_GUIA.SISTEMA_PARAMETRO P ON P.VCODPARAMETRO = 'ESTADO'
                                            AND P.NID = M.NESTADO
       WHERE M.NCODSISTEMA = NVL(valor, M.NCODSISTEMA)
      --WHERE M.NCODSISTEMA = P_NCODSISTEMA
       ORDER BY M.NCODSISTEMA, M.NCODMODULO;
  END SP_OBTENER_SISTEMA_MODULO;

  PROCEDURE SP_OBTENER_SISTEMA_MODULO_ACT(P_NCODSISTEMA S_GUIA.SISTEMA_MODULO.NCODSISTEMA%TYPE,
                                          pCursor       OUT sys_refcursor) AS
    valor INT;
  BEGIN
  
    IF (P_NCODSISTEMA IS NULL) THEN
      valor := 0;
    ELSE
      valor := P_NCODSISTEMA;
    END IF;
  
    IF valor > 0 THEN
      valor := P_NCODSISTEMA;
    ELSE
      valor := NULL;
    END IF;
  
    --DBMS_OUTPUT.PUT_LINE(valor);
  
    OPEN pCursor FOR
      SELECT M.NCODSISTEMA,
             M.NCODMODULO,
             UPPER(NVL(M.VDESCRIPCION, '')) VDESCRIPCION,
             M.NESTADO,
             UPPER(P.VDESCRIPCION) VESTADONOMBRE,
             UPPER(S.VDESCRIPCION) VSISTEMANOMBRE,
             M.VRESCRE,
             M.DFECCRE,
             M.VRESACT,
             M.DFECACT
        FROM S_GUIA.SISTEMA_MODULO M
       INNER JOIN S_GUIA.SISTEMA S ON M.NCODSISTEMA = S.NCODSISTEMA
        LEFT JOIN S_GUIA.SISTEMA_PARAMETRO P ON P.VCODPARAMETRO = 'ESTADO'
                                            AND P.NID = M.NESTADO
       WHERE M.NCODSISTEMA = NVL(valor, M.NCODSISTEMA)
            --WHERE M.NCODSISTEMA = P_NCODSISTEMA
         AND S.NESTADO = 0 --AND M.NESTADO = 1
       ORDER BY M.NCODSISTEMA, M.NCODMODULO, M.VDESCRIPCION;
  END SP_OBTENER_SISTEMA_MODULO_ACT;

  PROCEDURE SP_OBTENER_SIS_MOD_ACT_MACT(P_NCODSISTEMA S_GUIA.SISTEMA_MODULO.NCODSISTEMA%TYPE,
                                        pCursor       OUT sys_refcursor) AS
    valor INT;
  BEGIN
  
    /* IF (P_NCODSISTEMA IS NULL) THEN
      valor := 0;
    ELSE
      valor := P_NCODSISTEMA;
    END IF;
    
    IF valor > 0 THEN
      valor := P_NCODSISTEMA;
    ELSE
      valor := NULL;
    END IF;*/
  
    --DBMS_OUTPUT.PUT_LINE(valor);
  
    OPEN pCursor FOR
      SELECT M.NCODSISTEMA,
             M.NCODMODULO,
             UPPER(NVL(M.VDESCRIPCION, '')) VDESCRIPCION,
             M.NESTADO,
             UPPER(P.VDESCRIPCION) VESTADONOMBRE,
             UPPER(S.VDESCRIPCION) VSISTEMANOMBRE,
             M.VRESCRE,
             M.DFECCRE,
             M.VRESACT,
             M.DFECACT
        FROM S_GUIA.SISTEMA_MODULO M
       INNER JOIN S_GUIA.SISTEMA S ON M.NCODSISTEMA = S.NCODSISTEMA
        LEFT JOIN S_GUIA.SISTEMA_PARAMETRO P ON P.VCODPARAMETRO = 'ESTADO'
                                            AND P.NID = M.NESTADO
       WHERE M.NCODSISTEMA = P_NCODSISTEMA
            --WHERE M.NCODSISTEMA = P_NCODSISTEMA
         AND S.NESTADO = 0
         AND M.NESTADO = 1
       ORDER BY M.NCODSISTEMA, M.NCODMODULO, M.VDESCRIPCION;
  END SP_OBTENER_SIS_MOD_ACT_MACT;

  PROCEDURE SP_OBTENER_SISTEMA_MODULO_COD(P_NCODSISTEMA S_GUIA.SISTEMA_MODULO.NCODSISTEMA%TYPE,
                                          P_NCODMODULO  S_GUIA.SISTEMA_MODULO.NCODMODULO%TYPE,
                                          pCursor       OUT sys_refcursor) AS
  BEGIN
    OPEN pCursor FOR
      SELECT M.NCODSISTEMA,
             M.NCODMODULO,
             NVL(M.VDESCRIPCION, '') VDESCRIPCION,
             M.NESTADO,
             UPPER(P.VDESCRIPCION) VESTADONOMBRE,
             UPPER(S.VDESCRIPCION) VSISTEMANOMBRE,
             M.VRESCRE,
             M.DFECCRE,
             M.VRESACT,
             M.DFECACT
        FROM S_GUIA.SISTEMA_MODULO M
       INNER JOIN S_GUIA.SISTEMA S ON M.NCODSISTEMA = S.NCODSISTEMA
        LEFT JOIN S_GUIA.SISTEMA_PARAMETRO P ON P.VCODPARAMETRO = 'ESTADO'
                                            AND P.NID = M.NESTADO
       WHERE M.NCODSISTEMA = P_NCODSISTEMA
         AND M.NCODMODULO = P_NCODMODULO;
  END SP_OBTENER_SISTEMA_MODULO_COD;

  PROCEDURE SP_NUEVO_SISTEMA_MODULO(P_NCODSISTEMA  S_GUIA.SISTEMA_MODULO.NCODSISTEMA%TYPE,
                                    P_NCODMODULO   S_GUIA.SISTEMA_MODULO.NCODMODULO%TYPE,
                                    P_VDESCRIPCION S_GUIA.SISTEMA_MODULO.VDESCRIPCION%TYPE,
                                    P_NESTADO      S_GUIA.SISTEMA_MODULO.NESTADO%TYPE,
                                    P_VUSUARIO     S_GUIA.SISTEMA_MODULO.VRESCRE%TYPE) IS
    valor INT;
  BEGIN
    --DBMS_OUTPUT.PUT_LINE(P_NCODSISTEMA);
  
    SELECT MAX(NCODMODULO)
      INTO valor
      FROM S_GUIA.SISTEMA_MODULO
     WHERE NCODSISTEMA = P_NCODSISTEMA;
  
    valor := NVL(valor, 0) + 1;
  
    --DBMS_OUTPUT.PUT_LINE(valor);
  
    INSERT INTO S_GUIA.SISTEMA_MODULO
      (NCODSISTEMA,
       NCODMODULO,
       VDESCRIPCION,
       NESTADO,
       VRESCRE,
       DFECCRE,
       VRESACT,
       DFECACT)
    VALUES
      (P_NCODSISTEMA,
       valor,
       P_VDESCRIPCION,
       P_NESTADO,
       P_VUSUARIO,
       sysdate,
       P_VUSUARIO,
       sysdate);
  END SP_NUEVO_SISTEMA_MODULO;

  PROCEDURE SP_UPDATE_SISTEMA_MODULO(P_NCODSISTEMA  S_GUIA.SISTEMA_MODULO.NCODSISTEMA%TYPE,
                                     P_NCODMODULO   S_GUIA.SISTEMA_MODULO.NCODMODULO%TYPE,
                                     P_VDESCRIPCION S_GUIA.SISTEMA_MODULO.VDESCRIPCION%TYPE,
                                     P_NESTADO      S_GUIA.SISTEMA_MODULO.NESTADO%TYPE,
                                     P_VUSUARIO     S_GUIA.SISTEMA_MODULO.VRESCRE%TYPE) IS
  BEGIN
    UPDATE S_GUIA.SISTEMA_MODULO
       SET VDESCRIPCION = P_VDESCRIPCION,
           NESTADO      = P_NESTADO,
           VRESACT      = P_VUSUARIO,
           DFECACT      = sysdate
     WHERE NCODSISTEMA = P_NCODSISTEMA
       AND NCODMODULO = P_NCODMODULO;
  
    update sistema_modulo_form
       set nestado = P_NESTADO
     where ncodsistema = P_NCODSISTEMA
       and ncodmodulo = P_NCODMODULO;
  
  END SP_UPDATE_SISTEMA_MODULO;

  PROCEDURE SP_UPDATE_SISTEMA_MODULO_ESTAD(P_NCODSISTEMA S_GUIA.SISTEMA_MODULO.NCODSISTEMA%TYPE,
                                           P_NCODMODULO  S_GUIA.SISTEMA_MODULO.NCODMODULO%TYPE,
                                           P_NESTADO     S_GUIA.SISTEMA_MODULO.NESTADO%TYPE,
                                           P_VUSUARIO    S_GUIA.SISTEMA_MODULO.VRESCRE%TYPE) IS
  BEGIN
    UPDATE S_GUIA.SISTEMA_MODULO
       SET NESTADO = P_NESTADO, VRESACT = P_VUSUARIO, DFECACT = sysdate
     WHERE NCODSISTEMA = P_NCODSISTEMA
       AND NCODMODULO = P_NCODMODULO;
  
    update sistema_modulo_form
       set nestado = P_NESTADO
     where ncodsistema = P_NCODSISTEMA
       and ncodmodulo = P_NCODMODULO;
  
  END SP_UPDATE_SISTEMA_MODULO_ESTAD;

  PROCEDURE SP_OBTENER_ACCESO_SISTEMA(P_VCODUSUARIO S_GUIA.ACCESOS.VCODUSUARIO%TYPE,
                                      P_NCODIP      ACCESOS.NCODIP%TYPE,
                                      pCursor       OUT sys_refcursor) AS
  BEGIN
    OPEN pCursor FOR
      SELECT NCODSISTEMA,
             VCODUSUARIO,
             DFECFECHA,
             NCODIP,
             NCODAREA,
             VCODACCESO,
             VCODINGRESO,
             NCORRELATIVO,
             NESTADO,
             VRESCRE,
             DFECCRE,
             VRESACT,
             DFECACT,
             VTOKEN
        FROM accesos
       WHERE vcodusuario = P_VCODUSUARIO
         AND ncodsistema =
             (SELECT NCODSISTEMA
                FROM SISTEMA
               WHERE VABREVIATURA =
                     (SELECT VVALOR
                        FROM SISTEMA_PARAMETRO
                       WHERE VCODPARAMETRO = 'PREFIJO'))
         AND ncodip = P_NCODIP
         AND to_char(DFECFECHA, 'yyyyddmm') = to_char(SYSDATE, 'yyyyddmm')
         AND nestado = 1;
  END SP_OBTENER_ACCESO_SISTEMA;

  PROCEDURE SP_NUEVO_ACCESO(P_VCODUSUARIO ACCESOS.VCODUSUARIO%TYPE,
                            -- P_DFECFECHA   ACCESOS.DFECFECHA%TYPE,
                            P_NCODIP       ACCESOS.NCODIP%TYPE,
                            P_NCODAREA     ACCESOS.NCODAREA%TYPE,
                            P_VCODACCESO   ACCESOS.VCODACCESO%TYPE,
                            P_VCODINGRESO  ACCESOS.VCODINGRESO%TYPE,
                            P_NCORRELATIVO ACCESOS.NCORRELATIVO%TYPE,
                            P_NESTADO      ACCESOS.NESTADO%TYPE,
                            P_VTOKEN       ACCESOS.VTOKEN%TYPE) AS
  
    V_NCODSISTEMA number(5);
  BEGIN
  
    SELECT NCODSISTEMA
      INTO V_NCODSISTEMA
      FROM SISTEMA A
     WHERE VABREVIATURA = (SELECT VVALOR
                             FROM SISTEMA_PARAMETRO
                            WHERE VCODPARAMETRO = 'PREFIJO');
  
    INSERT INTO ACCESOS
      (NCODSISTEMA,
       VCODUSUARIO,
       DFECFECHA,
       NCODIP,
       NCODAREA,
       VCODACCESO,
       VCODINGRESO,
       NCORRELATIVO,
       NESTADO,
       VRESCRE,
       DFECCRE,
       VRESACT,
       DFECACT,
       VTOKEN)
    VALUES
      (V_NCODSISTEMA,
       P_VCODUSUARIO,
       SYSDATE,
       P_NCODIP,
       P_NCODAREA,
       P_VCODACCESO,
       P_VCODINGRESO,
       P_NCORRELATIVO,
       P_NESTADO,
       P_VCODUSUARIO,
       SYSDATE,
       P_VCODUSUARIO,
       SYSDATE,
       P_VTOKEN);
  
  END SP_NUEVO_ACCESO;

  PROCEDURE SP_INVALIDAR_ACCESO(P_VTOKEN S_GUIA.ACCESOS.VTOKEN%TYPE) AS
  BEGIN
  
    UPDATE ACCESOS
       SET NESTADO = 0, DFECACT = SYSDATE
     WHERE VTOKEN = P_VTOKEN;
  END SP_INVALIDAR_ACCESO;

  PROCEDURE SP_UPDATE_USUARIO_INTENTOS(P_VCODUSUARIO     S_GUIA.USUARIO.VCODUSUARIO%TYPE,
                                       P_NUMERO_INTENTOS S_GUIA.USUARIO.NUMERO_INTENTOS%TYPE) AS
  BEGIN
    UPDATE S_GUIA.USUARIO
       SET NUMERO_INTENTOS = P_NUMERO_INTENTOS
     WHERE VCODUSUARIO = P_VCODUSUARIO;
  
  END SP_UPDATE_USUARIO_INTENTOS;

  PROCEDURE SP_OBTENER_CLAVES(P_VCODUSUARIO S_GUIA.USUARIO.VCODUSUARIO%TYPE,
                              pCursor       OUT sys_refcursor) AS
  BEGIN
    OPEN pCursor FOR
      SELECT *
        FROM (SELECT VCODUSUARIO,
                     NCODCLAVE,
                     VPASS,
                     NESTADO,
                     VFLAG_TEMP,
                     VRESCRE,
                     DFECCRE,
                     VRESACT,
                     DFECACT
                FROM CLAVE
               WHERE VCODUSUARIO = P_VCODUSUARIO
               ORDER BY DFECCRE DESC)
       WHERE ROWNUM <= (select VVALOR
                          from sistema_parametro
                         WHERE VCODPARAMETRO = 'ANTERIOR');
  
  END SP_OBTENER_CLAVES;

  PROCEDURE SP_MANTENIMIENTO_CLAVES(P_VCODUSUARIO S_GUIA.USUARIO.VCODUSUARIO%TYPE,
                                    P_VPASS       S_GUIA.CLAVE.VPASS%TYPE,
                                    P_VFLAG_TEMP  S_GUIA.CLAVE.VFLAG_TEMP%TYPE) AS
  BEGIN
    /*
      UPDATE USUARIO
         SET VPASS = P_VPASS, VFLAG_CAMBIAR_CLAVE = P_VFLAG_TEMP
       WHERE VCODUSUARIO = P_VCODUSUARIO;
    */
    UPDATE CLAVE
       SET NESTADO = 0
     WHERE VCODUSUARIO = P_VCODUSUARIO
       AND NESTADO = 1;
  
    INSERT INTO CLAVE
    VALUES
      (P_VCODUSUARIO,
       (SELECT MAX(NCODCLAVE) + 1 FROM CLAVE),
       P_VPASS,
       1,
       P_VFLAG_TEMP,
       P_VCODUSUARIO,
       SYSDATE,
       P_VCODUSUARIO,
       SYSDATE,
       NULL);
  
  END SP_MANTENIMIENTO_CLAVES;

  PROCEDURE SP_CAMBIO_CLAVE_OBLIGATORIO(P_VCODUSUARIO S_GUIA.USUARIO.VCODUSUARIO%TYPE) AS
    V_USUARIO VARCHAR(15);
  
    CURSOR pCursor IS
    
      SELECT T.VCODUSUARIO
        FROM CLAVE T
       WHERE NESTADO = 1
         AND TRUNC(SYSDATE) - TRUNC(T.DFECCRE) >=
             (SELECT VVALOR
                FROM SISTEMA_PARAMETRO
               where VCODPARAMETRO = 'EXPIRACION');
  
  BEGIN
  
    OPEN pCursor;
  
    loop
      fetch pCursor
        into V_USUARIO;
      exit when pCursor%notfound;
    
      UPDATE USUARIO
         SET VFLAG_CAMBIAR_CLAVE = '1'
       WHERE VCODUSUARIO = V_USUARIO;
    
      UPDATE CLAVE
         SET NESTADO = 0
       WHERE VCODUSUARIO = V_USUARIO
         AND NESTADO = 1;
    
    end loop;
  
    CLOSE pCursor;
  
  END SP_CAMBIO_CLAVE_OBLIGATORIO;

  PROCEDURE SP_CAMBIO_CLAVE_TMP(P_VCODUSUARIO S_GUIA.USUARIO.VCODUSUARIO%TYPE) AS
    V_USUARIO VARCHAR(15);
  
    CURSOR pCursor IS
    
      SELECT T.VCODUSUARIO
        FROM CLAVE T
       WHERE NESTADO = 1
         AND VFLAG_TEMP = 1
         AND vflag_clave_invalida IS NULL
         AND TRUNC(SYSDATE) - TRUNC(T.DFECCRE) >=
             (SELECT VVALOR
                FROM SISTEMA_PARAMETRO
               where VCODPARAMETRO = 'CAMBIO_TMP')
       GROUP BY T.VCODUSUARIO
      HAVING COUNT(1) = 1;
  
  BEGIN
  
    OPEN pCursor;
  
    loop
      fetch pCursor
        into V_USUARIO;
      exit when pCursor%notfound;
    
      UPDATE CLAVE
         SET vflag_clave_invalida = '1'
       WHERE VCODUSUARIO = V_USUARIO;
    
    end loop;
  
    CLOSE pCursor;
  
  END SP_CAMBIO_CLAVE_TMP;

  PROCEDURE SP_OBTENER_ULTIMO_ACCESO(P_VCODUSUARIO S_GUIA.ACCESOS.VCODUSUARIO%TYPE,
                                     pCursor       OUT sys_refcursor) AS
  BEGIN
    OPEN pCursor FOR
      SELECT NCODSISTEMA,
             VCODUSUARIO,
             DFECFECHA,
             NCODIP,
             NCODAREA,
             VCODACCESO,
             VCODINGRESO,
             NCORRELATIVO,
             NESTADO,
             VRESCRE,
             DFECCRE,
             VRESACT,
             DFECACT,
             VTOKEN
        FROM accesos
       WHERE vcodusuario = P_VCODUSUARIO
         AND ncodsistema =
             (SELECT NCODSISTEMA
                FROM SISTEMA
               WHERE VABREVIATURA =
                     (SELECT VVALOR
                        FROM SISTEMA_PARAMETRO
                       WHERE VCODPARAMETRO = 'PREFIJO'))
         AND DFECCRE = (select max(DFECCRE) from accesos)
       ORDER BY DFECFECHA DESC;
  END SP_OBTENER_ULTIMO_ACCESO;

  PROCEDURE SP_VERI_CLAVE_INVALIDA(P_VCODUSUARIO S_GUIA.USUARIO.VCODUSUARIO%TYPE,
                                   pCursor       OUT sys_refcursor) AS
  BEGIN
    OPEN pCursor FOR
      SELECT *
        FROM (SELECT VCODUSUARIO,
                     NCODCLAVE,
                     VPASS,
                     NESTADO,
                     VFLAG_TEMP,
                     VRESCRE,
                     DFECCRE,
                     VRESACT,
                     DFECACT
                FROM CLAVE
               WHERE VCODUSUARIO = P_VCODUSUARIO
                 AND vflag_clave_invalida = '1'
                 AND NESTADO = 1
               ORDER BY DFECCRE DESC)
       WHERE ROWNUM <= 1;
  
  END SP_VERI_CLAVE_INVALIDA;

  PROCEDURE SP_OBTENER_SISTEMA_MODULO_NOM(P_NCODSISTEMA  S_GUIA.SISTEMA_MODULO.NCODSISTEMA%TYPE,
                                          P_VDESCRIPCION S_GUIA.SISTEMA_MODULO.VDESCRIPCION%TYPE,
                                          pCursor        OUT sys_refcursor) AS
  BEGIN
    OPEN pCursor FOR
      SELECT M.NCODSISTEMA,
             M.NCODMODULO,
             NVL(M.VDESCRIPCION, '') VDESCRIPCION,
             M.NESTADO,
             UPPER(P.VDESCRIPCION) VESTADONOMBRE,
             UPPER(S.VDESCRIPCION) VSISTEMANOMBRE,
             M.VRESCRE,
             M.DFECCRE,
             M.VRESACT,
             M.DFECACT
        FROM S_GUIA.SISTEMA_MODULO M
       INNER JOIN S_GUIA.SISTEMA S ON M.NCODSISTEMA = S.NCODSISTEMA
        LEFT JOIN S_GUIA.SISTEMA_PARAMETRO P ON P.VCODPARAMETRO = 'ESTADO'
                                            AND P.NID = M.NESTADO
       WHERE M.NCODSISTEMA = P_NCODSISTEMA
         AND M.VDESCRIPCION = P_VDESCRIPCION;
  END SP_OBTENER_SISTEMA_MODULO_NOM;

  PROCEDURE SP_OBTENER_MENU_PB(P_VCODUSUARIO S_GUIA.USUARIO.VCODUSUARIO%TYPE,
                               pCursor       OUT sys_refcursor) AS
  BEGIN
    OPEN pCursor FOR
      SELECT SMF.VURL_FORMULARIO
        FROM USUARIO_PERFIL_SISTEMA UPS
       INNER JOIN PERFIL_ACCION PA ON PA.NCODSISTEMA = UPS.NCODSISTEMA
                                  AND PA.NCODPERFIL = UPS.NCODPERFIL
       INNER JOIN ACCION_FORMULARIO AF ON AF.NCODACCION_FORMULARIO =
                                          PA.NCODACCION_FORMULARIO
       INNER JOIN SISTEMA_MODULO_FORM SMF ON SMF.NCODSISTEMA =
                                             UPS.NCODSISTEMA
                                         AND SMF.NCODFORMULARIO =
                                             AF.NCODFORMULARIO
       INNER JOIN S_GUIA.SISTEMA_PARAMETRO P ON P.VCODPARAMETRO = 'ESTADO'
                                            AND P.NID = SMF.NESTADO
       INNER JOIN S_GUIA.SISTEMA S ON UPS.NCODSISTEMA = S.NCODSISTEMA
       INNER JOIN S_GUIA.SISTEMA_MODULO M ON SMF.NCODSISTEMA =
                                             M.NCODSISTEMA
                                         AND AF.NCODMODULO = M.NCODMODULO
                                         AND SMF.NCODMODULO = M.NCODMODULO
       INNER JOIN PERFIL_SISTEMA PS ON UPS.NCODPERFIL = PS.NCODPERFIL
                                   AND UPS.NCODSISTEMA = PS.NCODSISTEMA
       WHERE UPS.VCODUSUARIO = P_VCODUSUARIO
         AND SMF.NESTADO = 1
         AND S.NESTADO = 0
         AND M.NESTADO = 1
         AND PS.VESTADO = 1;
  END SP_OBTENER_MENU_PB;

  PROCEDURE SP_OBTENER_SIS_MODULO_HIJOS(P_NCODSISTEMA    S_GUIA.SISTEMA_MODULO_FORM.NCODSISTEMA%TYPE,
                                        P_NCODMODULO     S_GUIA.SISTEMA_MODULO_FORM.NCODMODULO%TYPE,
                                        P_NCODFORMULARIO VARCHAR,
                                        -- P_NCODFORMULARIO S_GUIA.SISTEMA_MODULO_FORM.NCODFORMULARIO%TYPE,
                                        pCursor OUT sys_refcursor) AS
  BEGIN
    OPEN pCursor FOR
      SELECT F.NCODSISTEMA,
             F.NCODMODULO,
             F.NCODFORMULARIO,
             '',
             F.NESTADO,
             F.NCODFORMULARIO_PADRE,
             F.NNIVEL_FORMULARIO,
             F.NORDEN_FORMULARIO,
             F.VURL_FORMULARIO,
             '',
             '',
             '',
             F.VRESCRE,
             F.DFECCRE,
             F.VRESACT,
             F.DFECACT
        FROM SISTEMA_MODULO_FORM F
       WHERE F.NCODSISTEMA = P_NCODSISTEMA
         AND F.NCODMODULO = P_NCODMODULO
            --              AND (F.NCODFORMULARIO = P_NCODFORMULARIO OR F.NCODFORMULARIO_PADRE = P_NCODFORMULARIO)  ;
         AND (F.NCODFORMULARIO_PADRE IN P_NCODFORMULARIO);
    /*
    SELECT F.NCODSISTEMA,
           F.NCODMODULO,
           F.NCODFORMULARIO,
           '',
           F.NESTADO,
           F.NCODFORMULARIO_PADRE,
           F.NNIVEL_FORMULARIO,
           F.NORDEN_FORMULARIO,
           F.VURL_FORMULARIO,
           '',
           '',
           '',
           F.VRESCRE,
           F.DFECCRE,
           F.VRESACT,
           F.DFECACT
      FROM SISTEMA_MODULO_FORM F
     START WITH F.NCODSISTEMA = P_NCODSISTEMA
            AND F.NCODMODULO = P_NCODMODULO
            AND F.NCODFORMULARIO = P_NCODFORMULARIO
    CONNECT BY PRIOR NCODFORMULARIO = NCODFORMULARIO_PADRE;*/
  
  END SP_OBTENER_SIS_MODULO_HIJOS;

END PKG_SEGURIDAD;
