/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Modelo.Usuario;
import Persistencia.UsuarioJpaController;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author ander
 */
public class usuarioLogica {
    
   private  UsuarioJpaController usuarioDao = new UsuarioJpaController ();
           
    public void registrarUsuario (Usuario usuario) throws Exception{
        if(usuario == null){
            throw new Exception ("El usuario no trae información");
        }
        
        Usuario objUsuario = usuarioDao.findUsuario(usuario.getCodpersonal());
        if(objUsuario != null ){
            throw  new  Exception("El usuarioc con el codigo "+usuario.getCodpersonal()+" ya se escuentra registrado");
        }
        usuarioDao.create(usuario);
    }
    
     public void modificarUsuario(Usuario usuario) throws Exception{
        if(usuario == null){
            throw new Exception("El usuario no trae información");
        }
        Usuario objUsuario = usuarioDao.findUsuario(usuario.getCodpersonal());
        if(objUsuario != null){           
            usuarioDao.edit(usuario);//update usuario set...
        }else{
            throw new Exception("El usuario con documento "+usuario.getCodpersonal()+" no se encuentra registrado. No puede modificarlo.");
        }
                
    }
     
    public List<Usuario> consultarUsuarios(){
        return usuarioDao.findUsuarioEntities();
    } 
    
    public List<Usuario> consultarTipoUsuarios(String tipoUsuario){
        return usuarioDao.findxTipoUsuario(tipoUsuario);
    } 
    
    public Usuario consultarUsuario(Integer codigo){
        return usuarioDao.findUsuario(codigo);
    }
    
    public void eliminarUsuario(Integer id) throws Exception{
        try {
            usuarioDao.destroy(id);//delete from ...
        } catch (NonexistentEntityException ex) {
            throw new Exception("El usuario que intenta eliminar no existe");
        }
    }
}
