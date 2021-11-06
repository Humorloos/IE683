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

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import de.uni_mannheim.informatik.dws.winter.model.AbstractRecord;
import de.uni_mannheim.informatik.dws.winter.model.defaultmodel.Attribute;

/**
 * A {@link AbstractRecord} representing a movie.
 * 
 * @author Oliver Lehmberg (oli@dwslab.de)
 * 
 */
public class Imdb_movie extends AbstractRecord<Attribute> implements Serializable {

	/*
	 <movie>
        <id>imdb_1</id>
        <source>imdb</source>
        <genres>
            <genre>Romance</genre>
        </genres>
        <title>nan</title>
        <actors>
            <actor_names>Blanche Bayliss</actor_names>
            <actor_names>William Courtenay</actor_names>
            <actor_names>Chauncey Depew</actor_names>
        </actors>
        <release_date>1894-10-09 00:00:00</release_date>
        <directors>
            <director>Alexander Black</director>
        </directors>
        <duration>nan</duration>
        <languages>
            <language>None</language>
        </languages>
        <imdb_votes>nan</imdb_votes>
        <production_companies>
            <production_company>Alexander Black Photoplays</production_company>
        </production_companies>
        <imdb_score>nan</imdb_score>
        <writers>
            <writer>Alexander Black</writer>
        </writers>
        <avg_vote>nan</avg_vote>
        <countries>
            <country>USA</country>
        </countries>
        <year>nan</year>
        <budget>nan</budget>
        <original_title>nan</original_title>
        <reviews_from_critics>nan</reviews_from_critics>
        <reviews_from_users>nan</reviews_from_users>
        <title_id>nan</title_id>
        <country_availability>nan</country_availability>
        <hidden_gem_score>nan</hidden_gem_score>
        <image>nan</image>
        <imdb_link>nan</imdb_link>
        <netflix_link>nan</netflix_link>
        <netflix_release_date>nan</netflix_release_date>
        <poster>nan</poster>
        <series_or_movie>nan</series_or_movie>
        <tags>nan</tags>
        <disney_flag>nan</disney_flag>
        <hulu_flag>nan</hulu_flag>
        <netflix_flag>nan</netflix_flag>
        <prime_video_flag>nan</prime_video_flag>
    </movie>

	 */

	private static final long serialVersionUID = 1L;

	public Imdb_movie(String identifier, String provenance) {
		super(identifier, provenance);
		actors = new LinkedList<>();
	}

	protected String id;
	private String source;
	private List<String> genres = new ArrayList<>();
	private String title;
	private List<String> actors = new ArrayList<>() ;
	private LocalDateTime release_date;
	private List<String> directors = new ArrayList<>() ;
	private int duration;
	private List<String> languages = new ArrayList<>() ;
	private String imdb_votes;
	private List<String> production_companies = new ArrayList<>() ;
	private double imdb_score;
	private List<String> writers = new ArrayList<>() ;
	private double avg_vote;
	private List<String> countries = new ArrayList<>() ;
	private int year;
	private double budget;
	private String original_title;
	private String reviews_from_critics;
	private String reviews_from_users;
	private String title_id;
	private List<String> countries_availability = new ArrayList<>() ;
	private double hidden_gem_score;
	private String image;
	private String imdb_link;
	private String netflix_link;
	private LocalDateTime netflix_release_date;
	private String poster;
	private String series_or_movies;
	private List<String> tags = new ArrayList<>() ;
	private String disney_flag;
	private String hulu_flag;
	private String netflix_flag;
	private String prime_video_flag;

	@Override
	public String getIdentifier() {
		return id;
	}

	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	 public List<String> getGenre() {
	        return genres;
	    }
	 public void setGenre(List<String> genres) {
		 this.genres = genres;
		 }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getActors() {
        return actors;
    }
    public void setActors(List<String> actors) {
	 this.actors = actors;
	 }
	public LocalDateTime getRelease_date() {
		return release_date;
	}

	public void setRelease_date(LocalDateTime release_date) {
		this.release_date = release_date;
	}
	public List<String> getDirectors() {
        return directors;
    }
    public void setDirectors(List<String> directors) {
	 this.directors = directors;
	 }
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	public List<String> getLanguages() {
        return languages;
    }
    public void setLanguages(List<String> languages) {
	 this.languages = languages;
	 }
	public String getImdb_votes() {
		return imdb_votes;
	}

	public void setIdb_votes(String imdb_votes) {
		this.imdb_votes = imdb_votes;
	}
	public List<String> getProduction_companies() {
        return production_companies;
    }
    public void setProduction_companies(List<String> production_companies) {
	 this.production_companies = production_companies;
	 }
	public double getImdb_score() {
		return imdb_score;
	}

	public void setImdb_score(double imdb_score) {
		this.imdb_score = imdb_score;
	}
	public List<String> getWriters() {
        return writers;
    }
    public void setWriters(List<String> writers) {
	 this.writers = writers;
	 }
	public double getAvg_vote() {
		return avg_vote;
	}

