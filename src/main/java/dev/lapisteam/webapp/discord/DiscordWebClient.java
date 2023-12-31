package dev.lapisteam.webapp.discord;

import dev.lapisteam.webapp.discord.builders.ComponentBuilder;
import dev.lapisteam.webapp.discord.models.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class DiscordWebClient {

    private final String url;
    private HttpURLConnection httpURLConnection;

    public DiscordWebClient(String url) {
        this.url = url;
    }

    public void connect() throws IOException {
        if(this.httpURLConnection != null) this.httpURLConnection.disconnect();
        this.httpURLConnection = (HttpURLConnection) new URL(this.url).openConnection();

        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");

        httpURLConnection.setDoOutput(true);
    }

    public void send(ComponentBuilder componentBuilder) throws IOException {
        send(componentBuilder.getComponent());
    }

    public void send(Component component) throws IOException {
        if(httpURLConnection == null) connect();

        OutputStream outputStream = httpURLConnection.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);

        writer.write(component.toJsonObject().toString());
        writer.flush();
        writer.close();

        if(httpURLConnection.getResponseCode() != 204) System.out.println("Unexpected response code: " + httpURLConnection.getResponseCode());

        outputStream.close();
        httpURLConnection.disconnect();
        httpURLConnection = null;
    }
}
