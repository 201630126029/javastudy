package spittr.web;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HomeControllerTest {
    @Test
    public void testHomePage() throws Exception{
        HomeController contrller = new HomeController();
        assertEquals("home", contrller.home());
    }
}
