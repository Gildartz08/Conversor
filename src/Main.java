import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner entrada = new Scanner(System.in);

        while (true) {
            String apiUrl = "";
            int opcion;
            boolean opcionValida = true; //por si no elige una opcion numérica valida, se evita que se agregue a la url
            double cantidad = 0; //para que exista fuera del bloque try/catch
            boolean cantidadValida = false; //por si no elige un valor numérico para convertir

            System.out.println("""
                    *******************************************
                    Sea Bienvenido/a al conversor de Monedas =)
                    1) Dolar =>> Peso Argentino
                    2) Peso Argentino =>> Dolar
                    3) Dolar =>> Real Brasileno
                    4) Real Brasileno =>> Dolar
                    5) Dolar =>> Peso Colombiano
                    6) Peso colombiano =>> Dolar
                    7) Salir
                    ********************************************
                    """);
            try {
                opcion = entrada.nextInt();
            } catch (InputMismatchException e) {   //por si escribe algo que no sea un valor esperado
                System.out.println("Opcion no válida. Por favor, ingrese un número entre 1 y 7.");
                entrada.next(); //reinicio el scanner
                continue; //Se salta la iteración actual
            }

            if (opcion == 7) {
                break; //salida del while
            }
            switch (opcion) {
                case 1:
                    apiUrl = "USD/ARS";
                    break;
                case 2:
                    apiUrl = "ARS/USD";
                    break;
                case 3:
                    apiUrl = "USD/BRL";
                    break;
                case 4:
                    apiUrl = "BRL/USD";
                    break;
                case 5:
                    apiUrl = "USD/COP";
                    break;
                case 6:
                    apiUrl = "COP/USD";
                    break;
                default:
                    System.out.println("No eligió una opción valida");
                    opcionValida = false; //para evitar que continue con la request a la API
                    break;
            }

            if (opcionValida) {
                Conversor conversor = new Conversor();
                Moneda moneda = conversor.obtenerDatos(apiUrl);

                while (!cantidadValida) {
                    try {
                        System.out.println("Ingresa el valor que deseas convertir:");
                        cantidad = entrada.nextDouble();
                        cantidadValida = true;
                    } catch (InputMismatchException e) {
                        entrada.next();
                        System.out.println("Por favor, ingrese un monto a convertir expresado en numeros, Ejemplo: 150.");
                    }
                }

                double resultado = conversor.cambioDeMoneda(cantidad, moneda.conversion_rate());
                System.out.println("El valor " + cantidad + " " + moneda.base_code() + ", corresponde al valor final de =>>> " + resultado + " " + moneda.target_code() + ".");
                System.out.println("Desea realizar otro cambio?");
            }
        }
        System.out.println("Gracias por utilizar el Conversor de Monedas");
    }
}
