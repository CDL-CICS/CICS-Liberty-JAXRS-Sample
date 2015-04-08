<%@ page pageEncoding="utf-8" contentType="text/html;charset=utf-8" import="com.ibm.cics.jig.jmx.sample.JvmStatsUpTime;"%>
<% 
   JvmStatsUpTime aJvmStats = new JvmStatsUpTime() ;
   aJvmStats.getUpTime();
%>