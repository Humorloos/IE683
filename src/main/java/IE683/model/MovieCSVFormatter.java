package IE683.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import de.uni_mannheim.informatik.dws.winter.model.DataSet;
import de.uni_mannheim.informatik.dws.winter.model.defaultmodel.Attribute;
import de.uni_mannheim.informatik.dws.winter.model.io.CSVDataSetFormatter;

public class MovieCSVFormatter extends CSVDataSetFormatter<Movie, Attribute> {

	@Override
	public String[] getHeader(List<Attribute> orderedHeader) {
		// Returning all attributes with reference to integrated schema structure.
		return new String[] { "id", "source", "genre", "title", 
				              "actor_names", "release_year", "director", 
				              "duration", "language", "imdb_votes", 
				              "production_company", "imdb_score", 
				              "writer", "avg_vote", "country", "budget", 
				              "original_title", "reviews_from_critics", 
				              "reviews_from_users", "title_id", 
				              "country_availability", "hidden_gem_score", "image", 
				              "imdb_link", "netflix_link", "netflix_release_date", 
				              "poster", "series_or_movie", "tags", "disney_flag", 
				              "hulu_flag", "netflix_flag", "prime_video_flag" };
	}
	
	// Converting the double values into string.
	public String doubleToStringConvertor(Double value) {
		if (!value.equals(null)) {
		String string_value = Double.toString(value);
		return string_value;
		}
		return null;
	}
	
	// Converting the list values into string.
	public String listToStringConvertor(List<String> value) {
		
		if (!value.equals(null)) {
		String string_value = "[";
		
		for (int i = 0; i < value.size(); i++) {
			if (i == (value.size()-1))
				string_value = string_value+ value.get(i);
			else
				string_value = string_value+ value.get(i)+ ",";
			
		}
		
		string_value = string_value + "]"; 
		return string_value;
		}
		return null;
	}

	// Converting the date values into string.
	public String dateToStringConvertor(LocalDateTime value) {
		if (!value.equals(null)) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String string_value = value.format(formatter); 
		return string_value;
		}
		return null;
	}
	
	@Override
	public String[] format(Movie record, DataSet<Movie, Attribute> dataset, List<Attribute> orderedHeader) {
		// Returning all attributes with reference to integrated schema structure.
		// Converting list, double and date attributes to string values
		return new String[] {
				record.getIdentifier(),
				record.getSource(),
				listToStringConvertor(record.getGenre()),
				record.getTitle(),
				listToStringConvertor(record.getActors()),
				doubleToStringConvertor(record.getReleaseYear()),
				listToStringConvertor(record.getDirectors()),
				doubleToStringConvertor(record.getDuration()),
				listToStringConvertor(record.getLanguages()),
				record.getImdbVotes(),
				listToStringConvertor(record.getProductionCompanies()),
				doubleToStringConvertor(record.getImdbScore()),
				listToStringConvertor(record.getWriters()),
				doubleToStringConvertor(record.getAvgVote()),
				listToStringConvertor(record.getCountries()),
				doubleToStringConvertor(record.getBudget()),
				record.getOriginal_title(),
				record.getReviews_from_critics(),
				record.getReviews_from_users(),
				record.getTitle_id(),
				listToStringConvertor(record.getCountries_availability()),
				doubleToStringConvertor(record.getHidden_gem_score()),
				record.getImage(),
				record.getImdb_link(),
				record.getNetflix_link(),
				dateToStringConvertor(record.getNetflix_release_date()),
				record.getPoster(),
				record.getSeries_or_movie(),
				listToStringConvertor(record.getTags()),
				record.getDisney_flag(),
				record.getHulu_flag(),
				record.getNetflix_flag(),
				record.getPrime_video_flag()
		};
	}
}
