/*******************************************************************************
 * Copyright (c) 2005, 2015 Zend Technologies and others.
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
package org.eclipse.php.refactoring.core.rename;

import static org.junit.Assert.assertNotNull;

import org.eclipse.core.resources.IFile;
import org.eclipse.php.core.tests.TestUtils;
import org.eclipse.php.core.tests.runner.PDTTList;
import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.core.ast.nodes.ASTNode;
import org.eclipse.php.core.ast.nodes.Program;
import org.eclipse.php.refactoring.core.test.AbstractPDTTListRefactoringTest;
import org.eclipse.php.refactoring.core.test.PdttFileExt;
import org.junit.Test;

public class RenameTraitProcessorTest extends AbstractPDTTListRefactoringTest {

	public RenameTraitProcessorTest(String[] fileNames) {
		super(fileNames);
	}

	@PDTTList.Parameters
	public static String[] dirs = { "/resources/rename/renameTrait/" }; //$NON-NLS-1$

	@Override
	@PDTTList.BeforeList
	public void setUpListSuite() throws Exception {
		super.setUpListSuite();
		TestUtils.setProjectPHPVersion(project.getProject(), PHPVersion.PHP5_4);
	}

	@Test
	public void testRename(String fileName) throws Exception {
		PdttFileExt testFile = filesMap.get(fileName);
		IFile file = project.findFile(testFile.getTestFiles().get(0).getName());

		Program program = createProgram(file);

		assertNotNull(program);

		int start = Integer.valueOf(testFile.getConfig().get("start"));
		ASTNode selectedNode = locateNode(program, start, 0);
		assertNotNull(selectedNode);

		RenameTraitProcessor processor = new RenameTraitProcessor(file, selectedNode);

		processor.setNewElementName(testFile.getConfig().get("newName"));
		processor.setUpdateTextualMatches(Boolean.valueOf(testFile.getConfig().get("updateTextualMatches")));

		checkInitCondition(processor);
		checkFinalCondition(processor);

		performChange(processor);
		checkTestResult(testFile);
	}
}
