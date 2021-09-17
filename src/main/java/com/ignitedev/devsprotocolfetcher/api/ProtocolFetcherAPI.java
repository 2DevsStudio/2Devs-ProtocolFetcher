package com.ignitedev.devsprotocolfetcher.api;

import com.ignitedev.devsprotocolfetcher.data.EntityFetchedData;
import com.ignitedev.devsprotocolfetcher.enums.MinecraftVersion;
import com.ignitedev.devsprotocolfetcher.fetcher.DataFetcher;
import com.ignitedev.devsprotocolfetcher.interfaces.Fetchable;
import com.ignitedev.devsprotocolfetcher.util.DebugUtility;
import java.io.InputStream;
import java.net.URI;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
  private static final Logger logger = Logger.getLogger(ProtocolFetcherAPI.class.getName());

  @Setter
  @Getter
  private static boolean debugMode;

  @Getter
  private final Document document;
  private final Map<String, List<? extends Fetchable>> fetchedData = new HashMap<>();

  @Getter
  private List<EntityFetchedData> entitesFetchedData = new ArrayList<>();


  @SneakyThrows
  public ProtocolFetcherAPI(MinecraftVersion minecraftVersion) {
    URI uri = new URI(URL + minecraftVersion.getId() + ".html");
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
    this.entitesFetchedData = new DataFetcher(document).fetchEntities();

    DebugUtility.info("found entities: " + this.entitesFetchedData.toString());

    fetchedData.put(this.entitesFetchedData.get(0).getFetchedDataName(), this.entitesFetchedData);
  }

  @Nullable
  public EntityFetchedData getEntityByProtocolID(int protocolId) {
    for (EntityFetchedData data : entitesFetchedData) {
      if (data.getProtocolID() == protocolId) {
        return data;
      }
    }
    return null;
  }

  @Nullable
  public EntityFetchedData getEntityByDisplayName(String displayName) {
    for (EntityFetchedData data : entitesFetchedData) {
      if (data.getDisplayName().equalsIgnoreCase(displayName)) {
        return data;
      }
    }
    return null;
  }

  @Nullable
  public EntityFetchedData getEntityByTypeName(String typeName) {
    for (EntityFetchedData data : entitesFetchedData) {
      if (data.getTypeName().equalsIgnoreCase(typeName)) {
        return data;
      }
    }
    return null;
  }



}
