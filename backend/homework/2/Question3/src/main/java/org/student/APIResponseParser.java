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
        String response = """
                    <work>
                        <id type="integer">2361393</id>
                        <books_count type="integer">813</books_count>
                        <ratings_count type="integer">1,16,315</ratings_count>
                        <text_reviews_count type="integer">3439</text_reviews_count>
                        <original_publication_year type="integer">1854</original_publication_year>
                        <original_publication_month type="integer" nil="true"/>
                        <original_publication_day type="integer" nil="true"/>
                        <average_rating>3.79</average_rating>
                        <best_book type="Book">
                            <id type="integer">16902</id>
                            <title>Walden</title>
                            <author>
                                <id type="integer">10264</id>
                                <name>Henry David Thoreau</name>
                            </author>
                            <image_url>http://images.gr-assets.com/books/1465675526m/16902.jpg</image_url>
                            <small_image_url>http://images.gr-assets.com/books/1465675526s/16902.jpg</small_image_url>
                        </best_book>
                    </work>
                """;


        Book detail = APIResponseParser.parse(response);

        Logging.logger.info(String.format("Title: %s%nName: %s%nPublication Year: %d%nAverage rating: %.2f%nRatings count: %d%nImage URL: %s",
                detail.getTitle(), detail.getAuthor(), detail.getPublicationYear(),
                detail.getAverageRating(), detail.getRatingsCount(), detail.getImageUrl()));


    }
}
