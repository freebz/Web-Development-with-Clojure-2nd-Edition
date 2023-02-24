# Structuring the Code

l = range(1, 6)

for i, val in enumerate(l) :
  l[i] = val * val

for i in l :
  if i % 2 == 0 :
    print i
