package com.gzq.learn.jdk8.lambda.demo;

@FunctionalInterface
public interface MyPredicate<T> {

	public boolean test(T t);
	
}
