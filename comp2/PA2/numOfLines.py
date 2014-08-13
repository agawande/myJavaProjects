with open('datafile.txt') as fin:
    lines = sum(1 for line in fin)

print("Counter:", lines)    
