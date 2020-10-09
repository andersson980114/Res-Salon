/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;
import Modelo.Sede;
import Persistencia.SedeJpaController;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import javax.swing.JComboBox;
/**
 *
 * @author ander
 */
public class sedeLogica {
    private SedeJpaController sedeDAO = new SedeJpaController();
    
    public void registrarSede(Sede sede) throws Exception{
        if(sede == null){
            throw new Exception("La sede no trae información");
        }
        Sede objUsuario = sedeDAO.findSede(sede.getNumsede());
        if(objUsuario != null){
            throw new Exception("La sede "+sede.getNumsede()+" "+sede.getNombresede()+ " ya esta registrada");
        }
        sedeDAO.create(sede);//insert into sede values....        
    }
    
    
     public void modificarSede(Sede sede) throws Exception{
        if(sede == null){
            throw new Exception("El usuario no trae información");
        }
        Sede objUsuario = sedeDAO.findSede(sede.getNumsede());
        if(objUsuario != null){           
            sedeDAO.edit(sede);//update sede set...
        }else{
            throw new Exception("La sede "+sede.getNumsede()+" no se encuentra registrado. No puede modificarlo.");
        }
                
    }

    public List<Sede> consultarSedes(){
        return sedeDAO.findSedeEntities(); //select * from sede;
    }
    
    public Sede consultarSede(Integer codigo){
        return sedeDAO.findSede(codigo);
    }
    
    public void eliminarSede(Integer id) throws Exception{
        try {
            sedeDAO.destroy(id);//delete from ...
        } catch (NonexistentEntityException ex) {
            throw new Exception("El usuario que intenta eliminar no existe");
        }
    }
    
    
}

