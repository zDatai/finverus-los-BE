package com.zdatai.finverus.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Slf4j
@Configuration
@EnableTransactionManagement
@Aspect
public class TransactionConfig {
    private static final DefaultTransactionDefinition requiredReadCommitted = new DefaultTransactionDefinition(Propagation.REQUIRED.value());
    private static final DefaultTransactionDefinition requiredSerializable = new DefaultTransactionDefinition(Propagation.REQUIRED.value());
    private static final DefaultTransactionDefinition supportReadCommitted = new DefaultTransactionDefinition(Propagation.SUPPORTS.value());
    static {
        requiredReadCommitted.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        requiredReadCommitted.setTimeout(60);

        supportReadCommitted.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
        supportReadCommitted.setTimeout(60);

        requiredSerializable.setIsolationLevel(TransactionDefinition.ISOLATION_SERIALIZABLE);
        requiredSerializable.setTimeout(60);
    }

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Around("execution(* com.zdatai.finverus.service.impl..*.create*(..))")
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Object applyCreateTransaction(ProceedingJoinPoint joinPoint)throws Throwable{
        TransactionStatus status = transactionManager.getTransaction(requiredReadCommitted);
        try {
            Object result = joinPoint.proceed();
            transactionManager.commit(status);
            return result;
        } catch (Exception ex) {
            log.error("### Transaction will Rolle Back : {} ###",ex.getMessage());
            transactionManager.rollback(status);
            throw ex;
        }
    }

    @Around("execution(* com.zdatai.finverus.service.impl..*.update*(..))")
//	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Object applyUpdateTransaction(ProceedingJoinPoint joinPoint)throws Throwable{
        TransactionStatus status = transactionManager.getTransaction(requiredReadCommitted);
        try {
            Object result = joinPoint.proceed();
            transactionManager.commit(status);
            return result;
        } catch (Exception ex) {
            log.error("### Transaction will Rolle Back : {} ###",ex.getMessage());
            transactionManager.rollback(status);
            throw ex;
        }
    }

    @Around("execution(* com.zdatai.finverus.service.impl..*.delete*(..))")
//	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public Object applyDeleteTransaction(ProceedingJoinPoint joinPoint)throws Throwable{
        TransactionStatus status = transactionManager.getTransaction(requiredSerializable);
        try {
            Object result = joinPoint.proceed();
            transactionManager.commit(status);
            return result;
        } catch (Exception ex) {
            log.error("### Transaction will Rolle Back : {} ###",ex.getMessage());
            transactionManager.rollback(status);
            throw ex;
        }
    }

    @Around("execution(* com.zdatai.finverus.service.impl..*.get*(..))")
//	@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED)
    public Object applyGetTransaction(ProceedingJoinPoint joinPoint)throws Throwable{
        TransactionStatus status = transactionManager.getTransaction(supportReadCommitted);
        try {
            Object result = joinPoint.proceed();
            transactionManager.commit(status);
            return result;
        } catch (Exception ex) {
            log.error("### Transaction will Rolle Back : {} ###",ex.getMessage());
            transactionManager.rollback(status);
            throw ex;
        }
    }
}
