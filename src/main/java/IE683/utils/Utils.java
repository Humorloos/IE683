package IE683.utils;

import IE683.model.Movie;
import IE683.model.MovieXMLReader;
import de.uni_mannheim.informatik.dws.winter.datafusion.CorrespondenceSet;
import de.uni_mannheim.informatik.dws.winter.model.FusibleDataSet;
import de.uni_mannheim.informatik.dws.winter.model.FusibleHashedDataSet;
import de.uni_mannheim.informatik.dws.winter.model.HashedDataSet;
import de.uni_mannheim.informatik.dws.winter.model.defaultmodel.Attribute;
import de.uni_mannheim.informatik.dws.winter.utils.WinterLogManager;
import org.slf4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

import static IE683.constants.Constants.OUTPUT_DIR;
import static IE683.constants.Constants.XML_FILES_DIR;

public class Utils {
    private static final Logger logger = WinterLogManager.activateLogger("default");

    public static HashedDataSet<Movie, Attribute> loadDataset(String source) {
        HashedDataSet<Movie, Attribute> dataSet = new HashedDataSet<>();
        try {
            new MovieXMLReader().loadFromXML(new File(XML_FILES_DIR + "\\" + source + ".xml"),
                    "movies/movie", dataSet);
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
            e.printStackTrace();
        }
        return dataSet;
    }

    public static FusibleDataSet<Movie, Attribute> loadFusibleDataset(String source) {
        FusibleDataSet<Movie, Attribute> dataSet = new FusibleHashedDataSet<>();
        try {
            new MovieXMLReader().loadFromXML(new File(XML_FILES_DIR + "\\" + source + ".xml"),
                    "movies/movie", dataSet);
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
            e.printStackTrace();
        }
        return dataSet;
    }

    public static CorrespondenceSet<Movie, Attribute> getCorrespondences() throws IOException {
        // Load the Data into FusibleDataSet
        logger.info("*\tLoading datasets\t*");

        FusibleDataSet<Movie, Attribute> ds1 = loadFusibleDataset("netflix");
        FusibleDataSet<Movie, Attribute> ds2 = loadFusibleDataset("streaming");
        FusibleDataSet<Movie, Attribute> ds3 = loadFusibleDataset("imdb");

        // Maintain Provenance
        // Scores (e.g. from rating)
        ds1.setScore(3.0);
        ds2.setScore(1.0);
        ds3.setScore(2.0);

        // Date (e.g. last update)
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd")
                .parseDefaulting(ChronoField.CLOCK_HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .toFormatter(Locale.ENGLISH);

        ds1.setDate(LocalDateTime.parse("2021-04-27", formatter));
        ds2.setDate(LocalDateTime.parse("2020-05-23", formatter));
        ds3.setDate(LocalDateTime.parse("2020-09-15", formatter));

        // load correspondences
        logger.info("*\tLoading correspondences\t*");
        CorrespondenceSet<Movie, Attribute> correspondences = new CorrespondenceSet<>();
        correspondences.loadCorrespondences(OUTPUT_DIR.resolve("netflix_2_streaming_correspondences.csv")
                .toFile(), ds2, ds1);
        correspondences.loadCorrespondences(OUTPUT_DIR.resolve("netflix_2_imdb_correspondences.csv")
                .toFile(), ds1, ds3);
        return correspondences;
    }
}

