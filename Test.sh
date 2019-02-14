#!/usr/bin/env bash

test() {
  out="$(echo -e "$1" | scala Bacteria | xargs)"
  if [ "$out" == "$2" ]; then
    echo Pass
  else
    echo Fail
  fi
}

# Line away from axis
test "1,2\n2,2\n3,2\nend" "2,2 2,1 2,3 end"

