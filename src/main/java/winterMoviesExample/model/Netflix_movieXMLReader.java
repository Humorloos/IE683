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
public class Netflix_movieXMLReader extends XMLMatchableReader<Netflix_movie, Attribute> {

	@Override
	public Netflix_movie createModelFromElement(Node node, String provenanceInfo) {
		String id = getValueFromChildElement(node, "id");

		// create the object with id and provenance information
		Netflix_movie Netflix_movie = new Netflix_movie(id, provenanceInfo);

		// fill the attributes
		Netflix_movie.setTitle(getValueFromChildElement(node, "title"));
		Netflix_movie.setSource(getValueFromChildElement(node, "source"));
		Netflix_movie.setGenres(getValueFromChildElement(node, "genres"));
		Netflix_movie.setActors(getValueFromChildElement(node, "actors"));
		Netflix_movie.setRelease_date(getValueFromChildElement(node, "release_date"));
		Netflix_movie.setDirectors(getValueFromChildElement(node, "directors"));
		Netflix_movie.setDuration(getValueFromChildElement(node, "duration"));
		Netflix_movie.setLanguages(getValueFromChildElement(node, "languages"));
		Netflix_movie.setImdb_votes(getValueFromChildElement(node, "imdb_votes"));
		Netflix_movie.setProduction_companies(getValueFromChildElement(node, "production_companies"));
		Netflix_movie.setImdb_score(getValueFromChildElement(node, "imdb_score"));
		Netflix_movie.setWriters(getValueFromChildElement(node, "writers"));
		Netflix_movie.setAvg_vote(getValueFromChildElement(node, "avg_vote"));
		Netflix_movie.setCountry(getValueFromChildElement(node, "country"));
		Netflix_movie.setYear(getValueFromChildElement(node, "year"));
		Netflix_movie.setBudget(getValueFromChildElement(node, "budget"));
		Netflix_movie.setOriginal_title(getValueFromChildElement(node, "original_title"));
		Netflix_movie.setReviews_from_critics(getValueFromChildElement(node, "reviews_from_critics"));
		Netflix_movie.setReviews_from_users(getValueFromChildElement(node, "review_from_users"));
		Netflix_movie.setTitle_id(getValueFromChildElement(node, "title_id"));
		Netflix_movie.setCountries_availability(getValueFromChildElement(node, "countries_availability"));
		Netflix_movie.setHidden_gem_score(getValueFromChildElement(node, "hidden_gem_score"));
		Netflix_movie.setImage(getValueFromChildElement(node, "image"));
		Netflix_movie.setImdb_link(getValueFromChildElement(node, "imdb_link"));
		Netflix_movie.setNetflix_link(getValueFromChildElement(node, "netflix_link"));
		Netflix_movie.setNetflix_release_date(getValueFromChildElement(node, "netflix_release_date"));
		Netflix_movie.setPoster(getValueFromChildElement(node, "poster"));
		Netflix_movie.setSeries_or_Netflix_movies(getValueFromChildElement(node, "series_or_Netflix_movies"));
		Netflix_movie.setTags(getValueFromChildElement(node, "tags"));
		Netflix_movie.setDisney_flag(getValueFromChildElement(node, "disney_flag"));
		Netflix_movie.setHulu_flag(getValueFromChildElement(node, "hulu_flag"));
		Netflix_movie.setNetflix_flag(getValueFromChildElement(node, "netflix_flag"));
		Netflix_movie.setPrime_video_flag(getValueFromChildElement(node, "prime_video_flag"));


		return Netflix_movie;
		}

	}
