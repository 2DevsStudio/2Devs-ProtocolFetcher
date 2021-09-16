package com.ignitedev.devsprotocolfetcher.data;

import com.ignitedev.devsprotocolfetcher.interfaces.Fetchable;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@Data
public class EntityFetchedData implements Fetchable {

  private final String fetchedDataName = "Entities";

  private final int protocolID;
  private final String typeName;
  private final String displayName;
  private final double height;
  private final double width;

  private final Map<Integer, String> metaData = new HashMap<>();

}
