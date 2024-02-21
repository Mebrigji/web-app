package dev.lapisteam.webapp.logger;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class LogInjector {

  public static void bind() {
    bind(Logger.getLogger("STDOUT"), Logger.getLogger("STDERR"));
  }

  public static void bind(Logger loggerOut, Logger loggerErr) {
    try {

      String logFileName = generateLogFileName();

      File logsFolder = new File("logs");
      if (!logsFolder.exists()) {
        logsFolder.mkdir();
      }

      FileHandler fileHandler = new FileHandler("logs" + File.separator +  logFileName);
      fileHandler.setEncoding(StandardCharsets.UTF_8.name());
      fileHandler.setFormatter(new java.util.logging.SimpleFormatter());

      System.setOut(new PrintStream(new LoggerStream(loggerOut, Level.INFO, System.out, fileHandler), true));
      System.setErr(new PrintStream(new LoggerStream(loggerErr, Level.SEVERE, System.err, fileHandler), true));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static class LoggerStream extends OutputStream {
    private final Logger logger;
    private final Level logLevel;
    private final OutputStream outputStream;
    private final FileHandler fileHandler;
    private StringBuilder sbBuffer;

    public LoggerStream(Logger logger, Level logLevel, OutputStream outputStream, FileHandler fileHandler) {
      this.logger = logger;
      this.logLevel = logLevel;
      this.outputStream = outputStream;
      this.fileHandler = fileHandler;
      sbBuffer = new StringBuilder();
    }

    @Override
    public void write(byte[] b) throws IOException {
      doWrite(new String(b));
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
      doWrite(new String(b, off, len));
    }

    @Override
    public void write(int b) throws IOException {
      doWrite(String.valueOf((char) b));
    }

    private void doWrite(String str) throws IOException {
      sbBuffer.append(str);

      if (!sbBuffer.isEmpty() && sbBuffer.charAt(sbBuffer.length() - 1) == '\n') {
        sbBuffer.setLength(sbBuffer.length() - 1);
        if (!sbBuffer.isEmpty() && sbBuffer.charAt(sbBuffer.length() - 1) == '\r') {
          sbBuffer.setLength(sbBuffer.length() - 1);
        }
        String buf = sbBuffer.toString();
        sbBuffer.setLength(0);
        outputStream.write(buf.getBytes());
        outputStream.write('\n');
        logger.log(logLevel, buf);
        fileHandler.publish(new LogRecord(logLevel, buf.replaceAll("\u001B\\[[;\\d]*m", "")));
      }
    }
  }

  private static String generateLogFileName() {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    String currentDate = dateFormat.format(new Date());
    return currentDate + ".log";
  }
}
