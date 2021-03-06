package pe.com.sedapal.seguridad.core.commons;

/*
 * 
 * #Proyecto: Nuevo Sistema de Seguridad de Sedapal.
 * #Fecha Creación: 27/12/2016.
 * #Autor: Luis Castro Valdez.
 * #Empresa: Tgestiona.
 */

/**
 * 
 * @author lcastro
 * @version 1.0
 *  
 */
public class Constants {
//	inicio adecuación seguridad2
	public static final Integer CODIGO_SISTEMA = 46;
//	fin adecuacion seguridad2
		
	// ----- REFERENCIAS 
	public static final String P_SEPARADOR = ".";
	public static final String P_CURSOR = "sys_refcursor";
	public static final String P_IDENTITY = "P_IDENTITY";
	public static final String PKG_SEGURIDAD = "PKG_SEGURIDAD";
	// ----- INICIO PARAMETROS Y VARIABLES
	// GENERALES
	public static final String P_NCODSISTEMA = "NCODSISTEMA";
	public static final String P_NCODSISTEMA_ = "P_NCODSISTEMA";
	public static final String P_VDESCRIPCION = "P_VDESCRIPCION";
	public static final String P_VABREVIATURA = "P_VABREVIATURA";
	public static final String P_NESTADO = "P_NESTADO";
	public static final String P_VPROGRAMA = "P_VPROGRAMA";
	public static final String P_VFLAG_NIVEL = "P_VFLAG_NIVEL";
	public static final String P_VUSUARIO = "P_VUSUARIO";
	public static final String P_NCONEXION = "P_NCONEXION";
	
	
	
	// CENTRO
	public static final String P_NCODCENTRO = "NCODCENTRO";
	// AREA
	public static final String P_NCODAREA = "NCODAREA";
	// USUARIO
	public static final String P_VCODUSUARIO = "VCODUSUARIO";
	public static final String P_VRESPONSABLE = "VRESPONSABLE";
	public static final String P_NUMERO_INTENTOS = "P_NUMERO_INTENTOS";
	public static final String P_VZONA = "P_VZONA";
	public static final String P_NCODFICHA = "P_NCODFICHA";
	public static final String P_NINDICADOR = "P_NINDICADOR";
	public static final String P_VSUSTENTACION = "P_VSUSTENTACION";
	public static final String P_VDOC = "P_VDOC";
	public static final String P_VFLAG_CAMBIAR_CLAVE = "P_VFLAG_CAMBIAR_CLAVE";
	
	
	// TRABAJADOR
	public static final String P_NCODTRABAJADOR = "NCODTRABAJADOR";
	public static final String P_NFICHA = "NFICHA";
	// PARAMETROS
	public static final String P_VCODPARAMETRO = "VCODPARAMETRO";
	public static final String P_NCODIGO = "NCODIGO";
	public static final String P_VVALOR = "VVALOR";
	//ACCESOS

	public static final String P_DFECFECHA = "P_DFECFECHA";
	public static final String P_NCODIP = "P_NCODIP";                       
	public static final String P_VCODACCESO = "P_VCODACCESO";
	public static final String P_VCODINGRESO = "P_VCODINGRESO";
	public static final String P_NCORRELATIVO = "P_NCORRELATIVO";
	public static final String P_VTOKEN = "P_VTOKEN";
	
	// PERFILSISTEMAS
	public static final String P_NCODPERFIL = "NCODPERFIL";
	public static final String P_VESTADO = "VESTADO";
	
	// SISTEMA_MODULO
	public static final String P_NCODMODULO = "NCODMODULO";
	
	// SISTEMA_MODULO_FORM
	public static final String P_NCODFORMULARIO = "NCODFORMULARIO";
	public static final String P_NCODFORMULARIO_PADRE = "NCODFORMULARIO_PADRE";
	public static final String P_NNIVEL_FORMULARIO = "NNIVEL_FORMULARIO";
	public static final String P_NORDEN_FORMULARIO = "NORDEN_FORMULARIO";
	public static final String P_VURL_FORMULARIO = "VURL_FORMULARIO";
	
