import csv
import redis
import json

FILENAME = "d_bahnhof_alle.csv"
HOST = 'localhost'
PORT = 6379
STATION_PREFIX = "station:"


def read_csv_file(file_path):
    ds100 = []
    data = []

    # load data
    with open(file_path, 'r', encoding="utf8") as file:
        csv_reader = csv.DictReader(file, delimiter=';')

        for row in csv_reader:
            data.append(row)

    # process data
    for item in data:
        # extract ds100 identifiers
        ds100.append(item.pop('ds100'))

        # process geo values to float
        item["longitude"] = float(item["longitude"].replace(",", "."))
        item["latitude"] = float(item["latitude"].replace(",", "."))

    return ds100, data


def main():
    # read csv file
    ds100, data = read_csv_file(FILENAME)

    # assert length
    assert (len(ds100) == len(data))

    # open connection to database
    r = redis.Redis(host=HOST, port=PORT, decode_responses=True)

    # POST to redis database
    # naming convention: station:DS100code
    for it, code in enumerate(ds100):
        r.hset(STATION_PREFIX + code, mapping=data[it])


if __name__ == "__main__":
    main()
