/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;
import Modelo.Ambiente;
import Persistencia.AmbienteJpaController;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import javax.swing.JComboBox;
/**
 *
 * @author ander
 */
public class ambienteLogica {
    private AmbienteJpaController ambiDAO = new AmbienteJpaController();
    
    public void registrarAmbiente(Ambiente ambi) throws Exception{
        if(ambi == null){
            throw new Exception("El ambiente no trae información");
        }
        Ambiente objAmbi = ambiDAO.findAmbiente(ambi.getCodambiente());
        if(objAmbi != null){
            throw new Exception("El ambiente "+ambi.getCodambiente()+" "+ambi.getNombreambiente()+ " ya esta registrado");
        }
        ambiDAO.create(ambi);//insert into Ambiente values....        
    }
    
    
     public void modificarAmbiente(Ambiente ambi) throws Exception{
        if(ambi == null){
            throw new Exception("El ambiente no trae información");
        }
        Ambiente objUsuario = ambiDAO.findAmbiente(ambi.getCodambiente());
        if(objUsuario != null){           
            ambiDAO.edit(ambi);//update Ambiente set...
        }else{
            throw new Exception("El ambiente "+ambi.getCodambiente()+" no se encuentra registrado. No puede modificarlo.");
        }        
    }

    public List<Ambiente> consultarAmbientes(){
        return ambiDAO.findAmbienteEntities(); //select * from Ambiente;
    }
    
    public Ambiente consultarAmbiente(Integer codigo){
        return ambiDAO.findAmbiente(codigo);
    }
    
    public void eliminarAmbiente(Integer id) throws Exception{
        try {
            ambiDAO.destroy(id);//delete from ...
        } catch (NonexistentEntityException ex) {
            throw new Exception("El Ambiente que intenta eliminar no existe");
        }
    }
}
