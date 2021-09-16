package com.ignitedev.devsprotocolfetcher.api;

import com.ignitedev.devsprotocolfetcher.data.EntityFetchedData;
import com.ignitedev.devsprotocolfetcher.fetcher.DataFetcher;
import com.ignitedev.devsprotocolfetcher.interfaces.Fetchable;
import com.ignitedev.devsprotocolfetcher.util.DebugUtility;
import java.io.InputStream;
import java.net.URI;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.Nullable;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class ProtocolFetcherAPI {

  private static final String URL = "https://pokechu22.github.io/Burger/";

  @Setter
  @Getter
  private static boolean debugMode;

  @Getter
  private final Document document;
  private final Logger logger = Logger.getLogger(ProtocolFetcherAPI.class.getName());

  private final List<Fetchable> fetchedDataList = new ArrayList<>();

  @Nullable
  private EntityFetchedData entityFetchedData;


  @SneakyThrows
  public ProtocolFetcherAPI(String minecraftVersion) {
    URI uri = new URI(URL + minecraftVersion + ".html");
    URLConnection urlConnection = uri.toURL().openConnection();
    InputStream inputStream = urlConnection.getInputStream();
    String encoding =
        urlConnection.getContentEncoding() == null ? "UTF-8" : urlConnection.getContentEncoding();
    String body = IOUtils.toString(inputStream, encoding);

    document = Jsoup.parse(body);
    logger.log(Level.INFO, "Connected with " + minecraftVersion + " protocol!");
  }

  @SneakyThrows
  public void fetchData() {
    List<EntityFetchedData> entityFetchedData = new DataFetcher(document).fetchEntities();

    DebugUtility.info("found entities:");

    for (EntityFetchedData data : entityFetchedData) {
      DebugUtility.info(data.toString());
    }
  }


}
