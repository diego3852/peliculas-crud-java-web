package peliculas.crud.modelo;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author Charly Cimino Aprendé más Java en mi canal:
 * https://www.youtube.com/c/CharlyCimino Encontrá más código en mi repo de
 * GitHub: https://github.com/CharlyCimino
 */
public class Pelicula {

    private int id;
    private String nombre;
    private String genero;
    private LocalDate fechaNacimiento;
    private String foto;

    public Pelicula() {
    }

    public Pelicula(int id) {
        setId(id);
    }

  //  public Pelicula(String nombre, String genero, String fechaNacimiento) {
  //      this(nombre, genero, fechaNacimiento);
  //  }

 //   public Pelicula(int id, String nombre, String genero, String fechaNacimiento) {
  //      this(id, nombre, genero, fechaNacimiento);
   // }

    public Pelicula(int id, String nombre, String genero, String fechaNacimiento, String foto) {
        setId(id);
        setGenero(genero);
        setNombre(nombre);
        setFechaNacimiento(fechaNacimiento);
        setFoto(foto);
    }

    public void setId(int id) {
        if (id < 0) {
            throw new RuntimeException("Valor para ID inconsistente");
        }
        this.id = id;
    }

    public void setGenero(String genero) {
        if (genero == null || genero.trim().isEmpty()) {
            throw new RuntimeException("No se ha provisto un genero");
        }
        this.genero = genero.trim();
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new RuntimeException("No se ha provisto un nombre");
        }
        this.nombre = nombre.trim();
    }

    
    public void setFechaNacimiento(String fechaNacimiento) {
        if (fechaNacimiento == null || fechaNacimiento.trim().isEmpty()) {
            throw new RuntimeException("No se ha provisto una fecha de nacimiento");
        }
        try {
            LocalDate posibleFecha = LocalDate.parse(fechaNacimiento.trim());
            if (posibleFecha.isAfter(LocalDate.now())) {
                throw new RuntimeException("La fecha de nacimiento proveída es posterior al día de hoy");
            }
            this.fechaNacimiento = posibleFecha;
        } catch (DateTimeParseException ex) {
            throw new RuntimeException("La fecha de nacimiento proveída no es válida", ex);
        }
    }

    public void setFoto(String foto) {
        if (foto == null || foto.trim().isEmpty()) {
            foto = "assets/no-face.jpg";
        }
        if (!foto.contains("no-face") || this.foto == null || this.foto.contains("no-face")) {
            this.foto = foto.trim();
        }
    }

    public int getId() {
        return id;
    }

    public String getGenero() {
        return genero;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getNombreCompleto() {
        return nombre + " " + genero;
    }

    public int getEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    public String getFoto() {
        return foto;
    }

    @Override
    public String toString() {
        return "Alumno{" + "id=" + id + ", genero=" + genero + ", nombre=" + nombre + ", fechaNacimiento=" + fechaNacimiento + ", foto=" + foto + '}';
    }

}
