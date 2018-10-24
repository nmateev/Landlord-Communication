package com.wasp.landlordcommunication.models;

public class Rating {

    private int ratingId;
    private int voterId;
    private int votedForId;
    private double rating;

    public Rating() {

    }

    public Rating( int votedForId, double rating) {
        setVotedForId(votedForId);
        setRating(rating);
    }

    public int getRatingId() {
        return ratingId;
    }

    public int getVoterId() {
        return voterId;
    }

    public int getVotedForId() {
        return votedForId;
    }

    public double getRating() {
        return rating;
    }

    private void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }

    private void setVoterId(int voterId) {
        this.voterId = voterId;
    }

    private void setVotedForId(int votedForId) {
        this.votedForId = votedForId;
    }

    private void setRating(double rating) {
        this.rating = rating;
    }
}
