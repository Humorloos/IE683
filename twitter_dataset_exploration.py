import csv

import pandas as pd

from pandas_profiling import ProfileReport
from constants import TWITTER_DATA_DIR

twitter_friends = pd.read_csv(TWITTER_DATA_DIR.joinpath('friends.csv'), sep=r',(?=\S)', engine='python')
twitter_gender = pd.read_csv(TWITTER_DATA_DIR.joinpath('gender.csv'), encoding='ISO-8859-1')
twitter_users = pd.read_csv(TWITTER_DATA_DIR.joinpath('users.csv'), encoding='ISO-8859-1')

twitter_friends['avatar'] = twitter_friends['avatar'].str.strip("\"").str.replace('http://', 'https://')
twitter_friends['avatar'].isin(twitter_gender['profileimage']).sum()

twitter_friends['screenName'] = twitter_friends['screenName'].str.strip("\"")
twitter_friends['screenName'].isin(twitter_gender['name']).sum()
twitter_users['handle'].isin(twitter_gender['name']).sum()
twitter_users['handle'].isin(twitter_friends['screenName']).sum()
