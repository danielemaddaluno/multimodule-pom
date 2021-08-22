package io.multimodule.data.factory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.enterprise.inject.spi.CDI;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import io.multimodule.data.domain.utils.ReflectionUtils;

public class WeldJUnit4Runner extends BlockJUnit4ClassRunner {  
	  
    private final Class<?> klass;  
    private final Weld weld;  
    private final WeldContainer container;  
  
    public WeldJUnit4Runner(final Class<Object> klass) throws InitializationError {  
        super(klass);
        this.klass = klass;  
        this.weld = new Weld();  
        this.container = weld.initialize();
    }  
  
    @Override  
    protected Object createTest() throws Exception {  
        @SuppressWarnings("deprecation")
		final Object test = container.instance().select(klass).get(); 
        injectEjbs(test);
        return test;  
    }  
    
    private void injectEjbs(Object object) {
		injectEjbsWithMap(object, new HashMap<String, Object>());
	}

	/**
	 * @see https://stackoverflow.com/questions/24798529/how-to-programmatically-inject-a-java-cdi-managed-bean-into-a-local-variable-in
	 * for details about CDI injection
	 * @param object
	 * @param injectedEjbs
	 */
    private void injectEjbsWithMap(Object object, Map<String, Object> injectedEjbs) {
		Class<?> clazz = object.getClass();
		List<Field> fields = ReflectionUtils.getAllFieldsList(clazz);

		for (Field field : fields) {
			if (field.isAnnotationPresent(EJB.class)) {
				Class<?> klass = field.getType();
				String klassName = field.getType().getSimpleName();
				
				Object toInject = ReflectionUtils.getValueFromObject(object, field.getName(), klass);
				
				if(toInject == null) {
					if(injectedEjbs.containsKey(klassName)) {
						toInject = injectedEjbs.get(klassName);
					} else {
						toInject = CDI.current().select(klass).get();
						injectedEjbs.put(klassName, toInject);
						injectEjbsWithMap(toInject, injectedEjbs);
					}
					
					ReflectionUtils.setValueOnObject(object, field.getName(), toInject);
				}
			}
		}
	}
    
}  