	public void setAvg_vote(double avg_vote) {
		this.avg_vote = avg_vote;
	}
	public List<String> getCountries() {
        return countries;
    }
    public void setCountries(List<String> countries) {
	 this.countries = countries;
	 }
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	public double getBudget() {
		return budget;
	}

	public void setbudget(double budget) {
		this.budget = budget;
	}
	public String getOriginal_title() {
		return original_title;
	}

	public void setOriginal_title(String original_title) {
		this.original_title = original_title;
	}
	public String getReviews_from_critics() {
		return reviews_from_critics;
	}

	public void setReviews_from_critics(String reviews_from_critics) {
		this.reviews_from_critics = reviews_from_critics;
	}
	public String getReviews_from_users() {
		return reviews_from_users;
	}

	public void setReviews_from_users(String reviews_from_users) {
		this.reviews_from_users = reviews_from_users;
	}
	public String getTitle_id() {
		return title_id;
	}

	public void setTitle_id(String title_id) {
		this.title_id = title_id;
	}
	public List<String> getCountries_availability() {
		return countries_availability;
	}

	public void setCountries_availability(List<String> countries_availability) {
		this.countries_availability = countries_availability;
	}
	public double getHidden_gem_score() {
		return hidden_gem_score;
	}

	public void setHidden_gem_score(double hidden_gem_score) {
		this.hidden_gem_score = hidden_gem_score;
	}
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	public String getImdb_link() {
		return imdb_link;
	}

	public void setImdb_link(String imdb_link) {
		this.imdb_link = imdb_link;
	}
	public String getNetflix_link() {
		return netflix_link;
	}

	public void setNetflix_link(String netflix_link) {
		this.netflix_link = netflix_link;
	}
	public LocalDateTime getNetflix_release_date() {
		return netflix_release_date;
	}

	public void setNetflix_release_date(LocalDateTime netflix_release_date) {
		this.netflix_release_date = netflix_release_date;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getSeries_or_movies() {
		return series_or_movies;
	}

	public void setSeries_or_movies(String series_or_movies) {
		this.series_or_movies = series_or_movies;
	}
	public List<String> getTags() {
        return tags;
    }
 public void setTags(List<String> tags) {
	 this.tags = tags;
	 }
	public String getDisney_flag() {
		return disney_flag;
	}

	public void setDisney_flag(String disney_flag) {
		this.disney_flag = disney_flag;
	}
	public String getHulu_flag() {
		return hulu_flag;
	}

	public void setHulu_flag(String hulu_flag) {
		this.hulu_flag = hulu_flag;
	}
	public String getNetflix_flag() {
		return netflix_flag;
	}

	public void setNetflix_flag(String netflix_flag) {
		this.netflix_flag = netflix_flag;
	}
	public String getPrime_video_flag() {
		return prime_video_flag;
	}

	public void setPrime_video_flag(String prime_video_flag) {
		this.prime_video_flag = prime_video_flag;
	}
	

	private Map<Attribute, Collection<String>> provenance = new HashMap<>();
	private Collection<String> recordProvenance;

	public void setRecordProvenance(Collection<String> provenance) {
		recordProvenance = provenance;
	}

	public Collection<String> getRecordProvenance() {
		return recordProvenance;
	}

	public void setAttributeProvenance(Attribute attribute,
			Collection<String> provenance) {
		this.provenance.put(attribute, provenance);
	}

	public Collection<String> getAttributeProvenance(String attribute) {
		return provenance.get(attribute);
	}

	public String getMergedAttributeProvenance(Attribute attribute) {
		Collection<String> prov = provenance.get(attribute);

		if (prov != null) {
			return StringUtils.join(prov, "+");
		} else {
			return "";
		}
	}

	public static final Attribute SOURCE = new Attribute("Source");
	public static final Attribute TITLE = new Attribute("title");
	public static final Attribute ACTORS = new Attribute("actors");
	public static final Attribute DIRECTORS = new Attribute("directors");
	
	@Override
	public boolean hasValue(Attribute attribute) {
		if(attribute==SOURCE)
			return getSource() != null && !getSource().isEmpty();
		else if(attribute==TITLE)
			return getTitle() != null && !getTitle().isEmpty();
		else if(attribute==ACTORS)
			return getActors() != null;
		else if(attribute==DIRECTORS)
			return getDirectors() != null && getDirectors().size() > 0;
		else
			return false;
	}

	@Override
	public String toString() {
		return String.format("[Movie %s: %s / %s / %s]", getIdentifier(), getSource(),
				getTitle(), getActors().toString());
	}

	@Override
	public int hashCode() {
		return getIdentifier().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Imdb_movie){
			return this.getIdentifier().equals(((Imdb_movie) obj).getIdentifier());
		}else
			return false;
	}
	
	
	
}
