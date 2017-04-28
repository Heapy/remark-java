/*
 * Copyright 2011 OverZealous Creations, LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package by.heap.remark.util;

import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Phil DeJarnett
 */
public class StringUtilsTest {

    private final StringWriter testWriter = new StringWriter();
    private final PrintWriter testPrintWriter = new PrintWriter(testWriter);

    private PrintWriter getWriter() {
        testWriter.getBuffer().setLength(0);
        return testPrintWriter;
    }

    @SuppressWarnings({"RedundantThrows"})
    private void assertWriter(String expected) throws Exception {
        assertEquals(expected, testWriter.toString());
    }

    @Test
    public void testAlignLeft() throws Exception {
        assertEquals("foo      ", StringUtils.align("foo", 9, StringUtils.ALIGN_LEFT));
        assertEquals("fu+++++++", StringUtils.align("fu", 9, '+', StringUtils.ALIGN_LEFT));
    }

    @Test
    public void testAlignCenter() throws Exception {
        assertEquals("   foo   ", StringUtils.align("foo", 9, StringUtils.ALIGN_CENTER));
        assertEquals("+++fu++++", StringUtils.align("fu", 9, '+', StringUtils.ALIGN_CENTER));
    }

    @Test
    public void testAlignRight() throws Exception {
        assertEquals("      foo", StringUtils.align("foo", 9, StringUtils.ALIGN_RIGHT));
        assertEquals("+++++++fu", StringUtils.align("fu", 9, '+', StringUtils.ALIGN_RIGHT));
    }

    @Test
    public void testAlignWithWriterLeft() throws Exception {
        StringUtils.align(getWriter(), "foo", 9, StringUtils.ALIGN_LEFT);
        assertWriter("foo      ");

        StringUtils.align(getWriter(), "fu", 9, '+', StringUtils.ALIGN_LEFT);
        assertWriter("fu+++++++");
    }

    @Test
    public void testAlignWithWriterCenter() throws Exception {
        StringUtils.align(getWriter(), "foo", 9, StringUtils.ALIGN_CENTER);
        assertWriter("   foo   ");

        StringUtils.align(getWriter(), "fu", 9, '+', StringUtils.ALIGN_CENTER);
        assertWriter("+++fu++++");
    }

    @Test
    public void testAlignWithWriterRight() throws Exception {
        StringUtils.align(getWriter(), "foo", 9, StringUtils.ALIGN_RIGHT);
        assertWriter("      foo");
        StringUtils.align(getWriter(), "fu", 9, '+', StringUtils.ALIGN_RIGHT);
        assertWriter("+++++++fu");
    }

    @Test
    public void testMultiplyCharacter() throws Exception {
        assertEquals("", StringUtils.multiply('c', 0));
        assertEquals("ccc", StringUtils.multiply('c', 3));
    }

    @Test
    public void testMultiplyString() throws Exception {
        assertEquals("", StringUtils.multiply("str", 0));
        assertEquals("strstrstrstr", StringUtils.multiply("str", 4));
    }

    @Test
    public void testMultiplyCharacterWithWriter() throws Exception {
        StringUtils.multiply(getWriter(), 'c', 0);
        assertWriter("");
        StringUtils.multiply(getWriter(), 'c', 3);
        assertWriter("ccc");
    }

    @Test
    public void testMultiplyStringWithWriter() throws Exception {
        StringUtils.multiply(getWriter(), "str", 0);
        assertWriter("");
        StringUtils.multiply(getWriter(), "str", 4);
        assertWriter("strstrstrstr");
    }

    @Test
    public void testPrependEachLine() throws Exception {
        assertEquals("  1\n  2\n  3", StringUtils.prependEachLine("1\n2\n3", "  "));
    }

    @Test
    public void testPrependEachLineWithWriter() throws Exception {
        StringUtils.prependEachLine(getWriter(), "1\n2\n3", "  ");
        assertWriter("  1\n  2\n  3");
    }
}
