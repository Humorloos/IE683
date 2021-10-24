import pandas as pd
import numpy as np

from constants import MOVIES_DATA_DIR
from utils import get_integrated_schema_target_df

target_df = get_integrated_schema_target_df()

imdb_df = pd.read_csv(MOVIES_DATA_DIR.joinpath('imdb.csv'))

# Rename columns name and remove underscore(_) from columns
imdb_df.columns = imdb_df.columns.str.replace('_', ' ')
imdb_df = imdb_df.rename(columns={'imdb title id': 'title id',
                                  'actors': 'actor names',
                                  'date published': 'release date'})

# Attributes that have to be split into list[string]
imdb_df_map = ({'genre': 'genre',
                'language': 'language',
                'actor names': 'actor names',
                'production company': 'production company',
                'writer': 'writer',
                'director': 'director',
                'country': 'country'})
target_df[list(imdb_df_map.keys())] = imdb_df[imdb_df_map.values()].apply(
    lambda column: column.str.split(', '))

# Transform release date to datetime
target_df['release date'] = pd.to_datetime(imdb_df['release date'], errors='coerce')

# make all budget into dollar and delete other currency and change datatype of budget
imdb_df['budget'] = imdb_df['budget'].astype(str).replace(r"[\a-zA-Z|]", np.nan)
imdb_df['budget'] = imdb_df['budget'].str.extract('(\d+)', expand=False)
target_df['budget'] = target_df['budget'].astype(float)

target_df['source'] = 'imbd'