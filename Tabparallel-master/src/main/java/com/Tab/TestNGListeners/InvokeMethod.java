package com.Tab.TestNGListeners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class InvokeMethod implements IInvokedMethodListener
{

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult)
	{
	  
	//	  var browserName = method.getTestMethod().getXmlTest();
          System.out.println(method.getTestMethod());
          System.out.println(method.getTestResult());
          System.out.println(testResult.getInstanceName());
          System.out.println(testResult.getName());
           
         
		
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult)
	{
		System.out.println("Thread id = " + Thread.currentThread().getId());
        System.out.println("Hashcode of webDriver instance = ") ;
		
	}
}
