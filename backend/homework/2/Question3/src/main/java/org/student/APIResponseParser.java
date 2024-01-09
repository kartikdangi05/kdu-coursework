package org.student;

public class APIResponseParser {
    public static Book parse(String response) {

        Book book = new Book();

        String endRule = "<";
        String startRule = "<title>";
        String title = parse(response, startRule, endRule);
        book.setTitle(title);

        startRule = "<name>";
        String author = parse(response,startRule,endRule);
        book.setAuthor(author);

        startRule = "<original_publication_year type=\"integer\">";
        String publicationYear = parse(response,startRule,endRule);
        book.setPublicationYear(Integer.parseInt(publicationYear));

        startRule = "<average_rating>";
        String avgRating = parse(response,startRule,endRule);
        book.setAverageRating(Double.parseDouble(avgRating));

        startRule = "<ratings_count type=\"integer\">";
        String ratingsCount = parse(response,startRule,endRule);
        book.setRatingsCount(Integer.parseInt(ratingsCount.replace(",", "")));

        startRule = "<image_url>";
        String imageUrl = parse(response,startRule,endRule);
        book.setImageUrl(imageUrl);

        return book;
    }

    public static String parse(String response, String startRule, String endRule){

        int startIndex = response.indexOf(startRule) + startRule.length();

        String subString = response.substring(startIndex);
        int endIndex = subString.indexOf(endRule);

        String resultStr;
        resultStr = response.substring(startIndex,endIndex+startIndex);
        return resultStr;

    }

    public static void main(String[] args) {
        String response = "<work>\n" +
                "<id type=\"integer\">2361393</id>" +
                "<books_count type=\"integer\">813</books_count>\n" +
                "<ratings_count type=\"integer\">1,16,315</ratings_count>\n" +
                "<text_reviews_count type=\"integer\">3439</text_reviews_count>\n" +
                "<original_publication_year type=\"integer\">1854</original_publication_year>\n" +
                "<original_publication_month type=\"integer\" nil=\"true\"/>\n" +
                "<original_publication_day type=\"integer\" nil=\"true\"/>\n" +
                "<average_rating>3.79</average_rating>"
                + "<best_book type=\"Book\">\n" +
                "<id type=\"integer\">16902</id>\n" +
                "<title>Walden</title>\n" +
                "<author>\n" +
                "<id type=\"integer\">10264</id>\n" +
                "<name>Henry David Thoreau</name>\n" +
                "</author>\n" +
                "<image_url>http://images.gr-assets.com/books/1465675526m/16902.jpg</image_url>\n" +
                "<small_image_url>http://images.gr-assets.com/books/1465675526s/16902.jpg</small\n" +
                "_image_url>\n" +
                "</best_book>\n" +
                "</work>";

        Book detail = APIResponseParser.parse(response);
        Logging.logger.info("Title : " + detail.getTitle() + "\nName : " + detail.getAuthor() + "\nPublication Year : " + detail.getPublicationYear()
        + "\nAverage rating : " + detail.getAverageRating() + "\nRatings count : " + detail.getRatingsCount() + "\nImage URL : " + detail.getImageUrl());
    }
}
