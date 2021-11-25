package IE683;

import java.io.File;

import IE683.blocker.Title2Blocker;
import IE683.blocker.TitleBlocker;
import IE683.comparator.list.*;
import IE683.comparator.numeric.Year2Comparator;
import IE683.comparator.numeric.Year5Comparator;
import IE683.comparator.string.TitleComparator;
import IE683.comparator.string.TitleJaccardLevenComparator;
import org.slf4j.Logger;

import IE683.model.Movie;
import IE683.model.MovieXMLReader;
import de.uni_mannheim.informatik.dws.winter.matching.MatchingEngine;
import de.uni_mannheim.informatik.dws.winter.matching.MatchingEvaluator;
import de.uni_mannheim.informatik.dws.winter.matching.algorithms.MaximumBipartiteMatchingAlgorithm;
import de.uni_mannheim.informatik.dws.winter.matching.blockers.Blocker;
import de.uni_mannheim.informatik.dws.winter.matching.blockers.NoBlocker;
import de.uni_mannheim.informatik.dws.winter.matching.blockers.SortedNeighbourhoodBlocker;
import de.uni_mannheim.informatik.dws.winter.matching.blockers.StandardRecordBlocker;
import de.uni_mannheim.informatik.dws.winter.matching.rules.LinearCombinationMatchingRule;
import de.uni_mannheim.informatik.dws.winter.model.Correspondence;
import de.uni_mannheim.informatik.dws.winter.model.HashedDataSet;
import de.uni_mannheim.informatik.dws.winter.model.MatchingGoldStandard;
import de.uni_mannheim.informatik.dws.winter.model.Performance;
import de.uni_mannheim.informatik.dws.winter.model.defaultmodel.Attribute;
import de.uni_mannheim.informatik.dws.winter.model.io.CSVCorrespondenceFormatter;
import de.uni_mannheim.informatik.dws.winter.processing.Processable;
import de.uni_mannheim.informatik.dws.winter.utils.WinterLogManager;

public class Netflix_Imdb_Linear_IR {
    private static final Logger logger = WinterLogManager.activateLogger("default");

    public static void main( String[] args ) throws Exception
    {
        // loading data
        logger.info("*\tLoading datasets\t*");
        HashedDataSet<Movie, Attribute> dataImdb = new HashedDataSet<>();
        new MovieXMLReader().loadFromXML(new File("data/Movies/input/imdb.xml"), "/movies/movie", dataImdb);
        HashedDataSet<Movie, Attribute> dataNetflix = new HashedDataSet<>();
        new MovieXMLReader().loadFromXML(new File("data/Movies/input/netflix.xml"), "/movies/movie", dataNetflix);

        // load the gold standard (test set)
        logger.info("*\tLoading gold standard\t*");
        MatchingGoldStandard gsTest = new MatchingGoldStandard();
        gsTest.loadFromCSVFile(new File(
                "data/Movies/gold_standards/gs_netflix_imdb_mini.csv"));

        // create a matching rule
        LinearCombinationMatchingRule<Movie, Attribute> matchingRule = new LinearCombinationMatchingRule<>(
                0.7);
        matchingRule.activateDebugReport("data/Movies/output/netflix_imdb_debugResultsMatchingRule.csv", 5000, gsTest);

        // add comparators
        matchingRule.addComparator(new TitleComparator(), 0.7);
        //matchingRule.addComparator(new TitleJaccardLevenComparator(), 0.7);

        matchingRule.addComparator(new Year2Comparator(), 0.05);
        //matchingRule.addComparator(new Year5Comparator(), 0.05);

        matchingRule.addComparator(new DirectorsOverlapComparator(), 0.05 );
        //matchingRule.addComparator(new DirectorsComparator(0.8), 0.05);

        matchingRule.addComparator(new ActorsOverlapComparator(), 0.05 );
        //matchingRule.addComparator(new ActorsComparator(0.8), 0.05);

        matchingRule.addComparator(new GenresOverlapComparator(), 0.05 );
        //matchingRule.addComparator(new GenresComparator(0.8), 0.05);

        matchingRule.addComparator(new WritersOverlapComparator(), 0.05 );
        //matchingRule.addComparator(new WritersComparator(0.8), 0.05);

        matchingRule.addComparator(new LanguageOverlapComparator(), 0.05 );
        //matchingRule.addComparator(new LanguagesComparator(0.8), 0.05);

        // create a blocker (blocking strategy)
        StandardRecordBlocker<Movie, Attribute> blocker = new StandardRecordBlocker<Movie, Attribute>(new Title2Blocker());
//		NoBlocker<Movie, Attribute> blocker = new NoBlocker<>();
//		SortedNeighbourhoodBlocker<Movie, Attribute, Attribute> blocker = new SortedNeighbourhoodBlocker<>(new MovieBlockingKeyByTitleGenerator(), 1);
        blocker.setMeasureBlockSizes(true);
        //Write debug results to file:
        blocker.collectBlockSizeData("data/Movies/output/netflix_imdb_debugResultsBlocking.csv", 100);

        // Initialize Matching Engine
        MatchingEngine<Movie, Attribute> engine = new MatchingEngine<>();

        // Execute the matching
        logger.info("*\tRunning identity resolution\t*");
        Processable<Correspondence<Movie, Attribute>> correspondences = engine.runIdentityResolution(
                dataNetflix, dataImdb, null, matchingRule,
                blocker);

        // Create a top-1 global matching
//		  correspondences = engine.getTopKInstanceCorrespondences(correspondences, 1, 0.0);

//		 Alternative: Create a maximum-weight, bipartite matching
//		 MaximumBipartiteMatchingAlgorithm<Movie,Attribute> maxWeight = new MaximumBipartiteMatchingAlgorithm<>(correspondences);
//		 maxWeight.run();
//		 correspondences = maxWeight.getResult();

        // write the correspondences to the output file
        new CSVCorrespondenceFormatter().writeCSV(new File("data/Movies/output/netflix_2_imdb_correspondences.csv"), correspondences);

        logger.info("*\tEvaluating result\t*");
        // evaluate your result
        MatchingEvaluator<Movie, Attribute> evaluator = new MatchingEvaluator<Movie, Attribute>();
        Performance perfTest = evaluator.evaluateMatching(correspondences,
                gsTest);

        // print the evaluation result
        logger.info("Netflix <-> Imdb");
        logger.info(String.format(
                "Precision: %.4f",perfTest.getPrecision()));
        logger.info(String.format(
                "Recall: %.4f",	perfTest.getRecall()));
        logger.info(String.format(
                "F1: %.4f",perfTest.getF1()));
    }
}
