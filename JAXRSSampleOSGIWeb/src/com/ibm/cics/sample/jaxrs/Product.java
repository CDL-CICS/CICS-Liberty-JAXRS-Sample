package com.ibm.cics.sample.jaxrs;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.ibm.cics.sample.jaxrs.DFH0XCMNCommarea.CaCatItem;
import com.ibm.cics.server.ChangedException;
import com.ibm.cics.server.DuplicateKeyException;
import com.ibm.cics.server.FileDisabledException;
import com.ibm.cics.server.FileNotFoundException;
import com.ibm.cics.server.IOErrorException;
import com.ibm.cics.server.ISCInvalidRequestException;
import com.ibm.cics.server.InvalidProgramIdException;
import com.ibm.cics.server.InvalidRequestException;
import com.ibm.cics.server.InvalidSystemIdException;
import com.ibm.cics.server.LengthErrorException;
import com.ibm.cics.server.LoadingException;
import com.ibm.cics.server.LockedException;
import com.ibm.cics.server.LogicException;
import com.ibm.cics.server.NotAuthorisedException;
import com.ibm.cics.server.NotOpenException;
import com.ibm.cics.server.Program;
import com.ibm.cics.server.RecordBusyException;
import com.ibm.cics.server.RecordNotFoundException;
import com.ibm.cics.server.RolledBackException;
import com.ibm.cics.server.TerminalException;
import com.ibm.cics.server.KSDS;
import com.ibm.cics.server.RecordHolder;

@javax.ws.rs.Path("/Product/")
public class Product {
	
	   
	   @javax.ws.rs.GET
	   @javax.ws.rs.Path("/ListAll")
	   @javax.ws.rs.Produces("application/json") 
	   @javax.ws.rs.Consumes("application/json") 
	    public List<CaCatItem> getProducts() throws InvalidRequestException, LengthErrorException, InvalidSystemIdException, NotAuthorisedException, InvalidProgramIdException, RolledBackException, TerminalException {
		    Program cobolProg = new Program();
		    cobolProg.setName("DFH0XVDS");
		    

		    DFH0XCMNCommarea aCommarea = new DFH0XCMNCommarea();
		    aCommarea.setCaRequestId("01INQC");

		    
		    //Link cobol program
		    cobolProg.link(aCommarea.getByteBuffer());
		    
		    CaCatItem item;
		    List<CaCatItem> catalogList = new ArrayList<CaCatItem>();
		    int n = aCommarea.getCaItemCount();
		    for(int i=0; i<n ;i++){
		      item = aCommarea.getCaCatItem(i);
		      catalogList.add(item); 
		    }
		    
	        return catalogList;
	    }
	   
	   
	   @javax.ws.rs.GET
	   @javax.ws.rs.Path("/List/{myRid}")
	   @javax.ws.rs.Produces("application/json") 
	   @javax.ws.rs.Consumes("application/json") 
	    public Catalog getProductItem(@javax.ws.rs.PathParam("myRid") String myRid) throws LogicException, InvalidRequestException, IOErrorException, InvalidSystemIdException, ChangedException, LockedException, LoadingException, RecordBusyException, FileDisabledException, DuplicateKeyException, FileNotFoundException, ISCInvalidRequestException, NotAuthorisedException, RecordNotFoundException, NotOpenException, UnsupportedEncodingException {
		   
		   // read the KSDS file
		   KSDS myCatalog = new KSDS();
		   myCatalog.setName("EXMPCAT");
		   RecordHolder myRecordHolder = new RecordHolder();
		   myCatalog.read( myRid.getBytes("037"), myRecordHolder);
		   Catalog myCatalogItem = new Catalog(myRecordHolder.getValue()); 
	       return myCatalogItem;
	    }
	   
	      
}
