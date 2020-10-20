package com.k2view.cdbms.usercode.common.batchExecution;

import com.datastax.driver.core.*;
import com.datastax.driver.core.exceptions.*;

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
     * @param params
     * @throws IllegalArgumentException
     * @throws InvalidTypeException
     * @throws NullPointerException
     */
    public void addStatement(List<Statement> params) throws IllegalArgumentException, InvalidTypeException, NullPointerException {
     //add each input statements in our bach statement
      for(int i=0;i<params.size();i++ ){
          bs.add(params.get(i));
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
