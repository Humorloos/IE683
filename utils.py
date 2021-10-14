import pandas as pd

from constants import MOVIES_DATA_DIR


def get_integrated_schema_target_df():
    """
    Generates empty DataFrame with all columns that are supposed to be filled with values from the Movie datasets
    """
    integrated_schema = pd.read_csv(MOVIES_DATA_DIR.joinpath('integrated_schema.csv'), sep=';').drop(
        columns='Class name')
    integrated_schema.columns = ['name', 'type', 'dataset']
    integrated_schema = integrated_schema.apply(lambda column: column.str.split(', '))
    integrated_schema = integrated_schema.explode(column=['type', 'name'])
    return pd.DataFrame(columns=list(integrated_schema['name']) + ['source'])