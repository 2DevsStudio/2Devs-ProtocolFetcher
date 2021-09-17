package com.ignitedev.devsprotocolfetcher.fetcher;

import com.ignitedev.devsprotocolfetcher.api.ProtocolFetcherAPI;
import com.ignitedev.devsprotocolfetcher.data.EntityFetchedData;
import com.ignitedev.devsprotocolfetcher.util.DebugUtility;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;
import lombok.Data;
import org.jetbrains.annotations.Nullable;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Data
public class DataFetcher {

  private static final Logger logger = Logger.getLogger(DataFetcher.class.getName());
  private static final String ENTITIES = "#entities:";
  private static final String DIV_ENTRY = "div.entry";
  private static final String HREF = "a[href]";
  private static final String DL = "dl";
  private static final String DD = "dd";
  private static final String DT = "dt";
  private static final boolean DEBUG_MODE = ProtocolFetcherAPI.isDebugMode();

  private final Document documentToFetch;


  /**
   * @implNote EntityType -> EntityID
   */
  public List<EntityFetchedData> fetchEntities() {
    List<EntityFetchedData> entities = new ArrayList<>();
    int foundElement = 0;

    for (Element element : this.documentToFetch.select(DIV_ENTRY)) { // select all entries
      Element first = element.select(HREF).first();

      if (first == null) {
        continue;
      }
      String href = first.attr("href");

      if (href.contains("~") || !href.startsWith(
          ENTITIES)) { // ex: https://pokechu22.github.io/Burger/1.17.1.html#entities:area_effect_cloud
        continue;
      }
      entities.add(
          fetchEntitiesData(element.getElementsByTag(DL).first())); // wrapper of all attributes
      DebugUtility.info("Found element: " + foundElement);
      foundElement++;
    }
    return entities;
  }

  @Nullable
  private EntityFetchedData fetchEntitiesData(Element tab) {
    if (tab == null) {
      return null;
    }

    int protocolID = 0;
    String typeName = "";
    String displayName = "";
    double height = 0;
    double width = 0;

    for (Entry<String, String> pairedEntry : getPairedEntityDataWithoutMetaData(tab).entrySet()) {
      switch (pairedEntry.getKey()) {
        case "ID":
          protocolID = Integer.parseInt(pairedEntry.getValue());
          break;
        case "Name":
          typeName = pairedEntry.getValue();
          break;
        case "Display name":
          displayName = pairedEntry.getValue();
          break;
        case "Height":
          height = Double.parseDouble(pairedEntry.getValue());
          break;
        case "Width":
          width = Double.parseDouble(pairedEntry.getValue());
          break;
      }
    }
    return new EntityFetchedData(protocolID, typeName, displayName, height, width);
  }

  private Map<String, String> getPairedEntityDataWithoutMetaData(Element tab) {
    Map<String, String> pairedEntityData = new HashMap<>();
    Elements valueNames = tab.getElementsByTag(DT);

    int index = 0;

    for (Element element : tab.getElementsByTag(DD)) {
      Element firstValueName = valueNames.get(index);

      if (firstValueName == null) {
        continue;
      }
      index++;

      String valueNameAsText = firstValueName.text();

      if (valueNameAsText.startsWith("~")) {
        continue;
      }

      if (valueNameAsText.equalsIgnoreCase("metadata")) {
        return pairedEntityData;
      }

      DebugUtility.info("Found key: " + valueNameAsText + " || " + element.text());

      pairedEntityData.put(valueNameAsText, element.text());
    }
    return pairedEntityData;
  }
}
