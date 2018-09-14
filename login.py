import requests
import getpass

user = input("Username: ")
pw = getpass.getpass()

username = "admin"
password = "admin"

response = requests.get("http://localhost:9478/login/basic", auth=(user,pw))

print (response.content.decode('utf-8'))
