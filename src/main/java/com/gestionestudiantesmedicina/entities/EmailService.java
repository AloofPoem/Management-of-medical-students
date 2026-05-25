package com.gestionestudiantesmedicina.entities;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailService {

    // Dirección física del servidor de Google e infraestructura de red
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    
    // Cuenta Emisora (Tus datos para iniciar sesión en el servidor)
    private static final String SENDER_EMAIL = "juanjito.botero@gmail.com"; 
    private static final String SENDER_PASSWORD = "xxxx xxxx xxxx xxxx"; // 👈 Tu Token de aplicación de 16 letras

    // Cuenta Receptora (El Administrador fijo que recibirá las alertas del hospital)
    private static final String ADMIN_EMAIL = "galeanobarrerajuandavid@gmail.com"; 

    /**
     * Envía una alerta por correo al Administrador fijo de manera asíncrona
     * para evitar congelamientos en la interfaz de usuario de JavaFX.
     */
    public static void enviarNotificacion(String asunto, String mensaje) {
        // Guard Clauses para salir rápido si los datos no son válidos
        if (asunto == null || asunto.isBlank()) return;
        if (mensaje == null || mensaje.isBlank()) return;

        // Delegar la conexión de red a un hilo secundario
        new Thread(() -> {
            try {
                // 1. Configurar las propiedades de red para el protocolo SMTP
                Properties props = new Properties();
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.host", SMTP_HOST);
                props.put("mail.smtp.port", SMTP_PORT);

                // 2. Autenticarse en el servidor de Google con la cuenta emisora
                Session session = Session.getInstance(props, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
                    }
                });

                // 3. Construir la estructura del mensaje de correo
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(SENDER_EMAIL));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(ADMIN_EMAIL)); 
                message.setSubject(asunto);
                message.setText(mensaje);

                // 4. Despachar el correo a través de la red
                Transport.send(message);
                System.out.println("📧 Alerta enviada con éxito al Administrador: " + ADMIN_EMAIL);

            } catch (MessagingException e) {
                System.err.println("❌ Error crítico al enviar correo: " + e.getMessage());
                e.printStackTrace();
            }
        }).start();
    }
}