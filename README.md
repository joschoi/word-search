# word-search 

You are given a word (the source) and a list of translations for that word (the targets). Your task
is to display the source word and a grid of letters that contains all the target words. The user’s
task is to find all the translations in the grid.
The user selects a word by pressing down on a letter in the grid, dragging to another letter in the
grid, and then releasing their finger. If the selected word is a valid translation of the source word,
it should stay highlighted. Otherwise, the selection should disappear. After all the targets have
been found, the app should display a new grid and a new source word.
The source data is a file that your app should fetch via a GET request from
https://s3.amazonaws.com/duolingo­data/s3/js2/find_challenges.txt. Each line contains a JSON
object with the following attributes:
● “word”: the source word
● “character_grid”: the character grid to be shown
● “word_locations”: a dictionary where each key is a list of coordinates in the format [x1,
y1, x2, y2, ..., xn, yn] and the value is the target word in that location.
Additional requirements:
● Target words will only ever appear horizontally (left to right), vertically (top to bottom) or
diagonally (left to right, top to bottom).
● Your app must work on devices with API 15 and up.
● Your app must adapt to different device screen sizes (different phones / tablets).
