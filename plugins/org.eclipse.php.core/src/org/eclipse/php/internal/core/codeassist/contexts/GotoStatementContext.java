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
package org.eclipse.php.internal.core.codeassist.contexts;

import org.eclipse.dltk.annotations.NonNull;
import org.eclipse.dltk.core.CompletionRequestor;
import org.eclipse.dltk.core.IModelElement;
import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.php.core.PHPVersion;

public class GotoStatementContext extends StatementContext {

	private static final String GOTO_KEYWORD = "goto"; //$NON-NLS-1$

	private IModelElement currentElement;

	@Override
	public boolean isValid(@NonNull ISourceModule sourceModule, int offset, CompletionRequestor requestor) {
		if (!super.isValid(sourceModule, offset, requestor)) {
			return false;
		}
		if (getCompanion().getPHPVersion().isLessThan(PHPVersion.PHP5_3)) {
			return false;
		}
		try {
			if (GOTO_KEYWORD.equalsIgnoreCase(getPreviousWord())) {
				currentElement = getEnclosingElement();
				return true;
			}
		} catch (BadLocationException e) {
		}
		return false;
	}

	public IModelElement getCurrentElement() {
		return currentElement;
	}

}
