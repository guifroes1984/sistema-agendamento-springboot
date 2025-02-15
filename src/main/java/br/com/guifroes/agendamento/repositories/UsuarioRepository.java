package br.com.guifroes.agendamento.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.guifroes.agendamento.domain.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
	
	Optional<Usuario> findByEmail(String email);

}
