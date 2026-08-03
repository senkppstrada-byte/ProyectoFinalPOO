package logica;

import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClienteBackup {

    private static final String IP_SERVIDOR = "127.0.0.1";
    private static final int PUERTO = 7000;

    public static boolean enviarBackup() {
        try (Socket socket = new Socket(IP_SERVIDOR, PUERTO);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {

            oos.writeObject(BolsaLaboral.getInstancia());
            oos.flush();
            return true;

        } catch (Exception e) {
            System.err.println("No se pudo conectar con el servidor de backup: " + e.getMessage());
            return false;
        }
    }
}