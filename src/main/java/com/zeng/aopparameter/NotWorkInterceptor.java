package com.zeng.aopparameter;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class NotWorkInterceptor implements MethodInterceptor {
    public Object invoke(MethodInvocation invocation) throws Throwable {
        int test = 2;
        if (test == 2) {
            throw new IllegalStateException(
                    invocation.getMethod().getName() + " not allowed because it is 2!");
        }
        return invocation.proceed();
    }
}
