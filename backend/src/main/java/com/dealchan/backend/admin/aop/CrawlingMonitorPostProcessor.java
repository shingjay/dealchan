//package com.dealchan.backend.admin.aop;
//
//import com.dealchan.backend.dealsites.DealSiteService;
//import com.dealchan.backend.dealsource.adapter.DealSourceAdapter;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanDefinition;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Created by IntelliJ IDEA.
// * User: anbiniyar
// * Date: 2/20/12
// * Time: 3:34 AM
// * To change this template use File | Settings | File Templates.
// */
//@Configuration
//@Deprecated
//public class CrawlingMonitorPostProcessor implements BeanDefinitionRegistryPostProcessor {
//
//    @Override
//    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
//         System.out.println(registry.getClass().getCanonicalName() + " BEEN CALLED");
//    }
//
//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//        System.out.println(beanFactory.getClass().getCanonicalName() + " BEEN CALLED");
//    }
//
//    // I assume no duplicates
//    private void createCrawlingServicesBasedOnBeanNames(ConfigurableListableBeanFactory beanFactory) {
//
//        String[] dealSiteServiceBeans = beanFactory.getBeanNamesForType(DealSiteService.class);
//        String[] dealSiteDealSourceAdapterBeans = beanFactory.getBeanNamesForType(DealSourceAdapter.class);
//
//        HashMap<String, String> dealSiteServiceClass = stripAndGet(dealSiteServiceBeans, "Service", beanFactory);
//        HashMap<String, String> dealSiteDealSourceAdapterClass = stripAndGet(dealSiteServiceBeans, "DealSourceAdapter", beanFactory);
//
//        // now to actually hotwire
//        for(Map.Entry<String,String> entry : dealSiteServiceClass.entrySet()) {
//
//            String dealSiteServiceClassName             = entry.getValue();
//            String dealSiteDealSourceAdapterClassName   = dealSiteDealSourceAdapterClass.get(entry.getKey());
//
//            if(dealSiteDealSourceAdapterClassName != null) {
//               // CrawlingServiceProxy proxy = new CrawlingServiceProxy();
//               // beanFactory.registerSingleton(entry.getKey() + "CrawlingService", );
//            }
//        }
//
//    }
//
//    private HashMap<String,String> stripAndGet(String[] items, String textToStrip, ConfigurableListableBeanFactory beanFactory) {
//        // strip bean names
//        HashMap<String,String> cleanedClassName = new HashMap<String, String>();
//
//
//        for(String item : items) {
//            BeanDefinition definition = beanFactory.getBeanDefinition(item);
//            String className = definition.getBeanClassName();
//
//            String[] canonicalClassName = className.split(".");
//
//            // get the simple class name
//            String simpleClassName = canonicalClassName[canonicalClassName.length - 1];
//
//            // strip the service out
//            int index = simpleClassName.lastIndexOf(textToStrip);
//            String serviceIdentifier = simpleClassName.substring(0, index);
//
//            cleanedClassName.put(serviceIdentifier, className);
//        }
//
//        return cleanedClassName;
//    }
//}
