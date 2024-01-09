package org.student;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class SentimentAnalyzer {

    public static int[] detectProsAndCons(String review, String[][] featureSet, String[] posOpinionWords, String[] negOpinionWords) {

        int[] featureOpinions = new int[featureSet.length];

        for(int i=0; i < featureSet.length;i++){
            int opinion;
            for(int j = 0; j < featureSet[i].length; j++){
                opinion = getOpinionOnFeature(review,featureSet[i][j],posOpinionWords,negOpinionWords);
                if(opinion!=0){
                    featureOpinions[i] = opinion; break;
                }
            }
        }

        return featureOpinions;
    }

    private static int getOpinionOnFeature(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {

        int opinion;
        opinion = checkForWasPhrasePattern(review,feature,posOpinionWords,negOpinionWords);
        if(opinion == 0)
            opinion = checkForOpinionFirstPattern(review,feature,posOpinionWords,negOpinionWords);

        return opinion;
    }

    private static int checkForWasPhrasePattern(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {

        String lowerCaseReview = review.toLowerCase();
        String pattern = feature + " was ";

        int patternIndex = lowerCaseReview.indexOf(pattern);

        if (patternIndex != -1) {

            for (String posOpinionWord : posOpinionWords) {
                if (lowerCaseReview.indexOf(posOpinionWord) == patternIndex + pattern.length()) {
                    return 1;
                }
            }

            for (String negOpinionWord : negOpinionWords) {
                if (lowerCaseReview.indexOf(negOpinionWord) == patternIndex + pattern.length()) {
                    return -1;
                }
            }
        }

        return 0;
    }


    private static int checkForOpinionFirstPattern(String review, String feature, String[] posOpinionWords, String[] negOpinionWords) {

        String lowerCaseReview = review.toLowerCase();
        String[] sentences = lowerCaseReview.split("\\.");
        HashSet<String> posOpinionSet = new HashSet<>();
        HashSet<String> negOpinionSet = new HashSet<>();

        Collections.addAll(posOpinionSet, posOpinionWords);
        Collections.addAll(negOpinionSet, negOpinionWords);


        for (String sentence : sentences) {
            String[] words = sentence.split(" ");
            for(int i = 0; i < words.length; i++){
                if(posOpinionSet.contains(words[i]) && i + 1 < words.length && words[i+1].equals(feature)){
                    return 1;
                }
                if(negOpinionSet.contains(words[i]) && i + 1 < words.length && words[i+1].equals(feature)){
                    return -1;
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        String review = "Haven't been here in years! Fantastic service and the food was delicious! Definetly will be a frequent flyer! Francisco was very attentive";
        String[][] featureSet = {
                {"ambiance", "ambience", "atmosphere", "decor"},
                {"dessert", "ice cream", "desert"},
                {"food"},
                {"soup"},
                {"service", "management", "waiter", "waitress",
                        "bartender", "staff", "server"}};
        String[] posOpinionWords = {"good", "fantastic", "friendly",
                "great", "excellent", "amazing", "awesome",
                "delicious"};
        String[] negOpinionWords = {"slow", "bad", "horrible",
                "awful", "unprofessional", "poor"};

        int[] featureOpinions = detectProsAndCons(review, featureSet, posOpinionWords, negOpinionWords);
        Logging.logger.info("Opinions on Features: " + Arrays.toString(featureOpinions));
    }
}
