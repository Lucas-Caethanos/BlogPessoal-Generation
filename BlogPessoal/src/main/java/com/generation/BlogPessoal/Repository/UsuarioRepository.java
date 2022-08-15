package com.generation.BlogPessoal.Repository;

import com.generation.BlogPessoal.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findByUsuario(String usuario);

    public List<Usuario> findAllByNomeContainingIgnoreCase(String nome);
}
