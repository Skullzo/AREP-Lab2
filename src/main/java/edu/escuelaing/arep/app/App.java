package edu.escuelaing.arep.app;
import edu.escuelaing.arep.app.calculos.Calculadora;
import edu.escuelaing.arep.app.listas.ListaEnlazada;
import spark.Request;
import spark.Response;
import static spark.Spark.*;
import java.text.DecimalFormat;
/**
 * Clase principal encargada de llevar a cabo la ejecucion de todo el programa.
 * @author  Alejandro Toro Daza
 * @version 1.0.  (20 de Enero del 2021) 
 */
public class App {
    /**
     * Metodo principal main que se encarga del funcionamiento de toda la clase App.
     * @param args Parametro que indica la lista de los elementos a evaluar.
     */
	public static void main(String[] args) {
        port(getPort());
        get("/", (req, res) -> inputDataPage(req, res));
        get("/results", (req, res) -> resultsPage(req, res));
    }
    /**
     * Metodo encargado de obtener la media y la desviacion estandar (obtenida a traves de la varianza) de los datos ingresados.
     * @param elementos Parametro que indica los elementos de la lista.
     */
    public static String calcular(ListaEnlazada<Double> elementos){
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        double media = Calculadora.media(elementos);
        double desviacionEstandar = Calculadora.desviacionEstandar(elementos);
        return twoDForm.format(media)+" "+twoDForm.format(desviacionEstandar);
    }
   /**
    * @param req Parametro que se encarga de almacenar la informacion de la peticion.
    * @param res Parametro que se encarga de almacenar la informacion de la respuesta del servidor.
    * @return pageContent Retorna la pagina HTML que contiene la interfaz de usuario.
    */
    private static String inputDataPage(Request req, Response res) {
        String pageContent
                = "<!DOCTYPE html>"
                + "<html>"
                + "<title>Calculadora de Media y Desviacion Estandar</title>"
                + "<body style = \"background: url(https://blog.hdwallsource.com/wp-content/uploads/2017/05/desk-calculator-wallpaper-61252-63066-hd-wallpapers.jpg) no-repeat ; background-size: 100% 113%;\">"
                + "</body>"
                + "<body>"
                + "<div style=\"padding:30px; width:96.9%; background:#008000; color:white; font-size:500%; position:absolute ; top:0px ; left:0px\">"
        		+ "<center>Calculadora de Media y Desviacion Estandar</center>"
        	    + "</div>"
                + "<form action=\"/results\">"
                + "<div style=\"padding:30px; width:96.9%; background:#005a80; color:white; font-size:150%; position:absolute ; top:150px ; left:0px\">"
        		+ "<center>Ingrese los numeros separados por una coma sin espacios.</center>"
        	    + "</div>"
                + "<input style=\"width:500px ; font-size:150% ; position:absolute ; top:237px ; left:720px\" type=\"text\" name=\"Datos\">"
                + "<br><br>"
                + "<input style=\"width:508px ; font-size:150% ; position:absolute ; top:272px ; left:720px\" type=\"submit\" value=\"Calcular Media y Desviacion Estandar\">"
                + "</form>"
                + "<br><br>"
                + "<br><br>"
                + "<br><br>"
                + "<br><br>"
                + "<br><br>"
                + "<br><br>"
                + "<br><br>"
                + "<br><br>"
                + "<br><br>"
                + "<br><br>"
                + "<br><br>"
                + "<br><br>"
                + "<br><br>"
                + "<br><br>"
                + "<br><br>"
                + "<br><br>"
                + "<br><br>"
                + "<br><br>"
                + "<br><br>"
                + "<br><br>"
                + "<br><br>"
                + "<br><br>"
                + "<br><br>"
                + "</body>"
                + "</html>";
        return pageContent;
    }
   /**
    * @param req Parametro que se encarga de almacenar la informacion de la peticion.
    * @param res Parametro que se encarga de almacenar la informacion de la respuesta del servidor.
    * @return pageResponse Retorna la pagina HTML que contiene la interfaz de usuario.
    */
    private static String resultsPage(Request req, Response res) {
        ListaEnlazada<Double> elementos = new ListaEnlazada<Double>();
        String data = req.queryParams("Datos");
        String[] lista  = data.split(",");
        double n;
        for (String elemento: lista){
            n = Double.parseDouble(elemento);
            elementos.add(n);
        }
        String[] calculos = calcular(elementos).split(" ");
        String pageResponse
                = "<!DOCTYPE html>"
                + "<html>"
                + "<title>Calculadora de Media y Desviacion Estandar</title>"
                + "<body style = \"background: url(https://blog.hdwallsource.com/wp-content/uploads/2017/05/desk-calculator-wallpaper-61252-63066-hd-wallpapers.jpg) no-repeat ; background-size: 100% 111%;\">"
                + "</body>"
                + "<body>"
                + "<div style=\"padding:30px; width:96.9%; background:#008000; color:white; font-size:500%; position:absolute ; top:0px ; left:0px\">"
        		+ "<center>Resultados</center>"
        	    + "</div>"
        	    + "<div style=\"padding:30px; width:96.9%; background:#005a80; color:white; font-size:150%; position:absolute ; top:150px ; left:0px\">"
        		+ "<center>La Media y la Desviacion Estandar son:</center>"
        	    + "</div>"
        	    + "<br><br>"
        	    + "<br><br>"
        	    + "<br><br>"
        	    + "<br><br>"
        	    + "<br><br>"
        	    + "<br><br>"
        	    + "<br><br>"
        	    + "<br><br>"
        	    + "<br><br>"
        	    + "<br><br>"
        	    + "<br><br>"
                + calculos[0]
                + "<br>"
                + "El cálculo de la desviación estándar de los datos ingresados es: "
                + calculos[1]
                + "<br>"
                + "<a href=\"/\">Regresar a la Pagina Principal</a>"
                + "</body>"
                + "</html>";
        return pageResponse;
    }
   /**
    * Este metodo lee el puerto predeterminado segun lo especificado por la variable PORT en el entorno.
    * @return returns Retorna el puerto predeterminado si el heroku-port no esta configurado (es decir, en localhost).
    */
   static int getPort() {
       if (System.getenv("PORT") != null) {
           return Integer.parseInt(System.getenv("PORT"));
       }
       return 4567;
   }
}