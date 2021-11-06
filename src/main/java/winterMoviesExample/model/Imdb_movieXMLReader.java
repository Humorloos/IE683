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
package winterMoviesExample.model;;

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
public class Imdb_movieXMLReader extends XMLMatchableReader<Actor, Attribute> {

	@Override
	public Actor createModelFromElement(Node node, String provenanceInfo) {
		String id = getValueFromChildElement(node, "id");

		// create the object with id and provenance information
		Actor actor = new Actor(id, provenanceInfo);

		// fill the attributes
		Imdb_movie.setTitle(getValueFromChildElement(node, "title"));
		Imdb_movie.setSource(getValueFromChildElement(node, "source"));
		Imdb_movie.setGenres(getValueFromChildElement(node, "genres"));
		Imdb_movie.setActors(getValueFromChildElement(node, "actors"));
		Imdb_movie.setRelease_date(getValueFromChildElement(node, "release_date"));
		Imdb_movie.setDirectors(getValueFromChildElement(node, "directors"));
		Imdb_movie.setDuration(getValueFromChildElement(node, "duration"));
		Imdb_movie.setLanguages(getValueFromChildElement(node, "languages"));
		Imdb_movie.setImdb_votes(getValueFromChildElement(node, "imdb_votes"));
		Imdb_movie.setProduction_companies(getValueFromChildElement(node, "production_companies"));
		Imdb_movie.setImdb_score(getValueFromChildElement(node, "imdb_score"));
		Imdb_movie.setWriters(getValueFromChildElement(node, "writers"));
		Imdb_movie.setAvg_vote(getValueFromChildElement(node, "avg_vote"));
		Imdb_movie.setCountry(getValueFromChildElement(node, "country"));
		Imdb_movie.setYear(getValueFromChildElement(node, "year"));
		Imdb_movie.setBudget(getValueFromChildElement(node, "budget"));
		Imdb_movie.setOriginal_title(getValueFromChildElement(node, "original_title"));
		Imdb_movie.setReviews_from_critics(getValueFromChildElement(node, "reviews_from_critics"));
		Imdb_movie.setReviews_from_users(getValueFromChildElement(node, "review_from_users"));
		Imdb_movie.setTitle_id(getValueFromChildElement(node, "title_id"));
		Imdb_movie.setCountries_availability(getValueFromChildElement(node, "countries_availability"));
		Imdb_movie.setHidden_gem_score(getValueFromChildElement(node, "hidden_gem_score"));
		Imdb_movie.setImage(getValueFromChildElement(node, "image"));
		Imdb_movie.setImdb_link(getValueFromChildElement(node, "imdb_link"));
		Imdb_movie.setNetflix_link(getValueFromChildElement(node, "netflix_link"));
		Imdb_movie.setNetflix_release_date(getValueFromChildElement(node, "netflix_release_date"));
		Imdb_movie.setPoster(getValueFromChildElement(node, "poster"));
		Imdb_movie.setSeries_or_Imdb_movies(getValueFromChildElement(node, "series_or_Imdb_movies"));
		Imdb_movie.setTags(getValueFromChildElement(node, "tags"));
		Imdb_movie.setDisney_flag(getValueFromChildElement(node, "disney_flag"));
		Imdb_movie.setHulu_flag(getValueFromChildElement(node, "hulu_flag"));
		Imdb_movie.setNetflix_flag(getValueFromChildElement(node, "netflix_flag"));
		Imdb_movie.setPrime_video_flag(getValueFromChildElement(node, "prime_video_flag"));


		return imdb_movie;
		}

	}
