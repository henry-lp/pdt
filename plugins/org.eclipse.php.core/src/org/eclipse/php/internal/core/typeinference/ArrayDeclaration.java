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
package org.eclipse.php.internal.core.typeinference;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.php.core.compiler.ast.nodes.ArrayCreation;
import org.eclipse.php.core.compiler.ast.nodes.Assignment;
import org.eclipse.php.core.compiler.ast.nodes.GlobalStatement;

/**
 * This is a container for array declaration
 * 
 */
public class ArrayDeclaration extends Declaration {
	private LinkedList<Declaration> decls = new LinkedList<>();

	public ArrayDeclaration(boolean global, ASTNode declNode) {
		super(global, declNode);
	}

	/**
	 * Adds possible variable declaration
	 * 
	 * @param declNode
	 *            AST declaration statement node
	 */
	public void addDeclaration(ASTNode declNode) {
		// add new declaration
		if (declNode instanceof Assignment && (((Assignment) declNode).getValue() instanceof ArrayCreation)) {
			decls.addLast(new ArrayDeclaration(declNode instanceof GlobalStatement, declNode));

		} else {
			decls.addLast(new Declaration(declNode instanceof GlobalStatement, declNode));
		}

	}

	public List<Declaration> getDeclarations() {
		return decls;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((decls == null) ? 0 : decls.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ArrayDeclaration other = (ArrayDeclaration) obj;
		if (decls == null) {
			if (other.decls != null) {
				return false;
			}
		} else if (!decls.equals(other.decls)) {
			return false;
		}
		return true;
	}

}
