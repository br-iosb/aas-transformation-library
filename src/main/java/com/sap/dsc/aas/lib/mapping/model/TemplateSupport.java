/* 
  SPDX-FileCopyrightText: (C)2021 SAP SE or an affiliate company and aas-transformation-library contributors. All rights reserved. 

  SPDX-License-Identifier: Apache-2.0 
 */
package com.sap.dsc.aas.lib.mapping.model;

import java.util.Map;

public class TemplateSupport implements Template {

    private Object target;

    private BindSpecification bindSpecification;
    private Map<String, Object> variables;
    private Map<String, Object> definitions;

    public TemplateSupport() {
    }

    public TemplateSupport(Object target) {
        this.target = target;
    }

    protected void setTarget(Object target) {
        this.target = target;
    }

    protected Object getTarget() {
        return target;
    }

    @Override
    public BindSpecification getBindSpecification() {
        return bindSpecification;
    }

    @Override
    public void setBindSpecification(BindSpecification bindSpecification) {
        this.bindSpecification = bindSpecification;
    }

    @Override
    public Map<String, Object> getVariables() {
        return variables;
    }

    @Override
    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }

    @Override
    public Map<String, Object> getDefinitions() {
        return definitions;
    }

    @Override
    public void setDefinitions(Map<String, Object> definitions) {
        this.definitions = definitions;
    }
}
