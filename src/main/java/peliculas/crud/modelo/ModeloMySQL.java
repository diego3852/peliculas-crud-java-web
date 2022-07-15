package peliculas.crud.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ModeloMySQL implements Modelo {

    private static final String GET_ALL_QUERY = "SELECT * FROM peliculas";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM peliculas WHERE id_pelicula = ?";
    private static final String ADD_QUERY = "INSERT INTO peliculas VALUES (null, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE peliculas SET nombre = ?, genero = ?, fechaNac = ?, fotoBase64 = ? WHERE id_pelicula = ?";
    private static final String DELETE_QUERY = "DELETE FROM peliculas WHERE id_pelicula = ?";

    @Override
    public List<Pelicula> getPeliculas() {
        List<Pelicula> peliculas = new ArrayList<>();
        try ( Connection con = Conexion.getConnection();  PreparedStatement ps = con.prepareStatement(GET_ALL_QUERY);  ResultSet rs = ps.executeQuery();) {
            while (rs.next()) {
                peliculas.add(rsToPelicula(rs));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error de SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al obtener peliculas", ex);
        }
        return peliculas;
    }

    @Override
    public Pelicula getPelicula(int id) {
        Pelicula pel = null;
        try ( Connection con = Conexion.getConnection();  PreparedStatement ps = con.prepareStatement(GET_BY_ID_QUERY);) {
            ps.setInt(1, id);
            try ( ResultSet rs = ps.executeQuery();) {
                rs.next();
                pel = rsToPelicula(rs);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error de SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al obtener alumnos", ex);
        }
        return pel;
    }

    @Override
    public int addPelicula(Pelicula pelicula) {
        int regsAgregados = 0;
        try ( Connection con = Conexion.getConnection();  PreparedStatement ps = con.prepareStatement(ADD_QUERY);) {
            fillPreparedStatement(ps, pelicula);
            regsAgregados = ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error de SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al obtener peliculas", ex);
        }
        return regsAgregados;
    }

    @Override
    public int updatePelicula(Pelicula pelicula) {
        int regsModificados = 0;
        try ( Connection con = Conexion.getConnection();  PreparedStatement ps = con.prepareStatement(UPDATE_QUERY);) {
            fillPreparedStatement(ps, pelicula);
            ps.setInt(6, pelicula.getId());
            regsModificados = ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error de SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al obtener peliculas", ex);
        }
        return regsModificados;
    }

    @Override
    public int removePelicula(int id) {
        int regsBorrados = 0;
        try ( Connection con = Conexion.getConnection();  PreparedStatement ps = con.prepareStatement(DELETE_QUERY);) {
            ps.setInt(1, id);
            regsBorrados = ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException("Error de SQL", ex);
        } catch (Exception ex) {
            throw new RuntimeException("Error al obtener alumnos", ex);
        }
        return regsBorrados;
    }

    private void fillPreparedStatement(PreparedStatement ps, Pelicula pelicula) throws SQLException {
        ps.setString(1, pelicula.getNombre());
        ps.setString(2, pelicula.getGenero());
        ps.setString(3, pelicula.getFechaNacimiento());
        ps.setString(5, pelicula.getFoto());
    }

    private Pelicula rsToPelicula(ResultSet rs) throws SQLException {
        int id = rs.getInt(1);
        String nombre = rs.getString(2);
        String genero = rs.getString(3);
        String fechaNacimiento = rs.getString(4);
        String foto = rs.getString(5);
        return new Pelicula(id, nombre, genero, fechaNacimiento, foto);
    }
}
