package com.ignitedev.devsprotocolfetcher.api;

import com.ignitedev.devsprotocolfetcher.fetcher.DataFetcher;
import com.ignitedev.devsprotocolfetcher.util.DebugUtility;
import java.io.InputStream;
import java.net.URI;
import java.net.URLConnection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class ProtocolFetcherAPI {

  @Setter
  @Getter
  private static boolean debugMode;

  private final Logger logger = Logger.getLogger(ProtocolFetcherAPI.class.getName());

  @Getter
  private final Document document;

  @SneakyThrows
  public ProtocolFetcherAPI(String minecraftVersion) {
    URI uri = new URI("https://pokechu22.github.io/Burger/" + minecraftVersion + ".html");
    URLConnection urlConnection = uri.toURL().openConnection();
    InputStream inputStream = urlConnection.getInputStream();
    String encoding =
        urlConnection.getContentEncoding() == null ? "UTF-8" : urlConnection.getContentEncoding();
    String body = IOUtils.toString(inputStream, encoding);

    this.document = Jsoup.parse(body);
    logger.log(Level.INFO, "Connected with " + minecraftVersion + " protocol!");
  }

  @SneakyThrows
  public void fetchData() {
    Map<String, Integer> fetchEntities = new DataFetcher(document).fetchEntities();

    DebugUtility.info("Yoo! We found a lot of entities(perhaps???): " + fetchEntities.keySet());
  }


}