	// ACCION_FORMULARIO
	public static final String P_NCODACCION_FORMULARIO = "NCODACCION_FORMULARIO";
	
	// USUARIO PERFIL SISTEMA
	
	
	// PERFIL ACCION
	public static final String P_NCODPERFIL_ACCION = "P_NCODPERFIL_ACCION";
	
	
	// ----- FIN PARAMETROS Y VARIABLES	
	
	// ----- INICIO PROCEDIMIENTOS ALMACENADOS
	public static final String SP_NUEVO_SISTEMA = PKG_SEGURIDAD + P_SEPARADOR + "SP_NUEVO_SISTEMA";
	public static final String SP_OBTENER_SISTEMAS = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_SISTEMAS";
	public static final String SP_OBTENER_SISTEMAS_ACTIVOS = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_SISTEMAS_ACTIVOS";
	
	public static final String SP_OBTENER_SISTEMAS_POR_ABRE = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_SISTEMAS_POR_ABRE";
	public static final String SP_OBTENER_SISTEMA_ID = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_SISTEMA_ID";
	public static final String SP_UPDATE_SISTEMA = PKG_SEGURIDAD + P_SEPARADOR + "SP_UPDATE_SISTEMA";
	public static final String SP_UPDATE_SISTEMA_ESTADO = PKG_SEGURIDAD + P_SEPARADOR + "SP_UPDATE_SISTEMA_ESTADO";
	// USUARIO
	public static final String SP_OBTENER_USUARIO = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_USUARIO";
	public static final String SP_OBTENER_USUARIO_POR_USUARIO = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_USUARIO_POR_USUARIO";
	public static final String SP_OBTENER_USUARIO_SISTEMA_ID = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_USUARIO_SISTEMA_ID";
	
	public static final String SP_UPDATE_USUARIO_ESTADO = PKG_SEGURIDAD + P_SEPARADOR + "SP_UPDATE_USUARIO_ESTADO";
	public static final String SP_UPDATE_USUARIO_BLOQUEADO = PKG_SEGURIDAD + P_SEPARADOR + "SP_UPDATE_USUARIO_BLOQUEADO";
	public static final String SP_UPDATE_USUARIO_INTENTOS = PKG_SEGURIDAD + P_SEPARADOR + "SP_UPDATE_USUARIO_INTENTOS";
	// CENTRO
	public static final String SP_OBTENER_CENTRO = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_CENTRO";
	public static final String SP_OBTENER_CENTRO_COD = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_CENTRO_COD";
	// AREA
	public static final String SP_OBTENER_AREA = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_AREA";
	public static final String SP_OBTENER_AREA_COD = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_AREA_COD";
	// TRABAJADOR
	public static final String SP_OBTENER_TRABAJADOR = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_TRABAJADOR";
	public static final String SP_OBTENER_TRABAJADOR_COD = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_TRABAJADOR_COD";	
	public static final String SP_OBTENER_TRABAJADOR_FICHA = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_TRABAJADOR_FICHA";
	// PARAMETROS
	public static final String SP_OBTENER_PARAMETRO = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_PARAMETRO";
	public static final String SP_OBTENER_PARAMETRO_COD = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_PARAMETRO_COD";
	public static final String SP_OBTENER_PARAMETRO_GENERAL = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_PARAMETRO_GENERAL";
	public static final String SP_UPDATE_PARAMETRO_COD = PKG_SEGURIDAD + P_SEPARADOR + "SP_UPDATE_PARAMETRO_COD";
	// PERFILSISTEMAS
	public static final String SP_OBTENER_PERFILSISTEMA = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_PERFILSISTEMA";
	public static final String SP_OBTENER_PERFILSISTEMA_ACT = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_PERFILSISTEMA_ACT";
	public static final String SP_OBTENER_PERFIL_SISTEMA_TOT = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_PERFIL_SISTEMA_TOT";
	public static final String SP_OBTENER_PERFIL_SISTEMA_PAG = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_PERFIL_SISTEMA_PAG";
	public static final String SP_OBTENER_PERFIL_SISTEMA_COD = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_PERFIL_SISTEMA_COD";
	public static final String SP_NUEVO_PERFIL_SISTEMA = PKG_SEGURIDAD + P_SEPARADOR + "SP_NUEVO_PERFIL_SISTEMA";
	public static final String SP_UPDATE_PERFIL_SISTEMA = PKG_SEGURIDAD + P_SEPARADOR + "SP_UPDATE_PERFIL_SISTEMA";
	public static final String SP_UPDATE_PERFIL_SISTEMA_ESTAD = PKG_SEGURIDAD + P_SEPARADOR + "SP_UPDATE_PERFIL_SISTEMA_ESTAD";
	// SISTEMA_MODULO
	public static final String SP_OBTENER_SISTEMA_MODULO = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_SISTEMA_MODULO";
	public static final String SP_OBTENER_SISTEMA_MODULO_ACT = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_SISTEMA_MODULO_ACT";
	public static final String SP_OBTENER_SIS_MOD_ACT_MACT = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_SIS_MOD_ACT_MACT";
	
	
	public static final String SP_OBTENER_SISTEMA_MODULO_COD = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_SISTEMA_MODULO_COD";
	public static final String SP_OBTENER_SISTEMA_MODULO_NOM = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_SISTEMA_MODULO_NOM";
	
