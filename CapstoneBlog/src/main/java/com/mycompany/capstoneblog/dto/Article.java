package com.mycompany.capstoneblog.dto;


import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author apprentice
 */
public class Article {
    private String hashtags;
    private int articleID;
    private String articleBody;
    private Date postingDate;
    private Date takeDownDate;
    private int activeStatus;
    private boolean staticPage;
    private String articleHead;
   
    
    /**
     * @return the content
     */
    public String getArticleBody() {
        return articleBody;
    }

    /**
     * @param content the content to set
     */
    public void setArticleBody(String content) {
        this.articleBody = content;
    }

    /**
     * @return the articleBody
     */
    public String getHashtags() {
        return hashtags;
    }

    /**
     * @param hashtags the articleBody to set
     */
    public void setHashtags(String hashtags) {
        this.hashtags = hashtags;
    }

    /**
     * @return the articleID
     */
    public int getArticleID() {
        return articleID;
    }

    /**
     * @param articleID the articleID to set
     */
    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    /**
     * @return the postingDate
     */
    public Date getPostingDate() {
        return postingDate;
    }

    /**
     * @param postingDate the postingDate to set
     */
    public void setPostingDate(Date postingDate) {
        this.postingDate = postingDate;
    }

    /**
     * @return the takeDownDate
     */
    public Date getTakeDownDate() {
        return takeDownDate;
    }

    /**
     * @param takeDownDate the takeDownDate to set
     */
    public void setTakeDownDate(Date takeDownDate) {
        this.takeDownDate = takeDownDate;
    }

    /**
     * @return the actuveStatus
     */
    public int getActiveStatus() {
        return activeStatus;
    }

    /**
     * @param activeStatus the status to set
     */
    public void setActiveStatus(int activeStatus) {
        this.activeStatus = activeStatus;
    }

    /**
     * @return the staticPage
     */
    public boolean isStaticPage() {
        return staticPage;
    }

    /**
     * @param staticPage the staticPage to set
     */
    public void setStaticPage(boolean staticPage) {
        this.staticPage = staticPage;
    }

    /**
     * @return the articleHead
     */
    public String getArticleHead() {
        return articleHead;
    }

    /**
     * @param articleHead the articleHead to set
     */
    public void setArticleHead(String articleHead) {
        this.articleHead = articleHead;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.hashtags);
        hash = 47 * hash + this.articleID;
        hash = 47 * hash + Objects.hashCode(this.articleBody);
        hash = 47 * hash + Objects.hashCode(this.postingDate);
        hash = 47 * hash + Objects.hashCode(this.takeDownDate);
        hash = 47 * hash + this.activeStatus;
        hash = 47 * hash + (this.staticPage ? 1 : 0);
        hash = 47 * hash + Objects.hashCode(this.articleHead);
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
        final Article other = (Article) obj;
        if (this.articleID != other.articleID) {
            return false;
        }
        if (this.activeStatus != other.activeStatus) {
            return false;
        }
        if (this.staticPage != other.staticPage) {
            return false;
        }
        if (!Objects.equals(this.hashtags, other.hashtags)) {
            return false;
        }
        if (!Objects.equals(this.articleBody, other.articleBody)) {
            return false;
        }
        if (!Objects.equals(this.articleHead, other.articleHead)) {
            return false;
        }
        if (!Objects.equals(this.postingDate, other.postingDate)) {
            return false;
        }
        if (!Objects.equals(this.takeDownDate, other.takeDownDate)) {
            return false;
        }
        return true;
    }


}