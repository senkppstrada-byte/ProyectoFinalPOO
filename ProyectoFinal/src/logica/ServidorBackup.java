package logica;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import logica.BolsaLaboral;

public class ServidorBackup {

    private static final int PUERTO = 7000;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(PUERTO);
            System.out.println("=== SERVIDOR DE BACKUP INICIADO EN PUERTO " + PUERTO + " ===");
            System.out.println("Esperando solicitudes de respaldo...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Conexión recibida desde: " + socket.getInetAddress());

                new Thread(new HiloBackup(socket)).start();
            }

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class HiloBackup implements Runnable {

    private Socket socket;

    public HiloBackup(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {

            BolsaLaboral copiaBolsa = (BolsaLaboral) ois.readObject();

            File folder = new File("backups");
            if (!folder.exists()) {
                folder.mkdir();
            }

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String nombreArchivo = "backups/backup_bolsa_" + timestamp + ".dat";

            try (FileOutputStream fos = new FileOutputStream(nombreArchivo);
                 java.io.ObjectOutputStream oos = new java.io.ObjectOutputStream(fos)) {
                
                oos.writeObject(copiaBolsa);
                System.out.println("Backup guardado con exito");
            }

        } catch (Exception e) {
            System.err.println("Error procesando el backup: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}