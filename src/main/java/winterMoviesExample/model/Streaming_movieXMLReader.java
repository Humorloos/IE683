/*
 * Copyright (c) 2017 Data and Web Science Group, University of Mannheim, Germany (http://dws.informatik.uni-mannheim.de/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */
package winterMoviesExample.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

import org.w3c.dom.Node;

import de.uni_mannheim.informatik.dws.winter.model.defaultmodel.Attribute;
import de.uni_mannheim.informatik.dws.winter.model.io.XMLMatchableReader;

/**
 * A {@link XMLMatchableReader} for {@link Actor}s.
 * 
 * @author Oliver Lehmberg (oli@dwslab.de)
 * 
 */
public class Streaming_movieXMLReader extends XMLMatchableReader<Streaming_movie, Attribute> {

	@Override
	public Streaming_movie createModelFromElement(Node node, String provenanceInfo) {
		String id = getValueFromChildElement(node, "id");

		// create the object with id and provenance information
		Streaming_movie Streaming_movie = new Streaming_movie(id, provenanceInfo);

		// fill the attributes
		Streaming_movie.setTitle(getValueFromChildElement(node, "title"));
		Streaming_movie.setSource(getValueFromChildElement(node, "source"));
		Streaming_movie.setGenres(getValueFromChildElement(node, "genres"));
		Streaming_movie.setActors(getValueFromChildElement(node, "actors"));
		Streaming_movie.setRelease_date(getValueFromChildElement(node, "release_date"));
		Streaming_movie.setDirectors(getValueFromChildElement(node, "directors"));
		Streaming_movie.setDuration(getValueFromChildElement(node, "duration"));
		Streaming_movie.setLanguages(getValueFromChildElement(node, "languages"));
		Streaming_movie.setImdb_votes(getValueFromChildElement(node, "imdb_votes"));
		Streaming_movie.setProduction_companies(getValueFromChildElement(node, "production_companies"));
		Streaming_movie.setImdb_score(getValueFromChildElement(node, "imdb_score"));
		Streaming_movie.setWriters(getValueFromChildElement(node, "writers"));
		Streaming_movie.setAvg_vote(getValueFromChildElement(node, "avg_vote"));
		Streaming_movie.setCountry(getValueFromChildElement(node, "country"));
		Streaming_movie.setYear(getValueFromChildElement(node, "year"));
		Streaming_movie.setBudget(getValueFromChildElement(node, "budget"));
		Streaming_movie.setOriginal_title(getValueFromChildElement(node, "original_title"));
		Streaming_movie.setReviews_from_critics(getValueFromChildElement(node, "reviews_from_critics"));
		Streaming_movie.setReviews_from_users(getValueFromChildElement(node, "review_from_users"));
		Streaming_movie.setTitle_id(getValueFromChildElement(node, "title_id"));
		Streaming_movie.setCountries_availability(getValueFromChildElement(node, "countries_availability"));
		Streaming_movie.setHidden_gem_score(getValueFromChildElement(node, "hidden_gem_score"));
		Streaming_movie.setImage(getValueFromChildElement(node, "image"));
		Streaming_movie.setImdb_link(getValueFromChildElement(node, "imdb_link"));
		Streaming_movie.setNetflix_link(getValueFromChildElement(node, "netflix_link"));
		Streaming_movie.setNetflix_release_date(getValueFromChildElement(node, "netflix_release_date"));
		Streaming_movie.setPoster(getValueFromChildElement(node, "poster"));
		Streaming_movie.setSeries_or_Streaming_movies(getValueFromChildElement(node, "series_or_Streaming_movies"));
		Streaming_movie.setTags(getValueFromChildElement(node, "tags"));
		Streaming_movie.setDisney_flag(getValueFromChildElement(node, "disney_flag"));
		Streaming_movie.setHulu_flag(getValueFromChildElement(node, "hulu_flag"));
		Streaming_movie.setNetflix_flag(getValueFromChildElement(node, "netflix_flag"));
		Streaming_movie.setPrime_video_flag(getValueFromChildElement(node, "prime_video_flag"));


		return Streaming_movie;
		}

	}
