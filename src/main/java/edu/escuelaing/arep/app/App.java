package edu.escuelaing.arep.app;
import edu.escuelaing.arep.app.calculos.Calculadora;
import edu.escuelaing.arep.app.listas.ListaEnlazada;
import spark.Request;
import spark.Response;
import static spark.Spark.*;
import java.text.DecimalFormat;
/**
 * Clase encargada de llevar a cabo la ejecucion de todo el programa.
 * @author  Alejandro Toro Daza
 * @version 1.0.  (20 de Enero del 2021) 
 */
public class App {
    /**
     * Metodo principal main que se encarga del funcionamiento de toda la clase App.
     * @param args Parametro que indica la lista de los elementos a evaluar.
     */
    public static void main( String[] args ) {
    	port(getPort());
        get("/", (req, res) -> inputDataPage(req, res));
        get("/results", (req, res) -> resultsPage(req, res));
    }
    private static String inputDataPage(Request req, Response res) {
        String pageContent
                = "<!DOCTYPE html>"
                + "<html>"
                + "<title> Calculadora</title>"
                + "<body>"
                + "<h1>Calculadora de la media y desviaci�n est�ndar</h1>"
                + "<form action=\"/results\">"
                + "Por favor ingrese los datos separados por una coma(\",\"):<br>"
                + "<input type=\"text\" name=\"Datos\">"
                + "<br><br>"
                + "<input type=\"submit\" value=\"Submit\">"
                + "</form>"
                + "<p>If you click the \"Submit\" button, the form-data will be sent to a page called \"/results\".</p>"
                + "</body>"
                + "</html>";
        return pageContent;
    }
    /**
     * Metodo encargado de obtener la media y la desviacion estandar (obtenida a traves de la varianza) de los datos ingresados.
     * @param elementos Parametro que indica los elementos de la lista.
     */
    
    /**
    *
    * @param req Tiene la informacion de la petici�n.
    * @param res Tiene la informaci�n con la respuesta del servidor.
    * @return pageContent Contiene la pagina html.
    */
   

   /**
    *
    * @param req Tiene la informacion de la petici�n.
    * @param res Tiene la informaci�n con la respuesta del servidor.
    * @return pageResponse Contiene la pagina html.
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
               + "<title> Calculadora</title>"
               + "<body>"
               + "<h1>Resultados de las operaciones:</h1>\n"
               + "El c�lculo de la media de los datos ingresados es: "
               + calculos[0]
               + "<br>"
               + "El c�lculo de la desviaci�n est�ndar de los datos ingresados es: "
               + calculos[1]
               + "<br>"
               + "<a href=\"/\">Volver</a>"
               + "</body>"
               + "</html>";
       return pageResponse;
   }
   public static String calcular(ListaEnlazada<Double> elementos){
       DecimalFormat twoDForm = new DecimalFormat("#.##");
       double media = Calculadora.media(elementos);
       double desviacionEstandar = Calculadora.desviacionEstandar(elementos);
       return twoDForm.format(media)+" "+twoDForm.format(desviacionEstandar);
   }

   /**
    * This method reads the default port as specified by the PORT variable in
    * the environment.
    *
    * Heroku provides the port automatically so you need this to run the
    * project on Heroku.
    * @return returns default port if heroku-port isn't set (i.e. on localhost)
    */
   static int getPort() {
       if (System.getenv("PORT") != null) {
           return Integer.parseInt(System.getenv("PORT"));
       }
       return 4567; //returns default port if heroku-port isn't set (i.e. on localhost)
   }
}