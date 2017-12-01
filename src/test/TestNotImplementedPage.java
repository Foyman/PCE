/**
 * JUnit tests for the NotImplementedPage class
 * 
 * @author Tim Stoddard
 */

package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import logic.JComponentWithLayout;
import logic.NotImplementedPage;

class TestNotImplementedPage
{

    @Test
    void testCreateFrame()
    {
        List<JComponentWithLayout> panels = NotImplementedPage.createFrame();
        assertEquals(3, panels.size());
    }
}
