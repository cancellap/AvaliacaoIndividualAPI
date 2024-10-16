package com.serratec.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.serratec.domain.Livro;
import com.serratec.repository.LivroRepository;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/livros")
public class LivroController {

	@Autowired
	private LivroRepository lr;

	@GetMapping
	@Operation(summary = "Listar todos os livros", description = "Retorna uma lista de todos os livros cadastrados.")
	public ResponseEntity<List<Livro>> listar() {
		return ResponseEntity.ok(lr.findAll());
	}

	@GetMapping("/{id}")
	@Operation(summary = "Buscar livro por ID", description = "Retorna um livro específico pelo seu ID.")
	@ApiResponse(responseCode = "200", description = "Livro encontrado")
	@ApiResponse(responseCode = "404", description = "Livro não encontrado")
	public ResponseEntity<Livro> buscar(@Parameter(description = "ID do livro a ser buscado") @PathVariable Long id) {
		Optional<Livro> livroOpt = lr.findById(id);
		return livroOpt.map(livro -> ResponseEntity.ok(livro)).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/titulo/{titulo}")
	@Operation(summary = "Buscar livro por titulo", description = "Retorna um livro específico pelo seu titulo.")
	@ApiResponse(responseCode = "200", description = "Livro encontrado")
	@ApiResponse(responseCode = "404", description = "Livro não encontrado")
	public ResponseEntity<Livro> buscarPTiulo(@PathVariable String titulo) {
		Optional<Livro> byTitulo = lr.findByTitulo(titulo);
		return byTitulo.map(livro -> ResponseEntity.ok(livro)).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Inserir novo livro", description = "Adiciona um novo livro à lista.")
	@ApiResponse(responseCode = "201", description = "Livro criado")
	public Livro inserir(@Valid @RequestBody Livro livro) {
		livro = lr.save(livro);
		return livro;
	}

	@PutMapping("/{id}")
	@Operation(summary = "Alterar livro existente", description = "Atualiza os dados de um livro existente pelo seu ID.")
	@ApiResponse(responseCode = "200", description = "Livro atualizado")
	@ApiResponse(responseCode = "404", description = "Livro não encontrado")
	public ResponseEntity<Livro> alterar(@Parameter(description = "ID do livro a ser alterado") @PathVariable Long id,
			@Valid @RequestBody Livro livro) {
		if (!lr.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		livro.setId(id);
		livro = lr.save(livro);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar livro", description = "Remove um livro da lista pelo seu ID.")
	@ApiResponse(responseCode = "204", description = "Livro removido")
	@ApiResponse(responseCode = "404", description = "Livro não encontrado")
	public ResponseEntity<Void> delete(@Parameter(description = "ID do livro a ser deletado") @PathVariable Long id) {
		if (!lr.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		lr.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}/{atributo}")
	@Operation(summary = "Atualizar atributo de um livro", description = "Atualiza um atributo específico de um livro identificado pelo seu ID.")
	@ApiResponse(responseCode = "200", description = "Livro atualizado com sucesso")
	@ApiResponse(responseCode = "400", description = "Atributo ou valor inválido")
	@ApiResponse(responseCode = "404", description = "Livro não encontrado")
	public ResponseEntity<Livro> atualizarAtributo(
			@Parameter(description = "ID do livro a ser editado") @PathVariable Long id, @PathVariable String atributo,
			@RequestBody String novoValor) {
		Optional<Livro> livroOpt = lr.findById(id);

		if (!livroOpt.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Livro livro = livroOpt.get();
		switch (atributo.toLowerCase()) {
		case "titulo":
			livro.setTitulo(novoValor);
			break;
		case "qtdPagina":
			try {
				livro.setQtdPagina(Integer.parseInt(novoValor));
			} catch (NumberFormatException e) {
				return ResponseEntity.badRequest().build();
			}
			break;
		default:
			return ResponseEntity.badRequest().build();
		}

		lr.save(livro);
		return ResponseEntity.ok(livro);
	}

}
