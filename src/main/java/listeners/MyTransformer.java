package listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class MyTransformer implements IAnnotationTransformer{
	
	public void transform(ITestAnnotation myAnnotation, Class myClass, Constructor myConstructor, Method myMethod) {
		myAnnotation.setRetryAnalyzer(Retry.class);
	}

}
