/////////////////////////////////////////////////////////////////////////
// Project Web Services
/////////////////////////////////////////////////////////////////////////

package com.k2view.cdbms.usercode.lu.k2_ws.cassandraWs;

import java.util.*;

import com.datastax.driver.core.SimpleStatement;
import com.datastax.driver.core.Statement;
import com.k2view.cdbms.shared.user.WebServiceUserCode;
import com.k2view.cdbms.shared.utils.UserCodeDescribe.*;
import com.k2view.cdbms.usercode.common.batchExecution.BatchExecution;
import com.k2view.fabric.api.endpoint.Endpoint.*;

@SuppressWarnings({"unused", "DefaultAnnotationParam"})
public class Logic extends WebServiceUserCode {


    @desc("Cassandra batch process web service")
    @webService(path = "cassandraBatchWs", verb = {MethodType.GET, MethodType.POST, MethodType.PUT, MethodType.DELETE}, version = "1", isRaw = false, produce = {Produce.XML, Produce.JSON})
    public static String wsCassandraBatchClass(String cassandraQueries) throws Exception {
        String message="query is executed successfully!";
        List<String> cqlStatement = Arrays.asList(cassandraQueries.split(";"));

        int sizeOfStatement = cqlStatement.size();
        Statement statement;

        //create new List of simple statements to add the statements
        List<Statement> statementList = new ArrayList<>();
        for (int stat = 0; stat < sizeOfStatement; stat++) {
            statement = new SimpleStatement(cqlStatement.get(stat));
            statementList.add(statement);
        }
        try {
        //  create batch execution object in order to access the methods inside it
            BatchExecution batchExecution = new BatchExecution();
        //  invoke init method to instantiate
            batchExecution.init();
        //  invoke addStatement method to execute the cql statementd
            batchExecution.addStatement(statementList);
        //  invoke the execute method to execute the
            batchExecution.execute();
        //  invoke the clear method
            batchExecution.clearBatch();

        } catch (Exception exep) {
            exep.getLocalizedMessage();
        }

        return message;
    }


}
