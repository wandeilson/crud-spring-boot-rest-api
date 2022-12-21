package br.com.springboot.curso_jdev_treinamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.springboot.curso_jdev_treinamento.model.Usuario;
//Interface que vai ser reponsavel por salvar objetos do tipo Usuario
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
