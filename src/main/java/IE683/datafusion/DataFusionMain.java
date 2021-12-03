package IE683.datafusion;

import IE683.evaluationRule.list.*;
import IE683.evaluationRule.numeric.*;
import IE683.evaluationRule.string.TitleEvaluationRule;
import IE683.fuser.TitleFuser;
import IE683.fuser.list.*;
import IE683.fuser.numeric.YearFuser;
import IE683.fuser.numeric.average.*;
import IE683.model.Movie;
import IE683.model.MovieXMLFormatter;
import IE683.model.MovieXMLReader;
import IE683.utils.Utils;
import de.uni_mannheim.informatik.dws.winter.datafusion.CorrespondenceSet;
import de.uni_mannheim.informatik.dws.winter.datafusion.DataFusionEngine;
import de.uni_mannheim.informatik.dws.winter.datafusion.DataFusionEvaluator;
import de.uni_mannheim.informatik.dws.winter.datafusion.DataFusionStrategy;
import de.uni_mannheim.informatik.dws.winter.model.DataSet;
import de.uni_mannheim.informatik.dws.winter.model.FusibleDataSet;
import de.uni_mannheim.informatik.dws.winter.model.FusibleHashedDataSet;
import de.uni_mannheim.informatik.dws.winter.model.RecordGroupFactory;
import de.uni_mannheim.informatik.dws.winter.model.defaultmodel.Attribute;
import de.uni_mannheim.informatik.dws.winter.utils.WinterLogManager;
import org.slf4j.Logger;

import java.io.File;

public class DataFusionMain {

    private static final Logger logger = WinterLogManager.activateLogger("default");

    public static void main(String[] args) throws Exception {
        CorrespondenceSet<Movie, Attribute> correspondences = Utils.getCorrespondences();

        // write group size distribution
        correspondences.printGroupSizeDistribution();

        // load the gold standard
        logger.info("*\tEvaluating results\t*");
        DataSet<Movie, Attribute> gs = new FusibleHashedDataSet<>();
        new MovieXMLReader().loadFromXML(new File("data/Movies/gold_standards/gs_data_fusion.xml"), "/movies" +
				"/movie",	gs);

        for (Movie m : gs.get()) {
            logger.info(String.format("gs: %s", m.getIdentifier()));
        }

        // define the fusion strategy
        DataFusionStrategy<Movie, Attribute> strategy = new DataFusionStrategy<>(new MovieXMLReader());
        // write debug results to file
//        strategy.activateDebugReport("data/output/debugResultsDatafusion.csv", -1, gs);


        strategy.addAttributeFuser(Movie.ACTORS, new ActorsFuser(), new ActorsEvaluationRule(0.7));
        strategy.addAttributeFuser(Movie.DIRECTORS, new DirectorsFuser(), new DirectorsEvaluationRule(0.7));
        strategy.addAttributeFuser(Movie.WRITERS, new WritersFuser(), new WritersEvaluationRule(0.7));
        strategy.addAttributeFuser(Movie.TITLE, new TitleFuser(), new TitleEvaluationRule());
        strategy.addAttributeFuser(Movie.COUNTRIES, new CountriesFuser(), new CountriesEvaluationRule(0.80));
        strategy.addAttributeFuser(Movie.LANGUAGES, new LanguagesFuser(), new LanguagesEvaluationRule(0.80));
        strategy.addAttributeFuser(Movie.GENRES, new GenresFuser(), new GenresEvaluationRule(0.90));
        strategy.addAttributeFuser(Movie.PRODUCTION_COMPANIES, new ProductionCompaniesFuser(), new ProductionCompaniesEvaluationRule(0.75));
        strategy.addAttributeFuser(Movie.AVG_VOTE, new AvgVoteFuser(), new AvgVoteEvaluationRule());
        strategy.addAttributeFuser(Movie.BUDGET, new BudgetFuser(), new BudgetEvaluationRule());
        strategy.addAttributeFuser(Movie.DURATION, new DurationFuser(), new DurationEvaluationRule());
        strategy.addAttributeFuser(Movie.IMDB_SCORE, new ImdbScoreFuser(), new ImdbScoreEvaluationRule());
        strategy.addAttributeFuser(Movie.IMDB_VOTES, new ImdbVotesFuser(), new ImdbVotesEvaluationRule());
        strategy.addAttributeFuser(Movie.YEAR, new YearFuser(), new YearEvaluationRule());

        // create the fusion engine
        DataFusionEngine<Movie, Attribute> engine = new DataFusionEngine<>(strategy);
        // print consistency report
        engine.printClusterConsistencyReport(correspondences, null);

        // print record groups sorted by consistency
        engine.writeRecordGroupsByConsistency(new File("data/output/recordGroupConsistencies.csv"), correspondences, null);
        // run the fusion
        logger.info("*\tRunning data fusion\t*");
        FusibleDataSet<Movie, Attribute> fusedDataSet = engine.run(correspondences, null);
        // write the result
		new MovieXMLFormatter().writeXML(new File("data/output/fused.xml"), fusedDataSet);

        // evaluate
        DataFusionEvaluator<Movie, Attribute> evaluator = new DataFusionEvaluator<>(strategy, new RecordGroupFactory<>());
        double accuracy = evaluator.evaluate(fusedDataSet, gs, null);
        logger.info(String.format("*\tAccuracy: %.2f", accuracy));
    }

}