	public static final String SP_NUEVO_SISTEMA_MODULO = PKG_SEGURIDAD + P_SEPARADOR + "SP_NUEVO_SISTEMA_MODULO";
	public static final String SP_UPDATE_SISTEMA_MODULO = PKG_SEGURIDAD + P_SEPARADOR + "SP_UPDATE_SISTEMA_MODULO";
	public static final String SP_UPDATE_SISTEMA_MODULO_ESTAD = PKG_SEGURIDAD + P_SEPARADOR + "SP_UPDATE_SISTEMA_MODULO_ESTAD";
	//ACCESOS
	public static final String SP_NUEVO_ACCESO = PKG_SEGURIDAD + P_SEPARADOR + "SP_NUEVO_ACCESO";
	public static final String SP_OBTENER_ACCESO_SISTEMA = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_ACCESO_SISTEMA";
	public static final String SP_INVALIDAR_ACCESO = PKG_SEGURIDAD + P_SEPARADOR + "SP_INVALIDAR_ACCESO";
	public static final String SP_OBTENER_ULTIMO_ACCESO = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_ULTIMO_ACCESO";
	
	// SISTEMA_MODULO_FORM
	public static final String SP_OBTENER_SIS_MODULO_INICIO = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_SIS_MODULO_INICIO";
	public static final String SP_OBTENER_SIS_MOD_INICIO_COD = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_SIS_MOD_INICIO_COD";
	public static final String SP_OBTENER_SIS_MODULO_FORM_MEN = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_SIS_MODULO_FORM_MEN";
	public static final String SP_OBTENER_SIS_MODULO_FORM = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_SIS_MODULO_FORM";
	public static final String SP_OBTENER_SIS_MODULO_FORM_TOT = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_SIS_MODULO_FORM_TOT";
	public static final String SP_OBTENER_SIS_MODULO_FORM_PAG = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_SIS_MODULO_FORM_PAG";
	public static final String SP_OBTENER_SIS_MODULO_FORM_COD = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_SIS_MODULO_FORM_COD";
	public static final String SP_OBTENER_SIS_MODULO_HIJOS = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_SIS_MODULO_HIJOS";
	
