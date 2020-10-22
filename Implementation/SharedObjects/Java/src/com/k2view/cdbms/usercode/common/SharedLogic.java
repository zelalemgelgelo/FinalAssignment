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
		String storageLocation= "C:\\Users\\ZelalemZergawGelgelo\\Documents\\K2View Fabric Studio\\FinalAssignment\\FabricHome\\storage";

		File folderDirectory = new File(storageLocation);
		/**
		 * get the total space
		 */
		float storageTotalSpace=folderDirectory.getTotalSpace();
//		float storageUsedSpace=folderDirectory.getUsableSpace();
		float storageFreeSpace=folderDirectory.getFreeSpace();
		
		if(storageFreeSpace<storageTotalSpace*(0.5)){
			//invoke the method to send email
			JavaMailSender.sendMail("zola145103@gmail.com");
		}
	}


	
}
