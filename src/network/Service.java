package src.network;

import java.net.*;
import java.io.*;
import java.util.*;

class Service extends Thread {

    Socket socket;

    public Service( Socket socket ) {
        this.socket = socket;
        this.start();
    }

    public void run() {
        BufferedReader entree = null;
        PrintStream sortie = null;
        try {
            entree = new BufferedReader( new InputStreamReader( socket.getInputStream() ) );
            sortie = new PrintStream( socket.getOutputStream() );
        } catch( IOException e ) {
            try {
                socket.close();
            } catch( IOException e2 ) {
                System.out.println( "problème en fermant socket" );
            }
        }

        String texte;
        int compteur = 0;

        try {
            while( !(texte = entree.readLine()).equals("xxxxxx"))
                compteur += (new StringTokenizer( texte, " ,.;:_-+*/\\.;\"'{}()=><\t!\n")).countTokens();
            sortie.println( "votre texte possède " + compteur + " mots" );
            socket.close();
        } catch( IOException e ) {
            System.out.println( "problème dans run" );
        }
    }


}