	public static final String SP_NUEVO_SIS_MODULO_FORM = PKG_SEGURIDAD + P_SEPARADOR + "SP_NUEVO_SIS_MODULO_FORM";
	public static final String SP_UPDATE_SIS_MODULO_FORM = PKG_SEGURIDAD + P_SEPARADOR + "SP_UPDATE_SIS_MODULO_FORM";
	public static final String SP_UPDATE_SIS_MODULO_FORM_EST = PKG_SEGURIDAD + P_SEPARADOR + "SP_UPDATE_SIS_MODULO_FORM_EST";
	// ACCION_FORMULARIO
	public static final String SP_OBTENER_ACCION_FORMULARIO = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_ACCION_FORMULARIO";
	public static final String SP_OBTENER_ACCION_FORM_PERFIL = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_ACCION_FORM_PERFIL";
	public static final String SP_OBTENER_ACC_FORM_PER_USU = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_ACC_FORM_PER_USU";
	public static final String SP_NUEVO_ACCION_FORMULARIO = PKG_SEGURIDAD + P_SEPARADOR + "SP_NUEVO_ACCION_FORMULARIO";
	public static final String SP_UPDATE_ACCION_FORMULARIO = PKG_SEGURIDAD + P_SEPARADOR + "SP_UPDATE_ACCION_FORMULARIO";	
	// USUARIO
	public static final String SP_NUEVO_USUARIO = PKG_SEGURIDAD + P_SEPARADOR + "SP_NUEVO_USUARIO";
	public static final String SP_UPDATE_USUARIO = PKG_SEGURIDAD + P_SEPARADOR + "SP_UPDATE_USUARIO";
	// USUARIO PERFIL SISTEMA
	public static final String SP_OBTENER_USUARIO_PERFIL = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_USUARIO_PERFIL";
	public static final String SP_OBTENER_USUARIO_SISTEMA = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_USUARIO_SISTEMA";
	public static final String SP_OBTENER_USUA_PERF_SIST_USU = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_USUA_PERF_SIST_USU";
	public static final String SP_OBTENER_USUA_PERF_SIST     = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_USUA_PERF_SIST";
	public static final String SP_OBTENER_USUA_PERF_SIST_COD = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_USUA_PERF_SIST_COD";
	public static final String SP_NUEVO_USUA_PERF_SIST       = PKG_SEGURIDAD + P_SEPARADOR + "SP_NUEVO_USUA_PERF_SIST";
	public static final String SP_UPDATE_USUA_PERF_SIST      = PKG_SEGURIDAD + P_SEPARADOR + "SP_UPDATE_USUA_PERF_SIST";
	public static final String SP_UPDATE_USUA_PERF_SIST_EST = PKG_SEGURIDAD + P_SEPARADOR + "SP_UPDATE_USUA_PERF_SIST_EST";
	
	public static final String SP_OBTENER_USUARIO_PERFIL_TOT = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_USUARIO_PERFIL_TOT";
	public static final String SP_OBTENER_USUARIO_PERFIL_PAG = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_USUARIO_PERFIL_PAG";
	// PERFIL ACCION
	public static final String SP_OBTENER_PERFIL_ACCION = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_PERFIL_ACCION";
	public static final String SP_OBTENER_PERFIL_ACCION_COD = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_PERFIL_ACCION_COD";
	public static final String SP_NUEVO_PERFIL_ACCION = PKG_SEGURIDAD + P_SEPARADOR + "SP_NUEVO_PERFIL_ACCION";
	public static final String SP_UPDATE_PERFIL_ACCION = PKG_SEGURIDAD + P_SEPARADOR + "SP_UPDATE_PERFIL_ACCION";
	public static final String SP_DELETE_PERFIL_ACCION = PKG_SEGURIDAD + P_SEPARADOR + "SP_DELETE_PERFIL_ACCION";
	
	// OTROS
	public static final String SP_OBTENER_USUARIO_ACCION_COD = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_USUARIO_ACCION_COD";
	public static final String SP_OBTENER_MENU_PB = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_MENU_PB";
	
	
	
