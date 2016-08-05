/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrarymvc.dao;

import com.mycompany.dvdlibrarymvc.dto.DVD;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author apprentice
 */
public class DVDLibraryDAOInMemoryTest {
    private DVDLibraryDAOInMemory instance;
    private Map<Integer, DVD> dvdMap;
    private static int dvdIdCounter;
    
    public DVDLibraryDAOInMemoryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new DVDLibraryDAOInMemory();
        dvdMap = new HashMap<>();
        dvdIdCounter = 3;
        
        
        DVD a = new DVD();
        ArrayList<String> ratings = new ArrayList<>();
        a.setDirector("ThatGuy");
        a.setIdNumber(0);
        a.setImpaaRating("5");
        a.setReleaseDate("2205");
        a.setTitle("Batman");
        ratings.add("This things was great!");
        ratings.add("Na na na na na na na na BATMAN");
        a.setUserRating(ratings);
        dvdMap.put(0, a);
        
        DVD b = new DVD();
        ratings = new ArrayList<>();
        b.setDirector("Dudester");
        b.setIdNumber(1);
        b.setImpaaRating("3");
        b.setReleaseDate("2000");
        b.setTitle("Superman");
        ratings.add("yay superman!");
        b.setUserRating(ratings);
        dvdMap.put(1, b);
        
        DVD c = new DVD();
        ratings = new ArrayList<>();
        c.setDirector("WhoCares");
        c.setIdNumber(2);
        c.setImpaaRating("9");
        c.setReleaseDate("1995");
        c.setTitle("Titanic");
        ratings.add("So sad :(");
        ratings.add("Nooooooo! LEO!!!");
        c.setUserRating(ratings);
        dvdMap.put(2, c);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addDVD method, of class DVDLibraryDAOInMemory.
     */
    @Test
    public void testAddDVD() {
        System.out.println("addDVD");
         
        DVD d = new DVD();
        d.setDirector("Jon");
        d.setImpaaRating("9");
        d.setReleaseDate("1995");
        d.setTitle("Spiderman");
        
        instance.addDVD(d);
        assertTrue(instance.returnCollection().contains(d));
        assertEquals(d, instance.getDVDByID(3));

    }

    /**
     * Test of removeDVDByID method, of class DVDLibraryDAOInMemory.
     */
    @Test
    public void testRemoveDVDByID() {
        System.out.println("removeDVDByID");
        instance.removeDVDByID(1);
        
        assertEquals(2, instance.returnCollection().size());
    }



 
    /**
     * Test of searchDVDS method, of class DVDLibraryDAOInMemory.
     */
    @Test
    public void testSearchDVDS() {
        System.out.println("searchDVDS");
        Map<SearchTerm, String> criteria = null;
        DVDLibraryDAOInMemory instance = new DVDLibraryDAOInMemory();
        List<DVD> expResult = null;
        List<DVD> result = instance.searchDVDS(criteria);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateDVD method, of class DVDLibraryDAOInMemory.
     */
    @Test
    public void testUpdateDVD() {
        System.out.println("updateDVD");
        DVD d = new DVD();
        d.setDirector("Jon");
        d.setImpaaRating("9");
        d.setReleaseDate("1995");
        d.setTitle("Spiderman");
        d.setIdNumber(0);
        
        DVD e = instance.getDVDByID(0);
        
        instance.updateDVD(d);
        
        assertFalse(e.equals(instance.getDVDByID(0)));
    }
    
}
