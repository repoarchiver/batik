/*****************************************************************************
 * Copyright (C) The Apache Software Foundation. All rights reserved.        *
 * ------------------------------------------------------------------------- *
 * This software is published under the terms of the Apache Software License *
 * version 1.1, a copy of which has been included with this distribution in  *
 * the LICENSE file.                                                         *
 *****************************************************************************/

package org.apache.batik.css.value;

import java.util.Iterator;

import org.apache.batik.css.CSSOMReadOnlyStyleDeclaration;
import org.apache.batik.css.CSSOMReadOnlyValue;

import org.w3c.dom.Element;

import org.w3c.dom.css.CSSPrimitiveValue;
import org.w3c.dom.css.ViewCSS;

/**
 * This class provides a relative value resolver for the 'font-family' CSS
 * property.
 *
 * @author <a href="mailto:stephane@hillion.org">Stephane Hillion</a>
 * @version $Id$
 */
public class FontFamilyResolver implements RelativeValueResolver {

    /**
     * The application context.
     */
    protected CommonCSSContext context;
    
    /**
     * Creates a new FontFamilyRelativeValueResolver object.
     * @param ctx The application context.
     */
    public FontFamilyResolver(CommonCSSContext ctx) {
	context = ctx;
    }

    /**
     * Whether the handled property is inherited or not.
     */
    public boolean isInheritedProperty() {
	return true;
    }

    /**
     * Returns the name of the handled property.
     */
    public String getPropertyName() {
	return ValueConstants.CSS_FONT_FAMILY_PROPERTY;
    }

    /**
     * Returns the default value for the handled property.
     */
    public CSSOMReadOnlyValue getDefaultValue() {
        ImmutableValueList l = new ImmutableValueList();
        Iterator it = context.getDefaultFontFamilyValue().iterator();
        while (it.hasNext()) {
            String s = (String)it.next();
            l.append(new CSSOMReadOnlyValue(createFontFamilyValue(s)));
        }
        return new CSSOMReadOnlyValue(l);
    }
    
    /**
     * Resolves the given value if relative, and puts it in the given table.
     * @param element The element to which this value applies.
     * @param pseudoElement The pseudo element if one.
     * @param view The view CSS of the current document.
     * @param styleDeclaration The computed style declaration.
     * @param value The cascaded value.
     * @param priority The priority of the cascaded value.
     * @param origin The origin of the cascaded value.
     */
    public void resolveValue(Element element,
			     String pseudoElement,
			     ViewCSS view,
			     CSSOMReadOnlyStyleDeclaration styleDeclaration,
			     CSSOMReadOnlyValue value,
			     String priority,
			     int origin) {
	// Nothing to do
    }

    /**
     * Creates a font-family value.
     */
    protected ImmutableValue createFontFamilyValue(String s) {
        ImmutableValue res =
            (ImmutableValue)FontFamilyFactory.values.get(s.toLowerCase().intern());
        if (res != null) {
            return res;
        }
        return new ImmutableString(CSSPrimitiveValue.CSS_STRING, s);
    }
}
