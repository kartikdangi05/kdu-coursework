package org.student;

public class Book {
    private String title;
    private String author;
    private int publicationYear;
    private double averageRating;
    private int ratingsCount;
    private String imageUrl;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;

    }

    public double getAverageRating(){
        return this.averageRating;
    }

    public void setAverageRating(double averageRating){
        this.averageRating = averageRating;
    }

    public int getRatingsCount(){
        return this.ratingsCount;
    }

    public void setRatingsCount(int ratingsCount){
        this.ratingsCount = ratingsCount;
    }

    public String getAuthor(){
        return this.author;
    }

    public void setAuthor(String author){
        this.author = author;
    }
}
