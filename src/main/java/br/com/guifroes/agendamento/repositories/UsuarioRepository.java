package br.com.guifroes.agendamento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.guifroes.agendamento.domain.usuario.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {

}
