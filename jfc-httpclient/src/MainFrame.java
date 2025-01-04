import org.apache.hc.client5.http.async.methods.*;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
import org.apache.hc.core5.concurrent.FutureCallback;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.message.StatusLine;
import org.apache.hc.core5.io.CloseMode;
import org.apache.hc.core5.reactor.IOReactorConfig;
import org.apache.hc.core5.util.Timeout;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MainFrame {
    public static void main(String[] args) {
        final IOReactorConfig ioReactorConfig = IOReactorConfig.custom()
                .setSoTimeout(Timeout.ofSeconds(5))
                .build();

        final CloseableHttpAsyncClient client = HttpAsyncClients.custom()
                .setIOReactorConfig(ioReactorConfig)
                .build();

        client.start();

        final HttpHost target = new HttpHost("672fbf9066e42ceaf15e9a9b.mockapi.io");
        final String requestUrl = "/api/contacts";

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Contoh HTTP Client di Swing");
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.setSize(400, 200);
            frame.setLayout(new BorderLayout());

            JLabel statusLabel = new JLabel("Tekan Tombol Untuk Mulai Mengunduh Data", JLabel.CENTER);
            JButton startButton = new JButton("Mulai");
            JProgressBar progressBar = new JProgressBar(0, 100);
            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);

            frame.add(statusLabel, BorderLayout.NORTH);
            frame.add(scrollPane, BorderLayout.CENTER);

            JPanel panel = new JPanel(new FlowLayout());
            panel.add(startButton);
            panel.add(progressBar);
            frame.add(panel, BorderLayout.SOUTH);

            frame.addWindowListener(new WindowListener() {
                @Override
                public void windowClosing(WindowEvent e) {
                    client.close(CloseMode.GRACEFUL);
                    System.exit(0);
                }

                public void windowOpened(WindowEvent e) {}
                public void windowClosed(WindowEvent e) {}
                public void windowIconified(WindowEvent e) {}
                public void windowDeiconified(WindowEvent e) {}
                public void windowActivated(WindowEvent e) {}
                public void windowDeactivated(WindowEvent e) {}
            });

            final SimpleHttpRequest request = SimpleRequestBuilder.get()
                    .setHttpHost(target)
                    .setPath(requestUrl)
                    .build();

            startButton.addActionListener(e -> {
                progressBar.setIndeterminate(true);
                startButton.setEnabled(false);
                statusLabel.setText("Proses berjalan...");
                textArea.setText("");

                client.execute(
                        SimpleRequestProducer.create(request),
                        SimpleResponseConsumer.create(),
                        new FutureCallback<>() {
                            @Override
                            public void completed(SimpleHttpResponse response) {
                                try {
                                    JSONParser parser = new JSONParser();
                                    JSONArray contacts = (JSONArray) parser.parse(response.getBodyText());
                                    contacts.forEach(obj -> {
                                        JSONObject contact = (JSONObject) obj;
                                        textArea.append("Name: " + contact.get("name") +
                                                ", Phone: " + contact.get("phone") + "\n");
                                    });
                                    statusLabel.setText("Proses selesai");
                                } catch (ParseException ex) {
                                    textArea.setText("Error parsing JSON response");
                                }
                                progressBar.setIndeterminate(false);
                                startButton.setEnabled(true);
                            }

                            @Override
                            public void failed(Exception ex) {
                                statusLabel.setText("Proses gagal: " + ex.getMessage());
                                progressBar.setIndeterminate(false);
                                startButton.setEnabled(true);
                            }

                            @Override
                            public void cancelled() {
                                statusLabel.setText("Proses dibatalkan");
                                progressBar.setIndeterminate(false);
                                startButton.setEnabled(true);
                            }
                        }
                );
            });

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
