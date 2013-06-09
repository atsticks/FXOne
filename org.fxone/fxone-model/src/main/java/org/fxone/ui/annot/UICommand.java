package org.fxone.ui.annot;

public @interface UICommand {
	
	String value();

	String tree() default "default";

	String before() default "";

	String after() default "";

	boolean enabled() default true;
}
