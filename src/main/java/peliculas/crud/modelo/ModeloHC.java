package peliculas.crud.modelo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 */

// Modelo HC (Hard Codeado): Los datos se guardan en la RAM. Solo sirve para testear la app.
public class ModeloHC implements Modelo {

    private List<Pelicula> peliculasGuardadas;

    public ModeloHC() {
        this.peliculasGuardadas = new ArrayList<>();
        crearPeliculasFake();
    }

    @Override
    public List<Pelicula> getPeliculas() {
        return new ArrayList(this.peliculasGuardadas); // Copia de la lista original
    }

    @Override
    public Pelicula getPelicula(int id) {
        int i = 0;
        Pelicula encontrada = null;
        while (i < this.peliculasGuardadas.size() && encontrada == null) {
            Pelicula a = this.peliculasGuardadas.get(i);
            if (a.getId() == id) {
                encontrada = a;
            } else {
                i++;
            }
        }
        if (encontrada == null) {
            throw new RuntimeException("No se encontró pelicula con ID " + id);
        }
        return encontrada;
    }

    @Override
    public int addPelicula(Pelicula pelicula) {
        this.peliculasGuardadas.add(pelicula);
        return 0;
    }

    @Override
    public int updatePelicula(Pelicula a) {
        Pelicula target = getPelicula(a.getId());
        int idx = this.peliculasGuardadas.indexOf(target);
        this.peliculasGuardadas.set(idx, a);
        return 0;
    }

    @Override
    public int removePelicula(int id) {
        Pelicula target = getPelicula(id);
        this.peliculasGuardadas.remove(target);
        return 0;
    }

    private void crearPeliculasFake() {
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("carasFake.properties")) {
            Properties props = new Properties();
            props.load(is);
            this.peliculasGuardadas.add(new Pelicula(1, "La Bella y La Bestia", "Romantica", "1999-06-22", (String) props.get("HOMBRE_1")));
            this.peliculasGuardadas.add(new Pelicula(2, "Corazon Valiente", "Epica", "1991-02-28",(String) props.get("MUJER_1")));
            this.peliculasGuardadas.add(new Pelicula(3, "Duro de Matar", "Accion",  "1984-03-24", (String) props.get("MUJER_1")));
            this.peliculasGuardadas.add(new Pelicula(4, "Martes 13", "Terror", "1998-07-04", (String) props.get("HOMBRE_3")));
            this.peliculasGuardadas.add(new Pelicula(5, "Superman", "Accion", "1991-02-28", (String) props.get("MUJER_3")));
            this.peliculasGuardadas.add(new Pelicula(6, "Heroes", "Deporte",  "1986-11-13", (String) props.get("HOMBRE_2")));
            this.peliculasGuardadas.add(new Pelicula(7, "Tonto y Retonto", "Comedia", "1968-07-12", (String) props.get("MUJER_2")));
            this.peliculasGuardadas.add(new Pelicula(8, "Flashdance", "Drama", "1968-07-12" , (String) props.get("MUJER_4")));
            this.peliculasGuardadas.add(new Pelicula(9, "Rocky", "Accion", "1990-05-14", (String) props.get("HOMBRE_4")));
        } catch (IOException ex) {
            throw new RuntimeException("No se pudieron cargar las caras fake");
        }
    }
}
