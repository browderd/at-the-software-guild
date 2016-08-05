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
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author apprentice
 */
public class DVDLibraryDAOInMemory implements DVDLibraryDAO {

    private Map<Integer, DVD> dvdMap = popMap ();
    private static int dvdIdCounter = 3;
    
    private HashMap popMap (){
        HashMap<Integer, DVD> pop = new HashMap<>();
        
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
        pop.put(0, a);
        
        DVD b = new DVD();
        ratings = new ArrayList<>();
        b.setDirector("Dudester");
        b.setIdNumber(1);
        b.setImpaaRating("3");
        b.setReleaseDate("2000");
        b.setTitle("Superman");
        ratings.add("yay superman!");
        b.setUserRating(ratings);
        pop.put(1, b);
        
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
        pop.put(2, c);
        
        return pop;
    }
    
    @Override
    public DVD addDVD(DVD x) {
        x.setIdNumber(dvdIdCounter);
        dvdIdCounter++;
        dvdMap.put(x.getIdNumber(), x);
        return x;
    }

    @Override
    public void removeDVDByID(int dvdID) {
        dvdMap.remove(dvdID);
    }

    @Override
    public DVD getDVDByID(int dvdID) {
        return dvdMap.get(dvdID);
    }

    @Override
    public List<DVD> returnCollection() {
        return new ArrayList<>(dvdMap.values());
    }

    @Override
    public List<DVD> searchDVDS(Map<SearchTerm, String> criteria) {
        String titleCriteria = criteria.get(SearchTerm.TITLE);
        String releaseDateCriteria = criteria.get(SearchTerm.RELEASE_DATE);
        String impaaRatingCriteria = criteria.get(SearchTerm.IMPAA_RATING);
        String directorCriteria = criteria.get(SearchTerm.DIRECTOR);
        String studioCriteria = criteria.get(SearchTerm.STUDIO);
        
        Predicate<DVD> titleMatches;
        Predicate<DVD> releaseDateMatches;
        Predicate<DVD> impaaRatingMatches;
        Predicate<DVD> directorMatches;
        Predicate<DVD> studioMatches;
        
        Predicate<DVD> truePredicate = (DVD c) -> {return true;};
        
        titleMatches = (titleCriteria == null || titleCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getTitle().equals(titleCriteria);
        
        releaseDateMatches = (releaseDateCriteria == null || releaseDateCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getReleaseDate().equals(releaseDateCriteria);
        
        
        impaaRatingMatches = (impaaRatingCriteria == null || impaaRatingCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getImpaaRating().equals(impaaRatingCriteria);
        
        directorMatches = (directorCriteria == null || directorCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getDirector().equals(directorCriteria);
        
        studioMatches = (studioCriteria == null || studioCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getStudio().equals(studioCriteria);
        
        return dvdMap.values().stream()
                .filter(titleMatches
                        .and(releaseDateMatches)
                        .and(impaaRatingMatches)
                        .and(directorMatches)
                        .and(studioMatches))
                .collect(Collectors.toList());
        
    }
        

    @Override
    public void updateDVD(DVD x) {
        dvdMap.put(x.getIdNumber(), x);
    }
    
}
