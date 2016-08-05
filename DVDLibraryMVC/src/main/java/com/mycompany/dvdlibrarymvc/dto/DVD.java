/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.dvdlibrarymvc.dto;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author apprentice
 */
public class DVD {
    private int idNumber;
    private String title;
    private String releaseDate;
    private String impaaRating;
    private String director;
    private String studio;
    private ArrayList<String> userRating;
    
    
    public DVD (String title, String releaseDate, String impaaRating, String director, String studio, ArrayList<String>  userRating){
        this.title = title;
        this.releaseDate = releaseDate;
        this.impaaRating = impaaRating;
        this.director = director;
        this.studio = studio;
        this.userRating = userRating;
    }

    public DVD() {
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the releaseDate
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * @param releaseDate the releaseDate to set
     */
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * @return the impaaRating
     */
    public String getImpaaRating() {
        return impaaRating;
    }

    /**
     * @param impaaRating the impaaRating to set
     */
    public void setImpaaRating(String impaaRating) {
        this.impaaRating = impaaRating;
    }

    /**
     * @return the director
     */
    public String getDirector() {
        return director;
    }

    /**
     * @param director the director to set
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     * @return the studio
     */
    public String getStudio() {
        return studio;
    }

    /**
     * @param studio the studio to set
     */
    public void setStudio(String studio) {
        this.studio = studio;
    }

    /**
     * @return the userRating
     */
    public ArrayList<String>  getUserRating() {
        return userRating;
    }

    /**
     * @param userRating the userRating to set
     */
    public void setUserRating(ArrayList<String>  userRating) {
        this.userRating = userRating;
    }
    
    public String toString(){
        return title + " " + releaseDate + " " + director + " " + studio + " " + impaaRating + " " + userRating;
    }
    
    public boolean equals(DVD a) {
        return title.equals(a.title)  &&
        director.equals(a.director) &&
        studio.equals(a.studio) &&
        userRating.equals(a.userRating);
    }

    /**
     * @return the idNumber
     */
    public int getIdNumber() {
        return idNumber;
    }

    /**
     * @param idNumber the idNumber to set
     */
    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + this.idNumber;
        hash = 71 * hash + Objects.hashCode(this.title);
        hash = 71 * hash + Objects.hashCode(this.releaseDate);
        hash = 71 * hash + Objects.hashCode(this.impaaRating);
        hash = 71 * hash + Objects.hashCode(this.director);
        hash = 71 * hash + Objects.hashCode(this.studio);
        hash = 71 * hash + Objects.hashCode(this.userRating);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DVD other = (DVD) obj;
        if (this.idNumber != other.idNumber) {
            return false;
        }
        if (this.releaseDate != other.releaseDate) {
            return false;
        }
        if (this.impaaRating != other.impaaRating) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.director, other.director)) {
            return false;
        }
        if (!Objects.equals(this.studio, other.studio)) {
            return false;
        }
        if (!Objects.equals(this.userRating, other.userRating)) {
            return false;
        }
        return true;
    }
    
}
