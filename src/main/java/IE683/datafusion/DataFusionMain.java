package IE683.datafusion;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

import IE683.blocker.ReleaseYearBlocker;
import IE683.comparator.numeric.AvgVoteComparator;
import IE683.constants.Constants;
import IE683.evaluationRule.list.ActorsEvaluationRule;
import IE683.evaluationRule.list.CountriesEvaluationRule;
import IE683.evaluationRule.list.GenresEvaluationRule;
import IE683.evaluationRule.list.LanguagesEvaluationRule;
import IE683.evaluationRule.list.ProductionCompaniesEvaluationRule;
import IE683.evaluationRule.list.WritersEvaluationRule;
import IE683.evaluationRule.numeric.AvgVoteEvaluationRule;
import IE683.evaluationRule.numeric.BudgetEvaluationRule;
import IE683.evaluationRule.numeric.DurationEvaluationRule;
import IE683.evaluationRule.numeric.ImdbScoreEvaluationRule;
import IE683.evaluationRule.numeric.ImdbVotesEvaluationRule;
import IE683.evaluationRule.string.TitleEvaluationRule;
import IE683.fuser.list.WritersFuser;
import IE683.fuser.numeric.YearFuser;
import IE683.fuser.numeric.average.AvgVoteFuser;
import IE683.fuser.numeric.average.BudgetFuser;
import IE683.fuser.numeric.average.DurationFuser;
import IE683.fuser.numeric.average.ImdbScoreFuser;
import IE683.fuser.numeric.average.ImdbVotesFuser;
import IE683.fuser.TitleFuser;
import IE683.fuser.list.ActorsFuser; // I guess, it needs to be implemented, right?
import IE683.fuser.list.CountriesFuser;
import IE683.fuser.list.DirectorsFuser;
import IE683.fuser.list.GenresFuser;
import IE683.fuser.list.LanguagesFuser;
import IE683.fuser.list.ProductionCompaniesFuser;
import IE683.model.Movie;
import IE683.model.MovieXMLFormatter;
import IE683.model.MovieXMLReader;
import de.uni_mannheim.informatik.dws.winter.model.FusibleHashedDataSet;
import de.uni_mannheim.informatik.dws.winter.datafusion.CorrespondenceSet;
import de.uni_mannheim.informatik.dws.winter.datafusion.DataFusionEngine;
import de.uni_mannheim.informatik.dws.winter.datafusion.DataFusionEvaluator;
import de.uni_mannheim.informatik.dws.winter.datafusion.DataFusionStrategy;
import de.uni_mannheim.informatik.dws.winter.matching.MatchingEngine;
import de.uni_mannheim.informatik.dws.winter.matching.blockers.StandardRecordBlocker;
import de.uni_mannheim.informatik.dws.winter.matching.rules.LinearCombinationMatchingRuleWithPenalty;
import de.uni_mannheim.informatik.dws.winter.model.Correspondence;
import de.uni_mannheim.informatik.dws.winter.model.FusibleDataSet;
import de.uni_mannheim.informatik.dws.winter.model.HashedDataSet;
import de.uni_mannheim.informatik.dws.winter.model.defaultmodel.Attribute;
import de.uni_mannheim.informatik.dws.winter.processing.Processable;
import de.uni_mannheim.informatik.dws.winter.utils.WinterLogManager;
import de.uni_mannheim.informatik.dws.winter.model.RecordGroupFactory;
import de.uni_mannheim.informatik.dws.winter.model.DataSet;


import org.slf4j.Logger;

public class DataFusionMain {
	
	private static final Logger logger = WinterLogManager.activateLogger("default");
	
