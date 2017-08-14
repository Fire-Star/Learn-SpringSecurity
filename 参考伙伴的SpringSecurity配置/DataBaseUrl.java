package com.libo.server;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import com.libo.DAO.SysResources;

public class DataBaseUrl implements FilterInvocationSecurityMetadataSource,InitializingBean{
	
	private final static List<ConfigAttribute> NULL_CONFIG_ATTRIBUTE = Collections.emptyList();
	private Map<String, Collection<ConfigAttribute>> resultMap;
	private AntPathMatcher urlMatcher = new AntPathMatcher();
	@Resource
	private SysResourceService sysResourceService;
	private Logger logger = Logger.getLogger("File");
	
	public void setSysResourceService(SysResourceService sysResourceService) {
		this.sysResourceService = sysResourceService;
	}
	
	/**
	 * 根据request 的url 获取相应的auth
	 */
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		
		String url = ((FilterInvocation)object).getRequestUrl();
		
		if(url.contains("?")){
			url = url.substring(0,url.indexOf('?'));
		}
		
		if(resultMap.isEmpty()){
			this.resultMap = bindResquestMap();
		}
		
		Collection<ConfigAttribute> attributes = NULL_CONFIG_ATTRIBUTE;
		
		for(Map.Entry<String, Collection<ConfigAttribute>> entry : resultMap.entrySet()){
			logger.debug("[DataBaseUrl ----> getAttributes] url is "+url+", keyUrl : "+entry.getKey());
			if(urlMatcher.match(url, entry.getKey())){
				logger.debug("[DataBaseUrl ----> getAttributes] url is "+url+",   keyUrl : "+entry.getValue());
				attributes = entry.getValue();
				break;
			}
		}
		
		return attributes;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		
		Set<ConfigAttribute> configAttributes = new HashSet<ConfigAttribute>();
		for(Map.Entry<String, Collection<ConfigAttribute>> entry : resultMap.entrySet()){
			logger.debug("[DataBaseUrl ----> getAllConfigAttributes] url is "+entry.getKey()+", auth is "+entry.getValue());
			configAttributes.addAll(entry.getValue());
		}
		logger.debug("[DataBaseUrl ----> getAllConfigAttributes] "+configAttributes);
		return configAttributes;
	}
	
	public Map<String, String> loadResources(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		List<SysResources> resources = sysResourceService.loadResources();
		logger.debug("[DataBaseUrl ----> loadResources] resources is "+resources);
		if(resources != null && !resources.isEmpty()){
			for(SysResources resource : resources){
				logger.debug("[DataBaseUrl ----> loadResources] path is "+resource.getResourcePath());
				if(map.containsKey(resource.getResourcePath())){
					String mark = map.get(resource.getResourcePath());
					map.put(resource.getResourcePath(), mark+","+resource.getAuthName());
				}else {
					map.put(resource.getResourcePath(), resource.getAuthName());
				}
			}
		}
		
		return map;
	}
	
	public Map<String, Collection<ConfigAttribute>> bindResquestMap(){
		Map<String, String> sourceMap = this.loadResources();
		Map<String, Collection<ConfigAttribute>> resMap = new LinkedHashMap<String, Collection<ConfigAttribute>>();
		logger.info("[DataBaseUrl ----> bindResquestMap] sourceMap is "+sourceMap);
		for(Map.Entry<String, String> entry : sourceMap.entrySet()){
			String keyString = entry.getKey();
			Collection<ConfigAttribute> attr = new ArrayList<ConfigAttribute>();
			attr = SecurityConfig.createListFromCommaDelimitedString(entry.getValue());
			resMap.put(keyString, attr);
		}
		
		return resMap;
	}
	
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

	public void afterPropertiesSet() throws Exception {
		this.resultMap = this.bindResquestMap();
	}

}
