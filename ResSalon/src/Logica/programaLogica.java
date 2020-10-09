/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Modelo.Programa;
import Persistencia.ProgramaJpaController;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author ander
 */
public class programaLogica {
    
     private ProgramaJpaController progDAO = new ProgramaJpaController();
    
    public void registrarPrograma(Programa prog) throws Exception{
        if(prog == null){
            throw new Exception("El Programa no trae información");
        }
        Programa objProg = progDAO.findPrograma(prog.getCodprograma());
        if(objProg != null){
            throw new Exception("El Programa "+prog.getCodprograma()+" "+prog.getNombreprograma()+ " ya esta registrado");
        }
        progDAO.create(prog);//insert into Ambiente values....        
    }
    
    
     public void modificarPrograma(Programa prog) throws Exception{
        if(prog == null){
            throw new Exception("El Programa no trae información");
        }
        Programa objProg = progDAO.findPrograma(prog.getCodprograma());
        if(objProg != null){           
            progDAO.edit(prog);//update Programa set...
        }else{
            throw new Exception("El Programa "+prog.getCodprograma()+" no se encuentra registrado. No puede modificarlo.");
        }        
    }

    public List<Programa> consultarPrograma(){
        return progDAO.findProgramaEntities(); //select * from Programa;
    }
    
    public Programa consultarPrograma(Integer codigo){
        return progDAO.findPrograma(codigo);
    }
    
    public void eliminarPrograma(Integer id) throws Exception{
        try {
            progDAO.destroy(id);//delete from ...
        } catch (NonexistentEntityException ex) {
            throw new Exception("El Programa que intenta eliminar no existe");
        }
    }

}
