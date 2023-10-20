package src.network;

import java.net.*;
import java.io.*;

public class TCPServer {

    public TCPServer() {
        int port = 12345;
        ServerSocket receptionniste;
        Socket socket;

        try {
            receptionniste = new ServerSocket( port );
            while( true ) {
                System.out.println( "waiting..." );
                socket = receptionniste.accept();
                System.out.println( "connexion!" );
                new Service( socket );
            }
        } catch( IOException e ) {
            System.out.println( "problème de connexion" );
        }
    }
}
