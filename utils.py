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
	

def to_xml(df, filename=None, mode='w'):
    def row_to_xml(row):
        xml = ['\t<movie>']
        for i, col_name in enumerate(row.index):
            # for list columns
            if isinstance(row.iloc[i], list):
                xml.append(f'\t\t<{col_name}s>')
                for item in row.iloc[i]:
                    xml.append(f'\t\t\t<{col_name}>{item}</{col_name}>')
                xml.append(f'\t\t</{col_name}s>')
            # for normal columns
            else:
                xml.append(f'\t\t<{col_name}>{row.iloc[i]}</{col_name}>')
        xml.append('\t</movie>')
        return '\n'.join(xml)
    res = '\n'.join(df.apply(row_to_xml, axis=1))
    res = f'<movies>\n {res} \n</movies>'

    if filename is None:
        return res
    with open(filename, mode) as f:
        f.write(res)
