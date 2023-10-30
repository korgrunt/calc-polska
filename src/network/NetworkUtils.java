package src.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class NetworkUtils {

    public NetworkUtils() {

    }

    public static String isHttpEntry(InputStream inputStream) {
        // Créez un BufferedReader pour lire les données du flux
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        // Lisez la première ligne pour déterminer la version du protocole
        String firstLine = null;
        try {
            firstLine = reader.readLine();
            System.out.println(firstLine);
            if (firstLine != null && firstLine.contains("HTTP/1.1")) {
                // C'est du HTTP 1.1, continuez à lire l'en-tête et le corps
                String line;
                boolean headerComplete = false;
                StringBuilder body = new StringBuilder();

                while ((line = reader.readLine()) != null) {
                    if (line.isEmpty()) {
                        // Fin de l'en-tête
                        headerComplete = true;
                    }

                    if (headerComplete) {
                        // Lire le corps
                        body.append(line);
                    }
                }

                System.out.println("Protocole HTTP 1.1 détecté.");
                System.out.println("Corps HTTP :");
                System.out.println("\n--------------------");
                System.out.println(body.toString().replaceAll("\n", ""));
                System.out.println("\n--------------------");
                return body.toString().replaceAll("\n", "");
            } else {
                System.out.println("Le protocole n'est pas HTTP 1.1.");
            }

            // Fermez le BufferedReader
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


}