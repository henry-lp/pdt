/*******************************************************************************
 * Copyright (c) 2015 Zend Technologies and others.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Zend Technologies - initial API and implementation
 *******************************************************************************/
package org.eclipse.php.internal.debug.ui.pathmapper;

import org.eclipse.core.runtime.Status;
import org.eclipse.jface.window.Window;
import org.eclipse.php.internal.core.util.SyncObject;
import org.eclipse.php.internal.debug.core.pathmapper.ILocalFileSearchFilter;
import org.eclipse.php.internal.debug.core.pathmapper.LocalFileSearchResult;
import org.eclipse.php.internal.debug.core.pathmapper.PathEntry;
import org.eclipse.php.internal.debug.core.pathmapper.VirtualPath;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

/**
 * Default implementation of local file search filter for opening remote file
 * content.
 * 
 * @author Bartlomiej Laczkowski
 */
public class OpenLocalFileSearchFilter implements ILocalFileSearchFilter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.php.internal.debug.core.pathmapper.ILocalFileSearchFilter#
	 * filter(org.eclipse.php.internal.debug.core.pathmapper.PathEntry[],
	 * org.eclipse.php.internal.debug.core.pathmapper.VirtualPath, java.lang.String)
	 */
	@Override
	public LocalFileSearchResult filter(final PathEntry[] entries, final VirtualPath remotePath,
			final String serverName) {
		final SyncObject<LocalFileSearchResult> searchResult = new SyncObject<>();
		PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
			@Override
			public void run() {
				final Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
				if (shell != null) {
					shell.forceActive();
				}
				MapLocalFileDialog dialog = new MapLocalFileDialog(shell, remotePath, entries);
				LocalFileSearchResult filteredResult;
				if (dialog.open() == Window.OK) {
					filteredResult = new LocalFileSearchResult(dialog.getResult());
				} else {
					filteredResult = new LocalFileSearchResult(null, Status.CANCEL_STATUS);
				}
				searchResult.set(filteredResult);
			}
		});
		return searchResult.get();
	}

}
