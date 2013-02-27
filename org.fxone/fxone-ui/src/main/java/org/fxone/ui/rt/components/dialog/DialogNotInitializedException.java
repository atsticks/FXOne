/************************************************************************
 * 
 * Copyright (C) 2010 - 2012
 *
 * [DialogNotInitializedException.java]
 * AHCP Project (http://jacp.googlecode.com)
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 *
 *     http://www.apache.org/licenses/LICENSE-2.0 
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either 
 * express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 *
 *
 ************************************************************************/
package org.fxone.ui.rt.components.dialog;

/**
 * The Class DialogNotInitializedException.
 * 
 * @author Patrick Symmangk
 */
public class DialogNotInitializedException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -343178301862250777L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return "Initialize JACPDialog before using it -> Call initJACPDialog()";
	}

}
