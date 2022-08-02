package com.generation.blogpessoal.model;

import javax.persistence.Entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "tb_temas")
public class Tema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O atributo descrição é obrigatório")
    @Size(min = 3, max = 255)
    private String descricao;

    /*
     A anotação @OneToMany indica que a Classe Tema será o lado 1:N e terá uma
    Collection List contendo Objetos da Classe Postagem. Como a Relação entre as Classes será
    Bidirecional, a Collection List trará a lista com todos os Objetos da Classe Postagem relacionados
    com cada Objeto da Classe Tema
     */
    @OneToMany(mappedBy = "tema", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("tema")
    private List<Postagem> postagem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Postagem> getPostagem() {
        return postagem;
    }

    public void setPostagem(List<Postagem> postagem) {
        this.postagem = postagem;
    }
}
