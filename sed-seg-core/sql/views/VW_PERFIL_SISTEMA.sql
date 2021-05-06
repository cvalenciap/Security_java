CREATE OR REPLACE VIEW VW_PERFIL_SISTEMA AS
SELECT "NCODPERFIL","NCODSISTEMA","VDESCRIPCION","VESTADO","VESTADONOMBRE","VSISTEMANOMBRE","VRESCRE","DFECCRE","VRESACT","DFECACT","VBUSCAR" FROM (SELECT PS.NCODPERFIL,
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
             NVL(PS.VRESCRE,'') VRESCRE,
             PS.DFECCRE,
             --NVL(PS.DFECCRE,'06/06/2017') DFECCRE,
             NVL(PS.VRESACT,'') VRESACT,
             PS.DFECACT,
             --NVL(PS.DFECACT,'06/06/2017') DFECACT,
            (UPPER(PS.VDESCRIPCION) || UPPER(S.VDESCRIPCION || S.VDESCRIPCION)) VBUSCAR
        FROM S_GUIA.PERFIL_SISTEMA PS
       INNER JOIN S_GUIA.SISTEMA S ON PS.NCODSISTEMA = S.NCODSISTEMA
      --LEFT JOIN S_GUIA.SISTEMA_PARAMETRO P ON P.VCODPARAMETRO = 'ESTADO'
      --                                  AND P.NID = PS.VESTADO
      --AND PS.VESTADO IN ('1', '0')
      WHERE S.NESTADO = 0
       ORDER BY PS.NCODSISTEMA, PS.NCODPERFIL)
;
