/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrarymvc.dao;

import com.mycompany.dvdlibrarymvc.dto.DVD;
import java.util.List;
import java.util.Map;

/**
 *
 * @author apprentice
 */
public interface DVDLibraryDAO {
    
     
    public DVD addDVD (DVD x);
    public void removeDVDByID (int dvdID);
    public DVD getDVDByID (int dvdID);
    public List<DVD> returnCollection();
    public List<DVD> searchDVDS (Map<SearchTerm, String> criteria); 
    public void updateDVD (DVD x);
}
