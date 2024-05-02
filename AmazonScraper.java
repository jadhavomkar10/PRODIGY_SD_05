import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileWriter;
import java.io.IOException;

public class AmazonScraper {
    public static void main(String[] args) {
        final String url = "https://www.amazon.in/";

        try {
            // Connect to the Amazon website
            Document document = Jsoup.connect(url).get();

            // Select the product elements
            Elements products = document.select(".s-result-item");

            // Open CSV file for writing
            FileWriter csvWriter = new FileWriter("amazon_products.csv");
            csvWriter.append("Product Name,Price,Rating\n");

            // Iterate over each product and extract information
            for (Element product : products) {
                String name = product.select(".a-text-normal").text();
                String price = product.select(".a-price span.a-offscreen").text();
                String rating = product.select("span[data-component-type=s-product-image] i.a-icon-star-small span.a-icon-alt").text();

                // Write to CSV file
                csvWriter.append(name).append(",").append(price).append(",").append(rating).append("\n");
            }

            // Close CSV file
            csvWriter.flush();
            csvWriter.close();

            System.out.println("Scraping completed. Data saved to amazon_products.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
