package com.ibm.cics.sample.jaxrs;

import java.util.HashSet;
import java.util.Set;

public class ProductAppConfig extends javax.ws.rs.core.Application{
	
	public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(com.ibm.cics.sample.jaxrs.Product.class);
        return classes;
    }
}