	public static void main( String[] args ) throws Exception
    {
		// Load the Data into FusibleDataSet
		logger.info("*\tLoading datasets\t*");
		
		FusibleDataSet<Movie, Attribute> ds1 =  new FusibleHashedDataSet<>();
		new MovieXMLReader().loadFromXML(new File("data/input/netflix.xml"), "/movies/movie", ds1);
		ds1.printDataSetDensityReport();

		FusibleDataSet<Movie, Attribute> ds2 =  new FusibleHashedDataSet<>();
		new MovieXMLReader().loadFromXML(new File("data/input/streaming.xml"), "/movies/movie", ds2);
		ds2.printDataSetDensityReport();
		
		
		FusibleDataSet<Movie, Attribute> ds3 = new FusibleHashedDataSet<>();
		new MovieXMLReader().loadFromXML(new File("data/input/imdb.xml"), "/movies/movie", ds3);
		ds3.printDataSetDensityReport();

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
		correspondences.loadCorrespondences(new File("data/correspondances/corr_netflix_streaming.csv"),ds1, ds2);
		correspondences.loadCorrespondences(new File("data/correspondances/corr_netflix_imdb.csv"),ds1, ds3);

		// write group size distribution
		correspondences.printGroupSizeDistribution();
		
		
		// load the gold standard
		logger.info("*\tEvaluating results\t*");
		DataSet<Movie, Attribute> gs = new FusibleHashedDataSet<>();
		new MovieXMLReader().loadFromXML(new File("data/gold_standards/gs_data_fusion.xml"), "/movies/movie", gs);

		for(Movie m : gs.get()) {
			logger.info(String.format("gs: %s", m.getIdentifier()));
		}

		// define the fusion strategy
		DataFusionStrategy<Movie, Attribute> strategy = new DataFusionStrategy<>(new MovieXMLReader());
		// write debug results to file
		strategy.activateDebugReport("data/output/debugResultsDatafusion.csv", -1, gs);
		
		
		// adding attribute fuser strategies based on Honglin's insight, new implementations are required, please refer comments for explanations.   
		// We need a 'Union' of actor, director and writer names to be defined in the below stated rules.
		strategy.addAttributeFuser(Movie.ACTORS, new ActorsFuser(), new ActorsEvaluationRule()); // ActorsEvaluationRule needs to be changed.
		strategy.addAttributeFuser(Movie.DIRECTORS, new DirectorsFuser(), new DirectorsEvaluationRule()); // DirectorsEvaluationRule needs to be changed.
		strategy.addAttributeFuser(Movie.WRITERS, new WritersFuser(), new WritersEvaluationRule()); // WritersEvaluationRule needs to be changed.
		// Ideally, we should just copy title from IMDb, in case that doesn't work let's go for longest string.
		strategy.addAttributeFuser(Movie.TITLE, new TitleFuser(), new TitleEvaluationRule()); // TitleEvaluationRule should directly take data from IMDb dataset, if possible. 
		strategy.addAttributeFuser(Movie.COUNTRIES, new CountriesFuser(), new CountriesEvaluationRule(0.80)); // Looks good from my end, no changes required.
		strategy.addAttributeFuser(Movie.LANGUAGES, new LanguagesFuser(), new LanguagesEvaluationRule(0.80)); // Looks good from my end, no changes required.
		strategy.addAttributeFuser(Movie.GENRES, new GenresFuser(), new GenresEvaluationRule(0.90)); // Looks good from my end, no changes required.
		strategy.addAttributeFuser(Movie.PRODUCTION_COMPANIES, new ProductionCompaniesFuser(), new ProductionCompaniesEvaluationRule(0.75)); // Looks good from my end, no changes required.
		strategy.addAttributeFuser(Movie.AVG_VOTE, new AvgVoteFuser(), new AvgVoteEvaluationRule()); // We should take average of IMDb + Streaming data values with more weightage to IMDb field.
		strategy.addAttributeFuser(Movie.BUDGET, new BudgetFuser(), new BudgetEvaluationRule()); // We should take average of IMDb + Netflix data values with more weightage to IMDb field.
		strategy.addAttributeFuser(Movie.DURATION, new DurationFuser(), new DurationEvaluationRule()); // We should take average of IMDb + Streaming data values only with more weightage to IMDb field. (Ignoring Netflix)
		strategy.addAttributeFuser(Movie.IMDB_SCORE, new ImdbScoreFuser(), new ImdbScoreEvaluationRule()); // We should take average of IMDb + Netflix data values only with more weightage to IMDb field.
		strategy.addAttributeFuser(Movie.IMDB_VOTES, new ImdbVotesFuser(), new ImdbVotesEvaluationRule()); // We should take average of IMDb + Netflix data values only with more weightage to IMDb field.
		strategy.addAttributeFuser(Movie.YEAR, new YearFuser(), new YearEvaluationRule()); // We should take smallest value for this amongst all datasets, couldn't find the implementation for this.		
		
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
		DataFusionEvaluator<Movie, Attribute> evaluator = new DataFusionEvaluator<>(strategy, new RecordGroupFactory<Movie, Attribute>());
		double accuracy = evaluator.evaluate(fusedDataSet, gs, null);
		logger.info(String.format("*\tAccuracy: %.2f", accuracy));	
    }
}
