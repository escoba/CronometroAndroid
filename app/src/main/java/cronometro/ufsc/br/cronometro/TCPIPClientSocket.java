/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cronometro.ufsc.br.cronometro;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

/**
 *
 * @author escoba
 */

public class TCPIPClientSocket extends Thread {
    
    private Socket socket;
    private ObjectOutputStream objectoutputstream;
    private boolean on = true;

    public TCPIPClientSocket(String ip, int port) {
        
        try {
            this.socket = new Socket(ip, port);
            objectoutputstream = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("Connection successful");
        }
        catch(UnknownHostException uhe) {
            on = false;
            System.err.println(uhe);
        }
        catch(IOException ioe) {
            on = false;
            System.err.println(ioe);
        }
    }
        
    public void sendMessage(String send_menssage) {

        try {
            objectoutputstream.writeObject(send_menssage);
        }
        catch(IOException ioe) {
            on = false;
            System.err.println(ioe);
        }
    }
}