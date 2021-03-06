/*******************************************************************************
 * Copyright (c) 2009, 2016, 2017, 2018 IBM Corporation and others.
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
package org.eclipse.php.internal.core.documentModel.parser.regions;

import org.eclipse.dltk.annotations.NonNull;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.php.core.PHPVersion;
import org.eclipse.php.internal.core.documentModel.partitioner.PHPPartitionTypes;
import org.eclipse.wst.sse.core.internal.provisional.text.ITextRegion;

/**
 * This is a base class for a PHP script region The default implementation is
 * {@link PHPScriptRegion}
 * 
 * @author Roy, 2007
 */
public interface IPHPScriptRegion extends ITextRegion {

	/**
	 * Returns an array of php tokens that intersects with the interval [from, from
	 * + length - 1] and that includes (at least) value of
	 * getPhpToken(relativeOffset)
	 * 
	 * @param relativeOffset
	 * @param length
	 * @throws BadLocationException
	 *             when "relativeOffset" is an invalid offset or when "length" is <
	 *             0
	 */
	public abstract @NonNull ITextRegion[] getPHPTokens(int relativeOffset, int length) throws BadLocationException;

	public abstract @NonNull ITextRegion[] getUpdatedPHPTokens() throws BadLocationException;

	public int getUpdatedTokensStart();

	public int getUpdatedTokensLength();

	/**
	 * Returns a single php token that lay in the given offset. If offset is equal
	 * to getEnd(), the last php token from the region will be returned.
	 * 
	 * @param relativeOffset
	 * @return region (will never be null)
	 * @throws BadLocationException
	 */
	public abstract @NonNull ITextRegion getPHPToken(int relativeOffset) throws BadLocationException;

	/**
	 * returns the php partition type of the token that lay in the given offset PHP
	 * valid types:<br>
	 * PHP_DEFAULT - {@link PHPPartitionTypes#PHP_DEFAULT}<br>
	 * PHP_SINGLE_LINE_COMMENT -
	 * {@link PHPPartitionTypes#PHP_SINGLE_LINE_COMMENT}<br>
	 * PHP_MULTI_LINE_COMMENT - {@link PHPPartitionTypes#PHP_MULTI_LINE_COMMENT}
	 * <br>
	 * PHP_DOC - {@link PHPPartitionTypes#PHP_DOC}<br>
	 * PHP_QUOTED_STRING - {@link PHPPartitionTypes#PHP_QUOTED_STRING}<br>
	 * 
	 * @param relativeOffset
	 * @throws BadLocationException
	 */
	public abstract @NonNull String getPartition(int relativeOffset) throws BadLocationException;

	/**
	 * returns the php token type of the token that lays in the given offset Please
	 * refer {@link PHPRegionTypes} for list of php token types
	 * 
	 * @param relativeOffset
	 * @return php token type (will never be null)
	 * @throws BadLocationException
	 */
	public abstract @NonNull String getPHPTokenType(int relativeOffset) throws BadLocationException;

	/**
	 * Returns true if the given offset is in a back-quoted string, a single-quoted
	 * string, a double-quoted string or a heredoc/nowdoc section
	 * 
	 * @param relativeOffset
	 * @return
	 * @throws BadLocationException
	 */
	public abstract boolean isPHPQuotesState(int relativeOffset) throws BadLocationException;

	/**
	 * Performs a complete reparse in the document on the given interval
	 * 
	 * @param doc
	 * @param start
	 * @param length
	 */
	public abstract void completeReparse(IDocument doc, int start, int length);

	/**
	 * Performs a complete reparse in the document on the given interval and project
	 * configuration
	 * 
	 * @param doc
	 * @param start
	 * @param length
	 * @param phpVersion
	 * @param isSupportingASPTags
	 * @param useShortTags
	 */
	public abstract void completeReparse(IDocument doc, int start, int length, @NonNull PHPVersion phpVersion,
			boolean isSupportingASPTags, boolean useShortTags);

	/**
	 * Returns true if the last operation was a full reparse action
	 * 
	 * @return
	 */
	public boolean isFullReparsed();

	/**
	 * Sets if the last operation was a full reparse action
	 * 
	 * @param b
	 */
	public abstract void setFullReparsed(boolean isFullReparse);
}
