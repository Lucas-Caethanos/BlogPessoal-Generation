package com.generation.blogpessoal.controller;

/*
Controller - “End point”, é a camada responsável por manipular todas as requisições feitas do lado de fora da nossa API,
essas requisições são feitas através de URL’s seguindo o protocolo HTTP.
 */

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/*
A anotação @RestController define que a classe é do tipo RestController, que receberá requisições que serão compostas por:
    URL: Endereço da requisição (endpoint)
    Verbo: Define qual método HTTP será acionado na Classe controladora.
    Corpo da requisição (Request Body): Objeto que contém os dados que serão persistidos no Banco de dadas.
    Nem toda a requisição enviará dados no Corpo da Requisição.

A anotação @RequestMapping é usada para mapear as solicitações para os métodos da classe controladora PostagemController,
ou seja, definir a URL (endereço) padrão do Recurso (/postagens).
Ao digitar a url do servidor seguida da url do Recurso (http://localhost:8080/posta gens),
o Spring envia a requisição para a Classe responsável pelo Recurso associado à este endereço.

A anotação @CrossOrigin indica que a classe controladora permitirá o recebimento de requisições realizadas de fora do domínio
(localhost e futuramente do heroku quando o deploy for realizado) ao qual ela pertence.
 */
@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {

    /*
    A anotação @Autowired (Injeção de Dependência), é a implementação utilizada pelo Spring Framework
    para aplicar a Inversão de Controle (IoC) quando for necessário.
    A Injeção de Dependência define quais Classes serão instanciadas e em quais lugares serão injetadas quando houver necessidade.
     */
    @Autowired
    private PostagemRepository postagemRepository;

    /*
    A anotação @GetMapping mapeia todas as Requisições HTTP GET, enviadas para um endereço específico, chamado endpoint,
    dentro do Recurso Postagem, para um Método específico que responderá a requisição,
    ou seja, ele indica que o Método getAll(), responderá a todas as requisições do tipo HTTP GET,
    enviadas no endereço http://localhost:8080/postagens/.
     */
    @GetMapping
    public ResponseEntity<List<Postagem>> getAll() {

        return ResponseEntity.ok(postagemRepository.findAll());
    }

    /*
    A anotação @GetMapping("/{id}") mapeia todas as Requisições HTTP GET, enviadas para um endereço específico (Endpoint),
    dentro do Recurso Postagem, para um Método específico que responderá as requisições, ou seja, ele indica que o Método getById( Long id ),
    responderá a todas as requisições do tipo HTTP GET, enviadas no endereço http://localhost:808 0/postagens/id,
    onde id é uma Variável de Caminho (Path Variable), que receberá o id da Postagem que será Consultada.

    O Método getById(@PathVariable Long id) será do tipo ResponseEntity porque ele responderá Requisições HTTP (HTTP Request),
    com uma Resposta HTTP (HTTP Response). Observe que o Método possui um parâmetro do tipo Long, chamado id.

    @PathVariable Long id: Esta anotação insere o valor enviado no endereço do endpoint, na Variável de Caminho {id},
    no parâmetro do Método getById( Long id );
     */
    @GetMapping("/{id}")
    public ResponseEntity<Postagem> getById(@PathVariable Long id) {
        return postagemRepository.findById(id)
                .map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo) {
        return ResponseEntity.ok(postagemRepository
                .findAllByTituloContainingIgnoreCase(titulo));
    }

    /*
    A anotação @PostMapping indica que o Método post(Postagem postagem), responderá a todas as requisições do tipo HTTP POST,
    enviadas no endereço http://localhost:8080/postagens.
    @Valid: Esta anotação valida o Objeto Postagem enviado no Corpo da Requisição (Request Body),
    conforme as regras definidas na Model Postagem (@NotNull, @NotBlank, @Size e etc).
    @RequestBody Postagem postagem: Esta anotação recebe o Objeto do tipo Postagem enviado no Corpo da Requisição (Request Body)
    e insere no parâmetro Postagem do método post.

    ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));:
    Executa o Método padrão da Interface JpaRepository (save(postagem)) e retorna o HTTP Status CREATED🡪201 se o Objeto foi persistido no Banco de dados.
     */
    @PostMapping
    public ResponseEntity<Postagem> post(@Valid @RequestBody Postagem postagem) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postagemRepository.save(postagem));
    }

    /*
    A anotação @PutMapping indica que o Método put(Postagem postagem), responderá a todas as requisições do tipo HTTP PUT,
    enviadas no endereço http://localhost:8080/postagens.
    @Valid: Esta anotação valida o Objeto Postagem enviado no Corpo da Requisição (Request Body),
    conforme as regras definidas na Model Postagem (@NotNull, @NotBlank, @Size e etc).
    @RequestBody Postagem postagem: Esta anotação recebe o Objeto do tipo Postagem enviado no Corpo da Requisição (Request Body)
    e insere no parâmetro Postagem do método put.
     */
    @PutMapping
    public ResponseEntity<Postagem> put(@Valid @RequestBody Postagem postagem) {
        return postagemRepository.findById(postagem.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.OK)
                        .body(postagemRepository.save(postagem)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /*
    A anotação @ResponseStatus indica que o Método delete(Long id), terá uma Response Status específica, ou seja,
    quando a Resposta da Requisição for positiva, será retornado o HTTP Status NO_CONTENT 🡪 204, ao invés do HTTP Status OK 🡪 200 como resposta padrão.
    A anotação @DeleteMapping("/{id}") mapeia todas as Requisições HTTP DELETE, enviadas para um endereço específico (Endpoint),
    dentro do Recurso Postagem, para um Método específico que responderá as requisições, ou seja,
    ele indica que o Método delete( Long id ), responderá a todas as requisições do tipo HTTP DELETE,
    enviadas no endereço http://localhost: 8080/postagens/id, onde id é uma Variável de Caminho (Path Variable), que receberá o id da Postagem que será Deletada.
    O Método void delete(@PathVariable Long id) será do tipo void porque ele responda Requisições HTTP (HTTP Request),
    ao deletar uma Postagem ela deixa de existir, logo não tem nenhum tipo de retorno.
    Como configuramos a anotação @ResponseStatus, ele devolverá uma Resposta HTTP NO_CONTENT 🡪 204, indicando que o Objeto deletado não existe mais.
    Observe que o Método possui um parâmetro do tipo Long, chamado id.
    @PathVariable Long id: Esta anotação insere o valor enviado no endereço do endpoint,
    na Variável de Caminho {id}, no parâmetro do Método delete( Long id );
     */
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Optional<Postagem> postagem = postagemRepository.findById(id);

        if (postagem.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        postagemRepository.deleteById(id);
    }

}


