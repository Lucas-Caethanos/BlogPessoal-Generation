package com.generation.blogpessoal.repository;

/*
Repository/DAO -Interface responsável pena comunicação direta com o banco de dados.
 */

import com.generation.blogpessoal.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
A Anotação (Annotation) @Repository indica que a Interface é do tipo repositório, ou seja,
ela é responsável pela interação com o Banco de dados através dos métodos padrão (Herdados da Interface JPA Repository) e das Query Methods,
que são métodos personalizados que geram consultas (Instruções SQL do tipo Select),
através da combinação de palavras chave, que representam os comandos da linguagem SQL.
 */
@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {
    public List<Postagem> findAllByTituloContainingIgnoreCase (@Param("titulo") String titulo);

}
