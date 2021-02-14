import datetime
import json
import time

from datetime import date
from pip._vendor import requests
import sqlite3

# https://oauth.vk.com/authorize?client_id=#######&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=friends,photos,video,notes,groups,status&response_type=token&v=5.52

token = "&v=5.52&access_token=07f9eaace21ea06cd617dd4ecc2455f8b32fa3a082106ebe48aaed9f02506e3091eefbf35a692691600d4"

getAge = "https://api.vk.com/method/users.get?fields=bdate,city&user_ids="
getFriends = "https://api.vk.com/method/friends.get?user_id="
getGroups = "https://api.vk.com/method/groups.get?user_id="
getNotes = "https://api.vk.com/method/notes.get?user_id="
getPhotos = "https://api.vk.com/method/photos.get?album_id=profile&owner_id="
getVideos = "https://api.vk.com/method/video.get?owner_id="

globalUsers = []
end_deep = 1

conn = sqlite3.connect("vk_users_info.db")
cursor = conn.cursor()

def getJson(url):
    j = json.loads(requests.get(url).text)
    time.sleep(0.2)
    return json.loads(json.dumps(j["response"]))

def findUser(userID):
    cursor.execute("select user_id from users where user_id = " + str(userID))
    return len(cursor.fetchall())

class user:
    age = 0
    gcoun = 0
    pcoun = 0
    vcoun = 0
    ncoun = 0
    fcoun = 0
    city = ''
    city_dist = 0

    friends = []

    def __init__(self, deep, userID):
        j = getJson(getAge + str(userID) + token)
        today = date.today()
        for item in j:
            try:
                self.city = item["city"]['title']
                self.city_dist = item["city"]['id']

                born = datetime.datetime.strptime(item["bdate"], '%d.%m.%Y')
                self.age = today.year - born.year - ((today.month, today.day) < (born.month, born.day))
            except:
                self.age = 0

        j = getJson(getGroups + str(userID) + token)
        self.gcoun = j["count"]

        j = getJson(getNotes + str(userID) + token)
        self.ncoun = j["count"]

        j = getJson(getPhotos + str(userID) + token)
        self.pcoun = j["count"]

        j = getJson(getVideos + str(userID) + token)
        self.vcoun = j["count"]

        j = getJson(getFriends + str(userID) + token)
        self.fcoun = j["count"]

        if findUser(userID) == 0:
            cursor.execute(
                "Insert into users values(?, ?, ?, ?, ?, ?, ?, ?, ?)",
                [str(userID), self.age, self.gcoun, self.pcoun, self.vcoun, self.ncoun, self.fcoun, str(self.city), self.city_dist])
        else:
            cursor.execute(
                "UPDATE users SET age=? and groups=? and photos=? and videos=? and notes=? and friends=? and city=? and city_dist=? where user_id=?",
                [self.age, self.gcoun, self.pcoun, self.vcoun, self.ncoun, self.fcoun, self.city,
                 str(userID)])
        conn.commit()

        if end_deep != deep:
            blackList = []
            for item in j["items"]:
                if item not in globalUsers:
                    globalUsers.append(item)
                else:
                    blackList.append(item)

            for item in j["items"]:
                if item not in blackList:
                    self.friends.append(user(deep + 1, item))


if __name__ == '__main__':
    cursor.execute("drop table users")
    cursor.execute("SELECT count(*) FROM sqlite_master WHERE type='table' AND name='users';")
    results = cursor.fetchall()

    if results.pop(0)[0] == 0:
        cursor.execute(
            """CREATE TABLE users (user_id text, age int, groups int,  photos int, videos int, notes int, friends int, city text, city_dist int)""")
        conn.commit()

    user(0, "95795901")

    conn.close()