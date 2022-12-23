package br.com.springboot.curso_jdev_treinamento.controllers;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.curso_jdev_treinamento.model.Usuario;
import br.com.springboot.curso_jdev_treinamento.repository.UsuarioRepository;

@RestController
public class GreetingsController {
	
	@Autowired /*Injeção de depencencia*/
	private UsuarioRepository usuarioRepository;
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
    @RequestMapping(value = "/mostrarnome/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name) {
        return "Hello " + name + "!";
    }
    @RequestMapping(value = "/olamundo/{nome}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String retornaOlaMundo(@PathVariable String nome) {
    	
    	Usuario user = new Usuario();
    	user.setNome(nome);
    	usuarioRepository.save(user);
    	return "Olá mundo "+nome;
    }
    
    
    @GetMapping(value = "listall")//Anotação que 'cria' o caminho do end-point. por ex: http://localhost:8000/listatodos
    @ResponseBody //Retorna os dados para o corpo da resposta
    public ResponseEntity<List<Usuario>> listAllUsers(){
    	List<Usuario> usuarios =  usuarioRepository.findAll();//Busca todos usuarios no banco
    	
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);//Retorna um JSON
    }
    @PostMapping(value = "save")//mapeia a url
    @ResponseBody//Descrição da resposta
    public ResponseEntity<Usuario> save( @RequestBody Usuario usuario ){
    	Usuario user = usuarioRepository.save(usuario);
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    }
    
    @PutMapping(value = "update")//mapeia a url
    @ResponseBody//Descrição da resposta
    public ResponseEntity<?> update( @RequestBody Usuario usuario ){
    	
    	if(usuario.getId() == null) {
    		return new ResponseEntity<String>("Id not informed", HttpStatus.NOT_FOUND);
    	}
    	Usuario user = usuarioRepository.saveAndFlush(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    }  
    
    @DeleteMapping(value = "delete")//mapeia a url
    @ResponseBody//Descrição da resposta
    public ResponseEntity<String> delete( @RequestParam Long idUser ){
    	usuarioRepository.deleteById(idUser);
    	
    	return new ResponseEntity<String>("User deleted successfully", HttpStatus.OK);
  
    }
    
    @GetMapping(value = "findbyid")//mapeia a url
    @ResponseBody//Descrição da resposta
    public ResponseEntity<Usuario> findById( @RequestParam (name = "idUser") Long idUser ){
    	Usuario usuario = usuarioRepository.findById(idUser).get();
    	
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }
    
    @GetMapping(value = "findbyname")
    @ResponseBody
    public ResponseEntity<List<Usuario>> findByName( @RequestParam (name = "name")String name){
    	
    	List<Usuario> usersfind = usuarioRepository.findByName(name.trim().toUpperCase());
    	
    	return new ResponseEntity<List<Usuario>>(usersfind, HttpStatus.OK);
    	
    }
    

    



}
    

   
