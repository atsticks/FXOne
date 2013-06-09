package org.fxone.ui.annot;


public @interface UISeparator {
	String value();
	String tree() default "default";
	String before() default "";
	String after() default "";
}
