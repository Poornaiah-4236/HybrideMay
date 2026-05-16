package com.Listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.annotations.ITestAnnotation;
import org.testng.IAnnotationTransformer;

public class AnnotationTransformer implements IAnnotationTransformer{
	@Override
    public void transform(ITestAnnotation annotation, Class testClass, 
                         Constructor testConstructor, Method testMethod) {
        // This sets your RetryListener for every test automatically
        annotation.setRetryAnalyzer(RetryListener.class);
    }
}
