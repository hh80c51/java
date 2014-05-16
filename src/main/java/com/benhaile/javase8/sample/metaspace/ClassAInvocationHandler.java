package com.benhaile.javase8.sample.metaspace;

/*
 * #%L
 * javase8-sample
 * %%
 * Copyright (C) 2013 - 2014 benhaile
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * ClassAInvocationHandler
 * @author Pierre-Hugues Charbonneau
 *
 */
public class ClassAInvocationHandler implements InvocationHandler {
	
	private Object classAImpl;
	 
	public ClassAInvocationHandler(Object impl) {
	   this.classAImpl = impl;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
	   
		if(Object.class  == method.getDeclaringClass()) {
	       String name = method.getName();
	       if("equals".equals(name)) {
	           return proxy == args[0];
	       } else if("hashCode".equals(name)) {
	           return System.identityHashCode(proxy);
	       } else if("toString".equals(name)) {
	           return proxy.getClass().getName() + "@" +
	               Integer.toHexString(System.identityHashCode(proxy)) +
	               ", with InvocationHandler " + this;
	       } else {
	           throw new IllegalStateException(String.valueOf(method));
	       }
	   }
	   
	   return method.invoke(classAImpl, args);
	}
}
