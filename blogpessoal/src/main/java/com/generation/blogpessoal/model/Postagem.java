package com.generation.blogpessoal.model;
/*
Camada responsável pela abstração dos nossos Objetos em registros das nossas tabelas,
que serão geradas no Banco de dados.
As Classes criadas nesta camada representam os objetos
que serão persistidos no Banco de
dados
 */

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

/*
A Anotação (Annotation) @Entity indica que esta classe define uma entidade, ou seja, ela será utilizada para gerar uma tabela no Banco de dados da aplicação.
A Anotação @Table indica o nome da Tabela no Banco de dados. Caso esta anotação não seja declarada, o Banco de dados criará a tabela com o mesmo nome da Classe Model
(Postagem).
 */
@Entity
@Table(name = "tb_postagens")
public class Postagem {

    /*
    A Anotação @Id inidica que o atributo anotado será a Chave Primária (Primary Key PK) da Tabela tb_postagens.
    A Anotação @GeneratedValue indica que a Chave Primária será gerada pelo Spring Data JPA. O parâmetro strategy indica de que forma esta Chave Primária será gerada.
    A Estratégia GenerationType.IDENTITY indica que a Chave Primária será gerada pelo Banco de dados através da opção auto-incremento (auto-increment)
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
    A anotação @NotBlank não permite que o atributo seja Nulo ou contenha apenas Espaços em branco. Você pode configurar uma mensagem para o usuário através do atributo message.
    A anotação @Size define o valor Mínimo (min) e Máximo (max) de Caracteres do atributo.
     */
    @NotBlank(message = "O título é obrigatório")
    @Size(min = 3, max = 255)
    private String titulo;

    @NotBlank(message = "O texto é obrigatório")
    @Size(min = 5, max = 1000)
    private String texto;

    /*
    A anotação @UpdateTimestamp configura o atributo data como Timestamp, ou seja, o Spring se encarregará de obter a data do Sistema Operacional e inserir no atributo data
    toda vez que um Objeto da Classe Postagem for criado ou atualizado.
     */
    @UpdateTimestamp //
    private LocalDateTime data;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }


}
