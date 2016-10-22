package edu.aurora.oilchange.controller;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class ValidationsTest {
    @Test
    public void testOne() {
        assertTrue("Could not match single digit", Validations.digits("0").one());

        assertTrue("Could not match single alphanumeric character", Validations.alphanumeric("a").one());

        assertTrue("Could not match single alphabetical character", Validations.alphabetical("A").one());

        assertFalse("Matches multiple characters", Validations.digits("123").one());
    }

    @Test
    public void testAny() {
        assertTrue("Could not match zero digits with any()", Validations.digits("").any());
        assertTrue("Could not match multiple digits with any()", Validations.digits("12345").any());

        assertTrue("Could not match zero alphanumeric characters with any()",
                Validations.alphanumeric("").any());
        assertTrue("Could not match multiple alphanumeric with characters any()",
                Validations.alphanumeric("abc123").any());

        assertTrue("Could not match zero alphabetical characters with any()",
                Validations.alphabetical("").any());
        assertTrue("Could not match multiple alphabetical characters with any()",
                Validations.alphabetical("abcdef").any());
    }

    @Test
    public void testSome() {
        assertTrue("Could not match one digit with some()", Validations.digits("1").some());
        assertTrue("Could not match multiple digits with some()", Validations.digits("12345").some());

        assertTrue("Could not match one alphanumeric character with some()",
                Validations.alphanumeric("a").some());
        assertTrue("Could not match multiple alphanumeric characters with some()",
                Validations.alphanumeric("abc123").some());

        assertTrue("Could not match one alphabetical character with some()",
                Validations.alphabetical("a").some());
        assertTrue("Could not match multiple alphabetical characters with some()",
                Validations.alphabetical("abcdef").some());
    }

    @Test
    public void testRepeat() {
        assertFalse("Does not return false with times < 1", Validations.digits("123").repeat(-1));
        assertEquals("repeat(1) is not equivalent to one()",
                Validations.alphanumeric("a").repeat(1), Validations.alphanumeric("a").one());
        assertTrue("Does not match correct number of repetitions", Validations.alphabetical("aaaa").repeat(4));
        assertFalse("Matches with repetitions < input", Validations.digits("1234").repeat(3));
    }

    @Test
    public void testRange() {
        assertFalse("Does not return false with invalid range", Validations.digits("111").range(3, 2));
        assertTrue("Does not match range with equal from and to", Validations.digits("111").range(3, 3));
        assertTrue("Could not match upper bound of range", Validations.digits("123").range(2, 3));
        assertTrue("Could not match lower bound of range", Validations.alphanumeric("a2").range(2, 3));
        assertTrue("Could not match middle of range", Validations.alphabetical("abcde").range(3, 6));
    }

    @Test
    public void testNot() {
        assertTrue("Does not invert one()", Validations.digits("123").not().one());
        assertTrue("Does not invert any()", Validations.alphanumeric("!@?").not().any());
        assertTrue("Does not invert some()", Validations.alphabetical("1234").not().some());
        assertTrue("Does not invert repeat()", Validations.digits("113").not().repeat(4));
        assertTrue("Does not invert range()", Validations.alphabetical("1").not().range(2, 5));
        assertFalse("Inverted any() matches empty string", Validations.digits("").not().any());
    }
}