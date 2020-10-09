/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import Modelo.Reserva;
import Persistencia.ReservaJpaController;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author ander
 */
public class reservaLogica {
    private ReservaJpaController reserDAO = new ReservaJpaController();
    
    public void registrarReserva(Reserva reser) throws Exception{
        if(reser == null){
            throw new Exception("La reserva no trae información");
        }
        Reserva objReser = reserDAO.findReserva(reser.getCodreserva());
        if(objReser!= null){
            throw new Exception("La reserva "+reser.getCodreserva()+ " ya esta registrado");
        }
        reserDAO.create(reser);//insert into Reserva values....        
    }
    
     public void modificarAmbiente(Reserva reser) throws Exception{
        if(reser == null){
            throw new Exception("La reserva no trae información");
        }
        Reserva objUsuario = reserDAO.findReserva(reser.getCodreserva());
        if(objUsuario != null){           
            reserDAO.edit(reser);//update Reserva set...
        }else{
            throw new Exception("La reserva "+reser.getCodreserva()+" no se encuentra registrado. No puede modificarlo.");
        }        
    }

    public List<Reserva> consultarAmbientes(){
        return reserDAO.findReservaEntities(); //select * from reserva;
    }
    
    public Reserva consultarAmbiente(Integer codigo){
        return reserDAO.findReserva(codigo);
    }
    
    public void eliminarAmbiente(Integer id) throws Exception{
        try {
            reserDAO.destroy(id);//delete from ...
        } catch (NonexistentEntityException ex) {
            throw new Exception("La reserva que intenta eliminar no existe");
        }
    }
}
