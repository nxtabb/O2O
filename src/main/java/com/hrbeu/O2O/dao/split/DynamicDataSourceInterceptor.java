package com.hrbeu.O2O.dao.split;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Locale;
import java.util.Properties;
@Intercepts({@Signature(type = Executor.class,method = "update",args = {MappedStatement.class,Object.class}),
        @Signature(type = Executor.class,method = "query",args = {MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class})})
public class DynamicDataSourceInterceptor implements Interceptor {
    private static final String REGEX = ".*insert\\u0020.*|.*delete\\u0020.*|.*update\\u0020.*";
    private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //判断是否是支持事务的
        String lookupKey  = DynamicDataSourceHolder.DB_master;
        boolean synchronizationActive = TransactionSynchronizationManager.isActualTransactionActive();
        MappedStatement ms = null;
        if(!synchronizationActive){
            Object[] objects = invocation.getArgs();
            ms  = (MappedStatement) objects[0];
            //如果是读方法
            if(ms.getSqlCommandType().equals(SqlCommandType.SELECT)){
                //如果select key为自增id查询主键，使用主库
                if(ms.getId().contains(SelectKeyGenerator.SELECT_KEY_SUFFIX)){
                    lookupKey =DynamicDataSourceHolder.DB_master;
                }
                else {
                    BoundSql boundSql = ms.getSqlSource().getBoundSql(objects[1]);
                    String sql = boundSql.getSql().toLowerCase(Locale.CHINA).replace("[\\t\\n\\r]"," ");
                    if(sql.matches(REGEX)){
                        lookupKey = DynamicDataSourceHolder.DB_master;
                    }else {
                        lookupKey = DynamicDataSourceHolder.Db_slave;
                    }
                }
            }
        }
        else {
            lookupKey = DynamicDataSourceHolder.DB_master;
        }
        logger.debug("设置方法[{}] use[{}] Strategy,SqlCommonType[{}]..",ms.getId(),lookupKey,ms.getSqlCommandType().name());
        DynamicDataSourceHolder.setDbType(lookupKey);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        //如果拦截的对象属于Executor类型，则进行拦截，并进行代理加功能
        //Excutor代表mybatis里的增删改查操作
        if(target instanceof Executor){
            return Plugin.wrap(target,this);
        }
        //否则直接返回
        else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
