package com.webcohesion.enunciate.modules.jaxb.api.impl;

import com.webcohesion.enunciate.api.InterfaceDescriptionFile;
import com.webcohesion.enunciate.api.datatype.DataType;
import com.webcohesion.enunciate.api.datatype.Namespace;
import com.webcohesion.enunciate.facets.FacetFilter;
import com.webcohesion.enunciate.modules.jaxb.model.ComplexTypeDefinition;
import com.webcohesion.enunciate.modules.jaxb.model.EnumTypeDefinition;
import com.webcohesion.enunciate.modules.jaxb.model.SchemaInfo;
import com.webcohesion.enunciate.modules.jaxb.model.TypeDefinition;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ryan Heaton
 */
public class NamespaceImpl implements Namespace {

  private final SchemaInfo schema;

  public NamespaceImpl(SchemaInfo schema) {
    this.schema = schema;
  }

  @Override
  public String getUri() {
    String ns = this.schema.getNamespace();
    return ns == null ? "" : ns;
  }

  @Override
  public InterfaceDescriptionFile getSchemaFile() {
    return this.schema.getSchemaFile();
  }

  @Override
  public List<? extends DataType> getTypes() {
    FacetFilter facetFilter = this.schema.getContext().getContext().getConfiguration().getFacetFilter();

    ArrayList<DataType> dataTypes = new ArrayList<DataType>();
    for (TypeDefinition typeDefinition : this.schema.getTypeDefinitions()) {
      if (!facetFilter.accept(typeDefinition)) {
        continue;
      }
      
      if (typeDefinition instanceof ComplexTypeDefinition) {
        dataTypes.add(new ComplexDataTypeImpl((ComplexTypeDefinition) typeDefinition));
      }
      else if (typeDefinition instanceof EnumTypeDefinition) {
        dataTypes.add(new EnumDataTypeImpl((EnumTypeDefinition) typeDefinition));
      }
    }
    return dataTypes;
  }
}
