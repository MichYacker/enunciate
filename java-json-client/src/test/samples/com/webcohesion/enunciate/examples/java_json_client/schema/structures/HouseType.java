package com.webcohesion.enunciate.examples.java_json_client.schema.structures;

import com.webcohesion.enunciate.metadata.qname.XmlQNameEnum;
import com.webcohesion.enunciate.metadata.qname.XmlUnknownQNameEnumValue;

/**
 * @author Ryan Heaton
 */
@XmlQNameEnum(base = XmlQNameEnum.BaseType.URI)
public enum HouseType {

  brick,

  wood,

  mud,

  @XmlUnknownQNameEnumValue
  unknown
}
