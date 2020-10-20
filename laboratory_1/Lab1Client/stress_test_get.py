import requests
import concurrent.futures
import string
from random import randrange
import time

alphabet = string.ascii_lowercase

def call(i,times):
    lst = [alphabet[randrange(len(alphabet))] for _ in range(6)]
    print("Thread_"+str(i)+" sends "+str(lst))
    pload = {'letters':''.join(lst)}
    st = time.time()
    r = requests.get('http://localhost:8080/WebApplication1/LettersServlet?letters='+pload["letters"])
    t_i = time.time()-st
    times += [t_i]
    print('Thread '+str(i)+'[Time : {}]'.format(t_i) + 'Response : ' + r.text)
          
times = []
f=open("Stress_test_report.txt","a+")
f.write("[GET] Stress test for 6 letters and 500 threads\n")
with concurrent.futures.ThreadPoolExecutor(max_workers=500) as executor:
	for i in range(500):
		executor.submit(call,i,times)
#print(times)
#print(len(times))
f.write("Maximum time of response : "+ str(max(times)) + "\n")
f.write("Minimum time of response : "+ str(min(times)) + "\n")
f.write('Medium time of response : ' + str(sum(times) / len(times)) + "\n")
f.write('Total time of response : ' + str(sum(times)) + "\n\n")
f.close()          