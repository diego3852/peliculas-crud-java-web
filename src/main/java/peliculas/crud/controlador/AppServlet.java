package peliculas.crud.controlador;

import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Properties;
import peliculas.crud.modelo.Modelo;
import peliculas.crud.modelo.ModeloFactory;
import peliculas.crud.modelo.Pelicula;

/**
 
 */
@WebServlet(name = "AppServlet", urlPatterns = {"/app"})
public class AppServlet extends HttpServlet {

    private Modelo model;
    private final String URI_LIST = "listadoPeliculas.jsp";
    private final String URI_REMOVE = "/WEB-INF/pages/peliculas/borrarPelicula.jsp";   
    private final String URI_EDIT = "/WEB-INF/pages/peliculas/editarPelicula.jsp";
    @Override
    public void init() throws ServletException {
        this.model = getModelo();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        accion = accion == null ? "" : accion;
        int id;
        Pelicula pel;
        switch (accion) {
            case "edit":
                id = Integer.parseInt(request.getParameter("id"));
                pel = model.getPelicula(id);
                request.setAttribute("yaTieneFoto", !pel.getFoto().contains("no-face"));
                request.setAttribute("peliculaAEditar", pel);
                request.getRequestDispatcher(URI_EDIT).forward(request, response);
                break;
            case "remove":
                id = Integer.parseInt(request.getParameter("id"));
                pel = model.getPelicula(id);
                request.setAttribute("peliculaABorrar", pel);
                request.getRequestDispatcher(URI_REMOVE).forward(request, response);
                break;
            default:
                request.setAttribute("listaPeliculas", model.getPeliculas());
                request.getRequestDispatcher(URI_LIST).forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Pelicula pel;
        String accion = request.getParameter("accion");
        accion = accion == null ? "" : accion;
        int id;
        switch (accion) {
            case "add":
                pel = new Pelicula();
               // cargarPeliculasSegunParams(pel, request);
                model.addPelicula(pel);
                break;
            case "update":
                id = Integer.parseInt(request.getParameter("id"));
                pel = model.getPelicula(id);
                cargarPeliculaSegunParams(pel, request);
                model.updatePelicula(pel);
                break;
            case "delete":
                id = Integer.parseInt(request.getParameter("id"));
                model.removePelicula(id);
                break;
        }
        doGet(request, response);
    }

    private void cargarPeliculaSegunParams(Pelicula a, HttpServletRequest request) {
        a.setNombre(request.getParameter("nombre"));
        a.setGenero(request.getParameter("genero"));
        a.setFechaNacimiento(request.getParameter("fechaNac"));
        a.setFoto(request.getParameter("fotoBase64"));
    }

    private Modelo getModelo() throws ServletException {
        Modelo m = null;
        try ( InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties")) {
            Properties props = new Properties();
            props.load(is);
            String tipoModelo = props.getProperty("tipoModelo");
            m = ModeloFactory.getInstance().crearModelo(tipoModelo);
        } catch (IOException ex) {
            throw new ServletException("Error de E/S al al leer 'config' para iniciar el Servlet", ex);
        }
        return m;
    }

}
