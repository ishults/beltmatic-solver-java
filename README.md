### beltmatic-solver-java

A Java-based solver for the game Beltmatic ( https://store.steampowered.com/app/2674590/Beltmatic/ ). It's in Java, so it's a little clunky and object orient-y, but it does find some paths in fewer steps than some of the other (great) implementations out there, so I think there's value.

Takes two ints as arguments: target, and a max. The max is the highest level extraction unlocked in the game, up to 99. Note that 10 is not in the game, so it is skipped.

Currently does not use division (including remainders) as that did not appear in any paths during testing.

Tests written in Groovy and Spock.

Inspired by: https://dbrentley.github.io/beltmatic-solver/
