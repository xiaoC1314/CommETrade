package com.zhzx.uip.commons.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URI;

public class ContextUtils implements ApplicationContextAware, DisposableBean {
	
	private static ContextUtils _this=null;
	private static ApplicationContext _applicationContext=null;
	private String rootPath=null;
	private String contextPath=null;
	private String today=null;
	private HandlerInterceptorAdapter _intercepter=null;
	
	private ThreadLocal<HttpServletRequest> request = new ThreadLocal<HttpServletRequest>();
	private ThreadLocal<HttpServletResponse> response = new ThreadLocal<HttpServletResponse>();
	
	private ContextUtils(){}
	
	public static synchronized ContextUtils getInstance(){
		if(_this==null) _this=new ContextUtils();
		return _this;
	}
	
	public void _setApplicationContext(ApplicationContext context){
		_applicationContext=context;
	}
	
	/**
	 * 兼容非WebApplicaion情况下的Spring单元测试用
	 * @return ApplicationContext 
	 */
	public static ApplicationContext getApplicationContext(){
		return _applicationContext;
	}
	/**
	 * 实现了getServletContext()方法
	 * @return WebApplicationContext
	 */
	public static WebApplicationContext getWebApplicationContext(){
		return (_applicationContext instanceof WebApplicationContext)?(WebApplicationContext)_applicationContext:null;
	}
	
	/**
	 * 获取Spring的Bean对象
	 * @param beanName as String
	 * @return Object
	 */
	public static Object getBean(String beanName){
		Object obj=null;
		if(_applicationContext.containsBean(beanName)){
			obj = _applicationContext.getBean(beanName);
		}
		return obj;
	}
	
	public static <T> T getBean(Class<T> clazz){

		return _applicationContext.getBean(clazz);
	}

	public static <T> T getBean(String beanName,Class<T> clazz){
		T t=null;
		if(_applicationContext.containsBean(beanName))
			t=(T)_applicationContext.getBean(beanName,clazz);
		return t;
	}

	public final void _setRootPath(String path){
		_this.rootPath=path;
	}
	
	public final String getRootPath(){
		return _this.rootPath;
	}
	
	public final String getRealPath(String path) {
		File webRoot = new File(_this.rootPath);
		URI webRootUri = webRoot.toURI();
		URI uri = webRootUri.resolve(path);
		return uri.getPath();
	}
	
	public final void _setContextPath(String path){
		_this.contextPath=path;
	}
	
	public final String getContextPath(){
		return _this.contextPath;
	};
	
	/**
	 * 设置并初始化Jsp页面拦截器
	 * @param className as String
	 */
	public final void _setIntercepter(HandlerInterceptorAdapter inc){
		_intercepter=inc;
	}
	
	/**
	 * 获取Jsp页面拦截器
	 * @return 
	 */
	public final HandlerInterceptorAdapter getJspIntercepter(){
		return _this._intercepter;
	}
	
	/**
	 * 获取客户端ip地址
	 * @param request
	 * @return
	 */
	public final String getIpAddr(){
		String ip=_this.request.get().getHeader("x-forwarded-for");
		if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip))
			ip=_this.request.get().getHeader("Proxy-Client-IP");
		if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip))
			ip=_this.request.get().getHeader("WL-Proxy-Client-IP");
		if(ip==null || ip.length()==0 || "unknown".equalsIgnoreCase(ip))
			ip=_this.request.get().getRemoteAddr();
		return ip;
	}
	
	public final void _setServlet(HttpServletRequest request, HttpServletResponse response) {
		_this.request.set(request);
		_this.response.set(response);
	}
	
	public final HttpServletRequest getRequest(){
		return _this.request.get();
	}
	public static final HttpServletResponse getResponse(){
		return _this.response.get();
	}

	@Override
	public void destroy() throws Exception {
		_setApplicationContext(null);
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		_setApplicationContext(context);
	}

	public String getToday() {
		return _this.today;
	}

	public void _setToday(String today) {
		_this.today = today;
	}
	
}
