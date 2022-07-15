
package peliculas.crud.modelo;

import java.util.List;

/**
  
 */
public interface Modelo {
    /**
     * Devuelve una lista de alumnos existentes
     * @return 
     */
    public List<Pelicula> getPeliculas();
    
    /**
     * Retorna una pelicula por por ID
     * @param id el id de pelicula a retornar
     * @return La pelicula encontrado por ID o null si no existe
     */
    public Pelicula getPelicula(int id);
    
    /**
     * Agrega una pelicula al modelo
     * @param pelicula La pelicula a agregar
     * @return La cantidad de registros modificados
     */
    public int addPelicula(Pelicula pelicula);
    
    /**
     * Modifica un pelicula del modelo
     * @param pelicula La pelicula que contiene los datos para modificar
     * @return La cantidad de registros modificados
     */
    public int updatePelicula(Pelicula pelicula);
    
    /**
     * Borra una pelicula por ID
     * @param id el id de la pelicula a borrar
     * @return La cantidad de registros modificados
     */
    public int removePelicula(int id);
}
