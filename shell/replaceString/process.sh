#!/bin/bash

sed -n  '1,3 p'  < feed.txt
grep -E '0[0-9]{7}\|' feed.txt
sed -n  '4,7 s/[0-9]\{7\}|/A/p'   feed.txt


