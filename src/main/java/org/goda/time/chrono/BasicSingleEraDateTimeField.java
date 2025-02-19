/*
 *  Copyright 2001-2005 Stephen Colebourne
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.goda.time.chrono;

import org.goda.time.DateTimeConstants;
import org.goda.time.DateTimeFieldType;
import org.goda.time.DurationField;
import org.goda.time.DurationFieldType;
import org.goda.time.IllegalFieldValueException;
import org.goda.time.field.BaseDateTimeField;
import org.goda.time.field.FieldUtils;
import org.goda.time.field.UnsupportedDurationField;

import java.util.Locale;

/**
 * Provides time calculations for the coptic era component of time.
 *
 * @author Brian S O'Neill
 * @author Stephen Colebourne
 * @since 1.2, refactored from CopticEraDateTimeField
 */
final class BasicSingleEraDateTimeField extends BaseDateTimeField {

    /**
     * Value of the era, which will be the same as DateTimeConstants.CE.
     */
    private static final int ERA_VALUE = DateTimeConstants.CE;
    /**
     * Text value of the era.
     */
    private final String iEraText;

    /**
     * Restricted constructor.
     */
    BasicSingleEraDateTimeField(String text) {
        super(DateTimeFieldType.era());
        iEraText = text;
    }

    /** @inheritDoc */
    public boolean isLenient() {
        return false;
    }

    /** @inheritDoc */
    public int get(long instant) {
        return ERA_VALUE;
    }

    /** @inheritDoc */
    public long set(long instant, int era) {
        FieldUtils.verifyValueBounds(this, era, ERA_VALUE, ERA_VALUE);
        return instant;
    }

    /** @inheritDoc */
    public long set(long instant, String text, Locale locale) {
        if (iEraText.equals(text) == false && "1".equals(text) == false) {
            throw new IllegalFieldValueException(DateTimeFieldType.era(), text);
        }
        return instant;
    }

    /** @inheritDoc */
    public long roundFloor(long instant) {
        return Long.MIN_VALUE;
    }

    /** @inheritDoc */
    public long roundCeiling(long instant) {
        return Long.MAX_VALUE;
    }

    /** @inheritDoc */
    public long roundHalfFloor(long instant) {
        return Long.MIN_VALUE;
    }

    /** @inheritDoc */
    public long roundHalfCeiling(long instant) {
        return Long.MIN_VALUE;
    }

    /** @inheritDoc */
    public long roundHalfEven(long instant) {
        return Long.MIN_VALUE;
    }

    /** @inheritDoc */
    public DurationField getDurationField() {
        return UnsupportedDurationField.getInstance(DurationFieldType.eras());
    }

    /** @inheritDoc */
    public DurationField getRangeDurationField() {
        return null;
    }

    /** @inheritDoc */
    public int getMinimumValue() {
        return ERA_VALUE;
    }

    /** @inheritDoc */
    public int getMaximumValue() {
        return ERA_VALUE;
    }

    /** @inheritDoc */
    public String getAsText(int fieldValue, Locale locale) {
        return iEraText;
    }

    /** @inheritDoc */
    public int getMaximumTextLength(Locale locale) {
        return iEraText.length();
    }

}
