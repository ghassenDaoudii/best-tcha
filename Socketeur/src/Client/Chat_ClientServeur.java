/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author ghassen
 */
public class Chat_ClientServeur implements Runnable {

    private final Socket socket;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private Scanner sc;

    public Chat_ClientServeur(Socket s) {
        socket = s;
    }

    @Override
    public void run() {
        try {
            out = new PrintWriter(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            sc = new Scanner(System.in);

            Thread t4 = new Thread(new Emission(out));
            t4.start();
            Thread t3 = new Thread(new Reception(in));
            t3.start();

        } catch (IOException e) {
            System.err.println("Le serveur distant s'est déconnecté !");
        }
    }

}
