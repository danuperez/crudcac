
package com.mycompany.crudcac.model;

import com.mycompany.crudcac.Participante;
import java.util.List;

/**
 *
 * @author dani
 */
public interface Modelo {
    /**
     * @return 
     */
    public List<Participante> getParticipantes();
    
    
    public Participante getParticipante(int id);
    
    
    public int addParticipante(Participante participante);
    
    
    public int updateParticipante(Participante participante);
    
    
    public int removeParticipante(int id);
}
