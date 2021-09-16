package com.ignitedev.devsprotocolfetcher.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.Nullable;

@RequiredArgsConstructor
@Getter
public enum MetaDataFieldType {

  FLOAT(Float.class, new String[]{"float"}),
  VARINT(Integer.class, new String[]{"int", "integer", "varint"}),
  BOOLEAN(Boolean.class, new String[]{"boolean", "bool"}),
  OBJECT(Object.class, new String[]{"object", "obj", "unknown"}),
  BYTE(byte.class, new String[]{"byte"}),
  STRING(String.class, new String[]{"string", "text", "str"}),
  BLOCK_POSITION(Object.class, new String[]{"blockpos", "blockposition"}),
  UUID(java.util.UUID.class, new String[]{"uuid", "uid"}),
  PARTICLE(Object.class, new String[]{"particle"});

  private final Class<?> fieldTypeClass;
  private final String[] textValues;


  @Nullable
  public MetaDataFieldType getEnumByString(String string) {
    for (MetaDataFieldType value : values()) {
      for (String textValue : value.getTextValues()) {
        if (textValue.equalsIgnoreCase(string)) {
          return value;
        }
      }
    }
    return null;
  }

}
