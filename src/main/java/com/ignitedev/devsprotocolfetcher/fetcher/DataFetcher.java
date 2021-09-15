package com.ignitedev.devsprotocolfetcher.fetcher;

import com.ignitedev.devsprotocolfetcher.api.ProtocolFetcherAPI;
import com.ignitedev.devsprotocolfetcher.util.DebugUtility;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import lombok.Data;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

@Data
public class DataFetcher {

  private static final Logger logger = Logger.getLogger(DataFetcher.class.getName());
  private static final String ENTITIES = "entities:";
  private static final String DIV_ENTRY = "div.entry";
  private static final String HREF = "a[href]";
  private static final String DL = "dl";
  private static final String DD = "dd";
  private static final boolean DEBUG_MODE = ProtocolFetcherAPI.isDebugMode();

  private final Document documentToFetch;


  /**
   * @implNote EntityType -> EntityID
   */
  public Map<String, Integer> fetchEntities() {
    Map<String, Integer> entities = new HashMap<>();
    int foundElement = 0;

    for (Element element : this.documentToFetch.select(DIV_ENTRY)) { // select all entries
      Element first = element.select(HREF).first();

      if (first == null) {
        continue;
      }
      String href = first.attr(
          "href"); // ex: https://pokechu22.github.io/Burger/1.17.1.html#entities:area_effect_cloud

      if (!href.contains(ENTITIES)) {
        continue;
      }
      String entityType = href.substring(href.lastIndexOf(ENTITIES)); // example: area_effect_cloud
      Element firstTab = element.getElementsByTag(DL).first(); // wrapper of all attributes

      DebugUtility.info("Found entityType: " + entityType);

      if (firstTab == null) {
        continue;
      }
      Element protocolID = firstTab.getElementsByTag(DD).first(); // protocol id of entity from <dd>

      if (protocolID == null) {
        continue;
      }
      String textProtocolID = protocolID.text();

      if (textProtocolID.startsWith("~")) {
        return entities;
      }
      DebugUtility.info("Found ProtocolID: " + protocolID);
      DebugUtility.info("Found element:" + foundElement);

      entities.put(entityType, Integer.parseInt(textProtocolID));

      foundElement++;
    }
    return entities;
  }
}
