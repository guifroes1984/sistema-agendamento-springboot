package br.com.guifroes.agendamento.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.guifroes.agendamento.domain.usuario.Usuario;

@Service
public class TokenService {
	
	@Value("${api.security.token.segredo}")
	private String segredo;
	
	public String gerarToken(Usuario usuario) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(segredo);
			
			String token = JWT.create()
					.withIssuer("agendamento")
					.withSubject(usuario.getEmail())
					.withExpiresAt(this.gerarExpiracaoData())
					.sign(algorithm);
			return token;
		} catch (JWTCreationException exception) {
			throw new RuntimeErrorException(null, "Erro ao autenticar");
		}
	}
	
	public String validarToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(segredo);
			return JWT.require(algorithm)
					.withIssuer("agendamento")
					.build()
					.verify(token)
					.getSubject();
		} catch (JWTVerificationException exception) {
			return null;
		}
	}
	
	private Instant gerarExpiracaoData() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

}