	public static final String SEND_MAIL = "S_GUIA.ENVIA_CORREO";
	
	
	// ----- FIN PROCEDIMIENTOS ALMACENADOS
	public static final int ESTADO_INDICADOR_D = 2;
	public static final int ESTADO_INDICADOR_U = 1;
	public static final int ESTADO_INDICADOR_C = 0;
	public static final int ESTADO_ACTIVO = 1; 
	public static final int ESTADO_INACTIVO = 0;
	public static final int ESTADO_BLOQUEADO = 2;
	
	
	public static final int ESTADO_ACTIVO_SISTEMAS = 0; 
	public static final int ESTADO_INACTIVO_SISTEMAS = 1;
	
	public static final String ESTADO_ACTIVO_STR = "1"; 
	public static final String ESTADO_INACTIVO_STR = "0";
	public static final String ESTADO_BLOQUEADO_STR = "2";
	
	public static final String MSG_ERROR_CREDENCIALES = "Bad credentials";
	
	
	public static final String SUCCESS = "1"; 
	public static final String FAILURE = "0";
	
	public static final String FAILURE_USUARIO_NO_EXISTE = "2";
	public static final String FAILURE_MAXIMO_SESSIONES = "3";
	public static final String FAILURE_ERROR_INGRESANDO_CLAVE = "4";
	public static final String FAILURE_ERROR_VALIDANDO_CLAVE = "5";
	public static final String FAILURE_ERROR_MAX_NRO_CLAVES = "6";
	public static final String FAILURE_USUARIO_BLOQUEADO = "7";
	public static final String FAILURE_USUARIO_NOACTIVO = "8";
	public static final String FAILURE_CLAVE_TEMPORAL_INVALIDA = "9";
	public static final String FAILURE_AREAS_DIFERENTES = "10";
	public static final String FAILURE_ERROR_CAMBIANDO_CLAVE = "11";
	public static final String FAILURE_ERROR_OLVIDO_CLAVE = "12";
	public static final String FAILURE_ERROR_SISTEMA_NO_ACTIVO = "13";
	public static final String FAILURE_PERFIL_NO_ACTIVO = "14";
	
//	inicio adecuacion seguridad2
	public static final String FAILURE_SIN_PERFILES = "15";
	
	public static final Integer UNIQUE_VALUE = 1;
	public static final Integer EMPTY_VALUE = 0;
	public static final String SINGLE = "UNICO";
	public static final String MULTIPLE = "MULTIPLE";
	
	public static final String EDITAR = "EDITAR";
	public static final String NUEVO = "NUEVO";
	
	public static final String EMPTY_VALUE_ARR_JSON = "[]";
	public static final String EMPTY_VALUE_STRING = "";
	
	public static final Integer AUTENTICACION_TABLAS = 0;
	public static final Integer AUTENTICACION_LDAP = 1;
	public static final String AUTH_TABLAS_STR = "Autenticacion por Base de Datos";
	public static final String AUTH_LDAP_STR = "Autenticacion LDAP";
	public static final String FAILURE_USUARIO_LDAP_NO_VALID = "16";
	public static final String FAILURE_USUARIO_LDAP_NO_ASOCIA = "17";
	
	public static final String MENSAJE_ERROR = "Ocurri&oacute; un error inesperado con ID {0}, informar al área correspondiente.";
	
	public static final String OK = "OK";
    public static final String ERROR = "ERROR";
    public static final String ESTADO_OPCION_EDITAR = "EDITAR";
    public static final String ESTADO_OPCION_NUEVO = "NUEVO";
    
    public static final String ARCHIVO_CONFIGURACION = "application.properties";
    
    public static final String REDIS_PREFIX_USERS = "TOKEN";
	public static final String REDIS_KEYS_SEPARATOR = ":";
	public static final String FAILURE_TOKEN_INVALID = "18";
	
