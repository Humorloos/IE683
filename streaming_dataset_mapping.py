import pandas as pd
import numpy as np
from constants import MOVIES_DATA_DIR
from utils import get_integrated_schema_target_df, to_xml


target_df = get_integrated_schema_target_df()
source_df = pd.read_csv(MOVIES_DATA_DIR.joinpath('streaming.csv'))

# lower the columns
source_df.columns = source_df.columns.str.lower()

# id
target_df.id = source_df.id.apply(lambda id: 'streaming' + str(id))

# move as it is
simple_move_map = {
    'title' : 'title',
    'duration' : 'runtime',
    'year' : 'year',
    'imdb score' : 'imdb',
    'disney+ flag' : 'disney+',
    'hulu flag' : 'hulu',
    'netflix flag' : 'netflix',
    'prime video flag' : 'prime video'
    }
target_df[list(simple_move_map.keys())] = source_df[simple_move_map.values()]

# move list(string)
list_move_map = {
    'genre' : 'genres',
    'director' : 'directors',
    'language' : 'language',
    'country' : 'country'
}
target_df[list(list_move_map.keys())] = source_df[list_move_map.values()].apply(lambda c: c.str.split(','))

# source
target_df['source'] = 'streaming'

# to xml
target_df.columns = target_df.columns.str.replace(' ', '_')
pd.DataFrame.to_xml = to_xml
target_df.to_xml(MOVIES_DATA_DIR.joinpath('streaming.xml'))
