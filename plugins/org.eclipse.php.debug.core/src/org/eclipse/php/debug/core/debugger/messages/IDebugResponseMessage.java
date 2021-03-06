/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Zend Technologies
 *******************************************************************************/
package org.eclipse.php.debug.core.debugger.messages;

public interface IDebugResponseMessage extends IDebugMessage {

	/**
	 * Set the response id.
	 */
	public void setID(int id);

	/**
	 * Return the response id.
	 */
	public int getID();

	/**
	 * Set the response status.
	 */
	public void setStatus(int status);

	/**
	 * Return the response status.
	 */
	public int getStatus();
}
