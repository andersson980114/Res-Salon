/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Modelo.Asignatura;
import Persistencia.AsignaturaJpaController;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author ander
 */
public class asignaturaLogica {
    private AsignaturaJpaController asigDAO = new AsignaturaJpaController();
    
    public void registrarAsignatura(Asignatura asig) throws Exception{
        if(asig == null){
            throw new Exception("El asignatura no trae información");
        }
        Asignatura objAsig = asigDAO.findAsignatura(asig.getCodasignatura());
        if(objAsig != null){
            throw new Exception("La asignatura "+asig.getCodasignatura()+ " ya esta registrado");
        }
        asigDAO.create(asig);//insert into asignatura values....        
    }
    
    
     public void modificarAsignatura(Asignatura asig) throws Exception{
        if(asig == null){
            throw new Exception("La asignatura no trae información");
        }
        Asignatura objUsuario = asigDAO.findAsignatura(asig.getCodasignatura());
        if(objUsuario != null){           
            asigDAO.edit(asig);//update asignatura set...
        }else{
            throw new Exception("La asignatura "+asig.getCodasignatura()+" no se encuentra registrada. No puede modificarla.");
        }        
    }

    public List<Asignatura> consultarAsignaturas(){
        return asigDAO.findAsignaturaEntities(); //select * from asignatura;
    }
    
    public Asignatura consultarsignatura(Integer codigo){
        return asigDAO.findAsignatura(codigo);
    }
    
    public void eliminarAsignatura(Integer id) throws Exception{
        try {
            asigDAO.destroy(id);//delete from ...
        } catch (NonexistentEntityException ex) {
            throw new Exception("La asignatura que intenta eliminar no existe");
        }
    }

}
