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
/**
 * 
 */
package org.eclipse.php.internal.ui.projectoutlineview;

import org.eclipse.dltk.internal.ui.scriptview.ScriptExplorerPart;
import org.eclipse.dltk.internal.ui.scriptview.ToggleLinkingAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.php.internal.ui.actions.NavigateActionGroup;
import org.eclipse.php.internal.ui.actions.PHPExplorerActionGroup;
import org.eclipse.php.internal.ui.actions.PHPRefactorActionGroup;
import org.eclipse.php.internal.ui.actions.ViewActionGroup;

/**
 * @zhaozw
 * 
 */
public class PHPProjectOutlineActionGroup extends PHPExplorerActionGroup {
	private PHPRefactorActionGroup phpRefactorActionGroup;
	private NavigateActionGroup fNavigateActionGroup;
	private ViewActionGroup fViewActionGroup;
	private CollapseAllAction fCollapseAllAction;

	private ToggleLinkingAction fToggleLinkingAction;

	public PHPProjectOutlineActionGroup(ScriptExplorerPart part) {
		super(part);
		fCollapseAllAction = new CollapseAllAction(part);
		fToggleLinkingAction = new ToggleLinkingAction(part);
	}

	@Override
	protected void fillToolBar(IToolBarManager toolBar) {

		toolBar.add(new Separator());
		toolBar.add(fCollapseAllAction);
		toolBar.add(fToggleLinkingAction);
	}
}
