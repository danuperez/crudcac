
package com.mycompany.crudcac.model;

import com.mycompany.crudcac.Participante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dani
 */
public class ModeloMySQL implements Modelo {
    
    private static final String GET_ALL_QUERY = "SELECT * FROM participantes";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM participantes WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM participantes WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE participantes SET nombre=?, apellido=?, mail=?, fechaNac=?, fotobase64=? WHERE id=?";
    private static final String ADD_QUERY = "INSERT INTO participantes VALUES(null, ?, ?, ?, ?, ?)";
    
    @Override
    public List<Participante> getParticipantes() {
        List<Participante> lista = new ArrayList<>();
        try (Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(GET_ALL_QUERY);
            ResultSet rs = ps.executeQuery();) {            
            while(rs.next()) {
                lista.add( rsToParticipante(rs) );
            }         
        } catch(SQLException e) {
            throw new RuntimeException("Error de SQL", e);
        } catch (Exception ex) {
            throw new RuntimeException("Error al obtener lista de participantes", ex);
        }       
        return lista;
    }

    @Override
    public Participante getParticipante(int id) {
        Participante participante = null;
        try (Connection con = Conexion.getConnection();
            PreparedStatement ps = con.prepareStatement(GET_BY_ID_QUERY);
            ) {            
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery();) {
                rs.next();
                participante = rsToParticipante(rs);
            }    
        } catch(SQLException e) {
            throw new RuntimeException("Error de SQL", e);
        } catch (Exception ex) {
            throw new RuntimeException("Error al obtener participante", ex);
        }
        
        return participante;
    }

    @Override
    public int addParticipante(Participante participante) {
        int regsAgregados = 0;
        try ( Connection con = Conexion.getConnection();
              PreparedStatement ps = con.prepareStatement(ADD_QUERY);
                ) {
            fillPreparedStatement(ps, participante);
            regsAgregados = ps.executeUpdate();            
        } catch(SQLException e) {
            throw new RuntimeException("Error de SQL", e);
        } catch (Exception ex) {
            throw new RuntimeException("Error al borrar participante", ex);
        }
        return regsAgregados;
    }

    @Override
    public int updateParticipante(Participante participante) {
        int regsActualizados = 0;
        try ( Connection con = Conexion.getConnection();
              PreparedStatement ps = con.prepareStatement(UPDATE_QUERY);
                ) {
            fillPreparedStatement(ps, participante);
            ps.setInt(6, participante.getId());
            regsActualizados = ps.executeUpdate();            
        } catch(SQLException e) {
            throw new RuntimeException("Error de SQL", e);
        } catch (Exception ex) {
            throw new RuntimeException("Error al borrar participante", ex);
        }
        return regsActualizados;
    }

    @Override
    public int removeParticipante(int id) {
        int regsBorrados = 0;
        try ( Connection con = Conexion.getConnection();
              PreparedStatement ps = con.prepareStatement(DELETE_QUERY);
                ) {
            ps.setInt(1, id);
            regsBorrados = ps.executeUpdate();            
        } catch(SQLException e) {
            throw new RuntimeException("Error de SQL", e);
        } catch (Exception ex) {
            throw new RuntimeException("Error al borrar participante", ex);
        }
        return regsBorrados;
    }
    
    private void fillPreparedStatement(PreparedStatement ps, participantes alu) throws SQLException {
        ps.setString(1, alu.getNombre());
        ps.setString(2, alu.getApellido());
        ps.setString(3, alu.getMail());
        ps.setString(4, alu.getFechaNacimiento());
        ps.setString(5, alu.getFoto());        
    }
    
    private Participante rsToParticipante(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nombre = rs.getString("nombre");
        String apellido = rs.getString("apellido");
        String mail = rs.getString("mail");
        String fechaNac = rs.getString("fechaNac");
        String fotoBase64 = rs.getString("fotoBase64");
        return new Participante(id, nombre, apellido, mail, fechaNac, fotoBase64);
    }

    private void fillPreparedStatement(PreparedStatement ps, Participante participante) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
