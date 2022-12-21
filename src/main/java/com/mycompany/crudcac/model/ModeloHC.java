package com.mycompany.crudcac.model;

import com.mycompany.crudcac.Participante;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author dani
 */
// Modelo HC (Hard Codeado): Los datos se guardan en la RAM. Solo sirve para testear la app.
public class ModeloHC implements Modelo {

    private List<Participante> participantesGuardados;

    public ModeloHC() {
        this.participantesGuardados = new ArrayList<>();
        crearParticipantesFake();
    }

    @Override
    public List<Participante> getParticipantes() {
        return new ArrayList(this.participantesGuardados); // Copia de la lista original
    }

    @Override
    public Participante getParticipante(int id) {
        int i = 0;
        while (i < this.participantesGuardados.size() && this.participantesGuardados.get(i).getId() != id) {
            i++;
        }
        if (i >= this.participantesGuardados.size()) {
            throw new RuntimeException("No se encontró al participante " + id);
        }
        return this.participantesGuardados.get(i);
    }

    @Override
    public int addParticipante(Participante participante) {
        this.participantesGuardados.add(participante);
        return 0;
    }

    @Override
    public int updateParticipante(Participante a) {
        Participante target = getParticipante(a.getId());
        int idx = this.participantesGuardados.indexOf(target);
        this.participantesGuardados.set(idx, a);
        return 0;
    }

    @Override
    public int removeParticipante(int id) {
        Participante target = getParticipante(id);
        this.participantesGuardados.remove(target);
        return 0;
    }

    private void crearParticipantesFake() {
        try ( InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("carasFake.properties")) {
            Properties props = new Properties();
            props.load(is);
            this.participantesGuardados.add(new Participante(1, "Peter (HC)", "Parker", "parkerpet@mailsrv.fake", "1999-06-22", (String) props.get("HOMBRE_1")));
            this.participantesGuardados.add(new Participante(2, "Felina", "Kyle", "felikyle@mailsrv.fake", "1993-02-28"));
            this.participantesGuardados.add(new Participante(3, "María", "López", "maria_lopez@mailsrv.fake", "1984-03-24", (String) props.get("MUJER_1")));
            this.participantesGuardados.add(new Participante(4, "Luis", "García", "luis123@mailsrv.fake", "1998-07-04", (String) props.get("HOMBRE_3")));
            this.participantesGuardados.add(new Participante(5, "Sara", "Gómez", "saragomez@mailsrv.fake", "1991-02-28", (String) props.get("MUJER_3")));
            this.participantesGuardados.add(new Participante(6, "Pedro", "Ruiz", "ruiz.pedro@mailsrv.fake", "1986-11-13", (String) props.get("HOMBRE_2")));
            this.participantesGuardados.add(new Participante(7, "Lía", "Pérez", "lp12@mailsrv.fake", "1968-07-12", (String) props.get("MUJER_2")));
            this.participantesGuardados.add(new Participante(8, "Ana", "Suárez", "suanan@mailsrv.fake", "1992-05-16", (String) props.get("MUJER_4")));
            this.participantesGuardados.add(new Participante(9, "Samuel", "Mohamed", "samo@mailsrv.fake", "1990-05-14", (String) props.get("HOMBRE_4")));
        } catch (IOException ex) {
            throw new RuntimeException("No se pudieron cargar las caras fake");
        }
    }
}
