package IE683.model;

import de.uni_mannheim.informatik.dws.winter.model.HashedDataSet;
import de.uni_mannheim.informatik.dws.winter.model.defaultmodel.Attribute;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.File;
import java.io.IOException;
import java.util.List;

class MovieXMLReaderIT {
    public static final String XML_FILES_DIR = "C:\\Users\\lloos\\Google Drive\\IE683";

    @Test
    void testIdentityResolution() throws XPathExpressionException, ParserConfigurationException, IOException, SAXException {
        HashedDataSet<Movie, Attribute> dataSet = new HashedDataSet<>();
        for (String source : List.of("streaming", "imdb", "netflix")) {
            new MovieXMLReader().loadFromXML(new File(XML_FILES_DIR + "\\" + source + ".xml"),
                    "movies/movie", dataSet);
        }
        System.out.println("");
    }
}