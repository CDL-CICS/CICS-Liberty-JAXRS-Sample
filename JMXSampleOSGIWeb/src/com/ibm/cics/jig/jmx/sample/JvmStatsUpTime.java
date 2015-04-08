package com.ibm.cics.jig.jmx.sample;


import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;


public class JvmStatsUpTime {
	public void getUpTime() throws MalformedObjectNameException, AttributeNotFoundException, InstanceNotFoundException, MBeanException, ReflectionException{
	
	// Create an ObjectName object for the MBean that we're looking for.
	ObjectName beanObjName = new ObjectName("WebSphere:type=JvmStats");   

	// Obtain the full list of MBeanServers.   
	java.util.List servers = MBeanServerFactory.findMBeanServer(null);
	MBeanServer server = null ;     

	// Iterate through our list of MBeanServers and attempt to find the one that we want.   
	for (int i = 0; i < servers.size(); i++) 
	{      
	   // Check if the MBean domain matches what we're looking for.       
	   if (((MBeanServer)servers.get(i)).isRegistered(beanObjName)) 
	   {
	     server = (MBeanServer)servers.get(i);     
	   }   
	}

	Object attributeObj = server.getAttribute(beanObjName, "UpTime");
	System.out.println("UpTime of JVM is: " + attributeObj + ".");
	}
    

}
