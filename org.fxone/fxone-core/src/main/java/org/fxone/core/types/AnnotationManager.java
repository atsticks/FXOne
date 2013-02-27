package org.fxone.core.types;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.scannotation.AnnotationDB;
import org.scannotation.ClasspathUrlFinder;

public final class AnnotationManager {

	private AnnotationDB annotationDB = createEmptyDB();
	private AnnotationDB annotationDBMethodOnly = createEmptyDB();
	private AnnotationDB annotationDBClassOnly = createEmptyDB();

	private static String[] ignoredPackages = new String[] { "javax", "java",
			"sun", "com.sun", "javassist", "org.jboss", "org.eclipse",
			"org.apache", "org.homemotion.dao" };

	private static AnnotationManager INSTANCE = new AnnotationManager();

	private AnnotationManager() {
		try {
			Logger.getLogger(getClass()).info("Scanning annotations...");
			URL[] urls = ClasspathUrlFinder.findClassPaths();
			annotationDB.scanArchives(urls);
			annotationDBClassOnly.setScanClassAnnotations(true);
			annotationDBClassOnly.setScanFieldAnnotations(false);
			annotationDBClassOnly.setScanMethodAnnotations(false);
			annotationDBClassOnly.setScanParameterAnnotations(false);
			annotationDBClassOnly.scanArchives(urls);
			annotationDBMethodOnly.setScanClassAnnotations(false);
			annotationDBMethodOnly.setScanFieldAnnotations(false);
			annotationDBMethodOnly.setScanMethodAnnotations(true);
			annotationDBMethodOnly.setScanParameterAnnotations(false);
			annotationDBMethodOnly.scanArchives(urls);
		} catch (IOException e) {
			Logger.getLogger(getClass())
					.error("Failed to load annotations.", e);
		}
	}

	private AnnotationDB createEmptyDB() {
		AnnotationDB annotationDB = new AnnotationDB();
		annotationDB.setIgnoredPackages(ignoredPackages);
		return annotationDB;
	}

	public static Map<String, Set<String>> getAnnotationIndex() {
		return INSTANCE.annotationDB.getAnnotationIndex();
	}

	public static Set<String> getClassAnnotations(String classname) {
		return INSTANCE.annotationDB.getClassIndex().get(classname);
	}

	public static Set<String> getAnnotatedClasses(String annotation) {
		Map<String, Set<String>> index = INSTANCE.annotationDBClassOnly
				.getAnnotationIndex();
		return index.get(annotation);
	}

	public static Set<String> getMethodAnnotatedClasses(String annotation) {
		Map<String, Set<String>> index = INSTANCE.annotationDBMethodOnly
				.getAnnotationIndex();
		return index.get(annotation);
	}

	public static void printAnnotations() {
		System.out.println("***** Homemotion Annotations found. *****");
		System.out.println("  - ignored packages: "
				+ Arrays.toString(AnnotationManager.ignoredPackages));
		INSTANCE.annotationDB
				.outputAnnotationIndex(new PrintWriter(System.out));
	}

	public static void main(String[] args) {
		AnnotationManager.printAnnotations();
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
