#%%

import pandas as pd
from matplotlib import pyplot as plt

from schema_mapping.constants import MOVIES_DATA_DIR

#%%
OUTPUT_DIR = MOVIES_DATA_DIR / 'output'

group_sizes = pd.read_csv(
    OUTPUT_DIR / 'groupSizeDistribution.csv') \
    .sort_values(by='Group Size')
#%%

# group_sizes['Frequency'].hist()
fig, ax = plt.subplots()
group_sizes.iloc[:25].plot.bar(y='Frequency', x='Group Size', width=0.8)
plt.savefig(OUTPUT_DIR / 'group_size_distribution.png')
