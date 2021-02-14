import os
import shutil
from time import sleep
from bs4 import BeautifulSoup
from langdetect import detect
import nltk
from nltk.corpus import stopwords, wordnet
from nltk.stem import WordNetLemmatizer

data_dir = "files_data\\00"

def LemmatizeTitle(title):
    stopwords_rus = set(stopwords.words("russian"))
    sentences = nltk.sent_tokenize(title, "russian")

    words = []
    for sentence in sentences:
        words += nltk.word_tokenize(sentence)

    text = [word for word in words if word not in stopwords_rus]
    lemma_text = Lemmatize(text)

    return lemma_text

def Lemmatize(words):
    lemma_words = []
    lemmatizer = WordNetLemmatizer()

    for word in words:
        lemma_words.append(lemmatizer.lemmatize(word=word))

    return lemma_words

def FindWordsRUEN(files):
    english_pages = []
    russian_pages = []

    for file in files[:1000]:
        try:
            print("File's name: " + str(file))
            f = open(str(data_dir + "\\" + file), "r")
            text = BeautifulSoup(f.read(), "html.parser").text
            lang = detect(text)

            if lang == "en":
                english_pages.append(file)
                shutil.copy2(str(data_dir + "\\" + file), str(data_dir+"\\english"))
            elif lang == "ru":
                russian_pages.append(file)
                shutil.copy2(str(data_dir + "\\" + file), str(data_dir + "\\russian"))
        except:
            continue

    return (english_pages, russian_pages)

def isNews(files):
    stopwords_rus = set(stopwords.words("russian"))
    news = []
    news_words = ["газета", "новость", "сообщать", "пресс-служба"]

    for file in files:
        print("File's name: " + str(file))
        f = open(str(data_dir + "\\" + file), "r")
        page_text = BeautifulSoup(f.read(), "html.parser").text
        words = []
        sentences = nltk.sent_tokenize(page_text, "russian")

        for sentence in sentences:
            words += nltk.word_tokenize(sentence)

        text = [word for word in words if word not in stopwords_rus]
        lemma_text = Lemmatize(text)
        counter = 0

        for word in lemma_text:
            if word in news_words:
                counter += 1

        if counter >= 1:
            news.append(file)
            shutil.copy2(str(data_dir + "\\russian\\" + file), str(data_dir + "\\news"))
        counter = 0

    return news

def FilterByThems(files):
    media = []
    media_words = ["фильм", "песня", "книга", "художник"]
    it = []
    it_words = ["программист", "электронный", "интернет"]
    games = []
    games_words = ["релиз", "движок", "игра", "игрок", "шутер", "квест"]
    other = []
    stopwords_rus = set(stopwords.words("russian"))

    for file in files:
        print("File's name: " + str(file))

        f = open(str(data_dir + "\\" + file), "r", encoding="utf8")
        page_text = BeautifulSoup(f.read(), "html.parser").text
        words = []
        sentences = nltk.sent_tokenize(page_text, "russian")

        for sentence in sentences:
            words += nltk.word_tokenize(sentence)
        text = [word.lower() for word in words if word not in stopwords_rus]

        lemma_text = Lemmatize(text)
        media_counter = 0
        it_counter = 0
        games_counter = 0

        for word in lemma_text:
            if word in media_words:
                media_counter += 1
            elif word in it_words:
                it_counter += 1
            elif word in games_words:
                games_counter += 2

        if media_counter >= 1:
            media.append(file)
            shutil.copy2(str(data_dir + "\\russian\\" + file), str(data_dir + "\\media"))

            continue
        elif it_counter >= 1:
            it.append(file)
            shutil.copy2(str(data_dir + "\\russian\\" + file), str(data_dir + "\\it"))

            continue
        elif games_counter >= 1:
            games.append(file)
            shutil.copy2(str(data_dir + "\\russian\\" + file), str(data_dir + "\\games"))

            continue
        else:
            other.append(file)
            shutil.copy2(str(data_dir + "\\russian\\" + file), str(data_dir + "\\other"))

            continue

    return (media, it, games, other)

def CreatePlot(files):
    for file in files[1000:1010]:
        final_text = ""
        file_rd = open(data_dir + "\\russian\\" + file, "r", encoding="utf8")
        soup = BeautifulSoup(file_rd, "html.parser")
        title = soup.h1.text
        lemma_title = LemmatizeTitle(title)

        for fl in files:
            fl_rd = open(data_dir + "\\russian\\" + fl, "r", encoding="utf8")
            soup_fl = BeautifulSoup(fl_rd, "html.parser")
            fl_title = soup_fl.h1.text
            lemma_fl_title = LemmatizeTitle(fl_title)
            counter = 0

            for word in lemma_fl_title:
                if word in lemma_title:
                    counter += 1
                    continue
            if counter >= len(lemma_title)/3:
                final_text += soup_fl.text

        final_text_rd = open(data_dir + "\\plot\\" + title[:int(round(len(title)/2, 0))] + ".txt", "w", encoding="utf-8")
        final_text_rd.write(final_text)

    return final_text

# nltk.download("stopwords")
# nltk.download("punkt")
# nltk.download("wordnet")

files = os.listdir(path=data_dir + "\\russian")
text = CreatePlot(files)
print(text)