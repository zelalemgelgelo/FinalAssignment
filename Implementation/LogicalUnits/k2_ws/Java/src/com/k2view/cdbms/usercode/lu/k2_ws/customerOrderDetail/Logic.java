/////////////////////////////////////////////////////////////////////////
// Project Web Services
/////////////////////////////////////////////////////////////////////////

package com.k2view.cdbms.usercode.lu.k2_ws.customerOrderDetail;

import java.util.*;
import java.sql.*;
import java.math.*;
import java.io.*;

import com.k2view.cdbms.shared.*;
import com.k2view.cdbms.shared.user.WebServiceUserCode;
import com.k2view.cdbms.sync.*;
import com.k2view.cdbms.lut.*;
import com.k2view.cdbms.shared.utils.UserCodeDescribe.*;
import com.k2view.cdbms.shared.logging.LogEntry.*;
import com.k2view.cdbms.func.oracle.OracleToDate;
import com.k2view.cdbms.func.oracle.OracleRownum;
import com.k2view.cdbms.usercode.lu.k2_ws.*;
import com.k2view.fabric.api.endpoint.Endpoint.*;

import static com.k2view.cdbms.shared.utils.UserCodeDescribe.FunctionType.*;
import static com.k2view.cdbms.shared.user.ProductFunctions.*;
import static com.k2view.cdbms.usercode.common.SharedLogic.*;
import static com.k2view.cdbms.usercode.common.SharedGlobals.*;

@SuppressWarnings({"unused", "DefaultAnnotationParam"})
public class Logic extends WebServiceUserCode {


	@desc("return the customer orderdetail")
	@webService(path = "customer/OrderInfo", verb = {MethodType.GET, MethodType.POST, MethodType.PUT, MethodType.DELETE}, version = "1", isRaw = false, produce = {Produce.XML, Produce.JSON})
	public static Object wsCustomerOrder(String customer_id) throws Exception {
		/**
		 * creating a ws service for select all customer's order and fetching the customer order
		 */
		if (customer_id != null && customer_id.length() > 0) {
			String sqlForOrders = "SELECT contract_id, order_id, order_type, order_date, order_status FROM orders";
			fabric().execute("get CustomerLUT.?", customer_id);
			Db.Rows rows = db("fabric").fetch(sqlForOrders);
			/**
			 * separate file generated for IID
			 * If a file already exists for a specific IID, a new file should be generated with the relevant
			 sequence number.
			 */
			//			System.getProperty("user.dir");
			int SequenceNumberCounter = 1;
			String filePath = "C:\\Users\\ZelalemZergawGelgelo\\Documents\\K2View Fabric Studio\\FinalAssignment\\JsonFile\\JSONFile";
			String fileDir = filePath  + customer_id + SequenceNumberCounter + ".txt";
			//save it in the file
			File createFile = new File(fileDir);
			while (createFile.exists()) {
				SequenceNumberCounter++;
				fileDir = filePath+customer_id+ "_"  + SequenceNumberCounter + ".txt";
				createFile = new File(fileDir);
			}
			/**
			 * Each record should be converted to a json format and inserted into a file.
			 */
			Writer jsonWriter = new StringWriter();
			Utils.writeJSON(rows, jsonWriter);
			try {
				FileWriter writer = new FileWriter(createFile);
				writer.write(jsonWriter.toString());
		//				writer.flush();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			log.info("File recorded");
			return "File is successfully uploaded";
		}else{
			return "Customer Id is not available!";
		}
	}

	
	

	
}
