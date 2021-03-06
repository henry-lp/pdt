/*******************************************************************************
 * Copyright (c) 2014 IBM Corporation and others.
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
package org.eclipse.php.internal.ui.preferences;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.php.internal.ui.IPHPHelpContextIds;

public class PHPBasePreferenceEmptyPage extends AbstractEmptyPreferencePage {

	public PHPBasePreferenceEmptyPage() {
		super();
	}

	public PHPBasePreferenceEmptyPage(String title) {
		super();
	}

	public PHPBasePreferenceEmptyPage(String title, ImageDescriptor image) {
		super();
	}

	@Override
	public String getBodyText() {
		return PreferencesMessages.PHPBasePreferencePage_body_text;
	}

	@Override
	protected String getPropertiesHelpId() {
		return IPHPHelpContextIds.PHP_PROJECT_PROPERTIES;
	}

	@Override
	protected String getPreferenceHelpId() {
		return IPHPHelpContextIds.PREFERENCES;
	}

}
