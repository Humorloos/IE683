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
package IE683.model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.uni_mannheim.informatik.dws.winter.model.io.XMLFormatter;

/**
 * {@link XMLFormatter} for {@link Movie}s.
 *
 * @author Oliver Lehmberg (oli@dwslab.de)
 *
 */
public class MovieXMLFormatter extends XMLFormatter<Movie> {

	/*ActorXMLFormatter actorFormatter = new ActorXMLFormatter(); */

	@Override
	public Element createRootElement(Document doc) {
		return doc.createElement("movies");
	}

	@Override
	public Element createElementFromRecord(Movie record, Document doc) {
		Element movie = doc.createElement("movie");

		movie.appendChild(createTextElement("id", record.getIdentifier(), doc));

		movie.appendChild(createTextElement("title",
				record.getTitle(),
				doc));
		movie.appendChild(createTextElement("source",
				record.getSource(),
				doc));
		movie.appendChild(createTextElement("imdbVotes",
				record.getImdb_votes(),
				doc));
		movie.appendChild(createTextElement("original_title",
				record.getOriginal_title(),
				doc));
		movie.appendChild(createTextElement("reviews_from_critics",
				record.getReviews_from_critics(),
				doc));
		movie.appendChild(createTextElement("reviews_from_users",
				record.getReviews_from_users(),
				doc));
		movie.appendChild(createTextElement("title_id",
				record.getTitle_id(),
				doc));
		movie.appendChild(createTextElement("image",
				record.getImage(),
				doc));
		movie.appendChild(createTextElement("imdb_link",
				record.getImdb_link(),
				doc));
		movie.appendChild(createTextElement("netflix_link",
				record.getNetflix_link(),
				doc));
		movie.appendChild(createTextElement("poster",
				record.getPoster(),
				doc));
		movie.appendChild(createTextElement("series_or_movie",
				record.getSeries_or_movie(),
				doc));
		movie.appendChild(createTextElement("disney_flag",
				record.getDisney_flag(),
				doc));
		movie.appendChild(createTextElement("hulu_flag",
				record.getHulu_flag(),
				doc));
		movie.appendChild(createTextElement("netflix_flag",
				record.getNetflix_flag(),
				doc));
		movie.appendChild(createTextElement("prime_video_flag",
				record.getPrime_video_flag(),
				doc));
		movie.appendChild(createTextElement("imdbScore",
				record.getImdb_score(),
				doc));
		movie.appendChild(createTextElement("avgVote",
				record.getAvg_vote(),
				doc));
		movie.appendChild(createTextElement("budget",
				record.getBudget(),
				doc));
		movie.appendChild(createTextElement("hidden_gem_score",
				record.getHidden_gem_score(),
				doc));
		movie.appendChild(createTextElement("duration",
				record.getDuration(),
				doc));
		movie.appendChild(createTextElement("releaseYear",
				record.getReleaseYear(),
				doc));
		movie.appendChild(createTextElement("netflix_release_date",
				record.getNetflix_release_date(),
				doc));
		movie.appendChild(createTextElement("productionCompanies",
				record.getProduction_companies(),
				doc));
		movie.appendChild(createTextElement("genres",
				record.getGenres(),
				doc));
		movie.appendChild(createTextElement("actors",
				record.getActors(),
				doc));
		movie.appendChild(createTextElement("directors",
				record.getDirectors(),
				doc));
		movie.appendChild(createTextElement("languages",
				record.getLanguages(),
				doc));
		movie.appendChild(createTextElement("writers",
				record.getWriters(),
				doc));
		movie.appendChild(createTextElement("countries",
				record.getCountries(),
				doc));
		movie.appendChild(createTextElement("countries_availability",
				record.getCountries_availability(),
				doc));
		movie.appendChild(createTextElement("tags",
				record.getTags(),
				doc));
		/*movie.appendChild(createActorsElement(record, doc));*/

		return movie;
	}

	/*protected Element createTextElementWithProvenance(String name,
			String value, String provenance, Document doc) {
		Element elem = createTextElement(name, value, doc);
		elem.setAttribute("provenance", provenance);
		return elem;
	}

	protected Element createActorsElement(Movie record, Document doc) {
		Element actorRoot = actorFormatter.createRootElement(doc);

		for (Actor a : record.getActors()) {
			actorRoot.appendChild(actorFormatter
					.createElementFromRecord(a, doc));
		}

		return actorRoot;
	} */

}
