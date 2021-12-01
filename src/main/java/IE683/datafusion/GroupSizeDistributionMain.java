package IE683.datafusion;

import IE683.model.Movie;
import IE683.utils.Utils;
import de.uni_mannheim.informatik.dws.winter.datafusion.CorrespondenceSet;
import de.uni_mannheim.informatik.dws.winter.model.defaultmodel.Attribute;

import static IE683.constants.Constants.OUTPUT_DIR;

public class GroupSizeDistributionMain {
    public static void main(String[] args) throws Exception {
        CorrespondenceSet<Movie, Attribute> correspondences = Utils.getCorrespondences();
        correspondences.writeGroupSizeDistribution(OUTPUT_DIR.resolve("groupSizeDistribution.csv").toFile());
    }
}
