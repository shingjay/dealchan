package com.dealchan.backend.dealsource.adapter;

import com.dealchan.backend.dealsource.entity.DealSource;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: anbiniyar
 * Date: 2/28/12
 * Time: 10:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class DealSourceAdapterImpl implements DealSourceAdapter, ApplicationContextAware {
    
    private HashMap<Class, Properties> customMapping;
    private ApplicationContext context;
    
    public DealSourceAdapterImpl() {
        customMapping = new HashMap<Class, Properties>();
    }
    
    public synchronized void addCustomMapping(Class klazz, Properties methodMapping) {
        this.customMapping.put(klazz,methodMapping);
    }
    
    public DealSource create(Object object) {

        DealSource source = new DealSource();

        BeanUtils.copyProperties(object, source);

        // TargetClassAware to get base class

        // casting to TargetClassAware if it is not a BEAN!

        if(customMapping.containsKey(object.getClass())) {
            Properties properties = customMapping.get(object.getClass());
            
            for(String propertyName : properties.stringPropertyNames()) {
                String sourceMethod = propertyName;
                String destinationMethod = properties.getProperty(propertyName);
                
                try {
                    Object sourceValue = PropertyUtils.getSimpleProperty(object, sourceMethod);
                    PropertyUtils.setProperty(source,destinationMethod,sourceValue);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        }

        return source;
    }
    
    @Override
    public DealSource convert(Object object) {
        return create(object);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Collection<DealSource> convert(Collection objectList) {

        ArrayList<DealSource> dealSources = new ArrayList<DealSource>();
        
        for(Object obj : objectList) {
            dealSources.add(create(obj));
        }
        
        return dealSources;  //To change body of implemented methods use File | Settings | File Templates.
    }
    
    
    public static void main(String args[]) {
        DealSourceAdapterImpl dealSourceAdapter = new DealSourceAdapterImpl();

        Properties properties = new Properties();
        properties.setProperty("country", "city");

        dealSourceAdapter.addCustomMapping(DealSource.class, properties);

        DealSource source = new DealSource();
        source.setAddress("219 WAldron");
        source.setCountry("Malaysia");


        DealSource destination = dealSourceAdapter.create(source);
        System.out.println(destination.getAddress());
        System.out.println(destination.getCity());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
