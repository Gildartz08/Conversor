import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Conversor {

    public Moneda obtenerDatos(String string) throws IOException, InterruptedException {
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/53ca8dda67f7689b962020be/pair/" + string);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        return new Gson().fromJson(response.body(), Moneda.class);
    }

    public double cambioDeMoneda(double cantidad, double tazaDeCambio){
        return cantidad * tazaDeCambio;
    }


}

