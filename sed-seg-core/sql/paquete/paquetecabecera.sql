CREATE OR REPLACE PACKAGE PKG_SEGURIDAD AS

  PROCEDURE SP_NUEVO_SISTEMA(P_VDESCRIPCION S_GUIA.SISTEMA.VDESCRIPCION%TYPE,
                             P_VABREVIATURA S_GUIA.SISTEMA.VABREVIATURA%TYPE,
                             P_NESTADO      S_GUIA.SISTEMA.NESTADO%TYPE,
                             P_VPROGRAMA    S_GUIA.SISTEMA.VPROGRAMA%TYPE,
                             P_VFLAG_NIVEL  S_GUIA.SISTEMA.VFLAG_NIVEL%TYPE,
                             P_VUSUARIO     S_GUIA.SISTEMA.VRESCRE%TYPE);
  PROCEDURE SP_UPDATE_SISTEMA(P_NCODSISTEMA  S_GUIA.SISTEMA.NCODSISTEMA%TYPE,
                              P_VDESCRIPCION S_GUIA.SISTEMA.VDESCRIPCION%TYPE,
                              P_VABREVIATURA S_GUIA.SISTEMA.VABREVIATURA%TYPE,
                              P_NESTADO      S_GUIA.SISTEMA.NESTADO%TYPE,
                              P_VPROGRAMA    S_GUIA.SISTEMA.VPROGRAMA%TYPE,
                              P_VFLAG_NIVEL  S_GUIA.SISTEMA.VFLAG_NIVEL%TYPE,
                              P_VUSUARIO     S_GUIA.SISTEMA.VRESCRE%TYPE);
  PROCEDURE SP_OBTENER_SISTEMAS(pCursor OUT sys_refcursor);
  PROCEDURE SP_OBTENER_SISTEMAS_ACTIVOS(pCursor OUT sys_refcursor);
  PROCEDURE SP_OBTENER_SISTEMA_ID(P_NCODSISTEMA S_GUIA.SISTEMA.NCODSISTEMA%TYPE,
                                  pCursor       OUT sys_refcursor);
  PROCEDURE SP_UPDATE_SISTEMA_ESTADO(P_NCODSISTEMA S_GUIA.SISTEMA.NCODSISTEMA%TYPE,
                                     P_NESTADO     S_GUIA.SISTEMA.NESTADO%TYPE,
                                     P_VUSUARIO    S_GUIA.SISTEMA.VRESCRE%TYPE);

  PROCEDURE SP_OBTENER_USUARIO_PERFIL_TOT(P_ValueSearch IN VARCHAR,
                                          P_TotalReg    OUT NUMBER);
  PROCEDURE SP_OBTENER_USUARIO_PERFIL_PAG(P_PageSize    IN INT,
                                          P_PageIndex   IN INT,
                                          P_ValueSearch IN VARCHAR,
                                          P_SortColumn  IN VARCHAR,
                                          pCursor       OUT sys_refcursor);
  ---//---- START //-- USUARIO PERFIL SISTEMA
  PROCEDURE SP_OBTENER_USUA_PERF_SIST(pCursor OUT sys_refcursor);
  PROCEDURE SP_OBTENER_USUA_PERF_SIST_USU(P_NCODSISTEMA S_GUIA.USUARIO_PERFIL_SISTEMA.NCODSISTEMA%TYPE,
                                          P_VCODUSUARIO S_GUIA.USUARIO_PERFIL_SISTEMA.VCODUSUARIO%TYPE,
                                          pCursor       OUT sys_refcursor);
  PROCEDURE SP_OBTENER_USUA_PERF_SIST_COD(P_NCODPERFIL  S_GUIA.USUARIO_PERFIL_SISTEMA.NCODPERFIL%TYPE,
                                          P_NCODSISTEMA S_GUIA.USUARIO_PERFIL_SISTEMA.NCODSISTEMA%TYPE,
                                          P_VCODUSUARIO S_GUIA.USUARIO_PERFIL_SISTEMA.VCODUSUARIO%TYPE,
                                          pCursor       OUT sys_refcursor);
  PROCEDURE SP_NUEVO_USUA_PERF_SIST(P_NCODPERFIL  S_GUIA.USUARIO_PERFIL_SISTEMA.NCODPERFIL%TYPE,
                                    P_NCODSISTEMA S_GUIA.USUARIO_PERFIL_SISTEMA.NCODSISTEMA%TYPE,
                                    P_VCODUSUARIO S_GUIA.USUARIO_PERFIL_SISTEMA.VCODUSUARIO%TYPE,
                                    P_NESTADO     S_GUIA.USUARIO_PERFIL_SISTEMA.NESTADO%TYPE,
                                    P_VUSUARIO    S_GUIA.USUARIO_PERFIL_SISTEMA.VRESCRE%TYPE);

  PROCEDURE SP_UPDATE_USUA_PERF_SIST(P_NCODPERFIL  S_GUIA.USUARIO_PERFIL_SISTEMA.NCODPERFIL%TYPE,
                                     P_NCODSISTEMA S_GUIA.USUARIO_PERFIL_SISTEMA.NCODSISTEMA%TYPE,
                                     P_VCODUSUARIO S_GUIA.USUARIO_PERFIL_SISTEMA.VCODUSUARIO%TYPE,
                                     P_NESTADO     S_GUIA.USUARIO_PERFIL_SISTEMA.NESTADO%TYPE,
                                     P_VUSUARIO    S_GUIA.USUARIO_PERFIL_SISTEMA.VRESCRE%TYPE);

  PROCEDURE SP_UPDATE_USUA_PERF_SIST_EST(P_NCODPERFIL  S_GUIA.USUARIO_PERFIL_SISTEMA.NCODPERFIL%TYPE,
                                         P_NCODSISTEMA S_GUIA.USUARIO_PERFIL_SISTEMA.NCODSISTEMA%TYPE,
                                         P_VCODUSUARIO S_GUIA.USUARIO_PERFIL_SISTEMA.VCODUSUARIO%TYPE,
                                         P_NESTADO     S_GUIA.USUARIO_PERFIL_SISTEMA.NESTADO%TYPE,
                                         P_VUSUARIO    S_GUIA.USUARIO_PERFIL_SISTEMA.VRESCRE%TYPE);
  ---//---- END //-- USUARIO PERFIL SISTEMA

  PROCEDURE SP_OBTENER_PERFIL_ACCION(P_NCODPERFIL  S_GUIA.PERFIL_ACCION.NCODPERFIL%TYPE,
                                     P_NCODSISTEMA S_GUIA.PERFIL_ACCION.NCODSISTEMA%TYPE,
                                     pCursor       OUT sys_refcursor);
  PROCEDURE SP_OBTENER_PERFIL_ACCION_COD(P_NCODPERFIL_ACCION S_GUIA.PERFIL_ACCION.NCODPERFIL%TYPE,
                                         pCursor             OUT sys_refcursor);
  PROCEDURE SP_NUEVO_PERFIL_ACCION(NCODPERFIL_ACCION       S_GUIA.PERFIL_ACCION.NCODACCION_FORMULARIO%TYPE,
                                   P_NCODPERFIL            S_GUIA.PERFIL_ACCION.NCODPERFIL%TYPE,
                                   P_NCODSISTEMA           S_GUIA.PERFIL_ACCION.NCODSISTEMA%TYPE,
                                   P_NCODACCION_FORMULARIO S_GUIA.PERFIL_ACCION.NCODACCION_FORMULARIO%TYPE);
  PROCEDURE SP_UPDATE_PERFIL_ACCION(P_NCODPERFIL  S_GUIA.PERFIL_ACCION.NCODPERFIL%TYPE,
                                    P_NCODSISTEMA S_GUIA.PERFIL_ACCION.NCODSISTEMA%TYPE);

  PROCEDURE SP_OBTENER_USUARIO_SISTEMA(pCursor OUT sys_refcursor);
  PROCEDURE SP_OBTENER_USUARIO_PERFIL(pCursor OUT sys_refcursor);
  PROCEDURE SP_OBTENER_USUARIO_POR_USUARIO(P_VUSUARIO S_GUIA.USUARIO.VCODUSUARIO%TYPE,
                                           pCursor    OUT sys_refcursor);
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
                             P_NCODPERFIL          S_GUIA.USUARIO_PERFIL_SISTEMA.NCODPERFIL%TYPE);
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
                              P_NCODPERFIL          S_GUIA.USUARIO_PERFIL_SISTEMA.NCODPERFIL%TYPE);
  PROCEDURE SP_UPDATE_USUARIO_ESTADO(P_VCODUSUARIO  S_GUIA.USUARIO.VCODUSUARIO%TYPE,
                                     P_NESTADO      S_GUIA.USUARIO.NESTADO%TYPE,
                                     P_VRESPONSABLE S_GUIA.USUARIO.VRESPONSABLE%TYPE,
                                     P_NCODSISTEMA  S_GUIA.USUARIO_PERFIL_SISTEMA.NCODSISTEMA%TYPE);

  PROCEDURE SP_UPDATE_USUARIO_BLOQUEADO(P_VCODUSUARIO     S_GUIA.USUARIO.VCODUSUARIO%TYPE,
                                        P_NESTADO         S_GUIA.USUARIO.NESTADO%TYPE,
                                        P_NUMERO_INTENTOS S_GUIA.USUARIO.NUMERO_INTENTOS%TYPE,
                                        P_VRESPONSABLE    S_GUIA.USUARIO.VRESPONSABLE%TYPE,
                                        P_NCODSISTEMA     S_GUIA.USUARIO_PERFIL_SISTEMA.NCODSISTEMA%TYPE);

  PROCEDURE SP_OBTENER_CENTRO(pCursor OUT sys_refcursor);
  PROCEDURE SP_OBTENER_CENTRO_COD(P_NCODCENTRO S_GUIA.CENTRO.NCODCENTRO%TYPE,
                                  pCursor      OUT sys_refcursor);

  PROCEDURE SP_OBTENER_AREA(pCursor OUT sys_refcursor);
  PROCEDURE SP_OBTENER_AREA_COD(P_NCODAREA S_GUIA.AREA.NCODAREA%TYPE,
                                pCursor    OUT sys_refcursor);

  PROCEDURE SP_OBTENER_TRABAJADOR(pCursor OUT sys_refcursor);
  PROCEDURE SP_OBTENER_TRABAJADOR_COD(P_NCODTRABAJADOR S_GUIA.TRABAJADOR.NCODTRABAJADOR%TYPE,
                                      pCursor          OUT sys_refcursor);
  PROCEDURE SP_OBTENER_TRABAJADOR_FICHA(P_NFICHA S_GUIA.TRABAJADOR.NFICHA%TYPE,
                                        pCursor  OUT sys_refcursor);

  PROCEDURE SP_OBTENER_USUARIO_ACCION_COD(P_VCODUSUARIO S_GUIA.USUARIO.VCODUSUARIO%TYPE,
                                          pCursor       OUT sys_refcursor);

  PROCEDURE SP_OBTENER_PARAMETRO(P_VCODPARAMETRO S_GUIA.SISTEMA_PARAMETRO.VCODPARAMETRO%TYPE,
                                 pCursor         OUT sys_refcursor);
  PROCEDURE SP_OBTENER_PARAMETRO_GENERAL(pCursor OUT sys_refcursor);
  PROCEDURE SP_OBTENER_PARAMETRO_COD(P_VCODPARAMETRO S_GUIA.SISTEMA_PARAMETRO.VCODPARAMETRO%TYPE,
                                     P_NCODIGO       S_GUIA.SISTEMA_PARAMETRO.NCODIGO%TYPE,
                                     pCursor         OUT sys_refcursor);
  PROCEDURE SP_UPDATE_PARAMETRO_COD(P_VCODPARAMETRO S_GUIA.SISTEMA_PARAMETRO.VCODPARAMETRO%TYPE,
                                    P_NCODIGO       S_GUIA.SISTEMA_PARAMETRO.NCODIGO%TYPE,
                                    P_VDESCRIPCION  S_GUIA.SISTEMA_PARAMETRO.VDESCRIPCION%TYPE,
                                    P_VVALOR        S_GUIA.SISTEMA_PARAMETRO.VVALOR%TYPE,
                                    P_NESTADO       S_GUIA.SISTEMA_PARAMETRO.NESTADO%TYPE,
                                    P_VUSUARIO      S_GUIA.SISTEMA_PARAMETRO.VRESACT%TYPE);

  PROCEDURE SP_OBTENER_PERFILSISTEMA(P_NCODSISTEMA S_GUIA.PERFIL_SISTEMA.NCODSISTEMA%TYPE,
                                     pCursor       OUT sys_refcursor);

  PROCEDURE SP_OBTENER_PERFILSISTEMA_ACT(P_NCODSISTEMA S_GUIA.PERFIL_SISTEMA.NCODSISTEMA%TYPE,
                                         pCursor       OUT sys_refcursor);

  PROCEDURE SP_OBTENER_PERFIL_SISTEMA_TOT(P_ValueSearch IN VARCHAR,
                                          P_TotalReg    OUT NUMBER);
  PROCEDURE SP_OBTENER_PERFIL_SISTEMA_PAG(P_PageSize    IN INT,
                                          P_PageIndex   IN INT,
                                          P_ValueSearch IN VARCHAR,
                                          P_SortColumn  IN VARCHAR,
                                          pCursor       OUT sys_refcursor);

  PROCEDURE SP_OBTENER_PERFIL_SISTEMA_COD(P_NCODPERFIL  S_GUIA.PERFIL_SISTEMA.NCODPERFIL%TYPE,
                                          P_NCODSISTEMA S_GUIA.PERFIL_SISTEMA.NCODSISTEMA%TYPE,
                                          pCursor       OUT sys_refcursor);
  PROCEDURE SP_NUEVO_PERFIL_SISTEMA(P_NCODPERFIL   S_GUIA.PERFIL_SISTEMA.NCODPERFIL%TYPE,
                                    P_NCODSISTEMA  S_GUIA.PERFIL_SISTEMA.NCODSISTEMA%TYPE,
                                    P_VDESCRIPCION S_GUIA.PERFIL_SISTEMA.VDESCRIPCION%TYPE,
                                    P_VESTADO      S_GUIA.PERFIL_SISTEMA.VESTADO%TYPE,
                                    P_VUSUARIO     S_GUIA.PERFIL_SISTEMA.VRESCRE%TYPE,
                                    P_IDENTITY     OUT NUMBER);

  PROCEDURE SP_UPDATE_PERFIL_SISTEMA(P_NCODPERFIL   S_GUIA.PERFIL_SISTEMA.NCODPERFIL%TYPE,
                                     P_NCODSISTEMA  S_GUIA.PERFIL_SISTEMA.NCODSISTEMA%TYPE,
                                     P_VDESCRIPCION S_GUIA.PERFIL_SISTEMA.VDESCRIPCION%TYPE,
                                     P_VESTADO      S_GUIA.PERFIL_SISTEMA.VESTADO%TYPE,
                                     P_VUSUARIO     S_GUIA.PERFIL_SISTEMA.VRESCRE%TYPE);
  PROCEDURE SP_UPDATE_PERFIL_SISTEMA_ESTAD(P_NCODPERFIL  S_GUIA.PERFIL_SISTEMA.NCODPERFIL%TYPE,
                                           P_NCODSISTEMA S_GUIA.PERFIL_SISTEMA.NCODSISTEMA%TYPE,
                                           P_VESTADO     S_GUIA.PERFIL_SISTEMA.VESTADO%TYPE,
                                           P_VUSUARIO    S_GUIA.PERFIL_SISTEMA.VRESCRE%TYPE);

  PROCEDURE SP_OBTENER_ACCION_FORM_PERFIL(P_NCODSISTEMA    S_GUIA.ACCION_FORMULARIO.NCODSISTEMA%TYPE,
                                          P_NCODMODULO     S_GUIA.ACCION_FORMULARIO.NCODMODULO%TYPE,
                                          P_NCODFORMULARIO S_GUIA.ACCION_FORMULARIO.NCODFORMULARIO%TYPE,
                                          P_NCODPERFIL     S_GUIA.PERFIL_ACCION.NCODPERFIL%TYPE,
                                          pCursor          OUT sys_refcursor);
  PROCEDURE SP_OBTENER_ACC_FORM_PER_USU(P_NCODSISTEMA    S_GUIA.ACCION_FORMULARIO.NCODSISTEMA%TYPE,
                                        P_NCODMODULO     S_GUIA.ACCION_FORMULARIO.NCODMODULO%TYPE,
                                        P_NCODFORMULARIO S_GUIA.ACCION_FORMULARIO.NCODFORMULARIO%TYPE,
                                        P_NCODPERFIL     S_GUIA.PERFIL_ACCION.NCODPERFIL%TYPE,
                                        pCursor          OUT sys_refcursor);
  -------- START / SISTEMA_MODULO_FORM ----------------------------------/
  PROCEDURE SP_OBTENER_ACCION_FORMULARIO(P_NCODSISTEMA    S_GUIA.ACCION_FORMULARIO.NCODSISTEMA%TYPE,
                                         P_NCODMODULO     S_GUIA.ACCION_FORMULARIO.NCODMODULO%TYPE,
                                         P_NCODFORMULARIO S_GUIA.ACCION_FORMULARIO.NCODFORMULARIO%TYPE,
                                         pCursor          OUT sys_refcursor);

  PROCEDURE SP_NUEVO_ACCION_FORMULARIO(P_NCODACCION_FORMULARIO S_GUIA.ACCION_FORMULARIO.NCODACCION_FORMULARIO%TYPE,
                                       P_NCODSISTEMA           S_GUIA.ACCION_FORMULARIO.NCODSISTEMA%TYPE,
                                       P_NCODMODULO            S_GUIA.ACCION_FORMULARIO.NCODMODULO%TYPE,
                                       P_NCODFORMULARIO        S_GUIA.ACCION_FORMULARIO.NCODFORMULARIO%TYPE,
                                       P_VCODPARAMETRO         S_GUIA.ACCION_FORMULARIO.VCODPARAMETRO%TYPE,
                                       P_NCODIGO               S_GUIA.ACCION_FORMULARIO.NCODIGO%TYPE,
                                       P_NESTADO               S_GUIA.ACCION_FORMULARIO.NESTADO%TYPE);

  PROCEDURE SP_UPDATE_ACCION_FORMULARIO(P_NCODACCION_FORMULARIO S_GUIA.ACCION_FORMULARIO.NCODACCION_FORMULARIO%TYPE,
                                        --P_NCODSISTEMA          S_GUIA.ACCION_FORMULARIO.NCODSISTEMA%TYPE,
                                        --P_NCODMODULO           S_GUIA.ACCION_FORMULARIO.NCODMODULO%TYPE,
                                        --P_NCODFORMULARIO       S_GUIA.ACCION_FORMULARIO.NCODFORMULARIO%TYPE,                                     
                                        --P_VCODPARAMETRO         S_GUIA.ACCION_FORMULARIO.VCODPARAMETRO%TYPE,
                                        --P_NCODIGO               S_GUIA.ACCION_FORMULARIO.NCODIGO%TYPE,
                                        P_NESTADO S_GUIA.ACCION_FORMULARIO.NESTADO%TYPE);

  PROCEDURE SP_OBTENER_SIS_MODULO_FORM_MEN(P_NCODSISTEMA S_GUIA.SISTEMA_MODULO_FORM.NCODSISTEMA%TYPE,
                                           P_NCODMODULO  S_GUIA.SISTEMA_MODULO_FORM.NCODMODULO%TYPE,
                                           pCursor       OUT sys_refcursor);
  PROCEDURE SP_OBTENER_SIS_MODULO_INICIO(P_VCODPARAMETRO S_GUIA.SISTEMA_PARAMETRO.VCODPARAMETRO%TYPE,
                                         P_VCODUSUARIO   S_GUIA.USUARIO_PERFIL_SISTEMA.VCODUSUARIO%TYPE,
                                         pCursor         OUT sys_refcursor);
  PROCEDURE SP_OBTENER_SIS_MODULO_FORM(P_NCODSISTEMA S_GUIA.SISTEMA_MODULO_FORM.NCODSISTEMA%TYPE,
                                       P_NCODMODULO  S_GUIA.SISTEMA_MODULO_FORM.NCODMODULO%TYPE,
                                       pCursor       OUT sys_refcursor);
  PROCEDURE SP_OBTENER_SIS_MODULO_FORM_TOT(P_ValueSearch IN VARCHAR,
                                           P_TotalReg    OUT NUMBER);

  PROCEDURE SP_OBTENER_SIS_MODULO_FORM_PAG(P_PageSize    IN INT,
                                           P_PageIndex   IN INT,
                                           P_ValueSearch IN VARCHAR,
                                           P_SortColumn  IN VARCHAR,
                                           pCursor       OUT sys_refcursor);

  PROCEDURE SP_OBTENER_SIS_MOD_INICIO_COD(P_VCODPARAMETRO S_GUIA.SISTEMA_PARAMETRO.VCODPARAMETRO%TYPE,
                                          P_VCODUSUARIO   S_GUIA.USUARIO_PERFIL_SISTEMA.VCODUSUARIO%TYPE,
                                          pCursor         OUT sys_refcursor);

  PROCEDURE SP_OBTENER_SIS_MODULO_FORM_COD(P_NCODSISTEMA    S_GUIA.SISTEMA_MODULO_FORM.NCODSISTEMA%TYPE,
                                           P_NCODMODULO     S_GUIA.SISTEMA_MODULO_FORM.NCODMODULO%TYPE,
                                           P_NCODFORMULARIO S_GUIA.SISTEMA_MODULO_FORM.NCODFORMULARIO%TYPE,
                                           pCursor          OUT sys_refcursor);

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
                                     P_IDENTITY             OUT NUMBER);

  PROCEDURE SP_UPDATE_SIS_MODULO_FORM(P_NCODSISTEMA          S_GUIA.SISTEMA_MODULO_FORM.NCODSISTEMA%TYPE,
                                      P_NCODMODULO           S_GUIA.SISTEMA_MODULO_FORM.NCODMODULO%TYPE,
                                      P_NCODFORMULARIO       S_GUIA.SISTEMA_MODULO_FORM.NCODFORMULARIO%TYPE,
                                      P_VDESCRIPCION         S_GUIA.SISTEMA_MODULO_FORM.VDESCRIPCION%TYPE,
                                      P_NESTADO              S_GUIA.SISTEMA_MODULO_FORM.NESTADO%TYPE,
                                      P_NCODFORMULARIO_PADRE S_GUIA.SISTEMA_MODULO_FORM.NCODFORMULARIO_PADRE%TYPE,
                                      P_NNIVEL_FORMULARIO    S_GUIA.SISTEMA_MODULO_FORM.NNIVEL_FORMULARIO%TYPE,
                                      P_NORDEN_FORMULARIO    S_GUIA.SISTEMA_MODULO_FORM.NORDEN_FORMULARIO%TYPE,
                                      P_VURL_FORMULARIO      S_GUIA.SISTEMA_MODULO_FORM.VURL_FORMULARIO%TYPE,
                                      P_VUSUARIO             S_GUIA.SISTEMA_MODULO_FORM.VRESCRE%TYPE);

  PROCEDURE SP_UPDATE_SIS_MODULO_FORM_EST(P_NCODSISTEMA    S_GUIA.SISTEMA_MODULO_FORM.NCODSISTEMA%TYPE,
                                          P_NCODMODULO     S_GUIA.SISTEMA_MODULO_FORM.NCODMODULO%TYPE,
                                          P_NCODFORMULARIO S_GUIA.SISTEMA_MODULO_FORM.NCODFORMULARIO%TYPE,
                                          P_NESTADO        S_GUIA.SISTEMA_MODULO_FORM.NESTADO%TYPE,
                                          P_VUSUARIO       S_GUIA.SISTEMA_MODULO_FORM.VRESCRE%TYPE);
  --------- END / SISTEMA_MODULO_FORM ----------------------------------/

  PROCEDURE SP_OBTENER_SISTEMA_MODULO(P_NCODSISTEMA S_GUIA.SISTEMA_MODULO.NCODSISTEMA%TYPE,
                                      pCursor       OUT sys_refcursor);
  PROCEDURE SP_OBTENER_SISTEMA_MODULO_ACT(P_NCODSISTEMA S_GUIA.SISTEMA_MODULO.NCODSISTEMA%TYPE,
                                          pCursor       OUT sys_refcursor);
  PROCEDURE SP_OBTENER_SISTEMA_MODULO_COD(P_NCODSISTEMA S_GUIA.SISTEMA_MODULO.NCODSISTEMA%TYPE,
                                          P_NCODMODULO  S_GUIA.SISTEMA_MODULO.NCODMODULO%TYPE,
                                          pCursor       OUT sys_refcursor);
  PROCEDURE SP_NUEVO_SISTEMA_MODULO(P_NCODSISTEMA  S_GUIA.SISTEMA_MODULO.NCODSISTEMA%TYPE,
                                    P_NCODMODULO   S_GUIA.SISTEMA_MODULO.NCODMODULO%TYPE,
                                    P_VDESCRIPCION S_GUIA.SISTEMA_MODULO.VDESCRIPCION%TYPE,
                                    P_NESTADO      S_GUIA.SISTEMA_MODULO.NESTADO%TYPE,
                                    P_VUSUARIO     S_GUIA.SISTEMA_MODULO.VRESCRE%TYPE);
  PROCEDURE SP_UPDATE_SISTEMA_MODULO(P_NCODSISTEMA  S_GUIA.SISTEMA_MODULO.NCODSISTEMA%TYPE,
                                     P_NCODMODULO   S_GUIA.SISTEMA_MODULO.NCODMODULO%TYPE,
                                     P_VDESCRIPCION S_GUIA.SISTEMA_MODULO.VDESCRIPCION%TYPE,
                                     P_NESTADO      S_GUIA.SISTEMA_MODULO.NESTADO%TYPE,
                                     P_VUSUARIO     S_GUIA.SISTEMA_MODULO.VRESCRE%TYPE);
  PROCEDURE SP_UPDATE_SISTEMA_MODULO_ESTAD(P_NCODSISTEMA S_GUIA.SISTEMA_MODULO.NCODSISTEMA%TYPE,
                                           P_NCODMODULO  S_GUIA.SISTEMA_MODULO.NCODMODULO%TYPE,
                                           P_NESTADO     S_GUIA.SISTEMA_MODULO.NESTADO%TYPE,
                                           P_VUSUARIO    S_GUIA.SISTEMA_MODULO.VRESCRE%TYPE);

  PROCEDURE SP_OBTENER_ACCESO_SISTEMA(P_VCODUSUARIO S_GUIA.ACCESOS.VCODUSUARIO%TYPE,
                                      P_NCODIP      ACCESOS.NCODIP%TYPE,
                                      pCursor       OUT sys_refcursor);

  PROCEDURE SP_NUEVO_ACCESO(P_VCODUSUARIO ACCESOS.VCODUSUARIO%TYPE,
                            --P_DFECFECHA   ACCESOS.DFECFECHA%TYPE,
                            P_NCODIP       ACCESOS.NCODIP%TYPE,
                            P_NCODAREA     ACCESOS.NCODAREA%TYPE,
                            P_VCODACCESO   ACCESOS.VCODACCESO%TYPE,
                            P_VCODINGRESO  ACCESOS.VCODINGRESO%TYPE,
                            P_NCORRELATIVO ACCESOS.NCORRELATIVO%TYPE,
                            P_NESTADO      ACCESOS.NESTADO%TYPE,
                            P_VTOKEN       ACCESOS.VTOKEN%TYPE);

  PROCEDURE SP_INVALIDAR_ACCESO(P_VTOKEN S_GUIA.ACCESOS.VTOKEN%TYPE);
  PROCEDURE SP_UPDATE_USUARIO_INTENTOS(P_VCODUSUARIO     S_GUIA.USUARIO.VCODUSUARIO%TYPE,
                                       P_NUMERO_INTENTOS S_GUIA.USUARIO.NUMERO_INTENTOS%TYPE);
  PROCEDURE SP_OBTENER_CLAVES(P_VCODUSUARIO S_GUIA.USUARIO.VCODUSUARIO%TYPE,
                              pCursor       OUT sys_refcursor);

  PROCEDURE SP_MANTENIMIENTO_CLAVES(P_VCODUSUARIO S_GUIA.USUARIO.VCODUSUARIO%TYPE,
                                    P_VPASS       S_GUIA.CLAVE.VPASS%TYPE,
                                    P_VFLAG_TEMP  S_GUIA.CLAVE.VFLAG_TEMP%TYPE);

  PROCEDURE SP_CAMBIO_CLAVE_OBLIGATORIO(P_VCODUSUARIO S_GUIA.USUARIO.VCODUSUARIO%TYPE);

  PROCEDURE SP_CAMBIO_CLAVE_TMP(P_VCODUSUARIO S_GUIA.USUARIO.VCODUSUARIO%TYPE);

  PROCEDURE SP_OBTENER_ULTIMO_ACCESO(P_VCODUSUARIO S_GUIA.ACCESOS.VCODUSUARIO%TYPE,
                                     pCursor       OUT sys_refcursor);

  PROCEDURE SP_VERI_CLAVE_INVALIDA(P_VCODUSUARIO S_GUIA.USUARIO.VCODUSUARIO%TYPE,
                                   pCursor       OUT sys_refcursor);

  PROCEDURE SP_OBTENER_SISTEMAS_POR_ABRE(P_VABREVIATURA S_GUIA.SISTEMA.VABREVIATURA%TYPE,
                                         pCursor        OUT sys_refcursor);

  PROCEDURE SP_OBTENER_SIS_MOD_ACT_MACT(P_NCODSISTEMA S_GUIA.SISTEMA_MODULO.NCODSISTEMA%TYPE,
                                        pCursor       OUT sys_refcursor);

  PROCEDURE SP_OBTENER_USUARIO_SISTEMA_ID(P_VUSUARIO    S_GUIA.USUARIO.VCODUSUARIO%TYPE,
                                          P_NCODSISTEMA S_GUIA.USUARIO_PERFIL_SISTEMA.NCODSISTEMA%TYPE,
                                          pCursor       OUT sys_refcursor);
  PROCEDURE SP_OBTENER_SISTEMA_MODULO_NOM(P_NCODSISTEMA  S_GUIA.SISTEMA_MODULO.NCODSISTEMA%TYPE,
                                          P_VDESCRIPCION S_GUIA.SISTEMA_MODULO.VDESCRIPCION%TYPE,
                                          pCursor        OUT sys_refcursor);

  PROCEDURE SP_OBTENER_MENU_PB(P_VCODUSUARIO S_GUIA.USUARIO.VCODUSUARIO%TYPE,
                               pCursor       OUT sys_refcursor);

  PROCEDURE SP_OBTENER_SIS_MODULO_HIJOS(P_NCODSISTEMA    S_GUIA.SISTEMA_MODULO_FORM.NCODSISTEMA%TYPE,
                                        P_NCODMODULO     S_GUIA.SISTEMA_MODULO_FORM.NCODMODULO%TYPE,
                                        P_NCODFORMULARIO VARCHAR,
                                        --                                           P_NCODFORMULARIO S_GUIA.SISTEMA_MODULO_FORM.NCODFORMULARIO%TYPE,
                                        
                                        pCursor OUT sys_refcursor);

END PKG_SEGURIDAD;
