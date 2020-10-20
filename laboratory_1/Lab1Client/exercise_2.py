import json
from string import ascii_letters
import requests                            

letters = input("Give me the letters : ")

pload = {'letters':letters}
r = requests.post('http://localhost:8080/WebApplication1/LettersServlet',data = pload)
print(r.text)
