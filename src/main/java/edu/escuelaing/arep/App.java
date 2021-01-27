package edu.escuelaing.arep;

import edu.escuelaing.arep.calculadora.Calculos;
import edu.escuelaing.arep.model.LinkedList;
import java.text.DecimalFormat;
import spark.Request;
import spark.Response;
import static spark.Spark.*;

public class App {
    /**
     * This main method uses SparkWeb static methods and lambda functions to
     * create a simple Hello World web app. It maps the lambda function to the
     * /hello relative URL.
     */
    public static void main(String[] args) {
        port(getPort());
        get("/", (req, res) -> inputDataPage(req, res));
        get("/results", (req, res) -> resultsPage(req, res));
    }

    /**
     *
     * @param req Tiene la informacion de la petición.
     * @param res Tiene la información con la respuesta del servidor.
     * @return pageContent Contiene la pagina html.
     */
    private static String inputDataPage(Request req, Response res) {
        String pageContent
                = "<!DOCTYPE html>"
                + "<html>"
                + "<title> Calculadora</title>"
                + "<body>"
                + "<h1>Calculadora de la media y desviación estándar</h1>"
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
     *
     * @param req Tiene la informacion de la petición.
     * @param res Tiene la información con la respuesta del servidor.
     * @return pageResponse Contiene la pagina html.
     */
    private static String resultsPage(Request req, Response res) {
        LinkedList<Double> elementos = new LinkedList<Double>();
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
                + "El cálculo de la media de los datos ingresados es: "
                + calculos[0]
                + "<br>"
                + "El cálculo de la desviación estándar de los datos ingresados es: "
                + calculos[1]
                + "<br>"
                + "<a href=\"/\">Volver</a>"
                + "</body>"
                + "</html>";
        return pageResponse;
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

    /**
     * Obtiene la media y la desviación estandar de los datos ingresados.
     * @param elementos Lista que contiene los elementos a evaluar.
     * @return Una cadena con el resultado de la media y de la desviación estándar separados por un espacio.
     */
    public static String calcular(LinkedList<Double> elementos){
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        double mean = Calculos.mean(elementos);
        double deviation = Calculos.deviation(elementos);
        //System.out.println("Mean: " + twoDForm.format(mean) + " Deviation: "+ twoDForm.format(deviation));
        return twoDForm.format(mean)+" "+twoDForm.format(deviation);
    }
}