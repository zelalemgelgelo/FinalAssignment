/////////////////////////////////////////////////////////////////////////
// Project Shared Functions
/////////////////////////////////////////////////////////////////////////

package com.k2view.cdbms.usercode.common;

import java.util.*;
import java.sql.*;
import java.math.*;
import java.io.*;

import com.k2view.cdbms.shared.*;
import com.k2view.cdbms.shared.user.UserCode;
import com.k2view.cdbms.sync.*;
import com.k2view.cdbms.lut.*;
import com.k2view.cdbms.shared.logging.LogEntry.*;
import com.k2view.cdbms.func.oracle.OracleToDate;
import com.k2view.cdbms.func.oracle.OracleRownum;
import com.k2view.cdbms.shared.utils.UserCodeDescribe.*;

import static com.k2view.cdbms.shared.user.ProductFunctions.*;
import static com.k2view.cdbms.shared.user.UserCode.*;
import static com.k2view.cdbms.shared.utils.UserCodeDescribe.FunctionType.*;
import static com.k2view.cdbms.usercode.common.SharedGlobals.*;
import com.k2view.fabric.fabricdb.datachange.TableDataChange;

@SuppressWarnings({"unused", "DefaultAnnotationParam"})
public class SharedLogic {


	@desc("user job mail sender")
	@category("Utilities")
	@type(UserJob)
	public static void fabricStorageFunction() throws Exception {
		String folderLocation= "C:\\Users\\ZelalemZergawGelgelo\\Documents\\K2View Fabric Studio\\FinalAssignment\\FabricHome\\storage";
		
		File folderDirectory = new File(folderLocation);
		/**
		 * check the directory exist
		 * if it doesn't exist create it
		 */
		if(!folderDirectory.exists()){
			log.error("The directory doesn't exist");
		}
		
		/**
		 * if the directory exist
		 * get the total space
		 */
		float storageTotalSpace=folderDirectory.getTotalSpace();
		
		float storageFreeSpace=folderDirectory.getFreeSpace();
		
		if(storageFreeSpace<storageTotalSpace/2){
			//invoke the method to send email
			JavaMailSender.sendMail("zola145103@gmail.com");
		}
	}


	
}
