package br.com.springboot.curso_jdev_treinamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springboot.curso_jdev_treinamento.model.Usuario;
//Interface que vai ser reponsavel por salvar objetos do tipo Usuario
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	@Query (value = "select u from Usuario u where  upper(trim(u.nome)) like %?1%")
	public List<Usuario> findByName(String name);

}
