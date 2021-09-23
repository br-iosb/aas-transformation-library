/* Copyright (C)2021 SAP SE or an affiliate company and aas-transformation-library contributors. All rights reserved. */
package com.sap.dsc.aas.lib.aml.config.pojo;

import java.util.List;

public class ConfigAmlToAas {

    private String version;
    private String aasVersion;
    private List<ConfigMapping> configMappings;
    private List<Precondition> preconditions;
    private List<ConfigPlaceholder> placeholders;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAasVersion() {
        return aasVersion;
    }

    public void setAasVersion(String aasVersion) {
        this.aasVersion = aasVersion;
    }

    public List<ConfigMapping> getConfigMappings() {
        return configMappings;
    }

    public void setConfigMappings(List<ConfigMapping> configMappings) {
        this.configMappings = configMappings;
    }

    public List<Precondition> getPreconditions() {
        return preconditions;
    }

    public void setPreconditions(List<Precondition> preconditions) {
        this.preconditions = preconditions;
    }

    public List<ConfigPlaceholder> getPlaceholders() {
        return placeholders;
    }

    public void setPlaceholders(List<ConfigPlaceholder> placeholders) {
        this.placeholders = placeholders;
    }
}
