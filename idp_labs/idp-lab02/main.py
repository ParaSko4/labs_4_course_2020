from tkinter import *

from prettytable import PrettyTable
import requests
import re
import matplotlib.pyplot as pyplot

from bs4 import BeautifulSoup

sys.setrecursionlimit(7000)
globalHrefs = []

def printTable(th, td):
    columns = len(th)

    table = PrettyTable()
    table.field_names = th

    td_data = td[:]
    while td_data:
        table.add_row(td_data[:columns])
        td_data = td_data[columns:]

    print(table, "\n")

class hrefStat:
    mainUrl = ""

    deep = 0
    end_deep = 0
    hrefsCount = 0
    allWordsCount = 0
    ss = 0
    rus = 0
    engs = 0
    dict = {}

    listHrefsStatistic = None

    def __init__(self, deep, end_deep, url):
        self.deep = deep
        self.end_deep = end_deep
        self.mainUrl = url

        soup = BeautifulSoup(requests.get(url).text, "html.parser")

        hrefs = []
        nfiltred = ""

        for div in soup.find_all('div'):
            nfiltred += div.get_text() + " ";
        for p in soup.find_all('p'):
            nfiltred += p.get_text() + " ";
        for a in soup.find_all('a', href=True):
            if a['href'].startswith('https://'):
                hrefs.append(a['href'])

            nfiltred += a.get_text() + " ";

        filtred = " ".join(re.sub("[^\w]+|\d", " ", nfiltred).split())
        symbols = "".join(nfiltred.split())

        self.hrefsCount = len(hrefs)
        self.allWordsCount = len(filtred.split())
        self.allSymbolsCount = len(symbols)
        self.ss = len(re.sub("\w", "", symbols))
        self.rus = len(re.sub("[^а-яА-Я]", "", symbols))
        self.engs = len(re.sub("[^a-zA-Z]+", "", symbols))

        for word in filtred.split(' '):
            i = len(word)
            if self.dict.get(i) != None:
                self.dict[i] = self.dict[i] + 1
            else:
                self.dict[i] = 1

        self.getStat()

        blackList = []
        if self.deep != end_deep:
            if len(hrefs) > 0:
                for href in hrefs:
                    if href not in globalHrefs:
                        globalHrefs.append(href)
                    else:
                        blackList.append(href)

                self.listHrefsStatistic = []
                for href in hrefs:
                    if href not in blackList:
                        self.listHrefsStatistic.append(hrefStat(deep + 1, end_deep, href))

    def getStat(self):
        print(self.mainUrl)
        th = ["deep href", " hrefs count", "words count", "specific symbols count", "russian symbols count", "english symbols count"]
        td = [self.deep, self.hrefsCount, self.allWordsCount, self.ss, self.rus, self.engs]
        printTable(th, td)

        _, Ox = pyplot.subplots()
        Ox.bar(list(self.dict.keys()), list(self.dict.values()))
        pyplot.title(self.mainUrl)
        pyplot.show()

def getAllData(output, input):
    data = [input.hrefsCount, input.allWordsCount, input.ss, input.rus, input.engs]

    for i in range(0, len(data)):
        output[i] = output[i] + data[i]

    if input.listHrefsStatistic != None:
        for href in input.listHrefsStatistic:
            output = getAllData(output, href)

    return output

if __name__ == '__main__':
    url = 'https://irinabot.ru/'
    stats = hrefStat(0, 1, url)

    th = ["all hrefs count", "all words count", "all specific symbols count", "all russian symbols count",
          "all english symbols count"]
    td = [0, 0, 0, 0, 0]
    td = getAllData(td, stats)
    printTable(th, td)