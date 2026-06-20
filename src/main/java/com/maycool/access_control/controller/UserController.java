package com.maycool.access_control.controller;

import com.maycool.access_control.entity.User;
import com.maycool.access_control.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {


    private final UserService userService;

//    public ResponseEntity<List<User>> allList() — o método retorna uma resposta HTTP que contém uma lista de User dentro.
//            List<User> allList = userService.allList() — chama o Service que vai no banco, busca todos os usuários e armazena na variável allList.
//return ResponseEntity.ok(allList) — monta a resposta com status 200 OK e coloca a lista no corpo. O Spring converte a lista automaticamente pra JSON.
    @GetMapping
    public ResponseEntity<List<User>> allList() {
        List<User> allList = userService.allList();
        return ResponseEntity.ok(allList);
    }

    // GET /usuarios/{id} mapeia as requisições GET para rota /user/{id}, @PathVariable Long id — captura o valor do {id} da URL e injeta como parâmetro do método. Se a URL for /user/5, o id vai ser 5.
    // userService.srcById(id) — chama o Service que retorna um Optional<User>.
    //.map(ResponseEntity::ok) — se o Optional tiver um usuário dentro, transforma ele num ResponseEntity com status 200.
    //.orElse(ResponseEntity.notFound().build()) — se o Optional estiver vazio (usuário não existe), retorna status 404.
    @GetMapping("/{id}")
    public ResponseEntity<User> srcById(@PathVariable Long id) {
        return userService.srcById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

//    @PostMapping — esse método responde a requisições HTTP do tipo POST na rota /user.
//    public ResponseEntity<User> create — o método se chama create e vai retornar uma resposta HTTP com um User dentro.
//    @RequestBody User newUser — o corpo da requisição HTTP vem em JSON, e o Spring converte automaticamente esse JSON pra um objeto User. É o usuário que você mandou pelo Insomnia por exemplo.
//    User userSave = userService.save(newUser) — passa o usuário recebido pro Service, que salva no banco e retorna o usuário salvo já com o id gerado pelo banco.
//            ResponseEntity.status(201).body(userSave) — monta a resposta HTTP com status 201 Created (padrão quando algo é criado) e coloca o usuário salvo no corpo da resposta.
    @PostMapping
    public ResponseEntity<User> create(@RequestBody User newUser) {
        User userSave = userService.save(newUser);
        return ResponseEntity.status(201).body(userSave); // 201 Created
    }

//    @PutMapping("/{id}") — responde a requisições PUT na rota /user/{id}. PUT é usado quando você quer atualizar um recurso existente.
//    @PathVariable Long id — captura o {id} da URL. É o id do usuário que você quer atualizar.
//    @RequestBody User updatedData — recebe o JSON com os dados novos do usuário e converte pra objeto Java. São as informações que você mandou pelo Insomnia.
//    User userUpdated = userService.update(id, updatedData) — manda pro Service o id de quem atualizar e os dados novos. O Service busca no banco, atualiza e salva.
//            ResponseEntity.ok(userUpdated) — retorna o usuário já atualizado com status 200 OK.
   @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User updatedData) {
        User userUpdated = userService.update(id, updatedData);
        return ResponseEntity.ok(userUpdated);
    }
//    @PatchMapping("/{id}/status") — responde a requisições PATCH na rota /user/{id}/status. PATCH é usado quando você quer atualizar parcialmente um recurso — nesse caso só o status.
//            ResponseEntity<Void> — o Void significa que a resposta não tem corpo, só o status HTTP. Não tem nada pra retornar, só confirmar que funcionou.
//    @PathVariable Long id — captura o id do usuário que você quer ativar/desativar.
//            userService.replace(id) — chama o Service passando só o id. O Service busca o usuário, inverte o status e salva.
//            ResponseEntity.noContent().build() — retorna status 204 No Content. Significa "deu certo, mas não tenho nada pra te mostrar"
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> toggleStatus(@PathVariable Long id) {
        userService.toggleStatus(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

}
