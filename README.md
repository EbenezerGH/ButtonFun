[![Build Status](https://travis-ci.com/EbenezerGH/ButtonFun.svg?token=z8cBzQiMB7d5QTSrDmYj&branch=master)](https://travis-ci.com/EbenezerGH/ButtonFun)

Tech Stack
==

- 100% Kotlin
- MVVM
- Koin
- Android Architecture Components
- Android X

Reusability Features
==
- AAC's ViewModel that only needs height,width passed.
- Koin Service Locater that allows injectable dependencies anywhere
- Utils module w/ reusuable utilities
- Util level exensions and App level extensions more specific to just it's module

Notes
==
- I chose to create a dynamically sizing recyclerview that can fit any device.  It also adjusts to the devices pixel density.
I decided to calculate how many 40x40 squares fit in the screen, and fit in each column.  With those values I was able to
repopulate the listview with the same data while changing the rows dynamically. Because I chose to use a recyclerview to
fill the screen with a grid, it provided a different challenge for handling different orientations, but this method is the
most accurate.

- Viewmodel is a great tool because of it's in built ability to retain instances, however it is no substitute for caching.
In the data layer I needed to save the list of squares that was generated.  Creating a whole sqlite database with room
was overkill,  serializing the list into a string then putting it in the bundle is not good for big amounts of data.
I decided it was best to just read/write to the disk.