	public static final String TRABAJADOR_ACTIVO = "EIRC01";
	public static final String TRABAJADOR_BAJA = "EIRC02";	
	public static final String FAILURE_TRABAJADOR_BAJA = "19";
//	fin adecuacion seguridad2

	public static final String SP_OBTENER_CLAVES = PKG_SEGURIDAD + P_SEPARADOR + "SP_OBTENER_CLAVES";
	public static final String SP_MANTENIMIENTO_CLAVES = PKG_SEGURIDAD + P_SEPARADOR + "SP_MANTENIMIENTO_CLAVES";
	
	public static final String P_VPASS = "P_VPASS";
	public static final String P_VFLAG_TEMP = "P_VFLAG_TEMP";
	
	public static final String SP_CAMBIO_CLAVE_OBLIGATORIO = PKG_SEGURIDAD + P_SEPARADOR + "SP_CAMBIO_CLAVE_OBLIGATORIO";
	public static final String SP_CAMBIO_CLAVE_TMP = PKG_SEGURIDAD + P_SEPARADOR + "SP_CAMBIO_CLAVE_TMP";
	
	
	public static final String P_VFROM = "vFROM";
	public static final String P_VTO = "vTO";
	public static final String P_VSUBJECT = "vSubject";
	public static final String P_VMESSAGE = "vMessage";
	
	public static final String P_VCCTO = "vCCTO";
	public static final String P_VBCCTO = "vBCCTO";
	
	public static final String SP_VERI_CLAVE_INVALIDA = PKG_SEGURIDAD + P_SEPARADOR + "SP_VERI_CLAVE_INVALIDA";
	
	public static final String SERVER_CORREO = "SERVER_CORREO";
	public static final String NUMERO_SESSIONES = "NUMERO_SESSIONES";
	public static final String ANTERIOR = "ANTERIOR";
	public static final String FROM = "FROM";
	public static final String CCTO = "CCTO";
	public static final String BCCTO = "BCCTO";
	public static final String ASUNTO_USUARIO = "ASUNTO_USUARIO";
	public static final String BLOQUEO = "BLOQUEO";
	public static final String PREFIJO = "PREFIJO";
	public static final String SESSION = "SESSION";
	public static final String RUTAFOTOS = "RUTAFOTOS";
	public static final String RUTADOC = "RUTADOC";
	public static final String ACCIONES = "ACCIONES";
	public static final String ESTADO = "ESTADO";
	public static final String ESTADO_SISTEMA = "ESTADO_SISTEMA";
	public static final String NIVEL = "NIVEL";
	public static final String ASUNTO_CLAVE = "ASUNTO_CLAVE";
	public static final String USUARIO_SERVIDOR = "USUARIO_SERVIDOR";
	public static final String CLAVE_SERVIDOR = "CLAVE_SERVIDOR";
	public static final String TIPO_USUARIO = "TIPO_USUARIO";
	public static final String FLAG_BLOQUEO = "FLAG_BLOQUEO";
	public static final String TIPO_DOC = "TIPO_DOC";
	
	public static final Integer FLAG_BLOQUEO_SI = 1;
	public static final Integer FLAG_BLOQUEO_NO = 0;
	
	public static final String P_PAGE_SIZE = "P_PageSize";
	public static final String P_PAGE_INDEX = "P_PageIndex";
	public static final String P_VALUE_SEARCH = "P_ValueSearch";
	public static final String P_SORT_COLUMN = "P_SortColumn";
	public static final String P_TOTAL_REG = "P_TotalReg";
	
	public static final String FLAG_CAMBIAR_CLAVE = "1";
	
	public static final String P_PERFIL_ESTADO_ACTIVO = "1";
	public static final String P_PERFIL_ESTADO_INACTIVO = "0";
	
	public static final Integer TIPO_USUARIO_INTERNO = 1;
	public static final Integer TIPO_USUARIO_EXTERNO = 2;
	
}




