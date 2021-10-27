import pandas as pd

from constants import MOVIES_DATA_DIR
from utils import get_integrated_schema_target_df

target_df = get_integrated_schema_target_df()

netflix_df = pd.read_csv(MOVIES_DATA_DIR.joinpath('netflix.csv'))

# Attributes that can be copied (but target names are lower case)
lower_copy_columns = ["Title", "Hidden Gem Score", "Image", "IMDb Link", "Netflix Link",
                      "Netflix Release Date", "Poster", "Series or Movie", "IMDb Score", "IMDb Votes"]
target_df[[col.lower() for col in lower_copy_columns]] = netflix_df[lower_copy_columns]

# Attributes that have to be split into list[string], and sometimes renamed
lower_comma_space_to_list_columns = ["Genre", "Director", "Writer"]
comma_space_to_list_columns = {col.lower(): col for col in lower_comma_space_to_list_columns}
comma_space_to_list_columns.update({"actor names": "Actors",
                                    "language": "Languages",
                                    "production company": "Production House"})
target_df[list(comma_space_to_list_columns.keys())] = netflix_df[comma_space_to_list_columns.values()].apply(
    lambda column: column.str.split(', '))

# Split tags by only comma without trailing space
target_df.loc[netflix_df['Tags'].notnull(), 'tags'] = netflix_df['Tags'].dropna().apply(lambda tags: tags.split(','))

# Split country availability by only comma without trailing space
target_df["country availability"] = netflix_df["Country Availability"].str.split(',')

# Transform release date to datetime
target_df['release date'] = netflix_df['Release Date'].astype('datetime64')

# transform duration to minutes
duration_lookup = {'< 30 minutes': 15, '1-2 hour': 90, '> 2 hrs': 180, '30-60 mins': 45}
target_df.loc[netflix_df['Runtime'].notnull(), 'duration'] = netflix_df['Runtime'].dropna().apply(
    lambda runtime: duration_lookup[runtime])

# transform budget to float
target_df['budget'] = netflix_df['Boxoffice'].str.replace(r'\$|,', '').astype(float)

target_df['source'] = 'netflix'
