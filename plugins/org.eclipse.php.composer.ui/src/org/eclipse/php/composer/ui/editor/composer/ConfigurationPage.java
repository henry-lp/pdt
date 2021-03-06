/*******************************************************************************
 * Copyright (c) 2012, 2016, 2017 PDT Extension Group and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     PDT Extension Group - initial API and implementation
 *     Kaloyan Raev - [501269] externalize strings
 *******************************************************************************/
package org.eclipse.php.composer.ui.editor.composer;

import org.eclipse.php.composer.ui.editor.ComposerFormPage;
import org.eclipse.php.composer.ui.editor.FormLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

public class ConfigurationPage extends ComposerFormPage {

	public static final String ID = "org.eclipse.php.composer.ui.editor.composer.ConfigurationPage"; //$NON-NLS-1$

	private ComposerFormEditor editor;

	private ConfigSection configSection;
	private ScriptsSection scriptsSection;
	private RepositoriesSection repositoriesSection;

	private Composite left;
	private Composite right;

	/**
	 * @param editor
	 * @param id
	 * @param title
	 */
	public ConfigurationPage(ComposerFormEditor editor, String id, String title) {
		super(editor, id, title);
		this.editor = editor;

	}

	@Override
	public void setActive(boolean active) {
		super.setActive(active);

		if (active) {
			editor.getHeaderForm().getForm().setText(Messages.ConfigurationPage_Title);
		}
	}

	@Override
	protected void createFormContent(IManagedForm managedForm) {
		// general config settings (bin & target-dir)
		// config
		// packages
		// scripts

		ScrolledForm form = managedForm.getForm();
		FormToolkit toolkit = managedForm.getToolkit();

		form.getBody().setLayout(FormLayoutFactory.createFormGridLayout(true, 2));

		left = toolkit.createComposite(form.getBody(), SWT.NONE);
		left.setLayout(FormLayoutFactory.createFormPaneGridLayout(false, 1));
		left.setLayoutData(new GridData(GridData.FILL_BOTH));

		configSection = new ConfigSection(this, left);
		configSection.setEnabled(enabled);

		scriptsSection = new ScriptsSection(this, left);
		scriptsSection.setEnabled(enabled);

		right = toolkit.createComposite(form.getBody(), SWT.NONE);
		right.setLayout(FormLayoutFactory.createFormPaneGridLayout(false, 1));
		right.setLayoutData(new GridData(GridData.FILL_BOTH));

		repositoriesSection = new RepositoriesSection(this, right);
		repositoriesSection.setEnabled(enabled);
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);

		if (configSection != null) {
			configSection.setEnabled(enabled);
			scriptsSection.setEnabled(enabled);
			repositoriesSection.setEnabled(enabled);
		}
	}
}
