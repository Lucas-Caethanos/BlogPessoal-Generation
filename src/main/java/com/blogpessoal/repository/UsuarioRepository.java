package com.blogpessoal.repository;

import com.blogpessoal.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long>{
	public List<Usuario> findAllByUsuarioContainingIgnoreCase(@Param("usuario") String usuario);
	public Optional<Usuario> findByUsuario(String usuario);
	 
	public List<Usuario> findAllByNomeContainingIgnoreCase(String nome);
	
	}

