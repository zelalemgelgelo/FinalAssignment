package com.k2view.cdbms.usercode.common.JobFunction;

import com.datastax.driver.core.*;
import com.datastax.driver.core.exceptions.*;
import java.util.ArrayList;
import java.util.List;

public class BatchExecution {
    private Session ses;
    private BatchStatement bs;

    /**
     * initialization attributes method/function
     */
    public void init(){
      //log.info("This is from init in class: " + this.getClass().getName());
        ses= com.k2view.cdbms.cluster.CassandraClusterSingleton.getInstance().getSession();
        bs= new BatchStatement();
    }

    /**
     * add statement method/ function
     * @param statement
     * @throws IllegalArgumentException
     * @throws NullPointerException
     */
    public void addStatement(List<SimpleStatement> statement) throws IllegalArgumentException, NullPointerException {
     //add each input statements in our bach statement
      for(int i=0;i<statement.size();i++ ){
          bs.add(statement.get(i));
      }
    }
    /**
     * execution method/ function
     * @throws NoHostAvailableException
     * @throws QueryExecutionException
     * @throws QueryValidationException
     * @throws UnsupportedFeatureException
     */
    public void execute() throws NoHostAvailableException, QueryExecutionException, QueryValidationException, UnsupportedFeatureException {
        ses.execute(bs);
    }

    /**
     * clear the batch after execution
     */
    public void clearBatch() {
        bs.clear();
    }

}
