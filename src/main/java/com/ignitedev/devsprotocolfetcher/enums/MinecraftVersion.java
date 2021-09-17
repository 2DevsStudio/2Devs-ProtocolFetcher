package com.ignitedev.devsprotocolfetcher.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;

@RequiredArgsConstructor
@Getter
public enum MinecraftVersion {

  v_1_17("1.17.1", new String[]{"MC1_17_R1"}),
  v_1_16("1.16.5", new String[]{"MC1_16_R1", "MC1_16_R2", "MC1_16_R3"}),
  v_1_15("1.15.2", new String[]{"MC1_15_R1"}),
  v_1_14("1.14.4", new String[]{"MC1_14_R1"}),
  v_1_13("1.13.2", new String[]{"MC1_13_R1", "MC1_13_R2"}),
  v_1_12("1.12.2", new String[]{"MC1_12_R1"}),
  v_1_11("1.11.2", new String[]{"MC1_11_R1"}),
  V_1_10("1.10.2", new String[]{"MC1_10_R1"}),
  v_1_9("1.9.4", new String[]{"MC1_9_R2", "MC1_9_R1"}),
  v_1_8("1.8.9", new String[]{"MC1_8_R3"});

  private final String id;
  private final String[] rawValues;

  @Nullable
  public static MinecraftVersion getVersionByRaw(String raw) {
    for (MinecraftVersion version : values()) {
      for (String textValue : version.getRawValues()) {
        if (textValue.equalsIgnoreCase(raw)) {
          return version;
        }
      }
    }
    return null;
  }
}
