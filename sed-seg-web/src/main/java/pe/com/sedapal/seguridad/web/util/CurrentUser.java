package pe.com.sedapal.seguridad.web.util;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CurrentUser extends User{
	
	private String perfil;
    private String sistema;
	
	public CurrentUser(String username, String password, Collection<GrantedAuthority> authorities) {
		super(username, password, authorities);
		// TODO Auto-generated constructor stub
	}
		
	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}
	
	public void aditionPermission(User user, Collection<GrantedAuthority> authorities) {
		authorities = null; 
	}

}
