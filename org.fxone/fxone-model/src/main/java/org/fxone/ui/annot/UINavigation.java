package org.fxone.ui.annot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.TYPE})
public @interface UINavigation {
	int priority() default 0;
	boolean area() default false;
	String target();
	String perspective() default "default";
	String tree() default "default";
}
